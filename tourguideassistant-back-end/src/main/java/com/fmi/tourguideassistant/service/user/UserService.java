package com.fmi.tourguideassistant.service.user;

import com.fmi.tourguideassistant.model.User;
import com.fmi.tourguideassistant.service.firebase.FirebaseInitializerService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.QuerySnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class UserService {

    @Autowired
    FirebaseInitializerService db;

    public User addUser(User accountDto) throws ExecutionException, InterruptedException {
        String documentId = db.getFirebase().collection("users").document().getId();
        accountDto.setId(documentId);

        User existingUserWithEmail = this.getUserByEmail(accountDto.getEmail());
        if(existingUserWithEmail != null){
            return null;
        }

        db.getFirebase().collection("users").document(documentId).set(accountDto);

        return accountDto;
    }

    public User getUserByEmail(String email) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> result = db.getFirebase().collection("users").whereEqualTo("email", email).get();

        if(result.get().isEmpty()){
            return null;
        }

        return result.get().getDocuments().get(0).toObject(User.class);
    }

    public User getUserById(String id) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> result = db.getFirebase().collection("users").whereEqualTo("id", id).get();

        if(result.get().isEmpty()){
            return null;
        }

        return result.get().getDocuments().get(0).toObject(User.class);
    }

    public void deactivateUserById(String id) {
        DocumentReference ref = db.getFirebase().collection("users").document(id);

        if(ref == null){
            throw new NullPointerException();
        }

        ref.delete();
    }
}
