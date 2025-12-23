package edu.superandes.server.shoppingcart;
import lombok.Data;

@Data 
public class Shopping_cart_product
{
    private long id;
    private long product_id;
    private Integer units;
    private long shopping_cart_id;

}