package com.blit.ecommerce.project.service;

import com.blit.ecommerce.project.entities.OrderDetail;
import com.blit.ecommerce.project.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    OrderService orderService;
    public void sendOrderConfirmation(
            OrderDetail orderId) {

        OrderDetail order = orderService.getOrderById(orderId);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ecommerce.blit.september2023@gmail.com");
        message.setTo(order.getUser().getUsername());
        message.setSubject("OrderDetail Confirmation");
        String confirmationMessage = "Order confirmation for order " + order.getOrder_id() + ".\n Date: " +order.getDate()+"\nProducts: ";
        for(Product p : order.getProductList()){
            confirmationMessage+=p.getName() + " | Price: "+
                    p.getPrice() +
                    " | Quantity: " + p.getQuantity();
        }

        message.setText(confirmationMessage);
        emailSender.send(message);

    }
}

