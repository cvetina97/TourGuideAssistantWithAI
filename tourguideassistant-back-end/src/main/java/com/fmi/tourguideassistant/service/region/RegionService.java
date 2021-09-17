package com.fmi.tourguideassistant.service.region;

import com.fmi.tourguideassistant.model.Region;
import com.fmi.tourguideassistant.model.User;
import com.fmi.tourguideassistant.service.firebase.FirebaseInitializerService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class RegionService {

    @Autowired
    FirebaseInitializerService db;

    public Region getRegionById(String id) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> result = db.getFirebase().collection("regions").whereEqualTo("id", id).get();

        if(result.get().isEmpty()){
            return null;
        }

        return result.get().getDocuments().get(0).toObject(Region.class);
    }

    public List<Region> getAllRegions() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = db.getFirebase().collection("regions").get();
        List<Region> regions = new ArrayList<>();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
           Region region = document.toObject(Region.class);
           regions.add(region);
        }
        return regions;
    }
}
