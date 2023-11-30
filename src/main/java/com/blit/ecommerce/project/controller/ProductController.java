package com.blit.ecommerce.project.controller;



import com.blit.ecommerce.project.config.FilesManagerProperties;
import com.blit.ecommerce.project.entities.Product;
import com.blit.ecommerce.project.exception.FileManagerException;
import com.blit.ecommerce.project.service.FilesOperations_interface;
import com.blit.ecommerce.project.service.ProductService;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;






@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
   private final ProductService productService;

   @Autowired
   private FilesOperations_interface filesOperations_interface;

  @Autowired
  private FilesManagerProperties filesManagerProperties;

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
            Product sanatizedProduct = productService.sanatizingProductData(name,price,image,description,seller,quantity);

            productService.saveProduct(sanatizedProduct);
            return new ResponseEntity<>("Product added successfully.",HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }


    }

    @GetMapping("/image/{id}")
    public void getPetImage(
            @PathVariable("id") long id,
            HttpServletResponse response
    ) throws IOException {
        Product product  = productService.getProductById(id);
        InputStream inputStream = filesOperations_interface.getFileContent(filesManagerProperties.getStorageDir(),product.getImageName());
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        StreamUtils.copy(inputStream, response.getOutputStream());
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){

        return new ResponseEntity<String>(productService.deleteProduct(id),HttpStatus.ACCEPTED);


    }

    @PostMapping("/update-image")
    public ResponseEntity<String> updateImage(@RequestParam Long id, MultipartFile imageFile){

        try {

            return new ResponseEntity<String>(productService.updateProductImage(id,imageFile),HttpStatus.ACCEPTED);
        }catch (FileManagerException e){
            return  new ResponseEntity<String>("error",HttpStatus.NOT_ACCEPTABLE);
        }

    }

}
