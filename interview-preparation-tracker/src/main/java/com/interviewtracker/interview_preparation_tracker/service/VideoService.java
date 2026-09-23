package com.interviewtracker.interview_preparation_tracker.service;

import tools.jackson.databind.JsonNode;
import com.interviewtracker.interview_preparation_tracker.entity.ChannelResult;
import com.interviewtracker.interview_preparation_tracker.entity.VideoSuggestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class VideoService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${youtube.api.key}")
    private String apiKey;

    // Used by "Suggest videos for me" — searches across all of YouTube,
    // then ranks by views + engagement (likes/comments per view) so the
    // results are popular AND well-regarded by viewers, not just high view count.
    public List<VideoSuggestion> suggestVideos(String problemName, String codeLang, String noteLang) {
        String relevanceLanguage = "TAMIL".equalsIgnoreCase(noteLang) ? "ta" : "en";

        // Step 1: search for a wider pool of candidates
        String searchUrl = "https://www.googleapis.com/youtube/v3/search"
                + "?part=snippet&type=video&maxResults=10"
                + "&order=viewCount"
                + "&relevanceLanguage=" + relevanceLanguage
                + "&q=" + URLEncoder.encode(problemName + " " + codeLang + " explanation", StandardCharsets.UTF_8)
                + "&key=" + apiKey;

        JsonNode searchResponse = restTemplate.getForObject(searchUrl, JsonNode.class);

        StringBuilder idList = new StringBuilder();
        for (JsonNode item : searchResponse.get("items")) {
            if (idList.length() > 0) idList.append(",");
            idList.append(item.get("id").get("videoId").asText());
        }
        if (idList.length() == 0) return new ArrayList<>();

        // Step 2: fetch real stats (views, likes, comments) for those candidates
        String statsUrl = "https://www.googleapis.com/youtube/v3/videos"
                + "?part=snippet,statistics"
                + "&id=" + idList
                + "&key=" + apiKey;

        JsonNode statsResponse = restTemplate.getForObject(statsUrl, JsonNode.class);

        List<VideoSuggestion> results = new ArrayList<>();
        List<Double> scores = new ArrayList<>();

        for (JsonNode item : statsResponse.get("items")) {
            long views = item.get("statistics").has("viewCount")
                    ? item.get("statistics").get("viewCount").asLong() : 0;
            long likes = item.get("statistics").has("likeCount")
                    ? item.get("statistics").get("likeCount").asLong() : 0;
            long comments = item.get("statistics").has("commentCount")
                    ? item.get("statistics").get("commentCount").asLong() : 0;

            if (views < 500) continue; // skip near-zero-view junk

            // engagement ratio = how much people liked/commented relative to views
            double engagementRatio = (likes + comments * 2.0) / views;
            // score blends raw popularity with engagement quality
            double score = Math.log10(views + 1) * 2 + engagementRatio * 1000;

            VideoSuggestion v = new VideoSuggestion();
            v.setVideoId(item.get("id").asText());
            v.setTitle(item.get("snippet").get("title").asText());
            v.setChannelTitle(item.get("snippet").get("channelTitle").asText());
            v.setThumbnailUrl(item.get("snippet").get("thumbnails").get("medium").get("url").asText());

            results.add(v);
            scores.add(score);
        }

        // sort results by score, descending
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) indices.add(i);
        indices.sort((a, b) -> Double.compare(scores.get(b), scores.get(a)));

        List<VideoSuggestion> sorted = new ArrayList<>();
        for (int i = 0; i < Math.min(3, indices.size()); i++) {
            sorted.add(results.get(indices.get(i)));
        }
        return sorted;
    }

    // Step A of "Own" flow — find the channel the user typed
    public ChannelResult searchChannel(String channelName) {
        String url = "https://www.googleapis.com/youtube/v3/search"
                + "?part=snippet&type=channel&maxResults=1"
                + "&q=" + URLEncoder.encode(channelName, StandardCharsets.UTF_8)
                + "&key=" + apiKey;

        JsonNode response = restTemplate.getForObject(url, JsonNode.class);
        JsonNode item = response.get("items").get(0);

        ChannelResult result = new ChannelResult();
        result.setChannelId(item.get("id").get("channelId").asText());
        result.setChannelTitle(item.get("snippet").get("channelTitle").asText());
        result.setThumbnailUrl(item.get("snippet").get("thumbnails").get("medium").get("url").asText());
        return result;
    }

    // Step B of "Own" flow — find videos only from that specific channel,
    // filtered so the title actually relates to the problem name
    public List<VideoSuggestion> channelVideos(String channelId, String problemName) {
        String url = "https://www.googleapis.com/youtube/v3/search"
                + "?part=snippet&type=video&maxResults=10"
                + "&channelId=" + channelId
                + "&q=" + URLEncoder.encode(problemName, StandardCharsets.UTF_8)
                + "&key=" + apiKey;

        JsonNode response = restTemplate.getForObject(url, JsonNode.class);
        List<VideoSuggestion> all = parseVideoResults(response);

        // keep only videos whose title actually contains the problem's keywords
        String[] keywords = problemName.toLowerCase().split("\\s+");
        List<VideoSuggestion> matched = new ArrayList<>();
        for (VideoSuggestion v : all) {
            String titleLower = v.getTitle().toLowerCase();
            int matchCount = 0;
            for (String kw : keywords) {
                if (kw.length() > 2 && titleLower.contains(kw)) matchCount++;
            }
            // require at least half the keywords to appear in the title
            if (matchCount >= Math.max(1, keywords.length / 2)) {
                matched.add(v);
            }
        }

        // if filtering removed everything, fall back to the raw search results
        return matched.isEmpty() ? all : matched;
    }

    private List<VideoSuggestion> parseVideoResults(JsonNode response) {
        List<VideoSuggestion> results = new ArrayList<>();
        for (JsonNode item : response.get("items")) {
            VideoSuggestion v = new VideoSuggestion();
            v.setVideoId(item.get("id").get("videoId").asText());
            v.setTitle(item.get("snippet").get("title").asText());
            v.setChannelTitle(item.get("snippet").get("channelTitle").asText());
            v.setThumbnailUrl(item.get("snippet").get("thumbnails").get("medium").get("url").asText());
            results.add(v);
        }
        return results;
    }
}