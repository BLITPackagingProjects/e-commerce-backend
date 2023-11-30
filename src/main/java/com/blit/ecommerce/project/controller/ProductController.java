package com.blit.ecommerce.project.controller;



import com.blit.ecommerce.project.entities.Product;
import com.blit.ecommerce.project.exception.FileManagerException;
import com.blit.ecommerce.project.service.ProductService;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;






@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
   private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    public ResponseEntity <List<Product>> getProducts() {
    return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);

    }
    @GetMapping("/{product_id}")
        public ResponseEntity<Product> getProductById(@PathVariable Long product_id){
        return new ResponseEntity<>(productService.getProductById(product_id), HttpStatus.OK);
        }
    @GetMapping("/order/{order_id}")
    	public ResponseEntity<List<Product>> getProductByOrderId(@PathVariable Long order_id){
    	return new ResponseEntity<>(productService.findProductByOrderId(order_id),HttpStatus.OK);
    }
    @PutMapping("/{product_id}")
        public ResponseEntity<Product> updateProduct(@PathVariable Long product_id, @RequestBody Product product){
        productService.updateProduct(product_id, product);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
//  @PostMapping
//    public ResponseEntity<Product> saveProduct(@RequestBody Product product){
//
//        return new ResponseEntity<>(product,HttpStatus.CREATED);
//        // return new ResponseEntity<>(productService.saveProduct(product),HttpStatus.CREATED);
//    }
    @PostMapping
    public ResponseEntity<String> saveProduct(@RequestParam String name, double price, MultipartFile image, String description, String seller, int quantity) {

        try {
            productService.saveProduct(name, price, image, description, seller, quantity);
            return new ResponseEntity<>("Product added successfully.",HttpStatus.CREATED);
        }catch (FileManagerException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }


    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){

        return new ResponseEntity<String>(productService.deleteProduct(id),HttpStatus.ACCEPTED);


    }

}
