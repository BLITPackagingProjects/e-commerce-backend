package com.blit.ecommerce.project.service;

import com.blit.ecommerce.project.entities.Cart;
import com.blit.ecommerce.project.entities.Order;
import com.blit.ecommerce.project.entities.Product;
import com.blit.ecommerce.project.entities.User;
import com.blit.ecommerce.project.repository.CartRepository;
import com.blit.ecommerce.project.repository.OrderRepository;
import com.blit.ecommerce.project.repository.UserRepository;
import com.blit.ecommerce.project.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ProductService productService, CartService cartService, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.cartService = cartService;
        this.userRepository = userRepository;
    }


    @Override
    public List<Order> getOrders() {
        return null;
    }

    @Override
    public Order getOrderById(Long id) {
        return null;
    }

    @Override
    public void createOrder(Long userId, Integer cartId) {
        Order order = new Order();
        User user = userRepository.findById(userId)
                        .orElse(null);
        order.setUser(user);

        order.setDate(LocalDateTime.now());

        Cart cart = cartRepository.findById(cartId)
                .orElse(null);

        List<Product> orderedList = cart.getProductList();
        for (Product product : orderedList) {
            order.getProductList().add(product);
        }
        orderRepository.save(order);
        cartService.clearCart(cart);
    }

    @Override
    public void cancelOrder(Long id) {

    }
}

