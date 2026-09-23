package com.interviewtracker.interview_preparation_tracker.controller;

import com.interviewtracker.interview_preparation_tracker.entity.*;
import com.interviewtracker.interview_preparation_tracker.repository.ConceptVideoRepository;
import com.interviewtracker.interview_preparation_tracker.repository.NoteRepository;
import com.interviewtracker.interview_preparation_tracker.repository.ProblemRepository;
import com.interviewtracker.interview_preparation_tracker.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/videos")
@CrossOrigin(origins = "http://localhost:3000")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private ProblemRepository problemRepository;
    @Autowired
    private ConceptVideoRepository conceptVideoRepository;

    // "Suggest videos for me" results
    @GetMapping("/suggest")
    public List<VideoSuggestion> suggest(@RequestParam String problemName,
                                         @RequestParam(defaultValue = "C") String codeLang,
                                         @RequestParam(defaultValue = "ENGLISH") String noteLang) {
        return videoService.suggestVideos(problemName, codeLang, noteLang);
    }

    // "Own" flow step A — find the channel
    @GetMapping("/channel/search")
    public ChannelResult searchChannel(@RequestParam String channelName) {
        return videoService.searchChannel(channelName);
    }

    // "Own" flow step B — videos from that channel matching the problem
    @GetMapping("/channel/videos")
    public List<VideoSuggestion> channelVideos(@RequestParam String channelId,
                                               @RequestParam String problemName) {
        return videoService.channelVideos(channelId, problemName);
    }
    @GetMapping("/concept")
    public List<ConceptVideo> getConceptVideos(@RequestParam String category, @RequestParam String topic) {
        return conceptVideoRepository.findByCategoryAndTopic(category, topic);
    }
    @PostMapping("/concept/save")
    public ConceptVideo saveConceptVideo(@RequestParam String category,
                                         @RequestParam String topic,
                                         @RequestParam String videoUrl,
                                         @RequestParam String channelName,
                                         @RequestParam VideoSource source) {
        ConceptVideo cv = new ConceptVideo();
        cv.setCategory(category);
        cv.setTopic(topic);
        cv.setVideoUrl(videoUrl);
        cv.setChannelName(channelName);
        cv.setVideoSource(source);
        return conceptVideoRepository.save(cv);
    }
    // Save the user's chosen video (works for both OWN and SUGGESTED)
    @PostMapping("/{problemno}/save")
    public Note saveVideo(@PathVariable int problemno,
                          @RequestParam String videoId,
                          @RequestParam String channelName,
                          @RequestParam VideoSource source,
                          @RequestParam NoteLanguage language) {

        Note note = new Note();
        note.setVideoUrl("https://youtube.com/watch?v=" + videoId);
        note.setChannelName(channelName);
        note.setVideoSource(source);
        note.setLanguage(language);
        note.setProblem(problemRepository.findById(problemno).orElseThrow());

        return noteRepository.save(note);
    }
}