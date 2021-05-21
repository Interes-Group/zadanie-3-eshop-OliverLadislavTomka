package sk.stuba.fei.uim.oop.assignment3.cart;


import javassist.NotFoundException;
import javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import sk.stuba.fei.uim.oop.assignment3.product.ProductService;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ZCartService cartService;

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<CartResponse> createCart(){
        return new ResponseEntity<>(new CartResponse(this.cartService.create()),HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartResponse> getProductById(@PathVariable("id") Long id){
        try {
            var cart = cartService.findById(id);
            return new ResponseEntity<>(new CartResponse(cart), HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CartResponse> deleteProductById(@PathVariable("id") Long id){
        try {
            cartService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/{id}/add")
    public ResponseEntity<CartResponse> addProductToCart(@PathVariable("id") Long id, @RequestBody ProductInCart request){
        try {
                var cart = cartService.adddProductToCart(id, request);
                return new ResponseEntity<>(new CartResponse(cart),HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (BadHttpRequest e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/{id}/pay")
    public ResponseEntity<String> payCart(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<>(cartService.payForCart(id),HttpStatus.OK);
        } catch(NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (BadHttpRequest e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
