package com.interviewtracker.interview_preparation_tracker.service;

import com.interviewtracker.interview_preparation_tracker.entity.Problem;
import com.interviewtracker.interview_preparation_tracker.entity.Project;
import com.interviewtracker.interview_preparation_tracker.repository.ProblemRepository;
import com.interviewtracker.interview_preparation_tracker.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProjectService {
    @Autowired
    ProjectRepository projectRepository;

    public Project Addproject(Project p) {
        return projectRepository.save(p);
    }
    public List<Project> viewproject() {
        return projectRepository.findAll();
    }
    public Project UpdateProject(Project p)
    {
        return projectRepository.save(p);
    }
    public void Deleteproject(int id) {
         projectRepository.deleteById(id);
    }
}
