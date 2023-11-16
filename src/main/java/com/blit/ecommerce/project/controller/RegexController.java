package com.blit.ecommerce.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blit.ecommerce.project.entities.Product;
import com.blit.ecommerce.project.service.ProductService;

@RestController
@RequestMapping("/search")
public class RegexController {
	
	@Autowired
	ProductService pServ;
	
	@GetMapping("/basic/{name}")
	public ResponseEntity<List<Product>> searchProducts(@PathVariable String name){
		return new ResponseEntity<>(pServ.searchProducts(name),HttpStatus.OK);
	}
	
	@GetMapping("/regex")
	public ResponseEntity<List<Product>> regexProducts(@RequestBody String regex){
		return new ResponseEntity<>(pServ.regexProducts(regex),HttpStatus.OK);
	}
}
