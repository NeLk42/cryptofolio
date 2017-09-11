package nelk.io.crypton.retrofit.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Market extends Coin implements Parcelable {
    String MarketCurrency;
    String BaseCurrency;
    String MarketCurrencyLong;
    String BaseCurrencyLong;
    String MinTradeSize;
    String MarketName;
    String IsActive;
    String Created;
    String Notice;
    String IsSponsored;
    String LogoUrl;

    protected Market(Parcel in) {
        MarketCurrency = in.readString();
        BaseCurrency = in.readString();
        MarketCurrencyLong = in.readString();
        BaseCurrencyLong = in.readString();
        MinTradeSize = in.readString();
        MarketName = in.readString();
        IsActive = in.readString();
        Created = in.readString();
        Notice = in.readString();
        IsSponsored = in.readString();
        LogoUrl = in.readString();
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
        dest.writeString(MarketName);
        dest.writeString(IsActive);
        dest.writeString(Created);
        dest.writeString(Notice);
        dest.writeString(IsSponsored);
        dest.writeString(LogoUrl);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Market> CREATOR = new Parcelable.Creator<Market>() {
        @Override
        public Market createFromParcel(Parcel in) {
            return new Market(in);
        }

        @Override
        public Market[] newArray(int size) {
            return new Market[size];
        }
    };

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

    public String getMarketName() {
        return MarketName;
    }

    public String getIsActive() {
        return IsActive;
    }

    public String getCreated() {
        return Created;
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
}