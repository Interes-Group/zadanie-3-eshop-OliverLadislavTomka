package sk.stuba.fei.uim.oop.assignment3.cart;


import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import sk.stuba.fei.uim.oop.assignment3.product.Product;
import sk.stuba.fei.uim.oop.assignment3.product.ProductRepository;

import java.util.ArrayList;
import java.util.Optional;


@Service
public class CartService implements ZCartService{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository repositary;

    public CartService(ProductRepository repositary,CartRepository cartRepository) { this.repositary = repositary;this.cartRepository = cartRepository; }


    @Override
    public Cart create() {
        Cart cart = new Cart();
        cart.setPayed(false);
        cart.setShoppingList(new ArrayList<>());
        return this.cartRepository.save(cart);
    }


    @Override
    public Cart findById(Long id) throws NotFoundException {
        Optional<Cart> cart = cartRepository.findById(id);
        if (cart.isEmpty()) throw new NotFoundException("Cart with " + id + "was not found!");
        return cart.get();
    }

    @Override
    public void deleteById(Long id) throws NotFoundException {
        Optional<Cart> cart = cartRepository.findById(id);
        if (cart.isEmpty()) throw new NotFoundException("Cart with " + id + "was not found!");
        this.cartRepository.deleteById(id);
    }

    @Override
    public Cart adddProductToCart(Long id, ProductInCartRequest request) throws NotFoundException {
        Optional<Product> productOptional = repositary.findById(request.getProductId());
        Cart cart = cartRepository.findById(id).get();
        //ak produkt s id neexistuje
        //if (productOptional.isEmpty()) throw new NotFoundException("Product with this id doesnt exist");
        //ak vyzadovany amount je vacsi ako je available
        //if (productOptional.get().getAmount() > request.getAmount()) throw new NotFoundException("Product with this id doesnt exist");//HttpClientErrorException.BadRequest();
        // ak je cart uz zaplateny
        //if (cart.isPayed()) throw new NotFoundException("Product with this id doesnt exist");//HttpClientErrorException.BadRequest();


        return cart;
    }



}
