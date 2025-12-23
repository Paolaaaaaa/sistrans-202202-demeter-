package edu.superandes.server.shoppingcart;

import static edu.superandes.server.persistence.PersistenceManagerSingleton.getPersistenceManager;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;


public class Stock_shelfVault 
{


    public Stock_shelfVault() {}

    public long addStock (PersistenceManager pm,long id_shelf, long id_product, Integer quantity)
    {
        long touples_gen = 0;
        Query q = pm.newQuery("javax.jdo.query.SQL", "SELECT * FROM stock_shelf WHERE id_shelf= ?1 AND product_id= ?2 ");
        q.setParameters(id_shelf, id_product );
        q.setResultClass(Stock_shelf.class);

        if ((Stock_shelf)q.executeUnique() == null)
        {
            Query q1 = pm.newQuery("javax.jdo.query.SQL", "INSERT INTO stock_shelf (id_shelf, product_id, quantity) VALUES (?1, ?2,?3) ");
            q1.setParameters (id_shelf, id_product, quantity);
            return (long) q1.executeUnique();


        }
        else 

        {
            Query q2 = pm.newQuery("javax.jdo.query.SQL", "UPDATE stock_shelf set quantity = quantity + ? WHERE id_shelf = ? AND product_id= ? ");
            q.setParameters (quantity, id_shelf,id_product);
            return (long) q2.executeUnique();

        } 
    }


}