package com.blit.ecommerce.project;

import com.blit.ecommerce.project.entities.Order;
import com.blit.ecommerce.project.entities.Product;
import com.blit.ecommerce.project.repository.OrderRepository;
import com.blit.ecommerce.project.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ProjectApplication implements CommandLineRunner {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private OrderRepository orderRepository;
	private static final Logger logger = LoggerFactory.getLogger(ProjectApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(ProjectApplication.class, args);
		logger.info("Project Application started");
	}

	@Override
	public void run(String... args) throws Exception {

		List<Product> products = Arrays.asList(
				new Product(1, "iphone", 800.00,"iphoneImage", "this is a new iphone", "Lisa", "OnSale" ),
				new Product(2, "galaxy", 780.00, "galaxyImage", "this is a galaxy phone", "Lisa", "OnSale"),
				new Product(3, "macbook", 1500.00, "macbookImage", "this is a macBook", "Lisa", "OnSale")

		);

		productRepository.saveAll(products);

	}
}
