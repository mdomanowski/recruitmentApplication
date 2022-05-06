package com.recruitment.forexbuddy.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ExchangeResponseDto {
    private double amount;
    private String from;
    private String to;
    private double bid;
    private double ask;
    private double bidValue;
    private double askValue;
    private LocalDateTime date;
}
