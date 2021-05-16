package sk.stuba.fei.uim.oop.assignment3.bservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.Product;
import sk.stuba.fei.uim.oop.assignment3.crepositories.ProductRepository;
import java.util.List;

@Service
public class ProductService implements  IProductService{

    @Autowired
    private ProductRepository repository;

    @Override
    public List<Product> getAll(){
        return this.repository.findAll();
    }
}
