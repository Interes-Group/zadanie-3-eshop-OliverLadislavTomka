package sk.stuba.fei.uim.oop.assignment3.product;


import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;



@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ZProductService service;

    @GetMapping
    public List<ProductResponse> getAllProducts(){
        return this.service.getAll().stream().map(ProductResponse::new).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest request){
        return new ResponseEntity<>(new ProductResponse(this.service.create(request)), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") Long id){
        try {
            var product = service.findById(id);
            return new ResponseEntity<>(new ProductResponse(product), HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateById(@RequestBody ProductRequest request,@PathVariable("id") Long id){
        try {
            var product = service.updateById(id,request);
            return new ResponseEntity<>(new ProductResponse(product),HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponse> deleteProductById(@PathVariable("id") Long id){
        try {
            service.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/amount")
    public ResponseEntity<ProductAmountResponse> getAmount(@PathVariable("id") Long id){
        try {
            var product = service.findById(id);
            return new ResponseEntity<>(new ProductAmountResponse(product), HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/amount")
    public ResponseEntity<ProductAmountResponse> setAmount(@PathVariable("id") Long id, @RequestBody ProductRequest request){
        try {
            var product = service.findById(id);
            service.updateAmount(product,request);
            return new ResponseEntity<>(new ProductAmountResponse(product), HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
