package com.recruitment.forexbuddy.model.dto.response;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@ToString
public class CurrencyResponseDto {
    String currency;
    String code;
}
