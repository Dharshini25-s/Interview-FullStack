package com.interviewtracker.interview_preparation_tracker.repository;


import com.interviewtracker.interview_preparation_tracker.entity.Problem;
import com.interviewtracker.interview_preparation_tracker.entity.user;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProblemRepository extends JpaRepository<Problem, Integer> {


}
