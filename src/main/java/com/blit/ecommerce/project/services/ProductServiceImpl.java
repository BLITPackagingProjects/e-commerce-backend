package com.blit.ecommerce.project.services;

import com.blit.ecommerce.project.entities.Product;
import com.blit.ecommerce.project.exception.ResourceNotFoundException;
import com.blit.ecommerce.project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ProductServiceImpl implements ProductService{
    @Autowired

    private final ProductRepository productRepository;
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Override
    public List<Product> getAllProducts() {
       return productRepository.findAll();
    }
    @Override
    public Product getProductById(Long product_id) {
        return productRepository.findById(product_id)
        .orElseThrow(()-> new ResourceNotFoundException("Product not found with this id: " + product_id));
    }
    @Override
    public void updateProduct(Long product_id, Product product) {
        Product exsistinProduct = getProductById(product_id);
        exsistinProduct.setName(product.getName());
        exsistinProduct.setDescription(product.getDescription());
        exsistinProduct.setImageName(product.getImageName());
        exsistinProduct.setPrice(product.getPrice());
        //exsistinProduct.setStatus(product.getStatus());
        exsistinProduct.setSeller(product.getSeller());
        productRepository.save(exsistinProduct);

    }
}
