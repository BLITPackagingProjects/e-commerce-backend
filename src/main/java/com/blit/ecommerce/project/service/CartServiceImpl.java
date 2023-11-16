package com.blit.ecommerce.project.service;
import com.blit.ecommerce.project.entities.Cart;
import com.blit.ecommerce.project.entities.Product;
import com.blit.ecommerce.project.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductService productService;


    @Override
    public void addProductToCart(Cart cart, Long productId) {

        Product product = productService.getProductById(productId);

        cart.getProductList().add(product);
        cartRepository.save(cart);
    }

    @Override
    public List<Product> getAddedProducts(Cart cart) {
        return cart.getProductList();
    }

    @Override
    public double getTotalPrice(Cart cart) {
      return cart.getProductList().stream()
              .mapToDouble(Product::getPrice)
              .sum();
    }
}
