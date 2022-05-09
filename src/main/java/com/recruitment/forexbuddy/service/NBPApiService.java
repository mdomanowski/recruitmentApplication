package com.recruitment.forexbuddy.service;

import com.recruitment.forexbuddy.exception.InvalidCurrencyException;
import com.recruitment.forexbuddy.exception.InvalidAmountException;
import com.recruitment.forexbuddy.model.dto.request.CurrencyDetailsRequestDto;
import com.recruitment.forexbuddy.model.dto.response.HistoryLogResponseDto;
import com.recruitment.forexbuddy.model.enums.RequestType;
import com.recruitment.forexbuddy.model.dto.request.TableRequestDto;
import com.recruitment.forexbuddy.model.dto.response.CurrencyResponseDto;
import com.recruitment.forexbuddy.model.dto.response.DetailedRatesResponseDto;
import com.recruitment.forexbuddy.model.dto.response.CurrencyExchangeResponseDto;
import com.recruitment.forexbuddy.webclient.NBPApiClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class NBPApiService {

    private final NBPApiClient nbpApiClient = new NBPApiClient();
    private final DatabaseLogService databaseLogService;
    private final List<CurrencyResponseDto> currenciesAvailableToExchange = new ArrayList<>();

    public DetailedRatesResponseDto getDetailedRates() {
        databaseLogService.logRequestToDatabase(RequestType.EXCHANGE_RATES_LIST);
        TableRequestDto tableRequestDto = nbpApiClient.getAllDetailedRates();
        return DetailedRatesResponseDto.builder()
                .actualDate(tableRequestDto.getEffectiveDate())
                .rates(tableRequestDto.getRates())
                .build();
    }

    public CurrencyExchangeResponseDto getCurrentExchangeRate(String from, String to, String amount) throws InvalidCurrencyException {
        if (!"PLN".equals(from)) {
            throw new InvalidCurrencyException("Only exchange from PLN is possible");
        }
        TableRequestDto tableRequestDto = nbpApiClient.getAllDetailedRates();
        CurrencyDetailsRequestDto currencyTo;
        try {
            currencyTo = tableRequestDto.getRates().stream()
                    .filter(rate -> to.equals(rate.getCode()))
                    .findAny()
                    .get();
        } catch (NoSuchElementException noSuchElementException) {
            throw new InvalidCurrencyException("Please enter valid currency code");
        }
        try {
            databaseLogService.logRequestToDatabase(RequestType.EXCHANGE, from, to, Double.parseDouble(amount), currencyTo.getAsk());
            return CurrencyExchangeResponseDto.builder()
                    .currentDate(LocalDate.now())
                    .currentTime(LocalTime.now().truncatedTo(ChronoUnit.SECONDS))
                    .currencyFrom(from)
                    .amount(Double.parseDouble(amount))
                    .ask(currencyTo.getAsk())
                    .bid(currencyTo.getBid())
                    .currencyTo(to)
                    .build();
        } catch (NumberFormatException numberFormatException) {
            throw new InvalidAmountException("Please enter a valid amount, separated by coma");
        }
    }

    public List<CurrencyResponseDto> getCurrenciesAvailableToExchange() {
        databaseLogService.logRequestToDatabase(RequestType.CURRENCIES_LIST);
        return currenciesAvailableToExchange.isEmpty()
                ? nbpApiClient.getAllDetailedRates().getRates().stream()
                        .map(currencyDetailsRequestDto -> CurrencyResponseDto.builder()
                                .currency(currencyDetailsRequestDto.getCurrency())
                                .code(currencyDetailsRequestDto.getCode())
                                .build())
                        .collect(Collectors.toList())
                : currenciesAvailableToExchange;
    }

    public List<HistoryLogResponseDto> getLogHistory() {
        return databaseLogService.getAllLogs();
    }
}
