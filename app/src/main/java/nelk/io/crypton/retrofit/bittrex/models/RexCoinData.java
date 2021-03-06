package nelk.io.crypton.retrofit.bittrex.models;

import android.os.Parcel;
import android.os.Parcelable;

import nelk.io.crypton.retrofit.models.CoinData;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class RexCoinData implements CoinData {

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

    String Currency;
    Double Balance;
    Double Available;
    Double Pending;
    String CryptoAddress;

    protected RexCoinData(Parcel in) {
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
        Currency = in.readString();
        Balance = in.readByte() == 0x00 ? null : in.readDouble();
        Available = in.readByte() == 0x00 ? null : in.readDouble();
        Pending = in.readByte() == 0x00 ? null : in.readDouble();
        CryptoAddress = in.readString();
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
        dest.writeString(Currency);
        if (Balance == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(Balance);
        }
        if (Available == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(Available);
        }
        if (Pending == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(Pending);
        }
        dest.writeString(CryptoAddress);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<RexCoinData> CREATOR = new Parcelable.Creator<RexCoinData>() {
        @Override
        public RexCoinData createFromParcel(Parcel in) {
            return new RexCoinData(in);
        }

        @Override
        public RexCoinData[] newArray(int size) {
            return new RexCoinData[size];
        }
    };

    public RexCoinData(RexCoinData rexCoinData){
        addData(rexCoinData);
    }

    public RexCoinData addData(CoinData rexData){
        if(!isBlank(rexData.getMarketCurrency())){
            this.MarketCurrency = rexData.getMarketCurrency();
        }
        if(!isBlank(rexData.getBaseCurrency())){
            this.BaseCurrency = rexData.getBaseCurrency();
        }
        if(!isBlank(rexData.getMarketCurrencyLong())){
            this.MarketCurrencyLong = rexData.getMarketCurrencyLong();
        }
        if(!isBlank(rexData.getBaseCurrencyLong())){
            this.BaseCurrencyLong = rexData.getBaseCurrencyLong();
        }
        if(!isBlank(rexData.getMinTradeSize())){
            this.MinTradeSize = rexData.getMinTradeSize();
        }
        if(!isBlank(rexData.getMarketName())){
            this.MarketName = rexData.getMarketName();
        }
        if(!isBlank(rexData.getIsActive())){
            this.IsActive = rexData.getIsActive();
        }
        if(!isBlank(rexData.getCreated())){
            this.Created = rexData.getCreated();
        }
        if(!isBlank(rexData.getNotice())){
            this.Notice = rexData.getNotice();
        }
        if(!isBlank(rexData.getIsSponsored())){
            this.IsSponsored = rexData.getIsSponsored();
        }
        if(!isBlank(rexData.getLogoUrl())){
            this.LogoUrl = rexData.getLogoUrl();
        }
        if(!isBlank(rexData.getMarketName())){
            this.MarketName = rexData.getMarketName();
        }
        if(!isBlank(rexData.getHigh())){
            this.High = rexData.getHigh();
        }
        if(!isBlank(rexData.getLow())){
            this.Low = rexData.getLow();
        }
        if(!isBlank(rexData.getVolume())){
            this.Volume = rexData.getVolume();
        }
        if(!isBlank(rexData.getLast())){
            this.Last = rexData.getLast();
        }
        if(!isBlank(rexData.getBaseVolume())){
            this.BaseVolume = rexData.getBaseVolume();
        }
        if(!isBlank(rexData.getTimeStamp())){
            this.TimeStamp = rexData.getTimeStamp();
        }
        if(!isBlank(rexData.getBid())){
            this.Bid = rexData.getBid();
        }
        if(!isBlank(rexData.getAsk())){
            this.Ask = rexData.getAsk();
        }
        if(!isBlank(rexData.getOpenBuyOrders())){
            this.OpenBuyOrders = rexData.getOpenBuyOrders();
        }
        if(!isBlank(rexData.getOpenSellOrders())){
            this.OpenSellOrders = rexData.getOpenSellOrders();
        }
        if(!isBlank(rexData.getPrevDay())){
            this.PrevDay = rexData.getPrevDay();
        }
        if(!isBlank(rexData.getCreated())){
            this.Created = rexData.getCreated();
        }
        if(!isBlank(rexData.getCurrency())){
            this.Currency = rexData.getCurrency();
        }
        if(rexData.getBalance() != null && rexData.getBalance() != 0){
            this.Balance = rexData.getBalance();
        }
        if(rexData.getAvailable() != null && rexData.getAvailable() != 0){
            this.Available = rexData.getAvailable();
        }
        if(rexData.getPending() != null && rexData.getPending() != 0){
            this.Pending = rexData.getPending();
        }
        if(!isBlank(rexData.getCryptoAddress())){
            this.CryptoAddress = rexData.getCryptoAddress();
        }

        return this;
    }

    public String getMarketCurrency() {
        return MarketCurrency;
    }

    public String getBaseCurrency() {
        return BaseCurrency;
    }

    public String getMarketCurrencyLong() {
        return MarketCurrencyLong;
    }

    public String getBaseCurrencyLong() {
        return BaseCurrencyLong;
    }

    public String getMinTradeSize() {
        return MinTradeSize;
    }

    public String getIsActive() {
        return IsActive;
    }

    public String getNotice() {
        return Notice;
    }

    public String getIsSponsored() {
        return IsSponsored;
    }

    public String getLogoUrl() {
        return LogoUrl;
    }

    public String getMarketName() {
        return MarketName;
    }

    public String getHigh() {
        return High;
    }

    public String getLow() {
        return Low;
    }

    public String getVolume() {
        return Volume;
    }

    public String getLast() {
        return Last;
    }

    public String getBaseVolume() {
        return BaseVolume;
    }

    public String getTimeStamp() {
        return TimeStamp;
    }

    public String getBid() {
        return Bid;
    }

    public String getAsk() {
        return Ask;
    }

    public String getOpenBuyOrders() {
        return OpenBuyOrders;
    }

    public String getOpenSellOrders() {
        return OpenSellOrders;
    }

    public String getPrevDay() {
        return PrevDay;
    }

    public String getCreated() {
        return Created;
    }

    public String getCurrency() {
        return Currency;
    }

    public Double getBalance() {
        return Balance;
    }

    public Double getAvailable() {
        return Available;
    }

    public Double getPending() {
        return Pending;
    }

    public String getCryptoAddress() {
        return CryptoAddress;
    }
}
