package com.blit.ecommerce.project.controller;

import com.blit.ecommerce.project.entities.Product;
import com.blit.ecommerce.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ecommerce/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(){
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> saveProduct(@RequestBody Product product){
        productService.createProduct(product);
        return new ResponseEntity<>("Product created successfully", HttpStatus.CREATED);
    }

}
