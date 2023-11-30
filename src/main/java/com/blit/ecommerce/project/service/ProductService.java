package com.blit.ecommerce.project.service;

import com.blit.ecommerce.project.entities.Product;
import com.blit.ecommerce.project.exception.FileManagerException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product getProductById(Long id);

//    Product saveProduct(Product product);

    Product saveProduct(String name, double price, MultipartFile image, String description, String seller, int quantity) throws FileManagerException;

    Product updateProduct(Long id, Product product);

  String deleteProduct(Long id);

    List<Product> regexProducts(String regex);
    
	List<Product> findProductByOrderId(Long order_id);



}
