package com.example.ecommercebackend.service;

import com.example.ecommercebackend.model.Order;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    public void addOrder(Order order) {
        Firestore db = FirestoreClient.getFirestore();
        db.collection("orders").document(order.getId()).set(order);
    }

    public List<Order> getOrders() throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<com.google.cloud.firestore.QuerySnapshot> query = db.collection("orders").get();
        List<com.google.cloud.firestore.QueryDocumentSnapshot> documents =query.get().getDocuments();
        List<Order> orders= new ArrayList<>();
        for(com.google.cloud.firestore.QueryDocumentSnapshot document : documents) {
         orders.add(document.toObject(Order.class));
        }
        return orders;
    }

    public void updateOrder(String id, Order order) {
        Firestore db = FirestoreClient.getFirestore();
        db.collection("orders").document(id).set(order);
    }

    public void deleteOrder(String id) {
        Firestore db = FirestoreClient.getFirestore();
        db.collection("orders").document(id).delete();
    }
}
