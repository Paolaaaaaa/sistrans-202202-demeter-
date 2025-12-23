package edu.superandes.server.OrdersSupplier;
import static edu.superandes.server.persistence.PersistenceManagerSingleton.getPersistenceManager;
import javax.jdo.Query;
import javax.jdo.PersistenceManager;


public class SupplierVault {



    public Product_supplier getSuplierInfo (PersistenceManager pm,  long id_product)
    {
        Query q = pm.newQuery("javax.jdo.query.SQL", "SELECT * FROM product_supplier WHERE product_id= ?1  ");
        q.setParameters( id_product);
        q.setResultClass(Product_supplier.class);
        Product_supplier response = (Product_supplier)q.executeUnique();
        return response; 
    


    }

    
}
