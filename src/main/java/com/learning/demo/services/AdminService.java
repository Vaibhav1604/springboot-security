package com.learning.demo.services;

import com.learning.demo.entities.Incident;
import com.learning.demo.entities.User;
import com.learning.demo.repositories.IncidentRepository;
import com.learning.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IncidentRepository incidentRepository;

    public ResponseEntity<List<User>> getAllUsers(){
        List<User> allUsers = new ArrayList<>();
        userRepository.findAll().forEach((allUsers::add));
        if(allUsers!=null || !allUsers.isEmpty())
            return ResponseEntity.ok(allUsers);
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<User> getOneUser(int id){
        Optional<User> tentativeUser = userRepository.findById(id);
        if(!tentativeUser.isPresent()){
            return ResponseEntity.notFound().build();
        }
        User user = tentativeUser.get();
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<String> deleteUser(int id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok("user deleted successfully");
    }

    public ResponseEntity<User> searchUser(Integer userId, String username, String email) {
        Optional<User> user = Optional.empty();
        if (userId != null) {
            user = userRepository.findById(userId);
        } else if (username != null && !username.isEmpty()) {
            user = userRepository.findByUsername(username);
        } else if (email != null && !email.isEmpty()) {
            user = userRepository.findByEmail(email);
        }
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
