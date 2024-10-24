package com.learning.demo.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments_table")
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentID;

    @ManyToOne
    @JoinColumn(name = "inc_id", nullable = false)
    private Incident incident;

    @ManyToOne
    @JoinColumn(name ="user_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 500)
    private String commentText;

    @CreationTimestamp
    @Column(nullable = false, updatable=false)
    private LocalDateTime commentedAt;

    public Comments() {
        super();
    }

    public Integer getCommentID() {
        return commentID;
    }

    public void setCommentID(Integer commentID) {
        this.commentID = commentID;
    }

//    public Incident getIncident() {
//        return incident;
//    }

    public void setIncident(Incident incident) {
        this.incident = incident;
    }

//    public User getUser() {
//        return user;
//    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public LocalDateTime getCommentedAt() {
        return commentedAt;
    }

    public void setCommentedAt(LocalDateTime commentedAt) {
        this.commentedAt = commentedAt;
    }
}