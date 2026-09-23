package com.interviewtracker.interview_preparation_tracker.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private NoteLanguage language; // ENGLISH or TAMIL

    @Lob
    private String content;

    private String videoUrl;

    private String channelName;

    @Enumerated(EnumType.STRING)
    private VideoSource videoSource; // OWN or SUGGESTED

    @ManyToOne
    @JoinColumn(name = "problemno")
    private Problem problem;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public NoteLanguage getLanguage() {
        return language;
    }

    public void setLanguage(NoteLanguage language) {
        this.language = language;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public VideoSource getVideoSource() {
        return videoSource;
    }

    public void setVideoSource(VideoSource videoSource) {
        this.videoSource = videoSource;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }
}