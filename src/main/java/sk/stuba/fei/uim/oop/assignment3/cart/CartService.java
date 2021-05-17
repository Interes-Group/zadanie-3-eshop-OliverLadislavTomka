package sk.stuba.fei.uim.oop.assignment3.cart;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Override
    public Cart adddProductToCart(Long id, ProductInCart request) throws NotFoundException {
        Optional<Product> productOptional = repositary.findById(request.getProductId());
        Cart cart = null;
        if (cartRepository.findById(id).isPresent()) cart = cartRepository.findById(id).get();
        if (productOptional.isEmpty()) throw new NotFoundException("Product with this id doesnt exist");
        assert cart != null;
        for (ProductInCart pic: cart.getShoppingList()) {
            if (pic.getProductId().equals(request.getProductId())){
                pic.setAmount(pic.getAmount()+ request.getAmount());
                this.repositary.findById(request.getProductId()).get().setAmount(this.repositary.findById(request.getProductId()).get().getAmount() - request.getAmount());
                return cart;
            }
        }
        cart.getShoppingList().add(request);
        this.repositary.findById(request.getProductId()).get().setAmount(this.repositary.findById(request.getProductId()).get().getAmount() - request.getAmount());
        return cart;
    }

    @Override
    public String payForCart(Long id) throws NotFoundException {
        if (cartRepository.findById(id).isEmpty()) throw new NotFoundException("Cart with this ID doesnt exist");
        int sum = 0;
        Cart cart = cartRepository.findById(id).get();
        for (ProductInCart pic :cart.getShoppingList()) {
            if (repositary.findById(pic.getProductId()).isPresent())  sum+= repositary.findById(pic.getProductId()).get().getPrice() * pic.getAmount();
        }
        cartRepository.findById(id).get().setPayed(true);
        return "" + sum;
    }
}