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
public class ProjectApplication /* implements CommandLineRunner/ **/ {

	private static final Logger logger = LoggerFactory.getLogger(ProjectApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
		logger.info("The trojan application started successfully");
	}

}
