package com.blit.ecommerce.project;

import com.blit.ecommerce.project.auth.AuthenticationRequest;
import com.blit.ecommerce.project.auth.AuthenticationResponse;
import com.blit.ecommerce.project.auth.AuthenticationService;
import com.blit.ecommerce.project.auth.RegisterRequest;
import com.blit.ecommerce.project.config.JwtService;
import com.blit.ecommerce.project.entities.Type.Role;
import com.blit.ecommerce.project.entities.User;
import com.blit.ecommerce.project.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class JwtServiceTest {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;
    private AuthenticationManager authenticationManager;

    private AuthenticationService authenticationService;

    private UserDetailsService userDetailsService;


    @BeforeEach
    public void setUp() {
        userRepository = mock(UserRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        jwtService = mock(JwtService.class);
        authenticationManager = mock(AuthenticationManager.class);

        authenticationService = new AuthenticationService(userRepository, passwordEncoder, jwtService, authenticationManager);
    }


    @Test
    public void testRegister() {
        // Arrange
        RegisterRequest registerRequest = RegisterRequest.builder()
                .firstName("John")
                .lastName("Doe")
                .username("john.doe")
                .password("password")
                .build();

        User mockedUser = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .username(registerRequest.getUsername())
                .password("encodedPassword") // Mocked encoded password
                .role(Role.USER)
                .build();

        // Mock behavior
        when(passwordEncoder.encode(registerRequest.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(mockedUser)).thenReturn(mockedUser);

        // Act
        AuthenticationResponse registrationResponse = authenticationService.register(registerRequest);

        // Assert
        assertNotNull(registrationResponse);
        assertNotNull(registrationResponse.getUser());
        assertEquals("encodedPassword", registrationResponse.getUser().getPassword());

    }

//        @Test
//        void testJwtServiceAuthentication() {
//        JwtService jwtService = new JwtService();
//
//            // Test data
//            String username = "testUser";
//            String password = "testPassword";
//            Role role = Role.USER;
//
//            // Mock user repository
//            when(repository.findByUsername(username)).thenReturn(java.util.Optional.ofNullable(User.builder()
//                    .username(username)
//                    .password(password)
//                    .role(Role.valueOf(role.name()))
//                    .build()));
//
//            // Test Authentication
//            AuthenticationRequest authenticationRequest = AuthenticationRequest.builder()
//                    .username(username)
//                    .password(password)
//                    .build();
//
//            AuthenticationResponse registrationResponse = authenticationService.authenticate(authenticationRequest);
//            assertNotNull(registrationResponse);
//            assertNotNull(registrationResponse.getToken());
//            assertEquals(username, registrationResponse.getUser().getUsername());
//        }
}