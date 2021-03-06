package sk.stuba.fei.uim.oop.assignment3.product;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductService implements ZProductService {

    @Autowired
    private ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> getAll(){
        return this.repository.findAll();
    }


    @Override
    public Product create(ProductRequest request){
        Product newProduct = new Product();
        newProduct.setName(request.getName());
        newProduct.setDescription(request.getDescription());
        newProduct.setAmount(request.getAmount());
        newProduct.setUnit(request.getUnit());
        newProduct.setPrice(request.getPrice());
        return this.repository.save(newProduct);
    }

    @Override
    public Product findById(Long id) throws NotFoundException {
        Optional<Product> product = repository.findById(id);
        if (product.isEmpty()) throw new NotFoundException("Product with " + id + "was not found!");
        return product.get();
    }

    @Override
    public void deleteById(Long id) throws NotFoundException {
        Optional<Product> product = repository.findById(id);
        if (product.isEmpty()) throw new NotFoundException("Product with " + id + "was not found!");
        this.repository.deleteById(id);
    }

    @Override
    public Product updateById(Long id,ProductRequest request) throws NotFoundException {
        Optional<Product> product = repository.findById(id);
        if (product.isEmpty()) throw new NotFoundException("Product with " + id + "was not found!");
        Product prod1 = product.get();
        if (request.getName() != null) prod1.setName(request.getName());
        if (request.getDescription() != null) prod1.setDescription(request.getDescription());
        return prod1;
    }


    @Override
    public void updateAmount(Product product, ProductRequest request) {
        product.setAmount(product.getAmount() + request.getAmount());
    }
}
