package com.coinmarketadvisor.library.model;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class CryptoCurrencyTest {

    @Test(expected = IllegalStateException.class)
    public void givenANegativePrice_whenTryingToCreateACryptoCurrencyObjectWithIt_itShouldThrowAnIllegalStateException() {
        new CryptoCurrency(
                "btc",
                "bitcoin",
                BigDecimal.valueOf(-1),
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                new Date().getTime()
        );
    }

    @Test(expected = IllegalStateException.class)
    public void givenANegative24HPrice_whenTryingToCreateACryptoCurrencyObjectWithIt_itShouldThrowAnIllegalStateException() {
        new CryptoCurrency(
                "btc",
                "bitcoin",
                BigDecimal.ZERO,
                BigDecimal.valueOf(-1),
                BigDecimal.ZERO,
                new Date().getTime()
        );
    }

    @Test(expected = IllegalStateException.class)
    public void givenANegative7DaysPrice_whenTryingToCreateACryptoCurrencyObjectWithIt_itShouldThrowAnIllegalStateException() {
        new CryptoCurrency(
                "btc",
                "bitcoin",
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                BigDecimal.valueOf(-1),
                new Date().getTime()
        );
    }


    @Test(expected = IllegalStateException.class)
    public void givenAnEmptyCurrencyName_whenTryingToCreateACryptoCurrencyObjectWithIt_itShouldThrowAnIllegalStateException() {
        new CryptoCurrency(
                "btc",
                "",
                BigDecimal.valueOf(16_123.89),
                BigDecimal.valueOf(16_123.89),
                BigDecimal.valueOf(16_123.89),
                new Date().getTime()
        );
    }


    @Test(expected = IllegalStateException.class)
    public void givenAnEmptyCurrencySymbol_whenTryingToCreateACryptoCurrencyObjectWithIt_itShouldThrowAnIllegalStateException() {
        new CryptoCurrency(
                "",
                "bitcoin",
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                new Date().getTime()
        );
    }

    @Test
    public void givenACryptoCurrency_whenCalculating24HChange_expectRightOutput() {
        CryptoCurrency cryptoCurrency = new CryptoCurrency(
                "BTC",
                "bitcoin",
                BigDecimal.valueOf(34_500),
                BigDecimal.valueOf(34_400),
                BigDecimal.valueOf(34_450),
                new Date().getTime()
        );
        assertEquals(
                "Given a current price of 34.500 & 24H price of 34_400, we expect the the 24H change to be: 0,2907",
                BigDecimal.valueOf(0.2907),
                cryptoCurrency.getChange24Hours()
        );
    }

    @Test
    public void givenACryptoCurrency_whenCalculating7DaysChange_expectRightOutput() {
        CryptoCurrency cryptoCurrency = new CryptoCurrency(
                "BTC",
                "bitcoin",
                BigDecimal.valueOf(34_500),
                BigDecimal.valueOf(34_400),
                BigDecimal.valueOf(33_450),
                new Date().getTime()
        );
        assertEquals(
                "Given a current price of 34.500 & 7 Days price of 33_450, we expect the the 7Days change to be: 3,1390",
                new BigDecimal("3.1390"),
                cryptoCurrency.getChange7Days()
        );
    }

    @Test
    public void givenACryptoCurrency_whenCalculating24HChangeWithPriceDropping_expectNegativeChangePercentage() {
        CryptoCurrency cryptoCurrency = new CryptoCurrency(
                "BTC",
                "bitcoin",
                BigDecimal.valueOf(34_500),
                BigDecimal.valueOf(35_500),
                BigDecimal.valueOf(34_450),
                new Date().getTime()
        );
        assertEquals(
                "Given a current price of 34.500 & 24H price of 35_500, we expect the the 24H change to be: -2,8169",
                new BigDecimal("-2.8169"),
                cryptoCurrency.getChange24Hours()
        );
    }

    @Test
    public void givenACryptoCurrency_whenCalculating7DaysChangeWithPriceDropping_expectNegativeChangePercentage() {
        CryptoCurrency cryptoCurrency = new CryptoCurrency(
                "BTC",
                "bitcoin",
                BigDecimal.valueOf(34_500),
                BigDecimal.valueOf(34_400),
                BigDecimal.valueOf(39_600),
                new Date().getTime()
        );
        assertEquals(
                "Given a current price of 34.500 & 7 Days price of 39_600, we expect the the 7Days change to be: -12,8788",
                new BigDecimal("-12.8788"),
                cryptoCurrency.getChange7Days()
        );
    }

    @Test
    public void givenACryptoCurrency_whenConvertingItToObjectNode_expectEachFieldsToBeConverted() {
        ObjectNode objectNodeCurrency = new CryptoCurrency(
                "BTC",
                "bitcoin",
                BigDecimal.valueOf(34_500.3212),
                BigDecimal.valueOf(34_400),
                BigDecimal.valueOf(39_600),
                new Date().getTime()
        ).toObjectNode();

        assertEquals(
                "Symbol should be BTC",
                "BTC",
                objectNodeCurrency.get("symbol").asText()
        );

        assertEquals(
                "Name should be bitcoin",
                "bitcoin",
                objectNodeCurrency.get("name").asText()
        );

        assertEquals(
                "Price should be 34_500.3212",
                BigDecimal.valueOf(34_500.3212),
                BigDecimal.valueOf(objectNodeCurrency.get("price").asDouble())
        );

        assertEquals(
                "Change percentage per 24H should be 0.2916",
                BigDecimal.valueOf(0.2916),
                BigDecimal.valueOf(objectNodeCurrency.get("change24Hours").asDouble())
        );

        assertEquals(
                "Change percentage per 7 days should be -12.8780",
                BigDecimal.valueOf(-12.8780),
                BigDecimal.valueOf(objectNodeCurrency.get("change7Days").asDouble())
        );
    }


    // TODO: After implementing the calculateVolume() method create tests for it

}