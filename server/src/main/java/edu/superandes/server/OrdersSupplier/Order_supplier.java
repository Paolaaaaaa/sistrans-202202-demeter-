package edu.superandes.server.OrdersSupplier;
import java.sql.Timestamp;

import lombok.Data;
@Data
public class Order_supplier {

    private long id;
    private Timestamp   issue_date;
    private String state;
    private Timestamp   expecte_due_date;
    private Timestamp   due_date;
    private long supplier;
    private long branch_office_id;
    private long product_id;
    private Integer quantity; 
    
}
