package com.blit.ecommerce.project.services;

import com.blit.ecommerce.project.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long product_id);
    void updateProduct(Long product_id, Product product);
}
