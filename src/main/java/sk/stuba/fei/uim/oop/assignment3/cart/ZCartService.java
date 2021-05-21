package sk.stuba.fei.uim.oop.assignment3.cart;


import javassist.NotFoundException;
import javassist.tools.web.BadHttpRequest;

public interface ZCartService {
    Cart create();
    Cart findById(Long id) throws NotFoundException;
    void deleteById(Long id) throws NotFoundException;
    Cart adddProductToCart(Long id, ProductInCart request) throws NotFoundException, BadHttpRequest;
    String payForCart(Long id) throws NotFoundException, BadHttpRequest;
}
