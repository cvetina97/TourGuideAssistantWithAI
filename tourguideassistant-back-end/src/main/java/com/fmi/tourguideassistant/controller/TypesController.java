package com.fmi.tourguideassistant.controller;

import com.fmi.tourguideassistant.model.Category;
import com.fmi.tourguideassistant.model.Type;
import com.fmi.tourguideassistant.service.firebase.FirebaseInitializerService;
import com.fmi.tourguideassistant.service.type.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class TypesController {

    @Autowired
    FirebaseInitializerService db;

    @Autowired
    TypeService typeService;

    @GetMapping("/getTypeById")
    public ResponseEntity<Type> getTypeById(@RequestParam String id) throws ExecutionException, InterruptedException {
        if(id == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        Type type = this.typeService.getTypeById(id);

        if(type == null){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(type, HttpStatus.OK);
    }

    @GetMapping("/getAllTypes")
    public ResponseEntity<List<Type>> getAllTypes() throws ExecutionException, InterruptedException {
        List<Type> types = this.typeService.getAllTypes();

        if(types.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(types, HttpStatus.OK);
    }

    @GetMapping("/getAllTypesByCategoryId")
    public ResponseEntity<List<Type>> getAllTypesByCategoryId(@RequestParam String landmarkCategoryId) throws ExecutionException, InterruptedException {
        List<Type> types = this.typeService.getAllTypesByCategoryId(landmarkCategoryId);

        if(types.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(types, HttpStatus.OK);
    }
}
