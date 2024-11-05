package com.learning.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

//@Data
//@Builder
@Entity
@Table(name = "incidents_table")
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer incId;

    @Column(unique = true)
    private String incNumber;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "incident", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comments> comments;

    @OneToMany(mappedBy = "incident", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attachments> attachments;

    @Column(nullable = false)
    private String incSubject;

    @Column
    private String incDescription;

    @Enumerated(EnumType.STRING)
    private Status status;

    @CreationTimestamp
    @Column(nullable = false, updatable=false)
    private LocalDateTime createdAt;

    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    @PostPersist
    public void generateUniqueIncNumbers(){
        this.incNumber = "INC" + String.format("%010d", this.incId);
    }

    public Integer getIncId() {
        return incId;
    }

    public void setIncId(Integer incId) {
        this.incId = incId;
    }

    public String getIncNumber() {
        return incNumber;
    }

    public void setIncNumber(String incNumber) {
        this.incNumber = incNumber;
    }

//    public User getUser() {
//        return user;
//    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    public String getIncSubject() {
        return incSubject;
    }

    public void setIncSubject(String incSubject) {
        this.incSubject = incSubject;
    }

    public String getIncDescription() {
        return incDescription;
    }

    public void setIncDescription(String incDescription) {
        this.incDescription = incDescription;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
