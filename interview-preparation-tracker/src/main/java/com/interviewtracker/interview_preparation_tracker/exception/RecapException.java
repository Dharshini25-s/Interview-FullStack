package com.interviewtracker.interview_preparation_tracker.exception;

import org.springframework.http.HttpStatus;

public class RecapException extends RuntimeException {

    private final HttpStatus status;

    public RecapException(HttpStatus status, String userMessage) {
        super(userMessage);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}