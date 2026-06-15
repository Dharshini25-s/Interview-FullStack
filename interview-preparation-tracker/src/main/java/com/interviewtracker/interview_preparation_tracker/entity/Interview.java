package com.interviewtracker.interview_preparation_tracker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name = "interviews")
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int interviewId;
    private String companyName;
    private String role;
    private String interviewDate;
    private int roundsCount;
    private String questionsAsked;
    private String difficulty;
    private String result;
    private String preparationStatus;
    private String reminderNote;
    private String status;

    public int getInterviewId() { return interviewId; }
    public void setInterviewId(int interviewId) { this.interviewId = interviewId; }
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getInterviewDate() { return interviewDate; }
    public void setInterviewDate(String interviewDate) { this.interviewDate = interviewDate; }
    public int getRoundsCount() { return roundsCount; }
    public void setRoundsCount(int roundsCount) { this.roundsCount = roundsCount; }
    public String getQuestionsAsked() { return questionsAsked; }
    public void setQuestionsAsked(String questionsAsked) { this.questionsAsked = questionsAsked; }
    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }
    public String getResult() { return result; }
    public void setResult(String result) { this.result = result; }
    public String getPreparationStatus() { return preparationStatus; }
    public void setPreparationStatus(String preparationStatus) { this.preparationStatus = preparationStatus; }
    public String getReminderNote() { return reminderNote; }
    public void setReminderNote(String reminderNote) { this.reminderNote = reminderNote; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
