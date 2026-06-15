package com.interviewtracker.interview_preparation_tracker.entity;

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
    private String notes;
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
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}

