package com.blit.ecommerce.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blit.ecommerce.project.entities.Product;
import com.blit.ecommerce.project.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/search")
public class RegexController {
	
	@Autowired
	ProductService pServ;
	
	@GetMapping("/regex")
	@CrossOrigin
	public ResponseEntity<List<Product>> regexProducts(@RequestHeader String regex){
		System.out.println(regex);
		return new ResponseEntity<>(pServ.regexProducts(regex),HttpStatus.OK);
		
	}
}
