package sk.stuba.fei.uim.oop.assignment3.cart;

import javassist.NotFoundException;
import javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.product.Product;
import sk.stuba.fei.uim.oop.assignment3.product.ProductRepository;
import sk.stuba.fei.uim.oop.assignment3.product.ProductService;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CartService implements ZCartService{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    public ProductService productService;

    //public CartService(ProductRepository repositary,CartRepository cartRepository) { this.productService = productService;this.cartRepository = cartRepository; }

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
    public Cart adddProductToCart(Long id, ProductInCart request) throws NotFoundException, BadHttpRequest {
        Product produkt = productService.findById(request.getProductId());
        Cart cart = findById(id);
        if (this.findById(id).isPayed() || produkt.getAmount() < request.getAmount()) throw new BadHttpRequest();
        for (ProductInCart pic: cart.getShoppingList()) {
            if (pic.getProductId().equals(request.getProductId())){
                pic.setAmount(pic.getAmount()+ request.getAmount());
                this.productService.findById(request.getProductId()).setAmount(this.productService.findById(request.getProductId()).getAmount() - request.getAmount());
                return cart;
            }
        }
        cart.getShoppingList().add(request);
        this.productService.findById(request.getProductId()).setAmount(this.productService.findById(request.getProductId()).getAmount() - request.getAmount());
        return cart;
    }

    @Override
    public String payForCart(Long id) throws NotFoundException,BadHttpRequest{
        if (cartRepository.findById(id).isEmpty()) throw new NotFoundException("Cart with this ID doesnt exist");
        if (this.findById(id).isPayed()) throw new BadHttpRequest();
        int sum = 0;
        Cart cart = cartRepository.findById(id).get();
        for (ProductInCart pic :cart.getShoppingList()) {
            sum+= productService.findById(pic.getProductId()).getPrice() * pic.getAmount();
        }
        cartRepository.findById(id).get().setPayed(true);
        return "" + sum;
    }
}