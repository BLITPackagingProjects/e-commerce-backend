package com.blit.ecommerce.project.service;

import com.blit.ecommerce.project.entities.Product;
import com.blit.ecommerce.project.exception.ProductNotFoundException;
import com.blit.ecommerce.project.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void setup() throws Exception {
        MockitoAnnotations.openMocks(this)
                .close();
    }

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    public void testSaveProduct() {
        // Arrange
        Product productToSave = new Product(); // create a product to save

        // Act
         productService.createProduct(productToSave);

        // Assert
        verify(productRepository, times(1)).save(productToSave);

    }

    @Test
    public void testReturnAllProducts() {
        // Arrange
        List<Product> mockProducts = new ArrayList<>();
        mockProducts.add(new Product());
        mockProducts.add(new Product());

        // Stub the findAll method in productRepository
        when(productRepository.findAll()).thenReturn(mockProducts);

        // Act
        List<Product> returnedProducts = productService.getProducts();

        // Assert
        assertNotNull(returnedProducts);
        assertEquals(mockProducts.size(), returnedProducts.size());
    }

    @Test
    public void testReturnProductById() {
        // Arrange
        long productId = 1L;
        Product mockProduct = new Product("Product 1", 10.0);

        // Stub the findById method in productRepository
        when(productRepository.findById(productId)).thenReturn(Optional.of(mockProduct));

        // Act
        Product returnedProduct = productService.getProductById(productId);

        // Assert
        assertNotNull(returnedProduct);
        assertEquals(mockProduct, returnedProduct);
    }

    @Test
    public void testReturnProductByIdNotFound() {
        // Arrange
        long nonExistingProductId = 999L;

        // Stub the findById method in productRepository to return Optional.empty()
        when(productRepository.findById(nonExistingProductId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ProductNotFoundException.class, () -> productService.getProductById(nonExistingProductId));
    }
}


