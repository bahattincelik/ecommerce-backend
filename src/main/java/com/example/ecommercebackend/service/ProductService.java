package com.example.ecommercebackend.service;

import com.example.ecommercebackend.model.Product;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import org.apache.catalina.LifecycleState;
import org.springframework.stereotype.Service;
import com.google.cloud.firestore.Firestore;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    public  void  addProduct(Product product) {

        Firestore db = FirestoreClient.getFirestore();
        db.collection("products").document().set(product);
    }

    public void addProducts(List<Product> products) {
        Firestore db = FirestoreClient.getFirestore();
        for (Product product : products) {
            db.collection("products").document().set(product);
        }
    }

    public List<Product> getAllProducts() throws Exception{
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> query = db.collection("products").get();
        List<QueryDocumentSnapshot> documents = query.get().getDocuments();
        List<Product> products = new ArrayList<>();
        for (DocumentSnapshot document : documents) {
            Product product = document.toObject(Product.class);
            product.setId(document.getId());
            products.add(product);
        }
        return products;
    }

    public void updateProduct(String id, Product product) {
        Firestore db = FirestoreClient.getFirestore();
        db.collection("products").document(id).set(product);
    }

    public void deleteProduct(String id) {
        Firestore db = FirestoreClient.getFirestore();
        db.collection("products").document(id).delete();
    }
}
