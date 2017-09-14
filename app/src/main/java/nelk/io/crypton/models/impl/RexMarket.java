package nelk.io.crypton.models.impl;

import nelk.io.crypton.models.Market;


public class RexMarket implements Market {

    // Attributes
    String marketCurrency;
    String baseCurrency;
    String marketCurrencyLong;
    String baseCurrencyLong;
    String logoUrl;
    String marketName;
    String high;
    String low;
    String volume;
    String last;
    String baseVolume;
    String timeStamp;
    String bid;
    String ask;
    String openBuyOrders;
    String openSellOrders;
    String prevDay;

    @Override
    public String getMarketCurrency() {
        return marketCurrency;
    }

    @Override
    public void setMarketCurrency(String marketCurrency) {
        this.marketCurrency = marketCurrency;
    }

    @Override
    public String getBaseCurrency() {
        return baseCurrency;
    }

    @Override
    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    @Override
    public String getMarketCurrencyLong() {
        return marketCurrencyLong;
    }

    @Override
    public void setMarketCurrencyLong(String marketCurrencyLong) {
        this.marketCurrencyLong = marketCurrencyLong;
    }

    @Override
    public String getBaseCurrencyLong() {
        return baseCurrencyLong;
    }

    @Override
    public void setBaseCurrencyLong(String baseCurrencyLong) {
        this.baseCurrencyLong = baseCurrencyLong;
    }

    @Override
    public String getLogoUrl() {
        return logoUrl;
    }

    @Override
    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    @Override
    public String getMarketName() {
        return marketName;
    }

    @Override
    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    @Override
    public String getHigh() {
        return high;
    }

    @Override
    public void setHigh(String high) {
        this.high = high;
    }

    @Override
    public String getLow() {
        return low;
    }

    @Override
    public void setLow(String low) {
        this.low = low;
    }

    @Override
    public String getVolume() {
        return volume;
    }

    @Override
    public void setVolume(String volume) {
        this.volume = volume;
    }

    @Override
    public String getLast() {
        return last;
    }

    @Override
    public void setLast(String last) {
        this.last = last;
    }

    @Override
    public String getBaseVolume() {
        return baseVolume;
    }

    @Override
    public void setBaseVolume(String baseVolume) {
        this.baseVolume = baseVolume;
    }

    @Override
    public String getTimeStamp() {
        return timeStamp;
    }

    @Override
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String getBid() {
        return bid;
    }

    @Override
    public void setBid(String bid) {
        this.bid = bid;
    }

    @Override
    public String getAsk() {
        return ask;
    }

    @Override
    public void setAsk(String ask) {
        this.ask = ask;
    }

    @Override
    public String getOpenBuyOrders() {
        return openBuyOrders;
    }

    @Override
    public void setOpenBuyOrders(String openBuyOrders) {
        this.openBuyOrders = openBuyOrders;
    }

    @Override
    public String getOpenSellOrders() {
        return openSellOrders;
    }

    @Override
    public void setOpenSellOrders(String openSellOrders) {
        this.openSellOrders = openSellOrders;
    }

    @Override
    public String getPrevDay() {
        return prevDay;
    }

    @Override
    public void setPrevDay(String prevDay) {
        this.prevDay = prevDay;
    }
}
