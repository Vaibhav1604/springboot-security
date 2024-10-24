package com.learning.demo.services;

import com.learning.demo.entities.*;
import com.learning.demo.repositories.CommentsRepository;
import com.learning.demo.repositories.IncidentRepository;
import com.learning.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentsService {

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IncidentRepository incidentRepository;

//    public ResponseEntity<List<Comments>> getAllComments() {
//        List<Comments> comments = new ArrayList<>();
//        commentsRepository.findAll().forEach(comments::add);
//        if (comments.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        } else {
//            return ResponseEntity.ok(comments);
//        }
//    }

//    public ResponseEntity<List<Comments>> getCommentForUser(int userId){
//        Optional<User> optionalUser = userRepository.findById(userId);
//        if (optionalUser.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//        User user = optionalUser.get();
//        List<Comments> commentsList = user.getComments();
//        return ResponseEntity.ok(commentsList);
//    }

    public ResponseEntity<String> addComments(int adminId, int incId, Comments comment) {
        Optional<User> optionalUser = userRepository.findById(adminId);
        Optional<Incident> optionalIncident = incidentRepository.findById(incId);
        if(optionalUser.isEmpty() || optionalIncident.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user/incident not found");
        }
        User user = optionalUser.get();
        Incident incident = optionalIncident.get();
        comment.setUser(user);
        comment.setIncident(incident);
        incident.setStatus(Status.WIP);
        Comments createdComment = commentsRepository.save(comment);
        if(createdComment==null){
            return ResponseEntity.badRequest().body("comment could not be added");
        }
        return ResponseEntity.ok("comment added");
    }

    public ResponseEntity<String> updateComment(int incId, Comments comment) {
//        Optional<User> optionalUser = userRepository.findById(adminId);
        Optional<Incident> optionalIncident = incidentRepository.findById(incId);
//        Optional<Comments> optionalComment = commentsRepository.findById(commentId);
        if(optionalIncident.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user/incident/comment not found");
        }
//        User user = optionalUser.get();
        Incident incident = optionalIncident.get();
//        Comments fetchedComment = optionalComment.get();
//        comment.setUser(user);
//        comment.setIncident(incident);

        List<Comments> fetchedComment = incident.getComments();
        fetchedComment.get(0).setCommentText(comment.getCommentText());
        if(comment.getCommentText()!=null){
//            fetchedComment.setCommentText(comment.getCommentText());
            fetchedComment.get(0).setCommentText(comment.getCommentText());
        }
//        commentsRepository.save(fetchedComment);
        incidentRepository.save(incident);
        return ResponseEntity.ok("comment updated");
    }

}