package com.example.ecommercebackend.controller;

import com.example.ecommercebackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.ecommercebackend.model.Product;

import java.util.List;
import java.util.concurrent.ExecutionException;
@CrossOrigin(origins = "http://10.0.2.2:9090")
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public void addProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }

    @PostMapping("/batch")
    public void addProducts(@RequestBody List<Product> products) {
        productService.addProducts(products);
    }

    @GetMapping
    public List<Product> getAllProducts() throws Exception {
        return productService.getAllProducts();
    }

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable String id, @RequestBody Product updatedProduct) {
        productService.updateProduct(id, updatedProduct);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
    }
}
