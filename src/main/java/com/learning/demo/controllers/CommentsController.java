//package com.learning.demo.controllers;
//
//import com.learning.demo.entities.Comments;
//import com.learning.demo.services.CommentsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/comments")
//public class CommentsController {
//
//    @Autowired
//    private CommentsService commentsService;
//
//    @GetMapping("/all")
//    public ResponseEntity<List<Comments>> getAllComments(){
//        return commentsService.getAllComments();
//    }
//
//    @PostMapping("/add-comments")
//    public ResponseEntity<String> addComments(@RequestBody Comments comments){
//        return commentsService.addComments(comments);
//
//    }
//
//    @DeleteMapping("/rem-comments/{id}")
//    public ResponseEntity<String> removeComments(@PathVariable int id){
//        return commentsService.removeComments(id);
//    }
//
//    @PutMapping("/update-comment/{id}")
//    public ResponseEntity<String> updateComment(@PathVariable int id , @RequestBody Comments comment){
//        return commentsService.updateComment(id, comment);
//    }
//
//
//
//}