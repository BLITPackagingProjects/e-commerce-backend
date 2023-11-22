package com.blit.ecommerce.project.controller;

<<<<<<< HEAD

import com.blit.ecommerce.project.entities.Product;
import com.blit.ecommerce.project.service.ProductService;

=======
import com.blit.ecommerce.project.entities.Product;
import com.blit.ecommerce.project.service.ProductService;
import com.blit.ecommerce.project.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> Lisa
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

<<<<<<< HEAD




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
=======
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
    public ResponseEntity<Product> getProductById(@PathVariable("id") long id){
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> saveProduct(@RequestBody Product product){
        productService.saveProduct(product);
        return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
>>>>>>> Lisa
    }

}
