package com.coinmarketadvisor.library.serialization;

import com.coinmarketadvisor.library.model.CryptoCurrency;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CryptoCurrenciesSerializer implements Serializer<List<CryptoCurrency>> {

    private static final Logger logger = LoggerFactory.getLogger(CryptoCurrenciesSerializer.class.getName());

    @Override
    public byte[] serialize(String key, List<CryptoCurrency> currenciesToSerialize) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(currenciesToSerialize)
                               .getBytes();
        } catch (JsonProcessingException e) {
            logger.error("There was a problem when serializing a crypto currency.");
        }
        return new byte[] {};
    }
}