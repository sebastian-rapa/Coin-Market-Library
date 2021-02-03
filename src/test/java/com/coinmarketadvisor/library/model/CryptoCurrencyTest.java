package com.coinmarketadvisor.library.model;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

public class CryptoCurrencyTest {

    @Test(expected = IllegalStateException.class)
    public void givenANegativePrice_whenTryingToCreateACryptoCurrencyObjectWithIt_itShouldThrowAnIllegalStateException() {
        new CryptoCurrency("btc", "bitcoin", BigDecimal.valueOf(-1), new Date().getTime());
    }


    @Test(expected = IllegalStateException.class)
    public void givenAnEmptyCurrencyName_whenTryingToCreateACryptoCurrencyObjectWithIt_itShouldThrowAnIllegalStateException() {
        new CryptoCurrency("btc", "", BigDecimal.valueOf(16_123.89), new Date().getTime());
    }


    @Test(expected = IllegalStateException.class)
    public void givenAnEmptyCurrencySymbol_whenTryingToCreateACryptoCurrencyObjectWithIt_itShouldThrowAnIllegalStateException() {
        new CryptoCurrency("", "bitcoin", BigDecimal.valueOf(-1), new Date().getTime());
    }


    // TODO: After implementing the calculateVolume() method create tests for it

}