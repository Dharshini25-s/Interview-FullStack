package com.interviewtracker.interview_preparation_tracker.repository;

import com.interviewtracker.interview_preparation_tracker.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewRepository extends JpaRepository<Interview, Integer>
{
}
