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

    public void sendOrderConfirmation(
            OrderDetail orderdetail) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ecommerce.blit.september2023@gmail.com");
        message.setTo(orderdetail.getUser().getUsername());
        message.setSubject("Order Confirmation");
        String confirmationMessage = "Order confirmation for order " + orderdetail.getOrder_id() + ".\n Date: " +orderdetail.getDate()+"\nProducts: ";
        for(Product p : orderdetail.getProductList()){
            confirmationMessage+=p.getName() + " | Price: "+
                    p.getPrice() +
                    " | Quantity: " + p.getQuantity();
        }

        message.setText(confirmationMessage);
        emailSender.send(message);

    }
}