package com.example.ecommercebackend.service;

import com.example.ecommercebackend.model.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    public void addUser(User user) {
        Firestore db = FirestoreClient.getFirestore();
        db.collection("users").document(user.getId()).set(user);
    }

    public List<User> getAllUsers() throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<com.google.cloud.firestore.QuerySnapshot> query = db.collection("users").get();
        List<com.google.cloud.firestore.QueryDocumentSnapshot> documents = query.get().getDocuments();
        List<User> users = new ArrayList<>();
        for (com.google.cloud.firestore.QueryDocumentSnapshot document : documents) {
            users.add(document.toObject(User.class));
        }
        return users;
    }

    public void updateUser(String id, User user) {
        Firestore db = FirestoreClient.getFirestore();
        db.collection("users").document(id).set(user);
    }

    public void deleteUser(String id) {
        Firestore db = FirestoreClient.getFirestore();
        db.collection("users").document(id).delete();
    }
}
