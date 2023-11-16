package com.blit.ecommerce.project.controller;
import com.blit.ecommerce.project.entities.Product;
import com.blit.ecommerce.project.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    @PutMapping("/{product_id}")
        public ResponseEntity<Product> updateProduct(@PathVariable Long product_id, @RequestBody Product product){
        productService.updateProduct(product_id, product);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
