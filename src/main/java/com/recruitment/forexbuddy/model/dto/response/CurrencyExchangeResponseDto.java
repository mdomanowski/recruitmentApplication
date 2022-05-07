package com.recruitment.forexbuddy.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@JsonPropertyOrder({
        "currentDate",
        "currentTime",
        "currencyFrom",
        "currencyTo",
        "amount",
        "bid",
        "ask",
        "transferResult",
        "inverseTransferResult",
        "spread"
})
public class CurrencyExchangeResponseDto {
    private double amount;
    private String currencyFrom;
    private String currencyTo;
    private double bid;
    private double ask;
    private LocalDate currentDate;
    private LocalTime currentTime;

    @JsonProperty("transferResult")
    public double getTransferResult() {
        return Math.round((amount / ask) * 1000d) / 1000d;
    }
    @JsonProperty("inverseTransferResult")
    public double getInverseTransferResult() {
        return Math.round((getTransferResult() * bid) * 1000d) / 1000d;
    }
    @JsonProperty("spread")
    public double getSpread() {
        return Math.round((amount - getInverseTransferResult()) * 1000d) / 1000d;
    }

}
