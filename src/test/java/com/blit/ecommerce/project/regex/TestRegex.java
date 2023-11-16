package com.blit.ecommerce.project.regex;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.blit.ecommerce.project.entities.Product;
import com.blit.ecommerce.project.repository.ProductRepository;
import com.blit.ecommerce.project.service.ProductServiceImpl;

class TestRegex {

	@Mock
	private ProductRepository pRepo;
	@InjectMocks
	private ProductServiceImpl pServ;
	

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(pRepo).close();
	}


	@Test
	void testEmpty() {
		String regex = "";
		Mockito.when(pRepo.findProductByNameRegex(regex)).thenReturn(new ArrayList<Product>());
		
		List<Product> output = pServ.regexProducts(regex);
		
		assert(output.size()==0);
	}
	
	@Test
	void testNormal() {
		String regex = "o";
		List<Product> expected = new ArrayList<Product>();
		
		expected.add(new Product("powder", 10.0, null, "a fun time", "disney", 0L, null, null));
		expected.add(new Product("oil", 100.0, null, "Don't tell america", "Earth", 0L, null, null));
		expected.add(new Product("doot", 100.0, null, "dootdootdoot", "nootnoot", 0L, null, null));
		Mockito.when(pRepo.findProductByNameRegex(regex)).thenReturn(expected);
		
		List<Product> output = pServ.regexProducts(regex);
		
		assert(output.size()==3);
	}

}
