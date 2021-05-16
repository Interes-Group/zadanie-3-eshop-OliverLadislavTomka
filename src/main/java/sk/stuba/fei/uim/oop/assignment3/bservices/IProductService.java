package sk.stuba.fei.uim.oop.assignment3.bservices;

import javassist.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.Product;
import sk.stuba.fei.uim.oop.assignment3.ProductRequest;

import java.util.List;

public interface IProductService {
    List<Product> getAll();
    Product create(ProductRequest request);
    Product findById(Long id) throws NotFoundException;
}
