package com.learning.demo.repositories;

import com.learning.demo.entities.Attachments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentsRepository extends JpaRepository<Attachments, Integer> {
}
