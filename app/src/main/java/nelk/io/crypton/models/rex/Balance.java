package nelk.io.crypton.models.rex;


import android.os.Parcel;
import android.os.Parcelable;

import nelk.io.crypton.retrofit.models.CoinData;

public class Balance implements Parcelable {

    private String currencyName;
    private Double balance;
    private Double available;
    private Double pending;
    private String cryptoAddress;

    public Balance(CoinData coinData){
        this.currencyName = coinData.getCurrency();
        this.balance = coinData.getBalance();
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

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
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


    protected Balance(Parcel in) {
        currencyName = in.readString();
        balance = in.readByte() == 0x00 ? null : in.readDouble();
        available = in.readByte() == 0x00 ? null : in.readDouble();
        pending = in.readByte() == 0x00 ? null : in.readDouble();
        cryptoAddress = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(currencyName);
        if (balance == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(balance);
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
