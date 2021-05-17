package sk.stuba.fei.uim.oop.assignment3.cart;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductInCartRequest {
    private Long productId;
    private Long amount;
}
