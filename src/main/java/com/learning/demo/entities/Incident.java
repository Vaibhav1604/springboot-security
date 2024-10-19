package com.learning.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(nullable = false)
    private String incSubject;

    @Column
    private String incDescription;

    @Enumerated(EnumType.STRING)
    private Status status;

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
