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
    public void createOrder(long userId, OrderDetail orderDetail) {
        OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setCanceled(orderDetail.isCanceled());
        User user = userRepository.findById((int) userId).orElseThrow(null);
        orderDetail1.setDate(LocalDateTime.now());
        orderDetail1.setUser(user);
        orderRepository.save(orderDetail1);
    }

    @Override
    public void addProductToOrder(long orderId, long productId) {
       OrderDetail orderDetail = orderRepository.findById(orderId).orElse(null);
        Product product = productRepository.findById(productId).orElse(null);

       List<Product> products = new ArrayList<>();
        products.add(product);
//        orderDetail.setProductList(products);
        orderDetail.getProductList().add(product);

        orderRepository.save(orderDetail);
    }

	@Override
	public List<OrderDetail> findOrderByUserId(long userId) {
		// TODO Auto-generated method stub
		return orderRepository.findOrderByUserId(userId);
	}

	@Override
	public void checkout(long userId) {
		// TODO Auto-generated method stub
		OrderDetail current = this.findActiveOrderByUserId(userId);
		current.setCanceled(true);
		OrderDetail order = new OrderDetail();
		order.setCanceled(false);
		order.setDate(LocalDateTime.now());
		User user = userRepository.findById((int) userId).orElseThrow(null);
		order.setUser(user);
		orderRepository.save(current);
		orderRepository.save(order);
		}

	@Override
	public OrderDetail findActiveOrderByUserId(long userId) {
		// TODO Auto-generated method stub
		return orderRepository.findActiveOrderByUserId(userId);
	}


//    @Override
//    public void cancelOrder(long orderId) {
//        if(orderId!=null){
//            orderRepository.deleteById(orderId);
//        }

}

