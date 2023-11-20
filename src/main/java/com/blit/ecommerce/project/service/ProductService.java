package com.blit.ecommerce.project.service;

import com.blit.ecommerce.project.entities.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();
    Product getProductById(long id);
    void saveProduct(Product product);

}
