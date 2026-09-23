package com.interviewtracker.interview_preparation_tracker.entity;
import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name="projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projectId;
    private String projectName;
    private String frontendTech;
    private String backendTech;
    private String databaseName;
    private String status;
    private String githubLink;

    @Column(length = 2000)
    private String progress;

    @Column(length = 2000)
    private String whereLagged;

    @Column(length = 2000)
    private String howOvercome;

    @Column(length = 2000)
    private String nextSteps;

    @com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdDate = LocalDate.now();
    public LocalDate getCreatedDate() { return createdDate; }
    public void setCreatedDate(LocalDate createdDate) { this.createdDate = createdDate; }
    public int getProjectId() {
        return projectId;
    }
    public void setProjectId(int projectId)
    {
        this.projectId = projectId;
    }
    public String getProjectName()
    {
        return projectName;
    }
    public void setProjectName(String projectName) { this.projectName = projectName; }
    public String getFrontendTech() { return frontendTech; }
    public void setFrontendTech(String frontendTech) { this.frontendTech = frontendTech; }
    public String getBackendTech() { return backendTech; }
    public void setBackendTech(String backendTech) { this.backendTech = backendTech; }
    public String getDatabaseName() { return databaseName; }
    public void setDatabaseName(String databaseName) { this.databaseName = databaseName; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getGithubLink() { return githubLink; }
    public void setGithubLink(String githubLink) { this.githubLink = githubLink; }

    public String getProgress() { return progress; }
    public void setProgress(String progress) { this.progress = progress; }
    public String getWhereLagged() { return whereLagged; }
    public void setWhereLagged(String whereLagged) { this.whereLagged = whereLagged; }
    public String getHowOvercome() { return howOvercome; }
    public void setHowOvercome(String howOvercome) { this.howOvercome = howOvercome; }
    public String getNextSteps() { return nextSteps; }
    public void setNextSteps(String nextSteps) { this.nextSteps = nextSteps; }
}