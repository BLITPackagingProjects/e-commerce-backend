package com.blit.ecommerce.project;

import com.blit.ecommerce.project.entities.Product;
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
public class ProjectApplication  implements CommandLineRunner {
	@Autowired
	ProductRepository productRepository;
	private static final Logger logger =  LoggerFactory.getLogger(ProjectApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
		logger.info("ProjectApplication started");


	}
	@Override
	public void run(String... args) throws Exception {
		List<Product> products = Arrays.asList(
				new Product("Basket Ball",1200.45,"ball image","This basket ball is for kids","Bob","Active"),
				new Product("T-shirt",45.35,"t-shirt image","This t-shirt is made of cotton ","John","Active"),
				new Product("Jacket",32.40,"Jacket image","This Jacket made in USA","Lisa","Active"));
               productRepository.saveAll(products);
	}
}
