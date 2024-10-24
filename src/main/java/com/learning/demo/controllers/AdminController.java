package com.learning.demo.controllers;

import com.learning.demo.config.AuthenticationResponse;
import com.learning.demo.config.RegisterRequest;
import com.learning.demo.entities.Comments;
import com.learning.demo.entities.Incident;
import com.learning.demo.entities.User;
import com.learning.demo.services.AdminService;
import com.learning.demo.services.AuthenticationService;
import com.learning.demo.services.CommentsService;
import com.learning.demo.services.IncidentService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/{adminId}")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private IncidentService incidentService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private CommentsService commentsService;

//    @PostMapping("/add-admin")
//    public ResponseEntity<AuthenticationResponse> addAdminUser(@RequestBody RegisterRequest user){
//        return authenticationService.registerAdmin(user);
//    }

    @GetMapping("/")
    public ResponseEntity<List<Incident>> getAllIncidents(){
        return incidentService.getAllIncidents();
    }

    @PutMapping("/update-inc/{incId}")
    public ResponseEntity<String> updateIncident(@PathVariable int incId, @RequestBody Incident incident) {
        return incidentService.updateIncident(incId, incident);
    }

    @DeleteMapping("delete/{incId}")
    public ResponseEntity<String> deleteIncident(@PathVariable int incId){
        return incidentService.deleteIncident(incId);
    }

    @PostMapping("/add-comment/{incId}")
    public ResponseEntity<String> addComment(
            @PathVariable int adminId,
            @PathVariable int incId,
            @RequestBody Comments comment){
        return commentsService.addComments(adminId, incId, comment);
    }

    @PutMapping("/update-comment/{incId}")
    public ResponseEntity<String> updateComment(
                                                @PathVariable int incId,
                                                @RequestBody Comments comment){
        return commentsService.updateComment(incId, comment);
    }

    @PutMapping("/resolve-inc/{incId}")
    public ResponseEntity<String> resolveIncident(@PathVariable int incId){
        return incidentService.resolveIncident(incId);
    }

//    @GetMapping("/users/all")
//    public ResponseEntity<List<User>> getAllUsers(){
//        return adminService.getAllUsers();
//    }

//    @GetMapping("/incident/{id}")
//    public ResponseEntity<Incident> getOneIncident(@PathVariable String incNumber){
//        return incidentService.getOneIncident(incNumber);
//    }

//    @GetMapping("/user/{id}")
//    public ResponseEntity<User> getOneUser(@PathVariable int id){
//        return adminService.getOneUser(id);
//    }

//    @GetMapping("/users/search")
//    public ResponseEntity<User> searchUsers(
//            @RequestParam(required = false) int id,
//            @RequestParam(required = false) String username,
//            @RequestParam(required = false) String email
//    ) {
//        return adminService.searchUser(id, username, email);
//    }

//    @DeleteMapping
//    public ResponseEntity<String> deleteUser(@PathVariable int id){
//        return adminService.deleteUser(id);
//    }

}
