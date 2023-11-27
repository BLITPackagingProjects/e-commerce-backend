package com.blit.ecommerce.project;

import com.blit.ecommerce.project.auth.AuthenticationRequest;
import com.blit.ecommerce.project.auth.AuthenticationResponse;
import com.blit.ecommerce.project.auth.AuthenticationService;
import com.blit.ecommerce.project.config.JwtService;
import com.blit.ecommerce.project.entities.Type.Role;
import com.blit.ecommerce.project.entities.User;
import com.blit.ecommerce.project.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class ProjectApplicationTests {

//	@Mock
//	private UserRepository repository;
//
//	@Mock
//	private PasswordEncoder passwordEncoder;
//
//	@Mock
//	private JwtService jwtService;
//
//	@Mock
//	private AuthenticationManager authenticationManager;
//
//	@InjectMocks
//	private AuthenticationService authenticationService;
//
//	@BeforeEach
//	public void setUp() {
//		MockitoAnnotations.openMocks(this);
//	}
//
//	@Test
//	public void testAuthenticate() {
//		// Arrange
//		AuthenticationRequest authenticationRequest = AuthenticationRequest.builder()
//				.username("john.doe")
//				.password("password")
//				.build();
//
//		User mockedUser = User.builder()
//				.firstName("John")
//				.lastName("Doe")
//				.username("john.doe")
//				.password("encodedPassword")
//				.role(Role.USER)
//				.build();
//
//		// Mock behavior of repository
//		when(repository.findByUsername(authenticationRequest.getUsername())).thenReturn(Optional.of(mockedUser));
//
//		// Mock behavior of authenticationManager
//		Authentication authentication = mock(Authentication.class);
//		when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
//
//		// Mock behavior of jwtService
//		when(jwtService.generateToken(mockedUser)).thenReturn("mockedJwtToken");
//
//		// Act
//		AuthenticationResponse authenticationResponse = null;
//		try {
//			authenticationResponse = authenticationService.authenticate(authenticationRequest);
//		} catch (Exception e) {
//			// Handle or log the exception
//			e.printStackTrace();
//		}
//
//		System.out.println("Before authentication: " + authenticationResponse);
//		authenticationResponse = authenticationService.authenticate(authenticationRequest);
//		System.out.println("After authentication: " + authenticationResponse);
//
//		// Assert
//		//assertNotNull(authenticationResponse);
//		assertEquals(mockedUser, authenticationResponse.getUser());
//		assertEquals("mockedJwtToken", authenticationResponse.getToken());
//	}
//

}
