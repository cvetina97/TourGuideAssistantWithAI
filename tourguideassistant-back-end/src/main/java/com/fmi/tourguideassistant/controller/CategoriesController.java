package com.fmi.tourguideassistant.controller;

import com.fmi.tourguideassistant.model.Category;
import com.fmi.tourguideassistant.model.Region;
import com.fmi.tourguideassistant.service.category.CategoryService;
import com.fmi.tourguideassistant.service.firebase.FirebaseInitializerService;
import com.fmi.tourguideassistant.service.region.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class CategoriesController {

    @Autowired
    FirebaseInitializerService db;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/getCategoryById")
    public ResponseEntity<Category> getCategoryById(@RequestParam String id) throws ExecutionException, InterruptedException {
        if(id == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        Category category = this.categoryService.getCategoryById(id);

        if(category == null){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping("/getAllCategories")
    public ResponseEntity<List<Category>> getAllCategories() throws ExecutionException, InterruptedException {
        List<Category> categories = this.categoryService.getAllCategories();

        if(categories.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}
