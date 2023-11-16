package com.blit.ecommerce.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blit.ecommerce.project.entities.Product;
import com.blit.ecommerce.project.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository pRepo;
	
	@Override
	public List<Product> searchProducts(String name) {
		// TODO Auto-generated method stub
		return pRepo.findProductByNameLike(name);
	}
	
	@Override
	public List<Product> regexProducts(String regex) {
		// TODO Auto-generated method stub
		return pRepo.findProductByNameRegex(regex);
	}

}
