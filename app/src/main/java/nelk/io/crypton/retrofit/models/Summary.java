package nelk.io.crypton.retrofit.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Summary implements Parcelable {
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

    protected Summary(Parcel in) {
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
    public static final Parcelable.Creator<Summary> CREATOR = new Parcelable.Creator<Summary>() {
        @Override
        public Summary createFromParcel(Parcel in) {
            return new Summary(in);
        }

        @Override
        public Summary[] newArray(int size) {
            return new Summary[size];
        }
    };

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
}