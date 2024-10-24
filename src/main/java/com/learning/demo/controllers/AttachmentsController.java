//package com.learning.demo.controllers;
//
//import com.learning.demo.services.AttachmentsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//@RestController
//@RequestMapping("/attachment")
//public class AttachmentsController {
//
//    @Autowired
//    AttachmentsService attachmentService;
//
//    @PostMapping("/upload-file")
//    public ResponseEntity<String> uploadFile(@RequestParam("File") MultipartFile file) {
//
//        if (file.isEmpty()) {
//            return ResponseEntity.badRequest().body("No file uploaded.");
//        }
//        return attachmentService.uploadFile(file);
//
//    }
//
//}
