package com.learning.demo.controllers;

import com.learning.demo.entities.Incident;
import com.learning.demo.services.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/incident")
public class IncidentController {

    @Autowired
    private IncidentService incidentService;

    @GetMapping("/all")
    public ResponseEntity<List<Incident>> getAllIncidents(){
        return incidentService.getAllIncidents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Incident> getOneIncident(@PathVariable String id){
        return incidentService.getOneIncident(id);
    }

    @PostMapping("/add-inc")
    public ResponseEntity<String> addIncident(@RequestBody Incident incident) {
        return incidentService.addIncident(incident);
    }

    @PutMapping("/update-inc/{id}")
    public ResponseEntity<String> updateIncident(@PathVariable String id, @RequestBody Incident incident) {
        return incidentService.updateIncident(id, incident);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteIncident(@PathVariable String id){
        return incidentService.deleteIncident(id);
    }


}