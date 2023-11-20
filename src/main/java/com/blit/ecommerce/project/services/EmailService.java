package com.blit.ecommerce.project.services;

import com.blit.ecommerce.project.entities.Order;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendOrderConfirmation(
            String to, String confirmationTitle, String confirmationMessage) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ecommerce.blit.september2023@gmail.com");
        message.setTo(to);
        message.setSubject(confirmationTitle);
        message.setText(confirmationMessage);
        emailSender.send(message);

    }
}
