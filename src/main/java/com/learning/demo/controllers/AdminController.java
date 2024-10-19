package com.learning.demo.controllers;

import com.learning.demo.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

//    @Autowired
//    private AdminSerive adminSerive;
//
//    @GetMapping("/all-users")
//    public List<User> getAllUsers(){
//        return adminService.getAllUsers();
//    }
}
