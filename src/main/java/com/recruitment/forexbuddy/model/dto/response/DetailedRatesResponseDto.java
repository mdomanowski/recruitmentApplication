package com.recruitment.forexbuddy.model.dto.response;

import com.recruitment.forexbuddy.model.dto.request.CurrencyDetailsRequestDto;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@ToString
public class DetailedRatesResponseDto {
    private String actualDate;
    private List<CurrencyDetailsRequestDto> rates;
}
