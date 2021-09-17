package com.fmi.tourguideassistant.service.type;

import com.fmi.tourguideassistant.model.Category;
import com.fmi.tourguideassistant.model.Type;
import com.fmi.tourguideassistant.service.firebase.FirebaseInitializerService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class TypeService {

    @Autowired
    FirebaseInitializerService db;

    public Type getTypeById(String id) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> result = db.getFirebase().collection("types").whereEqualTo("id", id).get();

        if(result.get().isEmpty()){
            return null;
        }

        return result.get().getDocuments().get(0).toObject(Type.class);
    }

    public List<Type> getAllTypes() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = db.getFirebase().collection("types").get();
        List<Type> types = new ArrayList<>();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            Type type = document.toObject(Type.class);
            types.add(type);
        }
        return types;
    }

    public List<Type> getAllTypesByCategoryId(String landmarkCategoryId) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = db.getFirebase().collection("types").whereEqualTo("landmarkCategoryId", landmarkCategoryId).get();
        List<Type> types = new ArrayList<>();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            Type type = document.toObject(Type.class);
            types.add(type);
        }
        return types;
    }
}
