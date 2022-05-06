package com.recruitment.forexbuddy.service;

import com.recruitment.forexbuddy.model.DetailedRate;
import com.recruitment.forexbuddy.model.RequestType;
import com.recruitment.forexbuddy.model.ResponseC;
import com.recruitment.forexbuddy.model.dto.DetailedRatesDto;
import com.recruitment.forexbuddy.model.dto.ExchangeResponseDto;
import com.recruitment.forexbuddy.webclient.NBPApiClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApiService {

    private final NBPApiClient nbpApiClient = new NBPApiClient();
    private final LoggingService loggingService;

    public DetailedRatesDto getDetailedRates() {
        loggingService.logRequestToDatabase(RequestType.RATES_LIST);
        ResponseC responseC = nbpApiClient.getAllDetailedRates();
        return DetailedRatesDto.builder()
                .actualDate(responseC.getEffectiveDate())
                .rates(responseC.getRates())
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
    public ExchangeResponseDto getCurrentExchangeRate(String from, String to, double amount) {
        loggingService.logRequestToDatabase(RequestType.EXCHANGE, from, to, amount);
        ResponseC responseC = nbpApiClient.getAllDetailedRates();
        DetailedRate currencyTo = responseC.getRates().stream()
                .filter(rate -> to.equals(rate.getCode()))
                .reduce((a, b) -> {
                    throw new IllegalStateException("No currency found matching the code");
                })
                .get();
        return ExchangeResponseDto.builder()
                .date(LocalDateTime.now())
                .from(from)
                .amount(amount)
                .ask(currencyTo.getAsk())
                .bid(currencyTo.getBid())
                .to(to)
                .askValue(amount / currencyTo.getAsk())
                .bidValue(amount / currencyTo.getBid())
                .build();
    }

}
