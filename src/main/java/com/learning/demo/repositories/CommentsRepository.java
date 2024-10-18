package com.learning.demo.repositories;

import com.learning.demo.entities.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Integer> {

}

