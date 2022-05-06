package com.recruitment.forexbuddy.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DetailedRate {
    String currency;
    String code;
    double bid;
    double ask;
}
