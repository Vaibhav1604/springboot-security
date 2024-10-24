package com.learning.demo.controllers;

import com.learning.demo.entities.Comments;
import com.learning.demo.entities.Incident;
import com.learning.demo.entities.User;
import com.learning.demo.repositories.UserRepository;
import com.learning.demo.services.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user/{userId}")
public class UserController {

    @Autowired
    private IncidentService incidentService;

    @Autowired
    private AttachmentsService attachmentsService;

    @Autowired
    private DownloadService downloadService;

//    @GetMapping("/incident/{id}")
//    public ResponseEntity<Incident> getOneIncident(@PathVariable String id){
//        return incidentService.getOneIncident(id);
//    }

    @GetMapping("/")
    public ResponseEntity<List<Incident>> getAllIncidentsForUser(@PathVariable int userId){
        return incidentService.getAllIncidentsForUser(userId);
    }

    @PostMapping("/add-inc")
    public ResponseEntity<String> addIncident(@PathVariable int userId, @RequestBody Incident incident) {
        return incidentService.addIncident(userId, incident);
    }

    @PostMapping("add-inc/{incId}/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("File") MultipartFile file, @PathVariable int incId) {
        return attachmentsService.uploadFile(file,incId);
    }

    @PutMapping("/update-inc/{incId}")
    public ResponseEntity<String> updateIncident(@PathVariable int incId, @RequestBody Incident incident) {
        return incidentService.updateIncident(incId, incident);
    }

    @DeleteMapping("delete/{incId}")
    public ResponseEntity<String> deleteIncident(@PathVariable int incId){
        return incidentService.deleteIncident(incId);
    }

    @GetMapping("/download-report/{incId}")
    public ResponseEntity<Void> downloadReport(@PathVariable int incId, HttpServletResponse httpResponse) throws IOException {
        return downloadService.downloadReport(incId, httpResponse);
    }

}

