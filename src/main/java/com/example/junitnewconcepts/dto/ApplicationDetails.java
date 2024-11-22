package com.example.junitnewconcepts.dto;

public class ApplicationDetails {
    private String name;

    public ApplicationDetails(AutoLoanApplicant applicant) {
        this.name = applicant.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
