//package com.learning.demo.controllers;
//
//import com.learning.demo.services.DownloadService;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//
//@RestController
//@RequestMapping("/download")
//public class DownloadController {
//    @Autowired
//    private DownloadService downloadService;
//
//    @GetMapping("/download-report/{incId}")
//    public ResponseEntity<Void> downloadReport(@PathVariable int incId, HttpServletResponse httpResponse) throws IOException {
//        return downloadService.downloadReport(incId, httpResponse);
//    }
//}
