package nelk.io.crypton.models.rex;

import nelk.io.crypton.models.Coin;
import nelk.io.crypton.models.Market;


public class RexMarket implements Market {

    // API getMarkets
    String marketName;
    Coin marketCoin;
    Coin baseCoin;
    Boolean active;


    // API getsummaries
    String last;
    String prevDay;

    String high;
    String low;
    String volume;
    String baseVolume;
    String timeStamp;
    String bid;
    String ask;
    String openBuyOrders;
    String openSellOrders;

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public Coin getMarketCoin() {
        return marketCoin;
    }

    public void setMarketCoin(Coin marketCoin) {
        this.marketCoin = marketCoin;
    }

    public Coin getBaseCoin() {
        return baseCoin;
    }

    public void setBaseCoin(Coin baseCoin) {
        this.baseCoin = baseCoin;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getPrevDay() {
        return prevDay;
    }

    public void setPrevDay(String prevDay) {
        this.prevDay = prevDay;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getBaseVolume() {
        return baseVolume;
    }

    public void setBaseVolume(String baseVolume) {
        this.baseVolume = baseVolume;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getAsk() {
        return ask;
    }

    public void setAsk(String ask) {
        this.ask = ask;
    }

    public String getOpenBuyOrders() {
        return openBuyOrders;
    }

    public void setOpenBuyOrders(String openBuyOrders) {
        this.openBuyOrders = openBuyOrders;
    }

    public String getOpenSellOrders() {
        return openSellOrders;
    }

    public void setOpenSellOrders(String openSellOrders) {
        this.openSellOrders = openSellOrders;
    }
}
