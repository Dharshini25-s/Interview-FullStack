package com.interviewtracker.interview_preparation_tracker.repository;

import com.interviewtracker.interview_preparation_tracker.entity.user;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<user, Integer>
{

        Optional<user> findByEmail(String email);
}
