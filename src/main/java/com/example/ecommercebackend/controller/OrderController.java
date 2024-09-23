package com.example.ecommercebackend.controller;

import com.example.ecommercebackend.model.Order;
import com.example.ecommercebackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public void addOrder(@RequestBody Order order) {
        orderService.addOrder(order);  // Implement business logic to create an order
    }

    @GetMapping
    public List<Order> getOrders() throws Exception {
        return orderService.getOrders();  // Implement business logic to fetch all orders
    }

    @PutMapping("/{id}")
    public void updateOrder(@PathVariable String id, @RequestBody Order updatedOrder) {
        orderService.updateOrder(id, updatedOrder);  // Implement business logic to update an order
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable String id) {
        orderService.deleteOrder(id);  // Implement business logic to delete an order
    }
}
