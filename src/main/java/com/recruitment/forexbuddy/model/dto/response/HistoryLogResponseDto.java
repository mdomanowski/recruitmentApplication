package com.recruitment.forexbuddy.model.dto.response;

import com.recruitment.forexbuddy.model.enums.RequestType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Builder
@Getter
@Setter
public class HistoryLogResponseDto {
    private LocalDateTime date;
    private RequestType requestType;
    private String currencyFrom;
    private String currencyTo;
    private double rate;
    private double amount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoryLogResponseDto that = (HistoryLogResponseDto) o;
        return Double.compare(that.rate, rate) == 0
                && Double.compare(that.amount, amount) == 0
                && Objects.equals(date, that.date)
                && requestType == that.requestType
                && Objects.equals(currencyFrom, that.currencyFrom)
                && Objects.equals(currencyTo, that.currencyTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, requestType, currencyFrom, currencyTo, rate, amount);
    }
}
