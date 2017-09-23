package nelk.io.crypton.models.app;


import android.os.Parcel;
import android.os.Parcelable;

import nelk.io.crypton.retrofit.models.CoinData;

public class Balance implements Parcelable {

    private String currencyName;
    private Double currencyBalance;
    private Double available;
    private Double pending;
    private String cryptoAddress;

    private String logoUrl;
    private Double priceBought;
    private Double priceNow;
    private Double percentageChange;
    private Double totalSpent;
    private Double totalNow;
    private Double earnings;

    public Balance(CoinData coinData){
        this.currencyName = coinData.getCurrency();
        this.currencyBalance = coinData.getBalance();
        this.available = coinData.getAvailable();
        this.pending = coinData.getPending();
        this.cryptoAddress = coinData.getCryptoAddress();
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public Double getCurrencyBalance() {
        return currencyBalance;
    }

    public void setCurrencyBalance(Double currencyBalance) {
        this.currencyBalance = currencyBalance;
    }

    public Double getAvailable() {
        return available;
    }

    public void setAvailable(Double available) {
        this.available = available;
    }

    public Double getPending() {
        return pending;
    }

    public void setPending(Double pending) {
        this.pending = pending;
    }

    public String getCryptoAddress() {
        return cryptoAddress;
    }

    public void setCryptoAddress(String cryptoAddress) {
        this.cryptoAddress = cryptoAddress;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Double getPriceBought() {
        return priceBought;
    }

    public void setPriceBought(Double priceBought) {
        this.priceBought = priceBought;
    }

    public Double getPriceNow() {
        return priceNow;
    }

    public void setPriceNow(Double priceNow) {
        this.priceNow = priceNow;
    }

    public Double getPercentageChange() {
        return percentageChange;
    }

    public void setPercentageChange(Double percentageChange) {
        this.percentageChange = percentageChange;
    }

    public Double getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(Double totalSpent) {
        this.totalSpent = totalSpent;
    }

    public Double getTotalNow() {
        return totalNow;
    }

    public void setTotalNow(Double totalNow) {
        this.totalNow = totalNow;
    }

    public Double getEarnings() {
        return earnings;
    }

    public void setEarnings(Double earnings) {
        this.earnings = earnings;
    }

    protected Balance(Parcel in) {
        currencyName = in.readString();
        currencyBalance = in.readByte() == 0x00 ? null : in.readDouble();
        available = in.readByte() == 0x00 ? null : in.readDouble();
        pending = in.readByte() == 0x00 ? null : in.readDouble();
        cryptoAddress = in.readString();
        logoUrl = in.readString();
        priceBought = in.readByte() == 0x00 ? null : in.readDouble();
        priceNow = in.readByte() == 0x00 ? null : in.readDouble();
        percentageChange = in.readByte() == 0x00 ? null : in.readDouble();
        totalSpent = in.readByte() == 0x00 ? null : in.readDouble();
        totalNow = in.readByte() == 0x00 ? null : in.readDouble();
        earnings = in.readByte() == 0x00 ? null : in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(currencyName);
        if (currencyBalance == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(currencyBalance);
        }
        if (available == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(available);
        }
        if (pending == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(pending);
        }
        dest.writeString(cryptoAddress);
        dest.writeString(logoUrl);
        if (priceBought == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(priceBought);
        }
        if (priceNow == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(priceNow);
        }
        if (percentageChange == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(percentageChange);
        }
        if (totalSpent == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(totalSpent);
        }
        if (totalNow == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(totalNow);
        }
        if (earnings == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(earnings);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Balance> CREATOR = new Parcelable.Creator<Balance>() {
        @Override
        public Balance createFromParcel(Parcel in) {
            return new Balance(in);
        }

        @Override
        public Balance[] newArray(int size) {
            return new Balance[size];
        }
    };
}
