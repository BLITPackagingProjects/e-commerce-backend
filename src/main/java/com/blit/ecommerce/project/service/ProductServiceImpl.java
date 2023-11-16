package com.blit.ecommerce.project.service;

import com.blit.ecommerce.project.entities.Product;

import com.blit.ecommerce.project.exception.ProductNotFoundException;

import com.blit.ecommerce.project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getProducts() {

        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("Cannot found the product"));
    }

    @Override
    public void createProduct(Product product) {
         productRepository.save(product);
    }


}
