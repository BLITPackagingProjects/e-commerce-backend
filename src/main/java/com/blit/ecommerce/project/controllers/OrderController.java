package com.blit.ecommerce.project.controllers;

import com.blit.ecommerce.project.entities.Order;
import com.blit.ecommerce.project.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    EmailService emailService;
    //TO-DO postOrder controller method

    @PostMapping
    public void postOrder(@RequestBody Order order){
        emailService.sendOrderConfirmation(order);
        //orderService.createOrder(order);
    }
}
