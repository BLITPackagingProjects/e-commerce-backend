package com.blit.ecommerce.project;

import com.blit.ecommerce.project.entities.OrderDetail;
import com.blit.ecommerce.project.entities.User;
import com.blit.ecommerce.project.entities.Product;
import com.blit.ecommerce.project.repository.OrderRepository;
import com.blit.ecommerce.project.repository.ProductRepository;
import com.blit.ecommerce.project.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ProjectApplication /*/ implements CommandLineRunner /**/ {

	private static final Logger logger = LoggerFactory.getLogger(ProjectApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
		logger.info("The trojan application started successfully");
	}

	/*
	@Autowired
	private OrderRepository orderRepo;
	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private UserRepository userRepo;
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		//make sure to set table as create-drop in application.properties
		
		OrderDetail[] orders = {new OrderDetail(),new OrderDetail()};
		User u = new User();
		userRepo.save(u);
		Product[] products = {
				new Product("fur", 25, null, "soft and fluffy", null, 0),
				new Product("doot", 100, null, "dootdootdoot", null, 0),
				new Product("milk", 5.2, null, "stronk bones", null, 0),
				new Product("oil", 100, null, "don't tell America", null, 0),
				new Product("powder", 10, null, "a fun time,", null, 0),
				new Product("brain", 0.01, null, "a penny for your thoughts?", null, 0)
				};
		productRepo.saveAll(Arrays.asList(products));
		
		
		List<Product> o1 = orders[0].getProductList();
		o1.add(products[0]);
		o1.add(products[2]);
		o1.add(products[4]);
		orders[0].setProductList(o1);
		orders[0].setUser(u);
		orderRepo.save(orders[0]);
		List<Product> o2 = orders[1].getProductList();
		o2.add(products[1]);
		o2.add(products[3]);
		o2.add(products[5]);
		orders[1].setProductList(o2);
		orderRepo.save(orders[1]);
		}
	//*/
	

}
