package com.learning.demo.repositories;

import com.learning.demo.entities.Incident;
import com.learning.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Integer> {
    Optional<Incident> findByIncNumber(String incNumber);
    void deleteByIncNumber(String incNumber);
//    List<Incident> findAllById(int userId);
//    List<Incident> findAllByUser(User user);
}

