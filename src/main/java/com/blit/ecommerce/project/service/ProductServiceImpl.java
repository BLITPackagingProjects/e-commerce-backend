package com.blit.ecommerce.project.service;

import com.blit.ecommerce.project.entities.Product;
import com.blit.ecommerce.project.exception.ResourceNotFoundException;
import com.blit.ecommerce.project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;


//    @Override
//    public List<Product> getProducts() {
//        return productRepository.findAll();
//    }

//    @Override
//    public Product getProductById(Long id) {
//        return productRepository.findById(id)
//                .orElseThrow(()->new ResourceNotFoundException("No product with id #"+id+"exist"));
//    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

//    @Override
//    public Product updateProduct(Long id, Product product) {
//        Product existingProduct = getProductById(id);
//        existingProduct.setName(product.getName());
//        existingProduct.setPrice(product.getPrice());
//        existingProduct.setImageName(product.getImageName());
//        existingProduct.setDescription(product.getDescription());
//        existingProduct.setSeller(product.getSeller());
//        existingProduct.setStatus(product.getStatus());
//        productRepository.save(existingProduct);
//        return existingProduct;
//    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
