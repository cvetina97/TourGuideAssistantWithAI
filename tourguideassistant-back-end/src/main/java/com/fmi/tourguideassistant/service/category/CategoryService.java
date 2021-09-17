package com.fmi.tourguideassistant.service.category;

import com.fmi.tourguideassistant.model.Category;
import com.fmi.tourguideassistant.model.Region;
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
public class CategoryService {

    @Autowired
    FirebaseInitializerService db;

    public Category getCategoryById(String id) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> result = db.getFirebase().collection("categories").whereEqualTo("id", id).get();

        if(result.get().isEmpty()){
            return null;
        }

        return result.get().getDocuments().get(0).toObject(Category.class);
    }

    public List<Category> getAllCategories() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = db.getFirebase().collection("categories").get();
        List<Category> categories = new ArrayList<>();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            Category category = document.toObject(Category.class);
            categories.add(category);
        }
        return categories;
    }
}
