package com.recruitment.forexbuddy.model.dto.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CurrencyDetailsRequestDto {
    String currency;
    String code;
    double bid;
    double ask;
}
