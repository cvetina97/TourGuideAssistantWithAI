package com.fmi.tourguideassistant.controller;

import java.util.concurrent.ExecutionException;

import com.fmi.tourguideassistant.model.User;
import com.fmi.tourguideassistant.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fmi.tourguideassistant.service.firebase.FirebaseInitializerService;

@RestController()
public class UsersController {

    @Autowired
    FirebaseInitializerService db;

    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody User user) throws InterruptedException, ExecutionException {
        User userToAdd = this.userService.addUser(user);

        if(userToAdd == null){
            return new ResponseEntity<>(null, HttpStatus.OK);
        }

        return new ResponseEntity<>(userToAdd, HttpStatus.OK);
    }

    @GetMapping("/getUserById")
    public ResponseEntity<User> getUserById(@RequestParam String id) throws ExecutionException, InterruptedException {
        if(id == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        User user = this.userService.getUserById(id);

        if(user == null){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/deactivateUserById")
    public ResponseEntity<HttpStatus> deactivateUserById(@RequestParam String id) {
        if(id == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        this.userService.deactivateUserById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
