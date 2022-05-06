package com.recruitment.forexbuddy.service;

import com.recruitment.forexbuddy.model.RequestType;
import com.recruitment.forexbuddy.model.entity.HistoryLogEntity;
import com.recruitment.forexbuddy.repository.LogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LoggingService {

    private final LogRepository logRepository;

    public LoggingService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public HistoryLogEntity logRequestToDatabase(RequestType requestType) {
        HistoryLogEntity entity = HistoryLogEntity.builder()
                .requestType(requestType)
                .date(LocalDateTime.now())
                .build();
        return logRepository.saveAndFlush(entity);
    }

    public HistoryLogEntity logRequestToDatabase(RequestType requestType, String from, String to, double amount) {
        HistoryLogEntity entity = HistoryLogEntity.builder()
                .requestType(requestType)
                .amount(amount)
                .currencyFrom(from)
                .currencyTo(to)
                .date(LocalDateTime.now())
                .build();
        return logRepository.saveAndFlush(entity);
    }
}
