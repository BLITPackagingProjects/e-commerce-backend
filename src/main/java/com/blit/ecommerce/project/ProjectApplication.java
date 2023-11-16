package com.blit.ecommerce.project;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.blit.ecommerce.project.entities.Product;
import com.blit.ecommerce.project.repository.ProductRepository;

@SpringBootApplication
public class ProjectApplication /*implements CommandLineRunner/**/{

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}
/*
	@Autowired
	private ProductRepository pRepo;
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		pRepo.saveAll(Arrays.asList(
				new Product("powder", 10.0, null, "a fun time", "disney", 0L, null, null),
				new Product("oil", 100.0, null, "Don't tell america", "Earth", 0L, null, null),
				new Product("milk", 5.20, null, "stronk bones", "Big Dairy", 0L, null, null),
				new Product("doot", 100.0, null, "dootdootdoot", "nootnoot", 0L, null, null),
				new Product("fur", 25.0, null, "soft and fluffy", "bear", 0L, null, null)
				));
	}
//*/
}
