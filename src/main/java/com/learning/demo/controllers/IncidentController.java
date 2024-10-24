//package com.learning.demo.controllers;
//
//import com.learning.demo.entities.Incident;
//import com.learning.demo.services.IncidentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/{userId}")
//public class IncidentController {
//
//    @Autowired
//    private IncidentService incidentService;
//
////    @GetMapping("/all")
////    public ResponseEntity<List<Incident>> getAllIncidents(){
////        return incidentService.getAllIncidents();
////    }
//
////    @GetMapping("/")
////    public ResponseEntity<List<Incident>> getAllIncidentsForUser(@PathVariable int userId){
////        return incidentService.getAllIncidentsForUser(userId);
////    }
//
//    @GetMapping("/incident/{id}")
//    public ResponseEntity<Incident> getOneIncident(@PathVariable String id){
//        return incidentService.getOneIncident(id);
//    }
//
////    @PostMapping("incident/add-inc")
////    public ResponseEntity<String> addIncident(@RequestBody Incident incident) {
////        return incidentService.addIncident(incident);
////    }
//
////    @PutMapping("/update-inc/{id}")
////    public ResponseEntity<String> updateIncident(@PathVariable String id, @RequestBody Incident incident) {
////        return incidentService.updateIncident(id, incident);
////    }
////
////    @DeleteMapping("incident/{incNumber}")
////    public ResponseEntity<String> deleteIncident(@PathVariable String incNumber){
////        return incidentService.deleteIncident(incNumber);
////    }
//
//
//
//}