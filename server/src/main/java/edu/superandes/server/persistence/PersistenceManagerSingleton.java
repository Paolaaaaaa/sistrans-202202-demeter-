package edu.superandes.server.persistence;
import java.util.HashMap;
import java.util.List;
import edu.superandes.server.shoppingcart.Shopping_cart_product;
import edu.superandes.server.OrdersSupplier.Product_supplier;
import edu.superandes.server.OrdersSupplier.Order_supplier;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Map;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import edu.superandes.server.Bill.BillVault;
import edu.superandes.server.OrdersSupplier.Order_supplierVault;
import edu.superandes.server.OrdersSupplier.SupplierVault;
import edu.superandes.server.shoppingcart.ShoppingCartVault;
import edu.superandes.server.shoppingcart.Stock_shelfVault;
import edu.superandes.server.supermarket.analysis.BillAnalysisVault;
import edu.superandes.server.supermarket.analysis.OrderAnalysisVault;
import edu.superandes.server.supermarket.analysis.ProductAnalysis;

public class PersistenceManagerSingleton {
  private static PersistenceManagerSingleton pms;
  private final PersistenceManagerFactory pmf;


  private SequenceManager sqlSequence = new SequenceManager() ;
  private ShoppingCartVault sqlShopping_cart = new ShoppingCartVault();
  private BillVault sqlBill = new BillVault();
  private Order_supplierVault sqlOrderSupplier = new Order_supplierVault();
  private SupplierVault sqlProductSupplier = new SupplierVault();
  private Stock_shelfVault sqlStockShelf = new Stock_shelfVault();
  private BillAnalysisVault sqlBillAnalisis = new BillAnalysisVault();
  private OrderAnalysisVault sqlOrderAnalisis = new OrderAnalysisVault();


  public PersistenceManagerSingleton() {
    pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
  }

  public static PersistenceManager getPersistenceManager() {
    if (pms == null) {
      pms = new PersistenceManagerSingleton();
    }
    return pms.pmf.getPersistenceManager();
  }

   /**
   * 
   * shoping cart methods
   *      
   *    RF15: requestShoppingCart
   * 
   * 
   */

