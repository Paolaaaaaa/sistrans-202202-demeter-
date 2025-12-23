package edu.superandes.server.OrdersSupplier;
import java.sql.Timestamp;

import lombok.Data;
@Data

public class Product_supplier 
{
    private long nit_supplier;
    private Integer quality_of_service;
    private  long product_id;
    private Timestamp expected_due_date;


}