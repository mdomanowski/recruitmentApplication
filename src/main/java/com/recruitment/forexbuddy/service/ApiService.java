package com.recruitment.forexbuddy.service;

import com.recruitment.forexbuddy.model.ResponseC;
import com.recruitment.forexbuddy.model.dto.DetailedRatesDto;
import com.recruitment.forexbuddy.webclient.NBPApiClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApiService {

    private final NBPApiClient nbpApiClient = new NBPApiClient();

    public DetailedRatesDto getDetailedRates() {
        ResponseC responseC = nbpApiClient.getAllDetailedRates();
        return DetailedRatesDto.builder()
                .actualDate(responseC.getEffectiveDate())
                .rates(responseC.getRates())
                .build();
    }

}
