package com.interviewtracker.interview_preparation_tracker.service;

import com.interviewtracker.interview_preparation_tracker.entity.user;
import com.interviewtracker.interview_preparation_tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepo;
    public user loginUser(String email, String password) {
        Optional<user> foundUser = userRepo.findByEmail(email);
        if(foundUser.isPresent()) {
            user u = foundUser.get();
            if (u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }
    public user registerUser(user u) {
        Optional<user> existingUser = userRepo.findByEmail(u.getEmail());

        if(existingUser.isPresent()) {
            return null;
        }
        return userRepo.save(u);
    }
    public user updateUser(int id, user u) {
        u.setId(id);
        return userRepo.save(u);
    }
}
