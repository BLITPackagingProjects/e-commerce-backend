package com.blit.ecommerce.project.service;
import com.blit.ecommerce.project.entities.Cart;
import com.blit.ecommerce.project.entities.Product;
import com.blit.ecommerce.project.repository.CartRepository;
import com.blit.ecommerce.project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Override
    public void createCart(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public Cart getCartById(int cartId) {
        return cartRepository.findById(cartId).orElse(null);
    }

    @Override
    public void addProductToCart( long productId) {
        Cart cart = new Cart();
        Product product = productRepository.findById(productId).orElse(null);
        List<Product> cartProducts = new ArrayList<>();
        cartProducts.add(product);
        cart.setNumber(cartProducts.size());
        cart.setProductList(cartProducts);
        product.setCart(cart);

        cartRepository.save(cart);
        productRepository.save(product);
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

    @Override
    public void clearCart(Cart cart) {
        cart.getProductList().clear();
        cartRepository.save(cart);
    }
}
