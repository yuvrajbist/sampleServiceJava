package com.example.userservice.controller;

import com.example.userservice.service.ProductServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final ProductServiceClient productServiceClient;

    public UserController(ProductServiceClient productServiceClient) {
        this.productServiceClient = productServiceClient;
    }

    @GetMapping
    public List<Map<String, String>> getUsers() {
        log.info("Fetching users");
        return List.of(
                Map.of("id", "1", "name", "John Doe"),
                Map.of("id", "2", "name", "Jane Smith")
        );
    }

    @GetMapping("/with-products")
    public ResponseEntity<?> getUsersWithProducts() {
        try {
            log.info("Fetching users with products");
            
            Object nullObj = null;
            log.info("Null object value: {}", nullObj.toString());
            
            var users = getUsers();
            var products = productServiceClient.getProducts();
            
            return ResponseEntity.ok(Map.of(
                    "users", users,
                    "products", products
            ));
        } catch (Exception e) {
            log.error("Error in /users/with-products endpoint", e);
            return ResponseEntity.internalServerError().body(Map.of(
                    "error", "Failed to process request",
                    "details", e.getMessage()
            ));
        }
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        log.info("Health check for User Service");
        return ResponseEntity.ok("User Service is healthy");
    }
}
