package com.example.junitnewconcepts;

import com.example.junitnewconcepts.dto.ApplicationDetails;
import com.example.junitnewconcepts.dto.AutoLoanApplicant;
import com.example.junitnewconcepts.dto.AutoLoanApplicationResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest  // Loads the full Spring context
public class ApplicationServiceTest {

    @Value("${api.gateway.base.url.applications.search.appId}")
    private String searchApplicationUrl;

    @MockBean
    private RestTemplate oAuthresTemplate;  // Mocking RestTemplate for the test

    @InjectMocks
    private ApplicationService applicationService;  // Spring will inject dependencies

    @Test
    public void testGetApplicationDetails() {
        // Ensure the @Value is properly injected
        assertNotNull(searchApplicationUrl);
        assertEquals("http://example.com/api/v1/applications/search", searchApplicationUrl);

        // Mock behavior of RestTemplate
        AutoLoanApplicationResponse mockResponse = new AutoLoanApplicationResponse();
        AutoLoanApplicant applicant = new AutoLoanApplicant("java-hunk");
        applicant.setName("John Doe");
        mockResponse.setApplicants(List.of(applicant));

        ResponseEntity<AutoLoanApplicationResponse> mockResponseEntity = ResponseEntity.ok(mockResponse);
        when(oAuthresTemplate.exchange(any(), any(), any(HttpEntity.class), eq(AutoLoanApplicationResponse.class)))
                .thenReturn(mockResponseEntity);

        // Call the method under test
        List<ApplicationDetails> details = applicationService.getApplicationDetails("sample-app-id");

        // Validate the result
        assertNotNull(details);
        assertEquals(1, details.size());
        assertEquals("John Doe", details.get(0).getName());
    }
}
