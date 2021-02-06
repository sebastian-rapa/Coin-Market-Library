package com.coinmarketadvisor.library.model;

import com.coinmarketadvisor.library.exception.runtime.MissingVolumeCalculateMethodException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * Class that model a Crypto Currency
 * */
public class CryptoCurrency {

    /**
     * Class field that will be used hen converting a POJO to JSON
     * */
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    /**
     *  This is the symbol of the crypto currency
     *  For example: BTC, ETH, USDT
     * */
    private String symbol; // Let's use this as a primary key
    /**
     *  This is the name of the crypto currency
     *  For example: Bitcoin, Etherium..
     * */
    private String name;
    /**
     *  This is the price of the crypto currency at a given timestamp
     * */
    private BigDecimal price;
    /**
     *  This is the price of the crypto currency 24 hours prior to the timestamp
     * */
    private BigDecimal price24Hours;
    /**
     *  This is the price of the crypto currency 7 days prior to the  timestamp
     * */
    private BigDecimal price7Days;
    /**
     *  This is the percentage change of the crypto currency in the last 24 hours
     * */
    private BigDecimal change24Hours;
    /**
     *  This is the percentage change of the crypto currency in the last 7 days
     * */
    private BigDecimal change7Days;
    /**
     *  This is the timestamp when the price of the crypto currency was reached
     * */
    private long timeStampOfPrice; // The price is returned in ms

    /**
     * Empty constructor so the currency can be deserialized
     * This constructor is used only when a Consumer deserializes a Crypto Currency
     * */
    public CryptoCurrency() {}

    /**
     * This constructor will be used when Producing to the Kafka server
     * */
    public CryptoCurrency(final String symbol,
                          final String name,
                          final BigDecimal currentPrice,
                          final BigDecimal price24Hours,
                          final BigDecimal price7Days,
                          final long timeStampOfPrice) {
        // Make sure a symbol is introduced
        if (null == symbol || symbol.isEmpty()) {
            throw new IllegalStateException("You can not create a currency without a symbol.");
        }

        // Make sure a name is introduced
        if (null == name || name.isEmpty()) {
            throw new IllegalStateException("You can not create a currency without a name.");
        }

        // Make sure the price is never negative
        if (currentPrice.doubleValue() < 0) {
            throw new IllegalStateException("You can not create a currency with a negative price.");
        }

        // Make sure the price is never negative
        if (price24Hours.doubleValue() < 0) {
            throw new IllegalStateException("You can not create a currency with a negative price.");
        }

        // Make sure the price is never negative
        if (price7Days.doubleValue() < 0) {
            throw new IllegalStateException("You can not create a currency with a negative price.");
        }

        this.symbol = symbol;
        this.name = name;
        this.price = currentPrice;
        this.price24Hours = price24Hours;
        this.price7Days = price7Days;
        this.change24Hours = calculatePercentageChange(price24Hours);
        this.change7Days = calculatePercentageChange(price7Days);
        this.timeStampOfPrice = timeStampOfPrice;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getPrice24Hours() {
        return price24Hours;
    }

    public BigDecimal getPrice7Days() {
        return price7Days;
    }

    public BigDecimal getChange24Hours() {
        return change24Hours;
    }

    public BigDecimal getChange7Days() {
        return change7Days;
    }

    public long getTimeStampOfPrice() {
        return timeStampOfPrice;
    }

    private BigDecimal calculatePercentageChange(BigDecimal relativePrice) {
        return price.subtract(relativePrice)
                    .multiply(BigDecimal.valueOf(100))
                    .divide(relativePrice, 4, RoundingMode.HALF_EVEN);
    }
    /**
     * Method that converts an instance of this class to an ObjectNode
     * */
    public ObjectNode toObjectNode(){
        final ObjectNode objectNode = OBJECT_MAPPER.createObjectNode();
        objectNode.put("symbol", symbol);
        objectNode.put("name", name);
        objectNode.put("price", price);
        objectNode.put("change24Hours", change24Hours);
        objectNode.put("change7Days", change7Days);
        objectNode.put("timeStampOfPrice", timeStampOfPrice);
        objectNode.put("marketCapPer24H", calculateMarketVolumePer24H());
        objectNode.put("marketCapPer7Days", calculateMarketVolumePer7Days());
        return objectNode;
    }

    /**
     * This method provides the market volume of this particular currency
     * @param marketVolume represents the time frame on which to calculate
     * Check the enum Volume to see the time frames
     * */
    public double calculateMarketVolume(final MarketVolume marketVolume){
        // TODO: Think if this method it's even necessary
        // TODO: Probably it's good enough to calculate the market volumes when converting the POJO to JSON
        switch (marketVolume) {
            case LAST_24_H: return calculateMarketVolumePer24H();
            case LAST_7_DAYS: return calculateMarketVolumePer7Days();
            default: throw new MissingVolumeCalculateMethodException("There is no implementation to calculate " + marketVolume);
        }
    }

    private double calculateMarketVolumePer24H() {
        // TODO: Implement the logic here
        return 0;
    }

    private double calculateMarketVolumePer7Days() {
        // TODO: Implement the logic here
        return 0;
    }

    @Override
    public String toString() {
        return "CryptoCurrency{" +
                "symbol='" + symbol + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", timeStampOfPrice=" + timeStampOfPrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CryptoCurrency)) return false;
        CryptoCurrency that = (CryptoCurrency) o;
        return getTimeStampOfPrice() == that.getTimeStampOfPrice() && Objects.equals(getSymbol(), that.getSymbol()) && Objects
                .equals(getName(), that.getName()) && Objects.equals(getPrice(), that.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSymbol(), getName(), getPrice(), getTimeStampOfPrice());
    }
}
