package com.fmi.tourguideassistant.controller;

import com.fmi.tourguideassistant.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;


@RestController
public class AdminController {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/hello")
    public boolean sayHello() {
        return true;
    }
}
