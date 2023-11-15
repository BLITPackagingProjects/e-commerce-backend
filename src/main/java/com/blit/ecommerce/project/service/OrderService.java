package com.blit.ecommerce.project.service;

import com.blit.ecommerce.project.entities.Order;

import java.util.List;

public interface OrderService {

    List<Order> getOrders();
    Order getOrderById(Long id);
    Order saveOrder(Order order);
//    Order cancelOrder();
}
