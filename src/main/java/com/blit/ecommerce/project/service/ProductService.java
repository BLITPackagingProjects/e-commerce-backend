package com.blit.ecommerce.project.service;

import java.util.List;

import com.blit.ecommerce.project.entities.Product;

public interface ProductService {
	List<Product> searchProducts(String regex);
	List<Product> regexProducts(String regex);
	/*
	 * other functions
	 */

}
