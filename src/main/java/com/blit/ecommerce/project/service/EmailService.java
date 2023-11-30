package com.blit.ecommerce.project.service;

import com.blit.ecommerce.project.entities.OrderDetail;
import com.blit.ecommerce.project.entities.Product;
import com.blit.ecommerce.project.entities.User;
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
            OrderDetail order) {


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
    public void sendEmail(SimpleMailMessage email) {
        emailSender.send(email);
    }
    public void sendPasswordResetToken(String contextPath, User user, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        String url = contextPath+
                "/user/changePassword?token="+token;
        message.setFrom("ecommerce.blit.september2023@gmail.com");
        message.setTo(user.getUsername());
        message.setSubject("Password Reset Token");
        String messageStr = "Hi " + user.getFirstName()+",\n\n" +
                "Forgot password?\n" +
                "We received a request to reset the password for your account.\n\n" +
                "To reset your password, click on the button below:\n\n"+url;
        message.setText(messageStr);
        emailSender.send(message);

    }
}

