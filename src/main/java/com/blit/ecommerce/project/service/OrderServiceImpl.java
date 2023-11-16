package com.blit.ecommerce.project.service;

import com.blit.ecommerce.project.entities.Order;
import com.blit.ecommerce.project.entities.Product;
import com.blit.ecommerce.project.entities.User;
import com.blit.ecommerce.project.service.OrderService;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public List<Order> getOrders() {
        return null;
    }

    @Override
    public Order getOrderById(Long id) {
        return null;
    }

    @Override
    public Order createOrder(User user, Product... products) {
        return null;
    }

    @Override
    public void cancelOrder() {

    }
}

