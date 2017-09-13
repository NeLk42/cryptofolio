package nelk.io.crypton.retrofit.models.Bittrex;

import android.os.Parcel;
import android.os.Parcelable;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class RexData implements Parcelable {

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

    protected RexData(Parcel in) {
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
    public static final Parcelable.Creator<RexData> CREATOR = new Parcelable.Creator<RexData>() {
        @Override
        public RexData createFromParcel(Parcel in) {
            return new RexData(in);
        }

        @Override
        public RexData[] newArray(int size) {
            return new RexData[size];
        }
    };

    public RexData(RexData rexData){
        addData(rexData);
    }

    public RexData addData(RexData rexData){
        if(!isBlank(rexData.MarketCurrency)){
            this.MarketCurrency = rexData.MarketCurrency;
        }
        if(!isBlank(rexData.BaseCurrency)){
            this.BaseCurrency = rexData.BaseCurrency;
        }
        if(!isBlank(rexData.MarketCurrencyLong)){
            this.MarketCurrencyLong = rexData.MarketCurrencyLong;
        }
        if(!isBlank(rexData.BaseCurrencyLong)){
            this.BaseCurrencyLong = rexData.BaseCurrencyLong;
        }
        if(!isBlank(rexData.MinTradeSize)){
            this.MinTradeSize = rexData.MinTradeSize;
        }
        if(!isBlank(rexData.MarketName)){
            this.MarketName = rexData.MarketName;
        }
        if(!isBlank(rexData.IsActive)){
            this.IsActive = rexData.IsActive;
        }
        if(!isBlank(rexData.Created)){
            this.Created = rexData.Created;
        }
        if(!isBlank(rexData.Notice)){
            this.Notice = rexData.Notice;
        }
        if(!isBlank(rexData.IsSponsored)){
            this.IsSponsored = rexData.IsSponsored;
        }
        if(!isBlank(rexData.LogoUrl)){
            this.LogoUrl = rexData.LogoUrl;
        }
        if(!isBlank(rexData.MarketName)){
            this.MarketName = rexData.MarketName;
        }
        if(!isBlank(rexData.High)){
            this.High = rexData.High;
        }
        if(!isBlank(rexData.Low)){
            this.Low = rexData.Low;
        }
        if(!isBlank(rexData.Volume)){
            this.Volume = rexData.Volume;
        }
        if(!isBlank(rexData.Last)){
            this.Last = rexData.Last;
        }
        if(!isBlank(rexData.BaseVolume)){
            this.BaseVolume = rexData.BaseVolume;
        }
        if(!isBlank(rexData.TimeStamp)){
            this.TimeStamp = rexData.TimeStamp;
        }
        if(!isBlank(rexData.Bid)){
            this.Bid = rexData.Bid;
        }
        if(!isBlank(rexData.Ask)){
            this.Ask = rexData.Ask;
        }
        if(!isBlank(rexData.OpenBuyOrders)){
            this.OpenBuyOrders = rexData.OpenBuyOrders;
        }
        if(!isBlank(rexData.OpenSellOrders)){
            this.OpenSellOrders = rexData.OpenSellOrders;
        }
        if(!isBlank(rexData.PrevDay)){
            this.PrevDay = rexData.PrevDay;
        }
        if(!isBlank(rexData.Created)){
            this.Created = rexData.Created;
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
