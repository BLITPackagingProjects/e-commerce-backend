package com.blit.ecommerce.project.controller;

import com.blit.ecommerce.project.auth.AuthenticationRequest;
import com.blit.ecommerce.project.auth.AuthenticationResponse;
import com.blit.ecommerce.project.auth.AuthenticationService;
import com.blit.ecommerce.project.auth.RegisterRequest;
import com.blit.ecommerce.project.entities.User;
import com.blit.ecommerce.project.exception.UserNotFoundException;
import com.blit.ecommerce.project.repository.UserRepository;
import com.blit.ecommerce.project.service.UserService;
import jakarta.persistence.Id;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AuthenticationController {

    @Autowired
    UserService userService;

    private final AuthenticationService service;

    private final UserRepository repository;
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if(repository.existsByUsername(request.getUsername())){
            return ResponseEntity.badRequest().body("Email is already taken");
        }
        //System.out.println(request);
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate (@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/passwordReset")
    public ResponseEntity resetPassword(HttpServletRequest request, @RequestParam("email") String email){

        User user = userService.findByEmail(email);

        if(user==null){
            throw new UserNotFoundException("No user with that email exists");
        }
        String token = UUID.randomUUID().toString();
        userService.createPasswordResetToken(user, token);
        return null;
    }
}
