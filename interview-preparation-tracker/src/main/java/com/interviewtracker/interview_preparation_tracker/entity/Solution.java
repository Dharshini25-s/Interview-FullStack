package com.interviewtracker.interview_preparation_tracker.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "solutions")
public class Solution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private CodeLanguage language;

    @Lob
    private String code;

    @ManyToOne
    @JoinColumn(name = "problemno")
    private Problem problem;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CodeLanguage getLanguage() {
        return language;
    }

    public void setLanguage(CodeLanguage language) {
        this.language = language;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }
}