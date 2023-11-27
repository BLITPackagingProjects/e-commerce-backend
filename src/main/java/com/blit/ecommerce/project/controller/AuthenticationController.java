package com.blit.ecommerce.project.controller;

import com.blit.ecommerce.project.auth.AuthenticationRequest;
import com.blit.ecommerce.project.auth.AuthenticationResponse;
import com.blit.ecommerce.project.auth.AuthenticationService;
import com.blit.ecommerce.project.auth.RegisterRequest;
import com.blit.ecommerce.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AuthenticationController {

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

}
