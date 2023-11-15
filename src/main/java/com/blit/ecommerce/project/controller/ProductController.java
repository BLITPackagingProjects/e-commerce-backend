package com.blit.ecommerce.project.controller;

import com.blit.ecommerce.project.entities.Product;
import com.blit.ecommerce.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

//    @GetMapping
//    public ResponseEntity<List<Product>> getProducts(){
//        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
//
//    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Product> getProductById(@PathVariable Long id){
//        return new ResponseEntity<>(productService.getProductById(id),HttpStatus.OK);
//    }


    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody Product product){
        return new ResponseEntity<>(productService.saveProduct(product),HttpStatus.CREATED);
    }

//    @PutMapping("/{id}")
//    public  ResponseEntity<Product> UpdateProduct(@PathVariable Long id,@RequestBody Product product){
//        return new ResponseEntity<>(productService.updateProduct(id,product),HttpStatus.ACCEPTED);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return new ResponseEntity<Product>(HttpStatus.ACCEPTED);
    }

}
