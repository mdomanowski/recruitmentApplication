package com.recruitment.forexbuddy.controller;

import com.recruitment.forexbuddy.model.dto.response.CurrencyExchangeResponseDto;
import com.recruitment.forexbuddy.service.NBPApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/exchangerates")
public class NBPApiController {

    private final NBPApiService NBPApiService;

    @GetMapping("/table")
    public ResponseEntity<?> getDetailedRates() {
        return ResponseEntity.ok(NBPApiService.getDetailedRates());
    }

    @GetMapping("/{from}/{to}/{amount}")
    public ResponseEntity<?> getExchangeRateForCurrencyPair(@PathVariable("from") String from,
                                             @PathVariable("to") String to,
                                             @PathVariable("amount") double amount) {
        CurrencyExchangeResponseDto dto = NBPApiService.getCurrentExchangeRate(from, to, amount);
        return ResponseEntity.ok(dto);
    }
    @GetMapping("/currencies")
    public ResponseEntity<?> getAvailableCurrenciesToExchange() {
        return ResponseEntity.ok(NBPApiService.getCurrenciesAvailableToExchange());
    }
}
