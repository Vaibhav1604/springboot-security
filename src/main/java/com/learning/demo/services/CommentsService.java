package com.learning.demo.services;

import com.learning.demo.entities.Comments;
import com.learning.demo.entities.Role;
import com.learning.demo.entities.User;
import com.learning.demo.repositories.CommentsRepository;
import com.learning.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentsService {

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Comments> getAllComments() {
        List<Comments> comm = new ArrayList<>();
        commentsRepository.findAll().forEach(comm::add);
        return comm;
    }

    public ResponseEntity<String> addComments(Comments comment) {
        User user1 = comment.getUser();
        User fetchedUser = userRepository.findById(user1.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        comment.setUser(fetchedUser);
        String username = fetchedUser.getUsername();
        Role userRole = fetchedUser.getRole();

//	    System.out.println("Username: " + username);
//	    System.out.println("User Role: " + userRole);
        if (userRole != Role.ADMIN) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not allowed to add comment.");
        } else {
            commentsRepository.save(comment);
            return ResponseEntity.ok("Comment added successfully");
        }
    }


    public ResponseEntity<String> removeComments(int id) {

        Optional<Comments> comment = commentsRepository.findById(id);

        if(!(comment == null)) {
            commentsRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Delete the element");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found this element");
        }
    }

    public ResponseEntity<String> updateComment(int id, Comments comment) {
        Optional<Comments> optionalComment = commentsRepository.findById(id);

        if (!optionalComment.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comment not Found");
        }

        Comments toBeUpdatedComment = optionalComment.get();
        User user = comment.getUser(); // Assuming there's a User entity
        String username = (user != null) ? user.getUsername() : null;
        Role userRole = (user != null) ? user.getRole() : null;

        System.out.println(user.toString());

        if (username == null || userRole == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User or role not found in the request");
        }

//	    System.out.println("Fetching username: " + username);
//	    System.out.println("Fetching user role: " + userRole);


        Role desiredRole = Role.ADMIN;

        if (userRole.equals(desiredRole)) {
            System.out.println("Checking whether they're equal: " + userRole.equals(desiredRole));

            System.out.println("Username before saving in table: " + username);
            System.out.println("User role before saving in table: " + userRole);
            System.out.println("Desired role before saving in table: " + desiredRole);

            if (comment.getCommentText() != null) {
                toBeUpdatedComment.setCommentText(comment.getCommentText());
            }
            if (comment.getIncident() != null) {
                toBeUpdatedComment.setIncident(comment.getIncident());
            }
            if (user != null) {
                toBeUpdatedComment.setUser(user);
            }

            try {
                commentsRepository.save(toBeUpdatedComment);
                System.out.println("Username after saving in table: " + username);
                System.out.println("User role after saving in table: " + userRole);
                return ResponseEntity.ok("Comment updated successfully");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error-- Try Again");
            }
        } else {
            System.out.println("Username: " + username);
            System.out.println("User Role: " + userRole);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Employees not allowed here");
        }
    }

}



//	public List<String> getAllComments() {
//		List<Comments> comm = new ArrayList<>();
//		commentsRepository.findAll().forEach(comm::add);
//		List<String> lis1 = new ArrayList<>();
//
//		for(Comments c : comm) {
//			lis1.add(c.getCommentText());
//		}
//		return lis1;
//	}