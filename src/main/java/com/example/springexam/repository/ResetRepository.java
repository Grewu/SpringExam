package com.example.springexam.repository;

import com.example.springexam.model.entity.Question;
import com.example.springexam.model.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ResetRepository extends JpaRepository<Topic, Long> {

    @Modifying
    @Query(value = "DELETE FROM answers", nativeQuery = true)
    void clearAnswers();

    @Modifying
    @Query(value = "DELETE FROM explanations", nativeQuery = true)
    void clearExplanations();

    @Modifying
    @Query(value = "DELETE FROM questions", nativeQuery = true)
    void clearQuestions();

    @Modifying
    @Query(value = "DELETE FROM topics", nativeQuery = true)
    void clearTopics();
}