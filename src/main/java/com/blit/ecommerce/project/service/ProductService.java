package com.blit.ecommerce.project.service;

import com.blit.ecommerce.project.entities.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<Product> getProducts();
    Product getProductById(long id);
    void saveProduct(Product product);

}
