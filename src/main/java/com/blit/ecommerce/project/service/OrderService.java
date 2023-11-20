package com.blit.ecommerce.project.service;
import com.blit.ecommerce.project.entities.Order;

import java.util.List;

public interface OrderService {

    List<Order> getOrders();
    Order getOrderById(long id);
    void createOrder(long userId);
    void addProductToOrder(long orderId, long productId);

//    void cancelOrder(long id);
}
