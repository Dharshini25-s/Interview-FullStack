package com.interviewtracker.interview_preparation_tracker.controller;

import com.interviewtracker.interview_preparation_tracker.entity.user;
import com.interviewtracker.interview_preparation_tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class StreakController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/updatestreak/{userId}")
    public Map<String, Object> updateStreak(@PathVariable int userId) {
        Optional<user> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        user u = optionalUser.get();
        LocalDate today = LocalDate.now();
        LocalDate lastActive = u.getLastActivityDate();

        // only touch the streak if this is a new day for this user
        if (lastActive == null || !lastActive.equals(today)) {
            if (lastActive != null && lastActive.equals(today.minusDays(1))) {
                u.setCurrentStreak(u.getCurrentStreak() + 1); // came back the next day
            } else {
                u.setCurrentStreak(1); // missed a day, or first ever visit
            }
            u.setLastActivityDate(today);
            if (u.getCurrentStreak() > u.getLongestStreak()) {
                u.setLongestStreak(u.getCurrentStreak());
            }
            userRepository.save(u);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("currentStreak", u.getCurrentStreak());
        response.put("longestStreak", u.getLongestStreak());
        return response;
    }
}