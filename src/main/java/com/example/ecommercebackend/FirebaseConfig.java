package com.example.ecommercebackend;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {

    /* public void init() throws IOException{
       FileInputStream serviceAccount = new FileInputStream("src/main/resources/ecommerceproapp-firebase-adminsdk.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://ecommerceproapp.firebaseio.com")
                .build();

        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }
    }*/

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(FirebaseConfig.class);

        @PostConstruct
        public void init() {
            try {
                FileInputStream serviceAccount =
                        new FileInputStream("src/main/resources/ecommerceproapp-firebase-adminsdk.json");

                FirebaseOptions options = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .setDatabaseUrl("https://ecommerceproapp.firebaseio.com")
                        .build();

                FirebaseApp.initializeApp(options);
            } catch (IOException e) {
                logger.error("Error initializing Firebase", e);
            }
        }
}
