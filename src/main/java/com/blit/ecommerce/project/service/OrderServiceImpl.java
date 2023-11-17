package com.blit.ecommerce.project.service;

import com.blit.ecommerce.project.entities.Cart;
import com.blit.ecommerce.project.entities.Order;
import com.blit.ecommerce.project.entities.Product;
import com.blit.ecommerce.project.entities.User;
import com.blit.ecommerce.project.exception.OrderNotFoundException;
import com.blit.ecommerce.project.repository.CartRepository;
import com.blit.ecommerce.project.repository.OrderRepository;
import com.blit.ecommerce.project.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
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
        return (List<Order>) orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(()-> new OrderNotFoundException("Cannot found order."));
    }

    @Override
    public void createOrder(Long userId, Integer cartId, Integer countToOrder) {
        Order order = new Order();
        User user = userRepository.findById(userId).orElse(null);
        Cart cart = cartRepository.findById(cartId).orElse(null);

        order.setUser(user);
        order.setDate(LocalDateTime.now());

        List<Product> orderedList = cart.getProductList();
        for (Product product : orderedList) {
            order.getProductList().add(product);

            product.removeStock(countToOrder);
            productService.saveProduct(product);
        }

        orderRepository.save(order);
        cartService.clearCart(cart);
    }

//    @Override
//    public void cancelOrder(Long orderId) {
//        if(orderId!=null){
//            orderRepository.deleteById(orderId);
//        }




}

