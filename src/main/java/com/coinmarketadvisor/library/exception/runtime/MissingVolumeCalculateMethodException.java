package com.coinmarketadvisor.library.exception.runtime;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Runtime exception occurring when trying to calculate the market volume
 * for a specific currency and the CryptoCurrency class has not implemented
 * the logic to calculate Volume
 * */
public class MissingVolumeCalculateMethodException extends RuntimeException {


    // Generate a random serial version UID
    private static final long serialVersionUID = ThreadLocalRandom.current().nextLong();

    /**
     * Constructing the exception with just the message
     * */
    public MissingVolumeCalculateMethodException(final String message) {
        super(message);
    }

    /**
     * Constructing the exception with the message and the throwable
     * */
    public MissingVolumeCalculateMethodException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
