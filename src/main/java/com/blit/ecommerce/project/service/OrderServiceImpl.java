package com.blit.ecommerce.project.service;

import com.blit.ecommerce.project.entities.Cart;
import com.blit.ecommerce.project.entities.Order;
import com.blit.ecommerce.project.entities.Product;
import com.blit.ecommerce.project.entities.User;
import com.blit.ecommerce.project.exception.OrderNotFoundException;
import com.blit.ecommerce.project.repository.CartRepository;
import com.blit.ecommerce.project.repository.OrderRepository;
import com.blit.ecommerce.project.repository.ProductRepository;
import com.blit.ecommerce.project.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartService cartService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;

    @Override
    public List<Order> getOrders() {
        return (List<Order>) orderRepository.findAll();
    }

    @Override
    public Order getOrderById(long id) {
        return orderRepository.findById(id)
                .orElseThrow(()-> new OrderNotFoundException("Cannot found order."));
    }

    @Override
    public void createOrder(long userId, int cartId) {
        Order order = new Order();
        User user = userRepository.findById(userId).orElse(null);
        Cart cart = cartRepository.findById(cartId).orElse(null);

        order.setDate(LocalDateTime.now());
        order.setProductList(cart.getProductList());
        order.setUser(user);

        List<Order> orderList = new ArrayList<>();
        orderList.add(order);
        user.setOrderList(orderList);

        orderRepository.save(order);
        for (Product product : order.getProductList()) {
            product.setOrder(order);
            productRepository.save(product);
        }
        userRepository.save(user);
//        cartService.clearCart(cart);
    }

//    @Override
//    public void cancelOrder(long orderId) {
//        if(orderId!=null){
//            orderRepository.deleteById(orderId);
//        }




}

