package sk.stuba.fei.uim.oop.assignment3.cart;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CartRequest {
    private Long cartId;
    private List<ProductInCart> shoppingList;
    private boolean payed;
}
