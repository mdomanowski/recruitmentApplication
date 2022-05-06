package com.recruitment.forexbuddy.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DetailedRate {
    String currency;
    String code;
    BigDecimal bid;
    BigDecimal ask;
}
