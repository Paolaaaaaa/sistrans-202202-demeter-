package edu.superandes.server.persistence;

import static edu.superandes.server.persistence.PersistenceManagerSingleton.getPersistenceManager;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;




public class SequenceManager {

        //private final PersistenceManager pm = getPersistenceManager();

        public SequenceManager()
        {

        }
    
    
        /**
         * sequence shopping cart id 
         * @return
         */


    
        public long nextVal_shoppingCart (PersistenceManager pm)
        {
            Query q = pm.newQuery("javax.jdo.query.SQL", "SELECT shopping_cart_sequence.nextval FROM DUAL");
            q.setResultClass(Long.class);
            long resp = (long) q.executeUnique();
            return resp;
        }

        public long nextVal_element (PersistenceManager pm)
        {
            Query q = pm.newQuery("javax.jdo.query.SQL", "SELECT elment_sequence.nextval FROM DUAL");
            q.setResultClass(Long.class);
            long resp = (long) q.executeUnique();
            return resp;
        }
        public long nextVal_Bill (PersistenceManager pm)
        {
            Query q = pm.newQuery("javax.jdo.query.SQL", "SELECT bill_sequence.nextval FROM DUAL");
            q.setResultClass (Long.class);
            long resp = (long)q.executeUnique();
            return resp;
        }
    
    }
    
    

