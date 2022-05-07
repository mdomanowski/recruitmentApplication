package com.recruitment.forexbuddy.model.dto.request;


import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class TableRequestDto {
    String table;
    String no;
    String tradingDate;
    String effectiveDate;
    List<CurrencyDetailsRequestDto> rates;
}
