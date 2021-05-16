package sk.stuba.fei.uim.oop.assignment3.bservices;

import javassist.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.Product;
import sk.stuba.fei.uim.oop.assignment3.ProductAmountResponse;
import sk.stuba.fei.uim.oop.assignment3.ProductRequest;

import java.util.List;

public interface IProductService {
    List<Product> getAll();
    Product create(ProductRequest request);
    Product findById(Long id) throws NotFoundException;
    void deleteById(Long id) throws NotFoundException;
    Product updateById(Long id,ProductRequest request) throws NotFoundException;
    ProductAmountResponse getAmount(Long id) throws NotFoundException;
    ProductAmountResponse updateAmount(Product product,ProductRequest request);
}
