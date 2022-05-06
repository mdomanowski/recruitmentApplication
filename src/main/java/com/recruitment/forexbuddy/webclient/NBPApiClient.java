package com.recruitment.forexbuddy.webclient;


import com.recruitment.forexbuddy.model.ResponseC;
import org.springframework.web.client.RestTemplate;

public class NBPApiClient {

    public static final String TABLE_C_API_URL = "http://api.nbp.pl/api/exchangerates/tables/c/";
    private final RestTemplate restTemplate = new RestTemplate();

    public ResponseC getAllDetailedRates() {
        ResponseC[] response = new ResponseC[1];
        var entity = restTemplate.getForEntity(TABLE_C_API_URL, response.getClass());
        var body = entity.getBody();
        assert body != null;
        return body[0];
    }

}
