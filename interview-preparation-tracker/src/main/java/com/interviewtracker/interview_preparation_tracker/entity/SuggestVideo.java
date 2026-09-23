package com.interviewtracker.interview_preparation_tracker.entity;

public class SuggestVideo {
    private String channelName;
    private String videoTitle;
    private String videoUrl;
    private String reason;

    public String getChannelName() { return channelName; }
    public void setChannelName(String channelName) { this.channelName = channelName; }

    public String getVideoTitle() { return videoTitle; }
    public void setVideoTitle(String videoTitle) { this.videoTitle = videoTitle; }

    public String getVideoUrl() { return videoUrl; }
    public void setVideoUrl(String videoUrl) { this.videoUrl = videoUrl; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}