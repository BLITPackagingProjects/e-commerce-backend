package com.blit.ecommerce.project.service;


import com.blit.ecommerce.project.entities.Product;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProductService {

    List<Product> getProducts();

    Product getProductById(Long id);

   Product saveProduct(Product product);

   Product updateProduct(Long id, Product product);

   void deleteProduct(Long id);
  
  List<Product> regexProducts(String regex);


}
