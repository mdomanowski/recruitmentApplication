package com.recruitment.forexbuddy.webclient;

import com.recruitment.forexbuddy.exception.InvalidApiConnection;
import com.recruitment.forexbuddy.model.dto.request.TableRequestDto;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class NBPApiClient {

    public static final String TABLE_C_API_URL = "http://api.nbp.pl/api/exchangerates/tables/c/";
    private final RestTemplate restTemplate = new RestTemplate();


    public TableRequestDto getAllDetailedRates() {
        final int valueInsideJsonArray = 0;
        final int arraySizeInsideJson = 1;
        TableRequestDto[] response = new TableRequestDto[arraySizeInsideJson];
        try {
            var entity = restTemplate.getForEntity(TABLE_C_API_URL, response.getClass());
            var body = entity.getBody();
            assert body != null;
            return body[valueInsideJsonArray];
        } catch (RestClientException e) {
            throw new InvalidApiConnection("Error while connecting to API");
        }
    }
}
