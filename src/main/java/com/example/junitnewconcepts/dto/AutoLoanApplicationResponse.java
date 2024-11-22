package com.example.junitnewconcepts.dto;

import java.util.List;

public class AutoLoanApplicationResponse {
    private List<AutoLoanApplicant> applicants;

    public List<AutoLoanApplicant> getApplicants() {
        return applicants;
    }

    public void setApplicants(List<AutoLoanApplicant> applicants) {
        this.applicants = applicants;
    }
}
