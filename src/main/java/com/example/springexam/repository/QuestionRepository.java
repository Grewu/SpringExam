package com.example.springexam.repository;

import com.example.springexam.model.entity.Question;
import com.example.springexam.model.entity.enums.TopicType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    boolean existsByQuestionText(String questionText);

    @Query("SELECT q FROM Question q WHERE q.topic.name IN :topicTypes")
    List<Question> findByTopicTypes(@Param("topicTypes") Set<TopicType> topicTypes);
}