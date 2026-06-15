package com.interviewtracker.interview_preparation_tracker.controller;
import com.interviewtracker.interview_preparation_tracker.entity.Project;
import com.interviewtracker.interview_preparation_tracker.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
public class ProjectController {
    @Autowired
    ProjectService projectService;
    @PostMapping("/addproject")
    public Project addproject (@RequestBody Project p) {
        return projectService.Addproject(p);
    }
    @GetMapping("/viewproject")
    public List<Project> viewproject () {
        return projectService.viewproject();
    }
    @PostMapping("/updateproject")
    public Project updateproject (@RequestBody Project p) {
        return projectService.UpdateProject(p);
    }
    @DeleteMapping("/deleteproject/{id}")
    public void DeleteProject (@PathVariable int id) {
         projectService.Deleteproject(id);
    }
}
