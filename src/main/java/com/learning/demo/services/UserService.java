package com.learning.demo.services;

import com.learning.demo.entities.Incident;
import com.learning.demo.entities.User;
import com.learning.demo.repositories.IncidentRepository;
import com.learning.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IncidentRepository incidentRepository;




}