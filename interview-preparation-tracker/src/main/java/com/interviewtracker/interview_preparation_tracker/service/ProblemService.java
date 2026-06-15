package com.interviewtracker.interview_preparation_tracker.service;
import java.util.List;
import com.interviewtracker.interview_preparation_tracker.entity.Problem;
import com.interviewtracker.interview_preparation_tracker.repository.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProblemService {
    @Autowired
    ProblemRepository problemRepository;
    public Problem Addproblem (Problem p)
    {
        return problemRepository.save(p);
    }
    public List <Problem> viewproblem ()
    {
        return problemRepository.findAll();
    }

}
