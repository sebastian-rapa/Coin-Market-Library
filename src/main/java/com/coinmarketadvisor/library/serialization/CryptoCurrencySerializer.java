package com.coinmarketadvisor.library.serialization;

import com.coinmarketadvisor.library.model.CryptoCurrency;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CryptoCurrencySerializer implements Serializer<CryptoCurrency> {

    private static final Logger logger = LoggerFactory.getLogger(CryptoCurrencySerializer.class.getName());

    @Override
    public byte[] serialize(String key, CryptoCurrency currencyToSerialize) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(currencyToSerialize)
                               .getBytes();
        } catch (JsonProcessingException e) {
            logger.error("There was a problem when serializing a crypto currency.");
        }
        return new byte[] {};
    }
}