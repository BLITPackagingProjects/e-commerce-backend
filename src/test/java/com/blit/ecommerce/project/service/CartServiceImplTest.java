package com.blit.ecommerce.project.service;

import com.blit.ecommerce.project.entities.Cart;
import com.blit.ecommerce.project.entities.Product;
import com.blit.ecommerce.project.repository.CartRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CartServiceImplTest {

    @Mock
    private CartRepository cartRepository;
    @Mock
    private ProductService productService;

    @BeforeEach
    public void setup() throws Exception {
        MockitoAnnotations.openMocks(this)
                .close();
    }

    @InjectMocks
    private CartServiceImpl cartService;

    @Test
    public void testProductsAddedInCart(){
        //Arrange
        Cart cart = new Cart();
        Long productId =1L;
        Product product1 = new Product(productId,"iphone", 800.00);

        when(productService.getProductById(productId)).thenReturn(product1);
        when(cartRepository.save(cart)).thenReturn(cart);

        //Act
        cartService.addProductToCart(cart, productId);

        //Assert
        assertEquals(1, cart.getProductList().size());
        assertEquals(product1, cart.getProductList().get(0));
        verify(productService, times(1)).getProductById(productId);
        verify(cartRepository, times(1)).save(cart);

    }

    @Test
    public void testGetTotalPrice(){
        // Arrange
        Cart cart = new Cart();
        Long productId1 = 1L;
        Long productId2 = 2L;

        Product product1 = new Product(productId1, "Laptop", 999.99);
        Product product2 = new Product(productId2, "Smartphone", 499.99);

        cart.getProductList().addAll(Arrays.asList(product1, product2));

        when(productService.getProductById(productId1)).thenReturn(product1);
        when(productService.getProductById(productId2)).thenReturn(product2);

        // Act
        double totalPrice = cartService.getTotalPrice(cart);

        // Assert
        assertEquals(1499.98, totalPrice);
    }

    @Test
    public void testClearCart(){
        Cart cart = new Cart();
        Product product1 = new Product(1L, "Laptop", 1999.00);
        Product product2 = new Product(2L, "Smartphone", 999.00);
        cart.getProductList().addAll(Arrays.asList(product1, product2));

        // Mock the cartRepository.save(cart) method
        when(cartRepository.save(cart)).thenReturn(cart);

        cartService.clearCart(cart);

        // Verify that the product list is cleared
        assertTrue(cart.getProductList().isEmpty());

        // Verify that cartRepository.save(cart) is called once
        verify(cartRepository, times(1)).save(cart);
    }


}
