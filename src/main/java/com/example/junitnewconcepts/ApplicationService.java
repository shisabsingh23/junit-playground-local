package com.example.junitnewconcepts;

import com.example.junitnewconcepts.dto.ApplicationDetails;
import com.example.junitnewconcepts.dto.AutoLoanApplicant;
import com.example.junitnewconcepts.dto.AutoLoanApplicationResponse;
import com.example.junitnewconcepts.utils.HelperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationService {

    @Value("${api.gateway.base.url.applications.search.appId}")
    private String searchApplicationUrl;

    @Autowired
    private RestTemplate oAuthresTemplate;


    public List<ApplicationDetails> getApplicationDetails(String applicationId) {
        HttpHeaders headers = HelperUtils.buildHeaders("1.0", "application/json", "my-client-id");
        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<AutoLoanApplicationResponse> responseEntity = null;
        List<ApplicationDetails> details = new ArrayList<>();

        try {
            String appUrl = searchApplicationUrl + "?isTaxIdTokenized=false&appDetail=Y";
            responseEntity = oAuthresTemplate.exchange(appUrl, org.springframework.http.HttpMethod.GET, httpEntity, AutoLoanApplicationResponse.class);

            AutoLoanApplicationResponse applicantResponse = responseEntity.getBody();
            if (applicantResponse != null) {
                for (AutoLoanApplicant applicant : applicantResponse.getApplicants()) {
                    ApplicationDetails detail = new ApplicationDetails(applicant);
                    details.add(detail);
                }
            }
        } catch (HttpClientErrorException e) {
            System.err.println("Error: " + e.getMessage());
        }

        return details;
    }
}
