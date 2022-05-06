package com.recruitment.forexbuddy.model.dto;

import com.recruitment.forexbuddy.model.DetailedRate;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DetailedRatesDto {
    private String actualDate;
    private List<DetailedRate> rates;
}
