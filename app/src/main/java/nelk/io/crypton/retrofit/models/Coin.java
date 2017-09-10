package nelk.io.crypton.retrofit.models;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class Coin {

    String MarketCurrency;
    String BaseCurrency;
    String MarketCurrencyLong;
    String BaseCurrencyLong;
    String MinTradeSize;
    String IsActive;
    String Notice;
    String IsSponsored;
    String LogoUrl;
    String MarketName;
    String High;
    String Low;
    String Volume;
    String Last;
    String BaseVolume;
    String TimeStamp;
    String Bid;
    String Ask;
    String OpenBuyOrders;
    String OpenSellOrders;
    String PrevDay;
    String Created;

    public Coin (){

    }

    public Coin (Coin coin){
        addData(coin);
    }

    public Coin addData(Coin coin){
        if(!isBlank(coin.MarketCurrency)){
            this.MarketCurrency = coin.MarketCurrency;
        }
        if(!isBlank(coin.BaseCurrency)){
            this.BaseCurrency = coin.BaseCurrency;
        }
        if(!isBlank(coin.MarketCurrencyLong)){
            this.MarketCurrencyLong = coin.MarketCurrencyLong;
        }
        if(!isBlank(coin.BaseCurrencyLong)){
            this.BaseCurrencyLong = coin.BaseCurrencyLong;
        }
        if(!isBlank(coin.MinTradeSize)){
            this.MinTradeSize = coin.MinTradeSize;
        }
        if(!isBlank(coin.MarketName)){
            this.MarketName = coin.MarketName;
        }
        if(!isBlank(coin.IsActive)){
            this.IsActive = coin.IsActive;
        }
        if(!isBlank(coin.Created)){
            this.Created = coin.Created;
        }
        if(!isBlank(coin.Notice)){
            this.Notice = coin.Notice;
        }
        if(!isBlank(coin.IsSponsored)){
            this.IsSponsored = coin.IsSponsored;
        }
        if(!isBlank(coin.LogoUrl)){
            this.LogoUrl = coin.LogoUrl;
        }
        if(!isBlank(coin.MarketName)){
            this.MarketName = coin.MarketName;
        }
        if(!isBlank(coin.High)){
            this.High = coin.High;
        }
        if(!isBlank(coin.Low)){
            this.Low = coin.Low;
        }
        if(!isBlank(coin.Volume)){
            this.Volume = coin.Volume;
        }
        if(!isBlank(coin.Last)){
            this.Last = coin.Last;
        }
        if(!isBlank(coin.BaseVolume)){
            this.BaseVolume = coin.BaseVolume;
        }
        if(!isBlank(coin.TimeStamp)){
            this.TimeStamp = coin.TimeStamp;
        }
        if(!isBlank(coin.Bid)){
            this.Bid = coin.Bid;
        }
        if(!isBlank(coin.Ask)){
            this.Ask = coin.Ask;
        }
        if(!isBlank(coin.OpenBuyOrders)){
            this.OpenBuyOrders = coin.OpenBuyOrders;
        }
        if(!isBlank(coin.OpenSellOrders)){
            this.OpenSellOrders = coin.OpenSellOrders;
        }
        if(!isBlank(coin.PrevDay)){
            this.PrevDay = coin.PrevDay;
        }
        if(!isBlank(coin.Created)){
            this.Created = coin.Created;
        }

        return this;
    }


    public String getMarketCurrency() {
        return MarketCurrency;
    }

    public void setMarketCurrency(String marketCurrency) {
        MarketCurrency = marketCurrency;
    }

    public String getBaseCurrency() {
        return BaseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        BaseCurrency = baseCurrency;
    }

    public String getMarketCurrencyLong() {
        return MarketCurrencyLong;
    }

    public void setMarketCurrencyLong(String marketCurrencyLong) {
        MarketCurrencyLong = marketCurrencyLong;
    }

    public String getBaseCurrencyLong() {
        return BaseCurrencyLong;
    }

    public void setBaseCurrencyLong(String baseCurrencyLong) {
        BaseCurrencyLong = baseCurrencyLong;
    }

    public String getMinTradeSize() {
        return MinTradeSize;
    }

    public void setMinTradeSize(String minTradeSize) {
        MinTradeSize = minTradeSize;
    }

    public String getIsActive() {
        return IsActive;
    }

    public void setIsActive(String isActive) {
        IsActive = isActive;
    }

    public String getNotice() {
        return Notice;
    }

    public void setNotice(String notice) {
        Notice = notice;
    }

    public String getIsSponsored() {
        return IsSponsored;
    }

    public void setIsSponsored(String isSponsored) {
        IsSponsored = isSponsored;
    }

    public String getLogoUrl() {
        return LogoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        LogoUrl = logoUrl;
    }

    public String getMarketName() {
        return MarketName;
    }

    public void setMarketName(String marketName) {
        MarketName = marketName;
    }

    public String getHigh() {
        return High;
    }

    public void setHigh(String high) {
        High = high;
    }

    public String getLow() {
        return Low;
    }

    public void setLow(String low) {
        Low = low;
    }

    public String getVolume() {
        return Volume;
    }

    public void setVolume(String volume) {
        Volume = volume;
    }

    public String getLast() {
        return Last;
    }

    public void setLast(String last) {
        Last = last;
    }

    public String getBaseVolume() {
        return BaseVolume;
    }

    public void setBaseVolume(String baseVolume) {
        BaseVolume = baseVolume;
    }

    public String getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        TimeStamp = timeStamp;
    }

    public String getBid() {
        return Bid;
    }

    public void setBid(String bid) {
        Bid = bid;
    }

    public String getAsk() {
        return Ask;
    }

    public void setAsk(String ask) {
        Ask = ask;
    }

    public String getOpenBuyOrders() {
        return OpenBuyOrders;
    }

    public void setOpenBuyOrders(String openBuyOrders) {
        OpenBuyOrders = openBuyOrders;
    }

    public String getOpenSellOrders() {
        return OpenSellOrders;
    }

    public void setOpenSellOrders(String openSellOrders) {
        OpenSellOrders = openSellOrders;
    }

    public String getPrevDay() {
        return PrevDay;
    }

    public void setPrevDay(String prevDay) {
        PrevDay = prevDay;
    }

    public String getCreated() {
        return Created;
    }

    public void setCreated(String created) {
        Created = created;
    }
}
