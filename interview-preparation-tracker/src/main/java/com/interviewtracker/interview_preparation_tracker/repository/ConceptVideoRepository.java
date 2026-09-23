package com.interviewtracker.interview_preparation_tracker.repository;

import com.interviewtracker.interview_preparation_tracker.entity.ConceptVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ConceptVideoRepository extends JpaRepository<ConceptVideo, Integer> {
    List<ConceptVideo> findByCategoryAndTopic(String category, String topic);
}