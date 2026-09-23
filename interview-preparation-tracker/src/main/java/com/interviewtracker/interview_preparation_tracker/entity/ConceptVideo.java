package com.interviewtracker.interview_preparation_tracker.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "concept_videos")
public class ConceptVideo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String category;   // DSA, FRONTEND, BACKEND, AI_ML, DATABASE
    private String topic;      // user-typed, e.g. "Linked List"
    private String videoUrl;
    private String channelName;

    @Enumerated(EnumType.STRING)
    private VideoSource videoSource; // OWN or SUGGESTED

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }
    public String getVideoUrl() { return videoUrl; }
    public void setVideoUrl(String videoUrl) { this.videoUrl = videoUrl; }
    public String getChannelName() { return channelName; }
    public void setChannelName(String channelName) { this.channelName = channelName; }
    public VideoSource getVideoSource() { return videoSource; }
    public void setVideoSource(VideoSource videoSource) { this.videoSource = videoSource; }
}