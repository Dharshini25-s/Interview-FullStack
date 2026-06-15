package com.interviewtracker.interview_preparation_tracker.controller;

import com.interviewtracker.interview_preparation_tracker.entity.Interview;
import com.interviewtracker.interview_preparation_tracker.entity.Project;
import com.interviewtracker.interview_preparation_tracker.service.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
public class InterviewController {
    @Autowired
    InterviewService interviewService;
    @PostMapping("/addinterview")
    public Interview addinterview (@RequestBody Interview I) {
        return interviewService.AddInterview(I);
    }
    @GetMapping("/viewinterview")
    public List<Interview> viewinterview () {
        return interviewService.viewinterview();
    }
    @PostMapping("/updatainterview")
    public Interview updateinterview (@RequestBody Interview I) {
        return interviewService.UpdateInterview(I);
    }
    @DeleteMapping("/deleteinterview/{id}")
    public void Deleteinterview (@PathVariable int id) {
        interviewService.DeleteInterview(id);
    }
}
