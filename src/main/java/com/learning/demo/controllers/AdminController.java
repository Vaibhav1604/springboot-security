package com.learning.demo.controllers;

import com.learning.demo.config.AuthenticationResponse;
import com.learning.demo.config.RegisterRequest;
import com.learning.demo.entities.Incident;
import com.learning.demo.entities.User;
import com.learning.demo.services.AdminService;
import com.learning.demo.services.AuthenticationService;
import com.learning.demo.services.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private IncidentService incidentService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/add-admin")
    public ResponseEntity<AuthenticationResponse> addAdminUser(@RequestBody RegisterRequest user){
        return authenticationService.registerAdmin(user);
    }

    @GetMapping("/users/all")
    public ResponseEntity<List<User>> getAllUsers(){
        return adminService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getOneUser(@PathVariable int id){
        return adminService.getOneUser(id);
    }

    @GetMapping("/users/search")
    public ResponseEntity<User> searchUsers(
            @RequestParam(required = false) int id,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String email
    ) {
        return adminService.searchUser(id, username, email);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUser(@PathVariable int id){
        return adminService.deleteUser(id);
    }

    @GetMapping("/incidents/all")
    public ResponseEntity<List<Incident>> getAllIncidents(){
        return incidentService.getAllIncidents();
    }

    @GetMapping("/incident/{id}")
    public ResponseEntity<Incident> getOneIncident(@PathVariable String incNumber){
        return incidentService.getOneIncident(incNumber);
    }
}
