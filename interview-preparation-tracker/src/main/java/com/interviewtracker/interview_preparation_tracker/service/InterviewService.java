package com.interviewtracker.interview_preparation_tracker.service;

import com.interviewtracker.interview_preparation_tracker.entity.Interview;
import com.interviewtracker.interview_preparation_tracker.entity.Project;
import com.interviewtracker.interview_preparation_tracker.repository.InterviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterviewService {
    @Autowired
    InterviewRepository interviewRepository;

    public Interview AddInterview(Interview I) {
        return interviewRepository.save(I);
    }

    public List<Interview> viewinterview() {
        return interviewRepository.findAll();
    }

    public Interview UpdateInterview(Interview I) {
        return interviewRepository.save(I);
    }

    public void DeleteInterview(int id) {
        interviewRepository.deleteById(id);
    }
}
