package edu.superandes.server.supermarket.analysis;

import edu.superandes.server.OrdersSupplier.Order_supplier;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderAnalysisVault {

    public OrderAnalysisVault(){}

    public LocalDate getMin(PersistenceManager pm)
    {
        Query minDate = pm.newQuery("javax.jdo.query.SQL","SELECT MIN (issue_date) id FROM  order_supplier" );
        minDate.setResultClass(LocalDate.class);
        return (LocalDate) minDate.executeUnique();


    }
    public LocalDate getMax(PersistenceManager pm)
    {
        Query maxDate = pm.newQuery("javax.jdo.query.SQL","SELECT MAX (issue_date) id FROM  order_supplier" );
        maxDate.setResultClass(LocalDate.class);
        return (LocalDate) maxDate.executeUnique();

    }


    public void update_ordenesTOT(PersistenceManager pm, LocalDate inicDate, LocalDate fin_date)
    {
        Query update = pm.newQuery("javax.jdo.query.SQL","CREATE OR REPLACE VIEW ordenes_tot AS SELECT * FROM order_supplier os WHERE os.issue_date BETWEEN ?1 AND  ?2 " );
        update.setParameters(Timestamp.valueOf(inicDate.toString()),Timestamp.valueOf(fin_date.toString()));
        update.executeUnique();


    }


    public List<IdAnalysis> getOrdersMenosFv (PersistenceManager pm)
    {
        Query consulta = pm.newQuery("javax.jdo.query.SQL","SELECT product.id FROM product product LEFT JOIN ordenes_tot  on ordenes_tot.product_id = product.id GROUP BY product.id HAVING COUNT(ordenes_tot.id)=0  " );
        consulta.setResultClass(IdAnalysis.class);
        return (List<IdAnalysis>) consulta.executeList();

    }


    public Map<Long,Integer> generalLeastFav (PersistenceManager pm  )
    {
        LocalDate fecha_max = getMax(pm);
        LocalDate fecha_min = getMin(pm);
        Map<Long,Integer> prod_not = new HashMap<Long,Integer>();

        long months = ChronoUnit.MONTHS.between(YearMonth.from(fecha_min), YearMonth.from(fecha_max));
        for (int i = 0; i < months; i++) {
            update_ordenesTOT(pm, fecha_min.plusMonths(i), fecha_max.plusMonths(i+2));
            List<IdAnalysis>productos_id =getOrdersMenosFv(pm);
            prod_not=sumProd(prod_not,productos_id);



        }

        return prod_not;



    }

    public Map<Long,Integer> sumProd (Map<Long,Integer> count, List<IdAnalysis> products)
    {
        for (int i = 0; i < products.size(); i++) {
            if (count.get(products.get(i).getId()) == null)
            {
                count.put(products.get(i).getId(),1);
            }

        }
        return count;

    }
}
