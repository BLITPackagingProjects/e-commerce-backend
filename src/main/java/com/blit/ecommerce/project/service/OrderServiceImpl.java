package com.blit.ecommerce.project.service;

import com.blit.ecommerce.project.entities.Order;
import com.blit.ecommerce.project.entities.Product;
import com.blit.ecommerce.project.entities.User;
import com.blit.ecommerce.project.exception.OrderNotFoundException;
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
    private UserRepository userRepository;


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
    public void createOrder(long userId) {
        Order order = new Order();
        User user = userRepository.findById(userId).orElseThrow(null);
        order.setDate(LocalDateTime.now());
        order.setUser(user);
        orderRepository.save(order);
    }

    @Override
    public void addProductToOrder(long orderId, long productId) {
       Order order = orderRepository.findById(orderId).orElse(null);
        Product product = productRepository.findById(productId).orElse(null);
        product.setOrder(order);
        productRepository.save(product);


        orderRepository.save(order);
    }


//    @Override
//    public void cancelOrder(long orderId) {
//        if(orderId!=null){
//            orderRepository.deleteById(orderId);
//        }

}

