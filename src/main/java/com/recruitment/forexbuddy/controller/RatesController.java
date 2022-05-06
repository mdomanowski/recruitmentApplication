package com.recruitment.forexbuddy.controller;

import com.recruitment.forexbuddy.model.dto.ExchangeResponseDto;
import com.recruitment.forexbuddy.service.ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.time.temporal.ChronoUnit;

@RestController
@RequiredArgsConstructor
public class RatesController {

    private final ApiService apiService;

    @GetMapping("/rates")
    public ResponseEntity<?> getAllDetailedRates() {
        return ResponseEntity.ok(apiService.getDetailedRates());
    }

    @GetMapping("/rate/{from}/{to}/{amount}")
    public ResponseEntity<?> getExchangeRate(@PathVariable("from") String from,
                                             @PathVariable("to") String to,
                                             @PathVariable("amount") double amount) {
        ExchangeResponseDto dto = apiService.getCurrentExchangeRate(from, to, amount);
        final DecimalFormat df = new DecimalFormat("0.000");
        String response =
                            "Obecna data: " + dto.getDate().toLocalDate() + "\nGodzina: " + dto.getDate().toLocalTime().truncatedTo(ChronoUnit.MINUTES) + "\n"
                          + "Przeliczenie z " + dto.getFrom() + " na " + dto.getTo() + "\n"
                          + "Ilość jednostek do przeliczenia: " + dto.getAmount() + "\n"
                          + "Po kursie : \n bid: " + dto.getBid() + "\n ask: " + dto.getAsk() + "\n"
                          + "\nZa " + dto.getAmount() + " " + dto.getFrom() + " jest możliwe kupno " + df.format(dto.getBidValue()) + " " + dto.getTo();
        return ResponseEntity.ok(response);
    }
}
