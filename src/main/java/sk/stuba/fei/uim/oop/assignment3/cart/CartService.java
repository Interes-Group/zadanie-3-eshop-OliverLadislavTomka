package sk.stuba.fei.uim.oop.assignment3.cart;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class CartService implements ZCartService{

    @Autowired
    private CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart create() {
        Cart cart = new Cart();
        cart.setPayed(false);
        cart.setShoppingList(new ArrayList<>());
        return this.cartRepository.save(cart);
    }


}
