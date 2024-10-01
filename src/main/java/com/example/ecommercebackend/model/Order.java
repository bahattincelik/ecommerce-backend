package com.example.ecommercebackend.model;

import com.google.cloud.firestore.annotation.ServerTimestamp;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Order {
    private String id;
    private String userId;
    private List<OrderItem> items;// OrderItem nesnelerinden oluşan bir liste
    @ServerTimestamp
    private Date orderDate;
    private double totalPrice;

    public Order() {
        this.id = UUID.randomUUID().toString();
    }

    public Order(String id, String userId, Date orderDate, List<OrderItem> items, double totalPrice) {
        this.id = id;
        this.userId = userId;
        this.orderDate = orderDate;
        this.items = items;
        this.totalPrice = totalPrice;
    }

    // Getter ve Setter metodları
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
