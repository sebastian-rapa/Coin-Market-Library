package com.coinmarketadvisor.library.serialization;

import org.junit.Test;
import com.coinmarketadvisor.library.model.CryptoCurrency;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class CryptoCurrencySerializerTest {

    @Test
    public void givenAnInitialCryptoCurrency_whenSerializingItAndDeserializingIt_theDeserializedCurrencyShouldBeTheSameWithTheInitialCurrency() {
        CryptoCurrency initialCryptoCurrency =
                new CryptoCurrency(
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
    public void givenAnInitialCryptoCurrency_whenSerializingItTwice_theByteArraysShouldBeTheEqual() {
        CryptoCurrency initialCryptoCurrency =
                new CryptoCurrency(
                        "BTC",
                        "bitcoin",
                        BigDecimal.valueOf(16_1234.89),
                        BigDecimal.valueOf(16_1234.89),
                        BigDecimal.valueOf(16_1234.89),
                        new Date().getTime());

        byte[] firstSerializedCurrencyByteArray = serializeCurrency(initialCryptoCurrency);
        byte[] secondSerializedCurrencyByteArray = serializeCurrency(initialCryptoCurrency);

        assertArrayEquals(
                "Two byte arrays from the same crypto currency serialization should be equal",
                firstSerializedCurrencyByteArray,
                secondSerializedCurrencyByteArray
        );

    }

    private CryptoCurrency deserializeCurrency(byte[] serializedCurrency) {
        return new CryptoCurrencyDeserializer().deserialize("First argument", serializedCurrency);
    }

    private byte[] serializeCurrency(CryptoCurrency cryptoCurrency) {

        return new CryptoCurrencySerializer().serialize("a key", cryptoCurrency);
    }

}