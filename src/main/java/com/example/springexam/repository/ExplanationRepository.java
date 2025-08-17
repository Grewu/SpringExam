package com.example.springexam.repository;

import com.example.springexam.model.entity.Explanation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExplanationRepository extends JpaRepository<Explanation, Long> {
    Explanation findByQuestionId(Long id);
}
