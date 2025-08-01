package com.example.springexam.repository;

import com.example.springexam.model.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findAllByQuestionId(Long questionId);

    @Query("SELECT a FROM Answer a WHERE a.question.id = :questionId AND a.isCorrect = true")
    Set<Answer> findCorrectAnswersByQuestionId(Long questionId);

    @Query("SELECT a.question.id, a.id FROM Answer a WHERE a.isCorrect = true AND a.question.id IN :questionIds")
    List<Object[]> findCorrectAnswersByQuestionIds(Set<Long> questionIds);
}