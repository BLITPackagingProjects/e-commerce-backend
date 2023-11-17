package com.blit.ecommerce.project.service;

import com.blit.ecommerce.project.entities.Cart;
import com.blit.ecommerce.project.entities.Order;
import com.blit.ecommerce.project.entities.Product;
import com.blit.ecommerce.project.entities.User;

import java.util.List;

public interface OrderService {

    List<Order> getOrders();
    Order getOrderById(Long id);
    void createOrder(Long userId, Integer cartId, Integer count);
//    void cancelOrder(Long id);
}
