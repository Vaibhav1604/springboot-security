package com.learning.demo.services;

import com.learning.demo.entities.Incident;
import com.learning.demo.entities.Status;
import com.learning.demo.entities.User;
import com.learning.demo.repositories.IncidentRepository;
import com.learning.demo.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IncidentService {

    @Autowired
    private IncidentRepository incidentRepository;
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<List<Incident>> getAllIncidents(){
        List<Incident> allIncidents = new ArrayList<>();
        incidentRepository.findAll().forEach(allIncidents::add);
        if(allIncidents.isEmpty()){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(allIncidents);
        }
    }

    public ResponseEntity<List<Incident>> getAllIncidentsForUser(int userId){
        Optional<User> optionalUser = userRepository.findByUserId(userId);
        if(optionalUser.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        User user = optionalUser.get();
        List<Incident> incidents = user.getIncidents();
        return ResponseEntity.ok(incidents);
    }

    public ResponseEntity<Incident> getOneIncident(int incId) {
        Optional<Incident> optionalIncident = incidentRepository.findById(incId);
        if(!optionalIncident.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok().body(optionalIncident.get());
    }

    public ResponseEntity<String> addIncident(int userId, Incident incident) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        User user = optionalUser.get();
        incident.setUser(user);
        incident.setStatus(Status.CREATED);
        Incident tentativeIncident = incidentRepository.save(incident);
        try {
            if(tentativeIncident!=null) {
                return ResponseEntity.ok("incident created successfully");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("incident could not be created");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("an error occurred: " + e.getMessage());
        }
    }

    public ResponseEntity<String> updateIncident(@PathVariable int incId, @RequestBody Incident incident) {

        Optional<Incident> optionalIncident = incidentRepository.findById(incId);

        if(!optionalIncident.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Incident toBeUpdatedIncident = optionalIncident.get();

        if(incident.getIncDescription() != null) {
            toBeUpdatedIncident.setIncDescription(incident.getIncDescription());
        }
        if(incident.getIncSubject() != null) {
            toBeUpdatedIncident.setIncSubject(incident.getIncSubject());
        }
        if(incident.getStatus() != null) {
            toBeUpdatedIncident.setStatus(incident.getStatus());
        }

        try {
            incidentRepository.save(toBeUpdatedIncident);
        } catch (Exception e) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User could not be updated. Problem in incidentService..");
        }

        return ResponseEntity.ok("incident updated successfully");
    }

    @Transactional
    public ResponseEntity<String> deleteIncident(int incId){
        Optional<Incident> optionalIncident = incidentRepository.findById(incId);
        if(!optionalIncident.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("incident not found");
        }
        incidentRepository.deleteById(incId);
        return ResponseEntity.ok("incident successfully deleted");
    }

    @Transactional
    public ResponseEntity<String> resolveIncident(int incId){
        Optional<Incident> optionalIncident = incidentRepository.findById(incId);
        if(optionalIncident.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Incident incident = optionalIncident.get();
        incident.setStatus(Status.RESOLVED);
        incidentRepository.save(incident);
        return ResponseEntity.ok("incident resolved successfully");
    }
}