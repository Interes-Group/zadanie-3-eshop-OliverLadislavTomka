package sk.stuba.fei.uim.oop.assignment3.acontrollers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.ProductRequest;
import sk.stuba.fei.uim.oop.assignment3.ProductResponse;
import sk.stuba.fei.uim.oop.assignment3.bservices.IProductService;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/product")
public class TestController {


    @Autowired
    private IProductService service;

    @GetMapping
    public List<ProductResponse> getAllProducts(){
        return this.service.getAll().stream().map(ProductResponse::new).collect(Collectors.toList());

        /*var result = new ArrayList<ProductResponse>();
        for(Product p : this.service.getAll()) {
            result.add(new ProductResponse(p));
        }
        return result;*/
    }

    @PostMapping
    public ProductResponse addProduct(@RequestBody ProductRequest request){
        return new ProductResponse(this.service.create(request));
    }


}
