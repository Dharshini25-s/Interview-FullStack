package com.interviewtracker.interview_preparation_tracker.controller;
import com.interviewtracker.interview_preparation_tracker.entity.Problem;
import com.interviewtracker.interview_preparation_tracker.entity.user;
import com.interviewtracker.interview_preparation_tracker.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
public class ProblemController {
    @Autowired
    ProblemService problemService;
    @PostMapping("/addproblem")
    public Problem addProblem (@RequestBody Problem p) {
        return problemService.Addproblem(p);
    }

    @GetMapping("/viewproblem")
    public List<Problem> viewproblem () {
        return problemService.viewproblem();
    }
}
