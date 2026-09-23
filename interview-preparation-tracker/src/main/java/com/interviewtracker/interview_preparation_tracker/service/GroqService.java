package com.interviewtracker.interview_preparation_tracker.service;

import com.interviewtracker.interview_preparation_tracker.entity.YoutubeSearchResult;
import com.interviewtracker.interview_preparation_tracker.entity.SuggestVideo;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroqService {

    @Value("${groq.api.key}")
    private String groqApiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<SuggestVideo> pickBestChannels(List<YoutubeSearchResult> candidates, String problemName, String algorithm) {
        List<SuggestVideo> suggestions = new ArrayList<>();
        if (candidates.isEmpty()) return suggestions;

        StringBuilder candidateListText = new StringBuilder();
        for (int i = 0; i < candidates.size(); i++) {
            YoutubeSearchResult c = candidates.get(i);
            candidateListText.append(i + 1).append(". Channel: ").append(c.getChannelTitle())
                    .append(" | Title: ").append(c.getTitle())
                    .append(" | URL: ").append(c.getVideoUrl())
                    .append("\n");
        }

        String prompt = "A student wants to learn how to solve the coding problem \"" + problemName +
                "\" using the \"" + algorithm + "\" technique.\n\n" +
                "Here is a list of YouTube videos found for this topic:\n" + candidateListText +
                "\nPick the 3 BEST videos/channels from this list for explaining this problem clearly. " +
                "Respond ONLY with valid JSON, no extra text, no markdown, in this exact format:\n" +
                "[{\"channelName\": \"...\", \"videoTitle\": \"...\", \"videoUrl\": \"...\", \"reason\": \"short reason\"}]";

        try {
            ObjectMapper mapper = new ObjectMapper();
            var body = mapper.createObjectNode();
            body.put("model", "llama-3.3-70b-versatile");
            var messages = mapper.createArrayNode();
            var message = mapper.createObjectNode();
            message.put("role", "user");
            message.put("content", prompt);
            messages.add(message);
            body.set("messages", messages);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + groqApiKey);

            HttpEntity<String> request = new HttpEntity<>(body.toString(), headers);
            String response = restTemplate.postForObject(
                    "https://api.groq.com/openai/v1/chat/completions", request, String.class);

            JsonNode root = mapper.readTree(response);
            String content = root.get("choices").get(0).get("message").get("content").asText();
            content = content.replace("```json", "").replace("```", "").trim();

            JsonNode suggestionArray = mapper.readTree(content);
            for (JsonNode node : suggestionArray) {
                SuggestVideo s = new SuggestVideo();
                s.setChannelName(node.get("channelName").asText());
                s.setVideoTitle(node.get("videoTitle").asText());
                s.setVideoUrl(node.get("videoUrl").asText());
                s.setReason(node.get("reason").asText());
                suggestions.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return suggestions;
    }
}