package com.blit.ecommerce.project;

import com.blit.ecommerce.project.repository.OrderRepository;
import com.blit.ecommerce.project.repository.ProductRepository;
import com.blit.ecommerce.project.repository.UserRepository;
import com.blit.ecommerce.project.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectApplication implements CommandLineRunner {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private OrderService orderService;

	private static final Logger logger = LoggerFactory.getLogger(ProjectApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
		logger.info("Project Application started");
	}

	@Override
	public void run(String... args) throws Exception {

//		List<Product> products = Arrays.asList(
//				new Product("iphone", 800.00, 100 ),
//				new Product("galaxy", 780.00,  200),
//				new Product("macbook", 1500.00,  300)
//		);
//
//		User user = new User("Lisa");
//
//		productRepository.saveAll(products);
//		userRepository.save(user);
//		cartService.addProductToCart(1L);
//		orderService.createOrder(1L, 1);
	}
}
