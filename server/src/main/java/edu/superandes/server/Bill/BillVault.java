package edu.superandes.server.Bill;
import static edu.superandes.server.persistence.PersistenceManagerSingleton.getPersistenceManager;

import java.sql.Timestamp;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
public class BillVault {

    public long addBill( PersistenceManager pm,long user_id, Timestamp today, long id_cart)
    {
        Query q = pm.newQuery("javax.jdo.query.SQL", "INSERT INTO bill ( user_id , issue_date, shopping_cart_id  ) VALUES (  ?1 , ?2, ?3)");
        q.setParameters(   user_id,  today,  id_cart);
        long response = (long)q.executeUnique();
        return response;

    }



    
}
