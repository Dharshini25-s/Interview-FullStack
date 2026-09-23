package com.interviewtracker.interview_preparation_tracker.controller;
import com.interviewtracker.interview_preparation_tracker.entity.Problem;
import com.interviewtracker.interview_preparation_tracker.entity.SuggestVideo;
import com.interviewtracker.interview_preparation_tracker.entity.YoutubeSearchResult;
import com.interviewtracker.interview_preparation_tracker.entity.user;
import com.interviewtracker.interview_preparation_tracker.service.GroqService;
import com.interviewtracker.interview_preparation_tracker.service.ProblemService;
import com.interviewtracker.interview_preparation_tracker.service.YoutubeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    @GetMapping("/viewproblem/{id}")
    public Problem viewproblembyid(@PathVariable int id) {
        return problemService.viewproblembyid(id);
    }
    @Autowired
    YoutubeService youtubeService;

    @Autowired
    GroqService groqService;

    @GetMapping("/suggestvideos/{id}")
    public List<SuggestVideo> suggestVideos(@PathVariable int id) {
        Problem problem = problemService.viewproblembyid(id);
        if (problem == null) {
            return new ArrayList<>();
        }

        String query = problem.getProblemName() + " " + problem.getAlgorithm() + " coding interview solution";
        List<YoutubeSearchResult> candidates = youtubeService.search(query);

        return groqService.pickBestChannels(candidates, problem.getProblemName(), problem.getAlgorithm());
    }
}
