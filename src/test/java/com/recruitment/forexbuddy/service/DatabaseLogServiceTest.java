package com.recruitment.forexbuddy.service;

import com.recruitment.forexbuddy.model.dto.response.HistoryLogResponseDto;
import com.recruitment.forexbuddy.model.entity.HistoryLogEntity;
import com.recruitment.forexbuddy.model.enums.RequestType;
import com.recruitment.forexbuddy.repository.LogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class DatabaseLogServiceTest {

    private LogRepository logRepository;
    private DatabaseLogService databaseLogService;
    private NBPApiService nbpApiService;


    @BeforeEach
    public void init() {
        logRepository = mock(LogRepository.class);
        databaseLogService = new DatabaseLogService(logRepository);
        nbpApiService = new NBPApiService(databaseLogService);
    }

    @Test
    void Should_Return_List_With_One_Log_Of_Type_EXCHANGE_When_getCurrentExchangeRate_Was_Called() {
        // given
        RequestType requestType = RequestType.EXCHANGE;
        double amount = 17;
        String currencyFrom = "PLN";
        String currencyTo = "GBP";
        double rate = 5.5139;
        LocalDateTime date = LocalDateTime.now();

        HistoryLogEntity historyLogEntity = HistoryLogEntity.builder()
                .requestType(requestType)
                .amount(amount)
                .currencyFrom(currencyFrom)
                .currencyTo(currencyTo)
                .date(date)
                .rate(rate)
                .build();
        HistoryLogResponseDto historyLogResponseDto = HistoryLogResponseDto.builder()
                .requestType(requestType)
                .amount(amount)
                .currencyFrom(currencyFrom)
                .currencyTo(currencyTo)
                .date(date)
                .rate(rate)
                .build();
        databaseLogService.logRequestToDatabase(requestType, currencyFrom, currencyTo, amount, rate);
        List<HistoryLogEntity> historyLogEntityList = Collections.singletonList(historyLogEntity);

        // when
        when(logRepository.findAll()).thenReturn(historyLogEntityList);
        List<HistoryLogResponseDto> resultLogs = databaseLogService.getAllLogs();

        // then
        assertThat(resultLogs).hasSize(1).contains(historyLogResponseDto);
    }

    @Test
    void Should_Return_List_With_One_Log_Of_Type_CURRENCIES_LIST_When_getCurrentExchangeRate_Was_Called() {
        // given
        RequestType requestType = RequestType.CURRENCIES_LIST;

        LocalDateTime date = LocalDateTime.now();

        HistoryLogEntity historyLogEntity = HistoryLogEntity.builder()
                .requestType(requestType)
                .build();
        HistoryLogResponseDto historyLogResponseDto = HistoryLogResponseDto.builder()
                .requestType(requestType)
                .build();
        databaseLogService.logRequestToDatabase(requestType);
        List<HistoryLogEntity> historyLogEntityList = Collections.singletonList(historyLogEntity);

        // when
        when(logRepository.findAll()).thenReturn(historyLogEntityList);
        List<HistoryLogResponseDto> resultLogs = databaseLogService.getAllLogs();

        // then
        assertThat(resultLogs.get(0).getRequestType()).isEqualTo(RequestType.CURRENCIES_LIST);
        assertThat(resultLogs).hasSize(1).contains(historyLogResponseDto);
    }

    @Test
    void Should_Return_List_With_One_Log_Of_Type_EXCHANGE_RATES_LIST_When_getCurrentExchangeRate_Was_Called() {
        // given
        RequestType requestType = RequestType.EXCHANGE_RATES_LIST;
        double amount = 17;
        String currencyFrom = "PLN";
        String currencyTo = "GBP";
        double rate = 5.5139;
        LocalDateTime date = LocalDateTime.now();

        HistoryLogEntity historyLogEntity = HistoryLogEntity.builder()
                .requestType(requestType)
                .amount(amount)
                .currencyFrom(currencyFrom)
                .currencyTo(currencyTo)
                .date(date)
                .rate(rate)
                .build();
        HistoryLogResponseDto historyLogResponseDto = HistoryLogResponseDto.builder()
                .requestType(requestType)
                .amount(amount)
                .currencyFrom(currencyFrom)
                .currencyTo(currencyTo)
                .date(date)
                .rate(rate)
                .build();
        databaseLogService.logRequestToDatabase(requestType, currencyFrom, currencyTo, amount, rate);
        List<HistoryLogEntity> historyLogEntityList = Collections.singletonList(historyLogEntity);

        // when
        when(logRepository.findAll()).thenReturn(historyLogEntityList);
        List<HistoryLogResponseDto> resultLogs = databaseLogService.getAllLogs();

        // then
        assertThat(resultLogs.get(0).getRequestType()).isEqualTo(RequestType.EXCHANGE_RATES_LIST);
        assertThat(resultLogs).hasSize(1).contains(historyLogResponseDto);
    }
}