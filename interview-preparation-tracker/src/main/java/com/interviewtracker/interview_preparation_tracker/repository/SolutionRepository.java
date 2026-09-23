package com.interviewtracker.interview_preparation_tracker.repository;

import com.interviewtracker.interview_preparation_tracker.entity.Solution;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SolutionRepository extends JpaRepository<Solution, Integer> {
    List<Solution> findByProblem_Problemno(int problemno);
}