  public void requestShoppingCart (long branch_office_id, long id_user)
  {
      PersistenceManager pm = pmf.getPersistenceManager();
      Transaction tx=pm.currentTransaction();
      try
      {
          System.out.println("aquí empieza");
          tx.begin();
          long   id_cart = sqlSequence.nextVal_shoppingCart(pm);
          long tuplasInsertadas = sqlShopping_cart.reserveShoppingCart(pm,id_cart, branch_office_id, id_user);
          System.out.println("Inserted tuples : "+tuplasInsertadas);
          tx.commit();

      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
      finally
      {
          if (tx.isActive())
          {
              tx.rollback();
          }
          pm.close();
      }
  }
  /**
   * 
   *      RF16: add product to shopping cart
   */

  public void addProductShoppingCart (long product_id, Integer units, long shopping_cart_id, long id_estante )
  {
      PersistenceManager pm = pmf.getPersistenceManager();
      Transaction tx=pm.currentTransaction();
      try
      {
          tx.begin();
          long tuplasInsertadas = sqlShopping_cart.addProduct(pm,product_id, units, shopping_cart_id,id_estante);
          tx.commit();
          if (tuplasInsertadas== 0)
          {
            System.out.println("Producto con id: "+product_id + " Insertado en el carrito: "+ shopping_cart_id);
          }
          
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
      finally
      {
          if (tx.isActive())
          {
              tx.rollback();
          }
          pm.close();
      }
  }

    /**
     *
     *      RF17: return product
     */
    public void returnProduct (long product_id, long shopping_cart_id, long id_shelf )
    {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();

            long tuplasInsertadas = sqlShopping_cart.removeProduct(pm,  product_id,  shopping_cart_id,  id_shelf);
            long tuplasModificadas = sqlShopping_cart.deleteShopping_cart_prod(pm, shopping_cart_id, product_id);
            tx.commit();
            

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
    }

    /**
     *
     *      RF18: Generar recibo de pago 
     */
    public void payShoppingCartBill (long user_id, long shopping_cart_id )
    {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        Timestamp presentDay_presentTime = Timestamp .from(Instant.now());//jajaja
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlBill.addBill(pm,user_id, presentDay_presentTime, shopping_cart_id); // genera recibo
            long tuplasInsert = sqlShopping_cart.payShoppingCart(pm,shopping_cart_id);// actualiza estado
            tx.commit();
            

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
    }

    
    /**
     *
     *      RF19: Abandone shopping cart
     */
    public void abandonShopping_cart ( long shopping_cart_id )
    {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsert = sqlShopping_cart.abandoneShoppingCart(pm, shopping_cart_id);// actualiza estado
            tx.commit();
            

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
    }

     /**
     *
     *      RF20: organize abandoned childs 
     */
    public void retriveShopping_Cart ( long shopping_cart_id, long id_product, long id_shelf  )
    {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsert = sqlShopping_cart.removeProduct( pm, id_product,  shopping_cart_id,  id_shelf);// actualiza estado
            tx.commit();
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
    }
 
 /**
     *
     *      RF21:  Consolidate order to proveedor 
     */
    public void consolidate_order ( long shopping_cart_id, long branch_office_id  )
    {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        Timestamp presentDay_presentTime = Timestamp .from(Instant.now());
        try
        {
            tx.begin();
            List<Shopping_cart_product> products= sqlShopping_cart.getProducts_shoppingCart( pm, shopping_cart_id);// lista de productos a pagar
            
            for (int i= 0 ; i < products.size(); i++)
            {
                Product_supplier supplier = sqlProductSupplier.getSuplierInfo( pm, products.get(i).getProduct_id());

              sqlOrderSupplier.addOrder (pm,presentDay_presentTime, "TO_CONFIRM",supplier.getExpected_due_date(), supplier.getNit_supplier(),branch_office_id, products.get(i).getProduct_id(),  1 );


            }
            tx.commit();
            

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
    }

/**
     *
     *      RF22:  register the arrival of a product 
     */
    public void arribal_consolidate_order ( long id_order, long id_shelf   )
    {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        Timestamp presentDay_presentTime = Timestamp .from(Instant.now());
        try
        {
            tx.begin();
            long num_tuples = sqlOrderSupplier.updateDelviOrder(pm,id_order,presentDay_presentTime);
            Order_supplier os = sqlOrderSupplier.getOrderSupplier(pm, id_order);
            long num_tuples_2 = sqlStockShelf.addStock(pm,id_shelf, os.getProduct_id(), os.getQuantity());// update stock in shelf
            tx.commit();
            

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
    }

    /**
     *
     * RFC 8:Clientes frecuentes
     * */
    public Map<Long,Integer> frecuentClient (Timestamp inic_date, Timestamp end_date)
    {
        Map<Long,Integer> clients = new HashMap<Long,Integer>();



        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            clients = sqlBillAnalisis.frecuentClients(pm, inic_date,end_date);

            tx.commit();


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
        return clients;


    }
    /**
     *
     * RFC 9: Products that are not ordered
     * */

    public Map<Long,Integer> leasrOderedProducts ()
    {
        Map<Long,Integer> productos = new HashMap<Long,Integer>();



        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            productos = sqlOrderAnalisis.generalLeastFav(pm);

            tx.commit();


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
        return productos;

    }




    public static void main(String[] args) {
        PersistenceManagerSingleton pm = new PersistenceManagerSingleton();
        pm.requestShoppingCart(2,1);
       pm.addProductShoppingCart(1,1,9,1);
       pm.returnProduct(1,1,1);
       pm.payShoppingCartBill(1,9);

        pm.addProductShoppingCart(1,1,21,1);
        pm.addProductShoppingCart(1,1,23,1);

        pm.abandonShopping_cart(23);
       pm.retriveShopping_Cart(21,1,1);
       pm.consolidate_order(21,2);
    }

}




