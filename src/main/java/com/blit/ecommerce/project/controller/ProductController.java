package com.blit.ecommerce.project.controller;
import com.blit.ecommerce.project.entities.Product;
import com.blit.ecommerce.project.service.ProductService;


import com.blit.ecommerce.project.service.ProductServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/ecommerce/product")
public class ProductController {


   @Autowired
   ProductService productService;
    @GetMapping
    public ResponseEntity <List<Product>> getProducts() {
    return new ResponseEntity<List<Product>>(productService.getProducts(), HttpStatus.OK);


    }
    @GetMapping("/{product_id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long product_id){
        return new ResponseEntity<>(productService.getProductById(product_id), HttpStatus.OK);
    }
    @PutMapping("/{product_id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long product_id, @RequestBody Product product){
        productService.updateProduct(product_id, product);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody Product product){
        return new ResponseEntity<>(productService.saveProduct(product),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return new ResponseEntity<Product>(HttpStatus.ACCEPTED);
    }

}