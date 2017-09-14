package nelk.io.crypton.models;

public interface Market {

    String getMarketName();

    void setMarketName(String marketName);

    String getMarketCurrency();

    void setMarketCurrency(String marketCurrency);

    String getBaseCurrency();

    void setBaseCurrency(String baseCurrency);

    String getMarketCurrencyLong();

    void setMarketCurrencyLong(String marketCurrencyLong);

    String getBaseCurrencyLong();

    void setBaseCurrencyLong(String baseCurrencyLong);

    String getLogoUrl();

    void setLogoUrl(String logoUrl);

    String getHigh();

    void setHigh(String high);

    String getLow();

    void setLow(String low);

    String getVolume();

    void setVolume(String volume);

    String getLast();

    void setLast(String last);

    String getBaseVolume();

    void setBaseVolume(String baseVolume);

    String getTimeStamp();

    void setTimeStamp(String timeStamp);

    String getBid();

    void setBid(String bid);

    String getAsk();

    void setAsk(String ask);

    String getOpenBuyOrders();

    void setOpenBuyOrders(String openBuyOrders);

    String getOpenSellOrders();

    void setOpenSellOrders(String openSellOrders);

    String getPrevDay();

    void setPrevDay(String prevDay);
}
