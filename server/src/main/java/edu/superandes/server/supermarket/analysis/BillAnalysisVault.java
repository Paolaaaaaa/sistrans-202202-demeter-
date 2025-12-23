package edu.superandes.server.supermarket.analysis;

import edu.superandes.server.Bill.Bill;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BillAnalysisVault {

    public BillAnalysisVault(){}

    /**
     *
     * bill from clients between 2 dates
     * */

    public List<BillClient> getClientsB (PersistenceManager pm, LocalDate inic_date, LocalDate end_date )
    {
        Query consulta = pm.newQuery("javax.jdo.query.SQL","SELECT bill.user_id,bill.id, bill.issue_date, bill.shopping_cart_id FROM bill WHERE  issue_date BETWEEN ?1 AND ?2 GROUP BY bill.user_id,bill.id, bill.issue_date HAVING COUNT(bill.user_id)>1" );
        consulta.setParameters(inic_date,end_date);
        return (List<BillClient>) consulta.executeList();
    }

    /**
     * for between a num of months and takes a query to get the clients that are frecuent *-*
     *
     * */
    public Map<Long, Integer> frecuentClients (PersistenceManager pm, Timestamp inic_date, Timestamp end_date)
    {
       long months = ChronoUnit.MONTHS.between(YearMonth.from(LocalDate.parse(inic_date.toString())),LocalDate.parse(end_date.toString()));
       LocalDate today = LocalDate.parse(inic_date.toString());
       LocalDate nxtMonth = LocalDate.parse(end_date.toString());
       Map<Long,Integer> clients = new HashMap<Long,Integer>();
        for (int i = 0; i < months-1; i++) {
            today.plusMonths(i);
            today.plusMonths(i+1);

            List<BillClient> filtBill =getClientsB(pm,today,nxtMonth);

            sumFrecClients(clients,filtBill);


        }

        return clients;

    }

    /**
     * gets a table and add ups all clients count during the period of tiem
     * */
    public Map<Long,Integer> sumFrecClients (Map<Long,Integer> count, List<BillClient> bills)
    {
        for (int i = 0; i < bills.size(); i++) {
            if (count.get(bills.get(i).getUser_id()) == null)
            {
                count.put(bills.get(i).getUser_id(),bills.get(i).getBills());
            }
            else
            {
                count.replace(bills.get(i).getUser_id(),bills.get(i).getBills());
            }

        }
        return count;

    }
}
