package com.blit.ecommerce.project.controller;

import com.blit.ecommerce.project.entities.Order;
import com.blit.ecommerce.project.entities.Product;
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


    @PostMapping
    public void postOrder(@RequestBody Order order){

        //orderService.createOrder(order);
        String confirmationMessage = "Order confirmation for order " + order.getOrder_id() + ".\n Date: " +order.getDate()+"\nProducts: ";
        for(Product p : order.getProductList()){
            confirmationMessage+=p.getName() + " | Price: "+
                    p.getPrice() +
                    " | Quantity: " + p.getQuantity();
        }

        emailService.sendOrderConfirmation(order.getUser().getUsername(),"Order Confirmation", confirmationMessage);
    }
}
