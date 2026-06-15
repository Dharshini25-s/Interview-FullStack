package com.interviewtracker.interview_preparation_tracker.entity;

import jakarta.persistence.*;

@Entity
@Table(name="problems")
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int problemno;
    private String problemName;
    private String level;
    private Boolean itInterview;
    private int time;
    private String algorithm;

    public int getProblemno() {
        return problemno;
    }

    public void setProblemno(int problemno) {
        this.problemno = problemno;
    }

    public String getProblemName() {
        return problemName;
    }

    public void setProbelmName(String probelmName) {
        this.problemName = probelmName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Boolean getItInterview() {
        return itInterview;
    }

    public void setItInterview(Boolean itInterview) {
        this.itInterview = itInterview;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }
}
