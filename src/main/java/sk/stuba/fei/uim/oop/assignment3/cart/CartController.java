package sk.stuba.fei.uim.oop.assignment3.cart;


import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.product.ProductResponse;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ZCartService cartService;

    @PostMapping
    public ResponseEntity<CartResponse> createCart(){
        return new ResponseEntity<>(new CartResponse(this.cartService.create()),HttpStatus.CREATED);
    }

    /*@GetMapping("/{id}")
    public ResponseEntity<CartResponse> getCartById(@PathVariable("id") Long id){
        try {
            var product = cartService.findById(id);
            return new ResponseEntity<>(new ProductResponse(product), HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/


}
