package com.blit.ecommerce.project.service;
import com.blit.ecommerce.project.entities.OrderDetail;

import java.util.List;

public interface OrderService {

    List<OrderDetail> getOrders();
    OrderDetail getOrderById(long id);
    void createOrder(long userId);
    void addProductToOrder(long orderId, long productId);
    List<OrderDetail> findOrderByUserId(long userId);

//    void cancelOrder(long id);
}
