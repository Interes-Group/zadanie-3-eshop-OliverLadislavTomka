package sk.stuba.fei.uim.oop.assignment3.product;

import javassist.NotFoundException;

import java.util.List;

public interface ZProductService {
    List<Product> getAll();
    Product create(ProductRequest request);
    Product findById(Long id) throws NotFoundException;
    void deleteById(Long id) throws NotFoundException;
    Product updateById(Long id,ProductRequest request) throws NotFoundException;
    void updateAmount(Product product, ProductRequest request);
}
