package nelk.io.crypton.models.rex;


import nelk.io.crypton.retrofit.models.CoinData;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNoneBlank;

public class Market {

    // API pullMarketsData
    Coin marketCoin;
    Boolean active;
    String marketName;
    String baseCoin;
    String baseCoinLong;

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

    public Market(CoinData rexData){

        if(isNoneBlank(rexData.getMarketCurrency(), rexData.getMarketCurrencyLong())){
            this.marketCoin = new Coin(rexData.getMarketCurrency(),
                    rexData.getMarketCurrencyLong(),
                    rexData.getLogoUrl()
            );
        }
        if(!isBlank(rexData.getBaseCurrency())){
            this.baseCoin = rexData.getBaseCurrency();
        }
        if(!isBlank(rexData.getBaseCurrencyLong())){
            this.baseCoinLong = rexData.getBaseCurrencyLong();
        }
        if(!isBlank(rexData.getMarketName())){
            this.marketName = rexData.getMarketName();
        }
        if(!isBlank(rexData.getHigh())){
            this.high = rexData.getHigh();
        }
        if(!isBlank(rexData.getLow())){
            this.low = rexData.getLow();
        }
        if(!isBlank(rexData.getVolume())){
            this.volume = rexData.getVolume();
        }
        if(!isBlank(rexData.getLast())){
            this.last = rexData.getLast();
        }
        if(!isBlank(rexData.getTimeStamp())){
            this.timeStamp = rexData.getTimeStamp();
        }
        if(!isBlank(rexData.getBid())){
            this.bid = rexData.getBid();
        }
        if(!isBlank(rexData.getAsk())){
            this.ask = rexData.getAsk();
        }
        if(!isBlank(rexData.getPrevDay())){
            this.prevDay = rexData.getPrevDay();
        }

    }

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

    public String getBaseCoin() {
        return baseCoin;
    }

    public void setBaseCoin(String baseCoin) {
        this.baseCoin = baseCoin;
    }

    public String getBaseCoinLong() {
        return baseCoinLong;
    }

    public void setBaseCoinLong(String baseCoinLong) {
        this.baseCoinLong = baseCoinLong;
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

    public Market addData(CoinData rexData){

        if(isNoneBlank(rexData.getMarketCurrency(), rexData.getMarketCurrencyLong())){
            this.marketCoin = new Coin(rexData.getMarketCurrency(),
                                        rexData.getMarketCurrencyLong(),
                                        rexData.getLogoUrl()
            );
        }
        if(!isBlank(rexData.getBaseCurrency())){
            this.baseCoin = rexData.getBaseCurrency();
        }
        if(!isBlank(rexData.getBaseCurrencyLong())){
            this.baseCoinLong = rexData.getBaseCurrencyLong();
        }
        if(!isBlank(rexData.getMarketName())){
            this.marketName = rexData.getMarketName();
        }
        if(!isBlank(rexData.getHigh())){
            this.high = rexData.getHigh();
        }
        if(!isBlank(rexData.getLow())){
            this.low = rexData.getLow();
        }
        if(!isBlank(rexData.getVolume())){
            this.volume = rexData.getVolume();
        }
        if(!isBlank(rexData.getLast())){
            this.last = rexData.getLast();
        }
        if(!isBlank(rexData.getTimeStamp())){
            this.timeStamp = rexData.getTimeStamp();
        }
        if(!isBlank(rexData.getBid())){
            this.bid = rexData.getBid();
        }
        if(!isBlank(rexData.getAsk())){
            this.ask = rexData.getAsk();
        }
        if(!isBlank(rexData.getPrevDay())){
            this.prevDay = rexData.getPrevDay();
        }

        return this;

    }

}
