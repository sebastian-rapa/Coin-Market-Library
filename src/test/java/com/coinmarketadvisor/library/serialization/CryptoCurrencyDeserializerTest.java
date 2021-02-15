package com.coinmarketadvisor.library.serialization;

import org.junit.Test;
import com.coinmarketadvisor.library.model.CryptoCurrency;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.*;

public class CryptoCurrencyDeserializerTest {

    @Test
    public void givenAnInitialCryptoCurrency_whenSerializingItAndDeserializingIt_theDeserializedCurrencyShouldBeTheSameWithTheInitialCurrency() {
        CryptoCurrency initialCryptoCurrency =
                new CryptoCurrency(
                        UUID.randomUUID().toString(),
                        "BTC",
                        "bitcoin",
                        BigDecimal.valueOf(16_1234.89),
                        BigDecimal.valueOf(16_1234.89),
                        BigDecimal.valueOf(16_1234.89),
                        new Date().getTime());
        byte[] serializedCurrency = serializeCurrency(initialCryptoCurrency);

        CryptoCurrency deserializedCurrency = deserializeCurrency(serializedCurrency);

        assertEquals(
                "The initial crypto currency should be equal to the deserialized crypto currency",
                initialCryptoCurrency,
                deserializedCurrency
        );
    }

    @Test
    public void givenAnInitialCryptoCurrency_whenSerializingItAndDeserializingTwice_theDeserializedCurrenciesShouldBeTheEqual() {
        CryptoCurrency initialCryptoCurrency =
                new CryptoCurrency(
                        UUID.randomUUID().toString(),
                        "BTC",
                        "bitcoin",
                        BigDecimal.valueOf(16_1234.89),
                        BigDecimal.valueOf(16_1234.89),
                        BigDecimal.valueOf(16_1234.89),
                        new Date().getTime());
        byte[] serializedCurrency = serializeCurrency(initialCryptoCurrency);

        CryptoCurrency firstDeserializedCurrency = deserializeCurrency(serializedCurrency);
        CryptoCurrency secondDeserializedCurrency = deserializeCurrency(serializedCurrency);

        assertEquals(
                "Two deserialized crypto currencies from the same byte array should be equal",
                firstDeserializedCurrency,
                secondDeserializedCurrency
        );
    }

    private CryptoCurrency deserializeCurrency(byte[] serializedCurrency) {
        return new CryptoCurrencyDeserializer().deserialize("First argument", serializedCurrency);
    }

    private byte[] serializeCurrency(CryptoCurrency cryptoCurrency) {

        return new CryptoCurrencySerializer().serialize("a key", cryptoCurrency);
    }


}