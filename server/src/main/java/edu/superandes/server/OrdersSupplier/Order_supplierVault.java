
package edu.superandes.server.OrdersSupplier;


import static edu.superandes.server.persistence.PersistenceManagerSingleton.getPersistenceManager;


import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import java.sql.Timestamp;



public class Order_supplierVault 
{


    public long addOrder (PersistenceManager pm, Timestamp issue_date , String state, Timestamp expected_date, long supplier, long branch_office_id, long product_id, Integer quantity)
    {
        Query q = pm.newQuery("javax.jdo.query.SQL", "INSERT INTO order_supplier (  issue_date , state , expecte_due_date , due_date , supplier ,  branch_office_id , product_id , quantity ) VALUES ( ?1 , ?2 , ?3 , NULL , ?4 , ?5 , ?6 , ?7)");
    q.setParameters( issue_date,  state,  expected_date,  supplier,  branch_office_id,  product_id,  quantity);
    long response = (long)q.executeUnique();
    return response; 


    }

    public long updateDelviOrder (PersistenceManager pm, long id_order, Timestamp presentDay_presentTime)
    {
        Query q = pm.newQuery("javax.jdo.query.SQL", "UPDATE order_supplier set State = 'DELIVERED', due_date = ?1 WHERE id= ?2");
        q.setParameters( presentDay_presentTime,id_order);
        long response = (long)q.executeUnique();

        return response; 
    }

    public Order_supplier getOrderSupplier (PersistenceManager pm,long id_order)
    {
        Query q = pm.newQuery("javax.jdo.query.SQL", "SELECT * order_supplier  WHERE id= ?1");
        q.setParameters( id_order);
        q.setResultClass ( Order_supplier.class);
        Order_supplier response = (Order_supplier)q.executeUnique();
        return response; 

    }

    
   
}