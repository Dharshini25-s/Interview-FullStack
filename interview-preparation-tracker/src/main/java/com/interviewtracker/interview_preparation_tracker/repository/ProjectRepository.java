package com.interviewtracker.interview_preparation_tracker.repository;
import com.interviewtracker.interview_preparation_tracker.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
