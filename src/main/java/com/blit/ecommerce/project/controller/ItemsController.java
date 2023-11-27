package com.blit.ecommerce.project.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/items-controller")
public class ItemsController {

    @GetMapping
    public ResponseEntity<String> securedPoint() {
        return ResponseEntity.ok("List of items");
    }
}
