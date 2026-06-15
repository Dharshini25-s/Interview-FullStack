package com.interviewtracker.interview_preparation_tracker.controller;

import com.interviewtracker.interview_preparation_tracker.entity.user;
import com.interviewtracker.interview_preparation_tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/register")
    public user register(@RequestBody user u) {
        return userService.registerUser(u);
    }

    @PostMapping("/login")
    public user login(@RequestBody user u) {
        return userService.loginUser(u.getEmail(), u.getPassword());
    }
    @PutMapping("/updateuser/{id}")
    public user updateUser(@PathVariable int id, @RequestBody user u) {
        return userService.updateUser(id, u);
    }
}
