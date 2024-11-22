package com.example.junitnewconcepts.utils;

import org.springframework.http.HttpHeaders;

public class HelperUtils {

    public static HttpHeaders buildHeaders(String appJsonVersion, String appJson, String clientId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Client-ID", clientId);
        // Set other headers as needed
        return headers;
    }
}
