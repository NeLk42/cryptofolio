package nelk.io.crypton.models;

public interface Market {

    String getMarketName();

    void setMarketName(String marketName);

    Coin getMarketCoin();

    void setMarketCoin(Coin marketCoin);

    Coin getBaseCoin();

    void setBaseCoin(Coin baseCoin);

    Boolean getActive();

    void setActive(Boolean active);

    String getLast();

    void setLast(String last);

    String getPrevDay();

    void setPrevDay(String prevDay);

    String getHigh();

    void setHigh(String high);

    String getLow();

    void setLow(String low);

    String getVolume();

    void setVolume(String volume);

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

}
