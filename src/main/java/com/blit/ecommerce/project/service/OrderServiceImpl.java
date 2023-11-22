package com.blit.ecommerce.project.service;

import com.blit.ecommerce.project.entities.OrderDetail;
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
    public List<OrderDetail> getOrders() {
        return (List<OrderDetail>) orderRepository.findAll();
    }

    @Override
    public OrderDetail getOrderById(long id) {
        return orderRepository.findById(id)
                .orElseThrow(()-> new OrderNotFoundException("Cannot found order."));
    }

    @Override
    public void createOrder(long userId) {
        OrderDetail orderDetail = new OrderDetail();
        User user = userRepository.findById(userId).orElseThrow(null);
        orderDetail.setDate(LocalDateTime.now());
        orderDetail.setUser(user);
        orderRepository.save(orderDetail);
    }

    @Override
    public void addProductToOrder(long orderId, long productId) {
       OrderDetail orderDetail = orderRepository.findById(orderId).orElse(null);
        Product product = productRepository.findById(productId).orElse(null);

       List<Product> products = new ArrayList<>();
        products.add(product);
        product.setOrderDetail(orderDetail);
        productRepository.save(product);
        orderDetail.setProductList(products);

        orderRepository.save(orderDetail);
    }


//    @Override
//    public void cancelOrder(long orderId) {
//        if(orderId!=null){
//            orderRepository.deleteById(orderId);
//        }

}

