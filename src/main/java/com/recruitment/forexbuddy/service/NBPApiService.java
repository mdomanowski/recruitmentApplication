package com.recruitment.forexbuddy.service;

import com.recruitment.forexbuddy.model.dto.request.CurrencyDetailsRequestDto;
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
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class NBPApiService {

    private final NBPApiClient nbpApiClient = new NBPApiClient();
    private final DatabaseLogService databaseLogService;
    private final List<CurrencyResponseDto> currenciesAvailableToExchange = new ArrayList<>();

    public DetailedRatesResponseDto getDetailedRates() {
        databaseLogService.logRequestToDatabase(RequestType.RATES_LIST);
        TableRequestDto tableRequestDto = nbpApiClient.getAllDetailedRates();
        return DetailedRatesResponseDto.builder()
                .actualDate(tableRequestDto.getEffectiveDate())
                .rates(tableRequestDto.getRates())
                .build();
    }

    /**
     * Póki co przelicza ze złotówek, żeby przeliczało z innym należy pobrać tabelę A/B i przeliczyć stosunek średnich
     * cen lub z tabeli C
     * @param from
     * @param to
     * @param amount
     * @return
     */
    public CurrencyExchangeResponseDto getCurrentExchangeRate(String from, String to, double amount) {
        databaseLogService.logRequestToDatabase(RequestType.EXCHANGE, from, to, amount);
        TableRequestDto tableRequestDto = nbpApiClient.getAllDetailedRates();
        CurrencyDetailsRequestDto currencyTo = tableRequestDto.getRates().stream()
                .filter(rate -> to.equals(rate.getCode()))
                .reduce((a, b) -> {
                    throw new IllegalStateException("No currency found matching the code");
                })
                .get();
        return CurrencyExchangeResponseDto.builder()
                .currentDate(LocalDate.now())
                .currentTime(LocalTime.now().truncatedTo(ChronoUnit.SECONDS))
                .currencyFrom(from)
                .amount(amount)
                .ask(currencyTo.getAsk())
                .bid(currencyTo.getBid())
                .currencyTo(to)
                .build();
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
}
