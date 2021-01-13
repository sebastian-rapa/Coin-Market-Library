package com.coinmarketadvisor.library.serialization;

import com.coinmarketadvisor.library.model.CryptoCurrency;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CryptoCurrenciesDeserializer implements Deserializer<List<CryptoCurrency>> {

    private static final Logger logger = LoggerFactory.getLogger(CryptoCurrenciesDeserializer.class.getName());

    @Override
    public List<CryptoCurrency> deserialize(String s, byte[] serializedCryptoCurrency) {
        ObjectMapper objectMapper = new ObjectMapper();

//        try {
//            return objectMapper.readValue(serializedCryptoCurrency, List<CryptoCurrency>.class);
//        } catch (IOException e) {
//            logger.error("There was a problem while a crypto currency is being deserialize.");
//        }

        return null;
    }
}