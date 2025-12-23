package edu.superandes.server.Bill;

import java.sql.Timestamp;

import lombok.Data;
@Data
public class Bill {

    private long id;
    private long user_id;
    private Timestamp issue_date;
    private long shopping_cart_id;


    
}
