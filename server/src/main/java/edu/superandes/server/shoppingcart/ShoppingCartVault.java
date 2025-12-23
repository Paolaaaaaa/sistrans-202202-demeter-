package edu.superandes.server.shoppingcart;

import static edu.superandes.server.persistence.PersistenceManagerSingleton.getPersistenceManager;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class ShoppingCartVault {

  public ShoppingCartVault() {}


  

  public long reserveShoppingCart(PersistenceManager pm,long id, long branch_office_id, long user_id  )
  {

    /**
     * 
     * add a  new shopping cart 
     */

    Query q = pm.newQuery("javax.jdo.query.SQL",
            "INSERT INTO shopping_cart( id , branch_office_id , state  ) VALUES ( ?1, ?2 , 'ACTIVE')");
    q.setParameters(id,branch_office_id);
    var response =(long) q.executeUnique();
    System.out.println(response);


 /**
     * 
     * asociates shopping with client
     */
    Query query = pm.newQuery ("javax.jdo.query.SQL",
            "INSERT INTO client ( user_id, shopping_cart_id  ) VALUES ( ?1, ?2 ) ");
    query.setParameters( user_id,  id);
    long response2= (long) query.executeUnique();
    if (response2!= 0)
    {
      return response2;
    }
    else
    {
      return 0;
    }


  }




  /**
   * returns a shopping cart 
   * @param id
   */
  public ShoppingCart getShoppingCart(PersistenceManager pm,long id )
  {
    
        Query query = pm.newQuery ("javax.jdo.query.SQL","SELECT * FROM shopping_cart WHERE id = ?1");
        query.setParameters(id);
        query.setResultClass(ShoppingCart.class);
        ShoppingCart req_shoppingCart = (ShoppingCart) query.executeList() ;
        return req_shoppingCart;
    

  }
/**
 * Updates shopping cart status
 * @param id
 * @return shopping cart
 */

  public ShoppingCart returnShoppingCart(PersistenceManager pm,  long id)
{

  Query query = pm.newQuery ("javax.jdo.query.SQL","UPDATE shopping_cart SET  state = RETURNED WHERE id = ?1");
  query.setParameters(id);
  long ret = (long)query.executeUnique();
  Query q2 = pm.newQuery ("javax.jdo.query.SQL","SELECT * FROM shopping_cart WHERE id = ?1 ");
  q2.setParameters(id);
  q2.setResultClass(ShoppingCart.class);
  return (ShoppingCart)q2.executeUnique();


}

/**
 * Inserts tuple into table, 
 * @param product_id
 * @param units
 * @param shopping_cart_id
 * @return
 */

  public long addProduct(PersistenceManager pm,  long product_id, Integer units, long shopping_cart_id, long id_shelf )
  {

      Query consulta = pm.newQuery ("javax.jdo.query.SQL",
              "SELECT units FROM shopping_cart_product WHERE product_id= ?1 AND  shopping_cart_id= ?2");
      consulta.setParameters(product_id,shopping_cart_id);
      System.out.println("long");


      Query stock = pm.newQuery ("javax.jdo.query.SQL","UPDATE stock_shelf SET quantity= quantity-1 WHERE id_shelf = ?1 AND product_id= ?2  ");
      stock.setParameters ( id_shelf, product_id);
      long response2 = (long)stock.executeUnique();

    if ( consulta.executeUnique() ==null)
    {
        Query query = pm.newQuery ("javax.jdo.query.SQL",
                "INSERT INTO shopping_cart_product ( product_id, units , shopping_cart_id ) VALUES ( ?1 , ?2 , ?3 )");
        query.setParameters( product_id,units, shopping_cart_id);
        return (long)query.executeUnique();

    }
    else
    {
        Query query2 = pm.newQuery ("javax.jdo.query.SQL",
                "UPDATE shopping_cart_product set units = units + ?1 WHERE product_id= ?2 AND shopping_cart_id=?3");
        query2.setParameters( units,product_id, shopping_cart_id);
        return (long)query2.executeUnique();

    }

  }
/**
 * updates tuple from shopping cart product -1 and updates shelf +1
 * @param product_id
 * @param shopping_cart_id
 * @return 
 */


  public long removeProduct(PersistenceManager pm,long product_id, long shopping_cart_id, long id_shelf)
  {
    
    
  
    Query query = pm.newQuery ("javax.jdo.query.SQL","UPDATE shopping_cart_product SET units= units -1 WHERE shopping_cart_id = ?1 AND product_id= ?2  ");
    query.setParameters(shopping_cart_id, product_id);
    long response = (long)query.executeUnique();

    Query query2 = pm.newQuery ("javax.jdo.query.SQL","UPDATE stock_shelf SET quantity= quantity +1 WHERE id_shelf = ?1 AND product_id= ?2  ");
    query2.setParameters ( id_shelf, product_id);
    long response2 = (long)query2.executeUnique();
    if (response != 0 && response2 != 0 )
    {
      return 1;
    }
    else
    {
      return 0 ;
    }

  }

  /**
   * Delete tuple shopping_cart product 
   */

   public long deleteShopping_cart_prod (PersistenceManager pm,long id_cart, long id_product)
   {
      
    Query query = pm.newQuery ("javax.jdo.query.SQL","SELECT units FROM shopping_cart_product  WHERE shopping_cart_id = ?1 AND product_id= ?2  ");
    query.setParameters(id_cart, id_product);
    query.setResultClass(Integer.class);

    if (query.executeUnique() != null && (int)query.executeUnique() == 0 )
    {
      Query query2 = pm.newQuery ("javax.jdo.query.SQL","DELETE  FROM shopping_cart_product  WHERE shopping_cart_id = ?1 AND product_id= ?2  ");
      query.setParameters(id_cart, id_product);
      return (long)query2.executeUnique();

    }
    else 
    {
      return 0;

    }
   }
  
  /**
   * Client Abandones shopping cart
   *
   * */
  public long abandoneShoppingCart (PersistenceManager pm,long id_cart)
  {
    Query q = pm.newQuery("javax.jdo.query.SQL","UPDATE shopping_cart SET state = 'RETURNED' WHERE id = ?1 " );
    q.setParameters(id_cart);
    return (long)q.executeUnique();
  }

 /**
   * Client pays shopping  cart products 
   *
   * */
  public long payShoppingCart (PersistenceManager pm,long id_cart)
  {
    Query q = pm.newQuery("javax.jdo.query.SQL","UPDATE shopping_cart SET state = 'PAYED' WHERE id = ?1 " );
    q.setParameters(id_cart);
    return (long)q.executeUnique();
  }




  /**
   * get all product per car 
   * */
  public List<Shopping_cart_product> getProducts_shoppingCart (PersistenceManager pm,long id_cart)
  {
    Query q = pm.newQuery("javax.jdo.query.SQL","SELECT * FROM shopping_cart_product WHERE shopping_cart_id = ?1" );
    q.setParameters(id_cart);

    q.setResultClass(Shopping_cart_product.class);
    return (List<Shopping_cart_product>)q.executeResultList();
  }




}
