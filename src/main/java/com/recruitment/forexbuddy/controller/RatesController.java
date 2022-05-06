package com.recruitment.forexbuddy.controller;

import com.recruitment.forexbuddy.service.ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RatesController {

    private final ApiService apiService;

    @GetMapping("/rates")
    public ResponseEntity<?> getAllDetailedRates() {
        return ResponseEntity.ok(apiService.getDetailedRates());
    }
}
