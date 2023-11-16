package com.blit.ecommerce.project.service;
import com.blit.ecommerce.project.entities.Cart;
import com.blit.ecommerce.project.entities.Product;

import java.util.List;


public interface CartService {

    void addProductToCart(Cart cart, Long productId);
    List<Product> getAddedProducts(Cart cart);

    double getTotalPrice(Cart cart);


    void clearCart(Cart cart);
}
