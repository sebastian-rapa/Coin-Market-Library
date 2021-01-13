package com.coinmarketadvisor.library.serialization;

import com.coinmarketadvisor.library.model.CryptoCurrency;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class CryptoCurrencyDeserializer implements Deserializer<CryptoCurrency> {

    private static final Logger logger = LoggerFactory.getLogger(CryptoCurrencyDeserializer.class.getName());


    @Override
    public CryptoCurrency deserialize(String s, byte[] serializedCryptoCurrency) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(serializedCryptoCurrency, CryptoCurrency.class);
        } catch (IOException e) {
            logger.error("There was a problem while a crypto currency is being deserialize.");
        }

        return null;
    }
}
