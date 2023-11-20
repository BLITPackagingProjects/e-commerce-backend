package com.blit.ecommerce.project.services;

import com.blit.ecommerce.project.entities.Order;
import jakarta.jms.Destination;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

@Service
public class EmailService {






    public void sendOrderConfirmation(Order order){


    }
}
