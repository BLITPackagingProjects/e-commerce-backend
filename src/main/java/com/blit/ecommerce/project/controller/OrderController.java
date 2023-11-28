package com.blit.ecommerce.project.controller;

import com.blit.ecommerce.project.entities.OrderDetail;
import com.blit.ecommerce.project.entities.Product;
import com.blit.ecommerce.project.service.OrderService;
import com.blit.ecommerce.project.service.ProductService;
import com.blit.ecommerce.project.service.UserService;
//import com.blit.ecommerce.project.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ecommerce/order")
public class OrderController {


    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    //    @Autowired
//    EmailService emailService;

    @GetMapping("/orders")
    public ResponseEntity<List<OrderDetail>> getOrders(){
        return new ResponseEntity<>(orderService.getOrders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetail> getOrderbById(@PathVariable long id){
        return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<String> createOrder(
            @PathVariable long userId,
    @RequestBody OrderDetail orderDetail ){
        orderService.createOrder(userId, orderDetail);
        return new ResponseEntity<>("Order is created successfully", HttpStatus.CREATED);
    }

    @PostMapping("/{orderId}/{productId}")
    public ResponseEntity<String> getOrder(
            @PathVariable("orderId") long orderId,
            @PathVariable("productId") long productId){

            orderService.addProductToOrder(orderId, productId);
            return new ResponseEntity<>("Product is added in the order", HttpStatus.CREATED);
    }

    @GetMapping("/user/{user_id}")
	public ResponseEntity<List<OrderDetail>> getProductByOrderId(@PathVariable Long user_id){
	return new ResponseEntity<>(orderService.findOrderByUserId(user_id),HttpStatus.OK);
}

}
