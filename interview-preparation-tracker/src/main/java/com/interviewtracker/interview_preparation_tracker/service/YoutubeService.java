package com.interviewtracker.interview_preparation_tracker.service;

import com.interviewtracker.interview_preparation_tracker.entity.YoutubeSearchResult;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class YoutubeService {

    @Value("${youtube.api.key}")
    private String youtubeApiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<YoutubeSearchResult> search(String query) {
        List<YoutubeSearchResult> results = new ArrayList<>();

        String url = "https://www.googleapis.com/youtube/v3/search"
                + "?part=snippet"
                + "&type=video"
                + "&maxResults=10"
                + "&q=" + query.replace(" ", "+")
                + "&key=" + youtubeApiKey;

        try {
            String response = restTemplate.getForObject(url, String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode items = mapper.readTree(response).get("items");

            if (items != null) {
                for (JsonNode item : items) {
                    JsonNode snippet = item.get("snippet");
                    String videoId = item.get("id").get("videoId").asText();

                    YoutubeSearchResult candidate = new YoutubeSearchResult();
                    candidate.setVideoId(videoId);
                    candidate.setTitle(snippet.get("title").asText());
                    candidate.setChannelTitle(snippet.get("channelTitle").asText());
                    candidate.setDescription(snippet.get("description").asText());
                    candidate.setVideoUrl("https://www.youtube.com/watch?v=" + videoId);

                    results.add(candidate);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }
}