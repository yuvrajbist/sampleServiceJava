package com.example.productservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
@Slf4j
public class ProductController {

    @GetMapping
    public List<Map<String, Object>> getProducts() {
        log.info("Fetching products");
        return List.of(
                Map.of("id", "1", "name", "Laptop", "price", 999),
                Map.of("id", "2", "name", "Phone", "price", 699)
        );
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        log.info("Health check for Product Service");
        return ResponseEntity.ok("Product Service is healthy");
    }
}
