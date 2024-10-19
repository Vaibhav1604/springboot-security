package com.learning.demo.controllers;

import com.learning.demo.entities.User;
import com.learning.demo.repositories.UserRepository;
import com.learning.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value="/all")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @PostMapping("/add-user")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.ok("user created successfully");
    }

}

