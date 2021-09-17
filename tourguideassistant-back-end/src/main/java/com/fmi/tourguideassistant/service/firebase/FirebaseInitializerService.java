package com.fmi.tourguideassistant.service.firebase;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class FirebaseInitializerService {

    @PostConstruct
    private void initDB() throws IOException {
        InputStream serviceAccount = this.getClass().getClassLoader().getResourceAsStream("./tourguideassistant-8f948-firebase-adminsdk-3lt8x-571bd39faa.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://tourguideassistant-8f948-default-rtdb.firebaseio.com")
                .build();

        if(FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }
    }

    public Firestore getFirebase() {
        return FirestoreClient.getFirestore();
    }

    public FirebaseAuth currentUserExists() {
        return FirebaseAuth.getInstance();
    }
}
