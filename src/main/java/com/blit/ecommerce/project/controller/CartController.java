package com.blit.ecommerce.project.controller;

import com.blit.ecommerce.project.entities.Cart;
import com.blit.ecommerce.project.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ecommerce/cart")
public class CartController {
    @Autowired
    private CartService cartService;


    @PostMapping("/{productId}")
    public ResponseEntity<String> createCart(@PathVariable("productId") long productId){
        cartService.addProductToCart(productId);
        return new ResponseEntity<>("Cart is created successfully", HttpStatus.CREATED);
    }


}
