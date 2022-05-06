package com.recruitment.forexbuddy.model;


import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class ResponseC {
    String table;
    String no;
    String tradingDate;
    String effectiveDate;
    List<DetailedRate> rates;
}
