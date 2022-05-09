package com.recruitment.forexbuddy.service;

import com.recruitment.forexbuddy.exception.InvalidAmountException;
import com.recruitment.forexbuddy.exception.InvalidCurrencyException;
import com.recruitment.forexbuddy.model.dto.response.CurrencyExchangeResponseDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NBPApiServiceTest {

    private final DatabaseLogService databaseLogService = mock(DatabaseLogService.class);
    private final NBPApiService nbpApiService = new NBPApiService(databaseLogService);

    @Test
    void get_Current_Exchange_Rate_From_Currency_Other_Than_PLN_throwsInvalidCurrencyException() {
        // given
        String invalidCurrencyFrom = "EUR";
        String secondCurrencyTo = "USD";
        String amountToExchange = "200";
        // when
        var invalidCurrencyException = catchThrowable(() ->
                nbpApiService.getCurrentExchangeRate(invalidCurrencyFrom, secondCurrencyTo, amountToExchange));
        // then
        assertThat(invalidCurrencyException)
                .isInstanceOf(InvalidCurrencyException.class)
                .hasMessageContaining("Only exchange from PLN is possible");
    }

    @Test
    void get_Current_Exchange_Rate_From_PLN_For_Currency_With_Invalid_Code_throwsInvalidCurrencyException() {
        // given
        String currencyFrom = "PLN";
        String currencyTo = "US";
        String amountToExchange = "200";
        // when
        var invalidCurrencyException = catchThrowable(() ->
                nbpApiService.getCurrentExchangeRate(currencyFrom, currencyTo, amountToExchange));
        // then
        assertThat(invalidCurrencyException)
                .isInstanceOf(InvalidCurrencyException.class)
                .hasMessageContaining("Please enter valid currency code");
    }

    @Test
    void get_Current_Exchange_Rate_With_Invalid_Amount_throwsInvalidAmountException() {
        // given
        String currencyFrom = "PLN";
        String currencyTo = "USD";
        String amountToExchange = "200,12";
        // when
        var invalidAmountException = catchThrowable(() ->
                nbpApiService.getCurrentExchangeRate(currencyFrom, currencyTo, amountToExchange));
        // then
        assertThat(invalidAmountException)
                .isInstanceOf(InvalidAmountException.class)
                .hasMessageContaining("Please enter a valid amount, separated by coma");
    }

    @Test
    void get_Current_Exchange_Rate_With_Valid_Arguments_Produces_Valid_CurrencyExchangeResponseDto() throws InvalidCurrencyException {
        // given
        String currencyFrom = "PLN";
        String currencyTo = "USD";
        String amount = "200.12";
        // when
        when(databaseLogService.logRequestToDatabase(any())).thenReturn(null);
        CurrencyExchangeResponseDto detailedRatesResponseDto = nbpApiService.getCurrentExchangeRate(
                currencyFrom, currencyTo, amount);
        // then
        assertThat(detailedRatesResponseDto.getCurrencyFrom()).isEqualTo(currencyFrom);
        assertThat(detailedRatesResponseDto.getCurrencyTo()).isEqualTo(currencyTo);
        assertThat(String.valueOf(detailedRatesResponseDto.getAmount())).isEqualTo(amount);
    }
}