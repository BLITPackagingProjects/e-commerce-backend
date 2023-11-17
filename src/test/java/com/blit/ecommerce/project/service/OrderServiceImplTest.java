package com.blit.ecommerce.project.service;

import com.blit.ecommerce.project.entities.Cart;
import com.blit.ecommerce.project.entities.Order;
import com.blit.ecommerce.project.entities.Product;
import com.blit.ecommerce.project.entities.User;
import com.blit.ecommerce.project.repository.CartRepository;
import com.blit.ecommerce.project.repository.OrderRepository;
import com.blit.ecommerce.project.repository.UserRepository;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductServiceImpl productService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private CartServiceImpl cartService;

    @BeforeEach
    public void setup() throws Exception {
        MockitoAnnotations.openMocks(this)
                .close();
    }
    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    public void testCreateOrder(){
        MockitoAnnotations.openMocks(this);
        // Mock data
        Long userId = 1L;
        Integer cartId = 2;
        Integer countToOrder = 2; // Specify the count to order for each product

        User user = new User();
        user.setUser_id(userId);

        Cart cart = new Cart();
        cart.setCart_id(cartId);

        Product product1 = new Product(1L, "Product1", 5);
        Product product2 = new Product(2L, "Product2",  8);

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);

        cart.setProductList(productList);

        when(userRepository.findById(userId)).thenReturn(java.util.Optional.ofNullable(user));
        when(cartRepository.findById(cartId)).thenReturn(java.util.Optional.ofNullable(cart));

//        // Call the method to be tested
        orderService.createOrder(userId, cartId, countToOrder);

        // Verify that the expected methods were called
        verify(cartRepository, times(1)).findById(any());
        verify(orderRepository, times(1)).save(any(Order.class));
        verify(cartService, times(1)).clearCart(cart);
        verify(productService, times(2)).saveProduct(any(Product.class)); // Assuming you have two products in the cart
//
//        // Verify that the product quantities were reduced based on the specified countToOrder
        assert product1.getQuantity() == 3; // Assuming you reduce by 2 for Product1
        assert product2.getQuantity() == 6; // Assuming you reduce by 2 for Product2



//        MockitoAnnotations.openMocks(this); // Initialize mocks
//
//        User user = new User("Lisa");
//        Long userId = user.getUser_id();
//        int count = productService.getProductById(userId).getQuantity();
//        when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(user));
//
//        Cart cart = new Cart();
//        int cartId = cart.getCart_id();
//        Product product1 = new Product(1L, "Laptop", 2);
//        Product product2 = new Product(2L, "Smartphone", 3);
//        cart.getProductList().addAll(Arrays.asList(product1, product2));
//
//        when(cartRepository.findById(cartId)).thenReturn(java.util.Optional.of(cart));
//        when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(user));
//
//       orderService.createOrder(userId, cartId, count );
//        verify(orderRepository, times(1)).save(any(Order.class));
//        verify(cartService, times(1)).clearCart(cart);
//        verify(productService, times(2)).createProduct(any(Product.class)); // Assuming you have two products in the cart
//
//        // Verify that the product quantities were reduced based on the specified counts
//        assert product1.getQuantity() == 3; // Assuming you reduce by 2 for Product1
//        assert product2.getQuantity() == 5; // Assuming you reduce by 3 for Product2

    }


}
