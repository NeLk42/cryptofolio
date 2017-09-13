package nelk.io.crypton.retrofit.models;

import android.os.Parcel;
import android.os.Parcelable;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class Data implements Parcelable {

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

    protected Data(Parcel in) {
        MarketCurrency = in.readString();
        BaseCurrency = in.readString();
        MarketCurrencyLong = in.readString();
        BaseCurrencyLong = in.readString();
        MinTradeSize = in.readString();
        IsActive = in.readString();
        Notice = in.readString();
        IsSponsored = in.readString();
        LogoUrl = in.readString();
        MarketName = in.readString();
        High = in.readString();
        Low = in.readString();
        Volume = in.readString();
        Last = in.readString();
        BaseVolume = in.readString();
        TimeStamp = in.readString();
        Bid = in.readString();
        Ask = in.readString();
        OpenBuyOrders = in.readString();
        OpenSellOrders = in.readString();
        PrevDay = in.readString();
        Created = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(MarketCurrency);
        dest.writeString(BaseCurrency);
        dest.writeString(MarketCurrencyLong);
        dest.writeString(BaseCurrencyLong);
        dest.writeString(MinTradeSize);
        dest.writeString(IsActive);
        dest.writeString(Notice);
        dest.writeString(IsSponsored);
        dest.writeString(LogoUrl);
        dest.writeString(MarketName);
        dest.writeString(High);
        dest.writeString(Low);
        dest.writeString(Volume);
        dest.writeString(Last);
        dest.writeString(BaseVolume);
        dest.writeString(TimeStamp);
        dest.writeString(Bid);
        dest.writeString(Ask);
        dest.writeString(OpenBuyOrders);
        dest.writeString(OpenSellOrders);
        dest.writeString(PrevDay);
        dest.writeString(Created);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Data> CREATOR = new Parcelable.Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    public Data(Data data){
        addData(data);
    }

    public Data addData(Data data){
        if(!isBlank(data.MarketCurrency)){
            this.MarketCurrency = data.MarketCurrency;
        }
        if(!isBlank(data.BaseCurrency)){
            this.BaseCurrency = data.BaseCurrency;
        }
        if(!isBlank(data.MarketCurrencyLong)){
            this.MarketCurrencyLong = data.MarketCurrencyLong;
        }
        if(!isBlank(data.BaseCurrencyLong)){
            this.BaseCurrencyLong = data.BaseCurrencyLong;
        }
        if(!isBlank(data.MinTradeSize)){
            this.MinTradeSize = data.MinTradeSize;
        }
        if(!isBlank(data.MarketName)){
            this.MarketName = data.MarketName;
        }
        if(!isBlank(data.IsActive)){
            this.IsActive = data.IsActive;
        }
        if(!isBlank(data.Created)){
            this.Created = data.Created;
        }
        if(!isBlank(data.Notice)){
            this.Notice = data.Notice;
        }
        if(!isBlank(data.IsSponsored)){
            this.IsSponsored = data.IsSponsored;
        }
        if(!isBlank(data.LogoUrl)){
            this.LogoUrl = data.LogoUrl;
        }
        if(!isBlank(data.MarketName)){
            this.MarketName = data.MarketName;
        }
        if(!isBlank(data.High)){
            this.High = data.High;
        }
        if(!isBlank(data.Low)){
            this.Low = data.Low;
        }
        if(!isBlank(data.Volume)){
            this.Volume = data.Volume;
        }
        if(!isBlank(data.Last)){
            this.Last = data.Last;
        }
        if(!isBlank(data.BaseVolume)){
            this.BaseVolume = data.BaseVolume;
        }
        if(!isBlank(data.TimeStamp)){
            this.TimeStamp = data.TimeStamp;
        }
        if(!isBlank(data.Bid)){
            this.Bid = data.Bid;
        }
        if(!isBlank(data.Ask)){
            this.Ask = data.Ask;
        }
        if(!isBlank(data.OpenBuyOrders)){
            this.OpenBuyOrders = data.OpenBuyOrders;
        }
        if(!isBlank(data.OpenSellOrders)){
            this.OpenSellOrders = data.OpenSellOrders;
        }
        if(!isBlank(data.PrevDay)){
            this.PrevDay = data.PrevDay;
        }
        if(!isBlank(data.Created)){
            this.Created = data.Created;
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
