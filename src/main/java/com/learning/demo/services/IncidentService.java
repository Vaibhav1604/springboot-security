package com.learning.demo.services;

import com.learning.demo.entities.Incident;
import com.learning.demo.repositories.IncidentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<List<Incident>> getAllIncidents(){
        List<Incident> incidents = new ArrayList<>();
        incidentRepository.findAll().forEach(incidents::add);
        return ResponseEntity.ok(incidents);
    }

    public ResponseEntity<Incident> getOneIncident(String incNumber) {
        Optional<Incident> optionalIncident = incidentRepository.findByIncNumber(incNumber);
        if(!optionalIncident.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok().body(optionalIncident.get());
    }

    public ResponseEntity<String> addIncident(Incident incident) {
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

    public ResponseEntity<String> updateIncident(@PathVariable String incNumber, @RequestBody Incident incident) {

        Optional<Incident> optionalIncident = incidentRepository.findByIncNumber(incNumber);

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
    public ResponseEntity<String> deleteIncident(String incNumber){
        Optional<Incident> optionalIncident = incidentRepository.findByIncNumber(incNumber);
        if(!optionalIncident.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("incident not found");
        }

        incidentRepository.deleteByIncNumber(incNumber);
        return ResponseEntity.ok("incident successfully deleted");
    }
}