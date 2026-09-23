package com.interviewtracker.interview_preparation_tracker.repository;

import com.interviewtracker.interview_preparation_tracker.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Integer> {
    List<Note> findByProblem_Problemno(int problemno);
}