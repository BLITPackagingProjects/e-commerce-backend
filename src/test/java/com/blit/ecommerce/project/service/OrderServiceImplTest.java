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
import java.util.Arrays;

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
        MockitoAnnotations.openMocks(this); // Initialize mocks

        User user = new User("Lisa");
        Long userId = user.getUser_id();
        when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(user));

        Cart cart = new Cart();
        int cartId = cart.getCart_id();
        Product product1 = new Product(1L, "Laptop", 1999.00);
        Product product2 = new Product(2L, "Smartphone", 999.00);
        cart.getProductList().addAll(Arrays.asList(product1, product2));

        when(cartRepository.findById(cartId)).thenReturn(java.util.Optional.of(cart));

        Order order = new Order();
        order.setDate(LocalDateTime.now());
        order.setUser(user);

        when(orderRepository.save(any(Order.class))).thenReturn(order);

        orderService.createOrder(userId, cartId);

        verify(userRepository, times(1)).findById(userId);
        verify(cartRepository, times(1)).findById(cartId);
        verify(orderRepository, times(1)).save(argThat(savedOrder ->
                savedOrder.getUser().equals(user) && savedOrder.getDate() != null));

        verify(cartService, times(1)).clearCart(cart);


    }


}
