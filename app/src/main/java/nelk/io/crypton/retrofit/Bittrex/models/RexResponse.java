package nelk.io.crypton.retrofit.Bittrex.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class RexResponse implements Parcelable {

    String success;
    String message;
    List<RexCoinData> result;

    protected RexResponse(Parcel in) {
        success = in.readString();
        message = in.readString();
        if (in.readByte() == 0x01) {
            result = new ArrayList<RexCoinData>();
            in.readList(result, RexCoinData.class.getClassLoader());
        } else {
            result = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(success);
        dest.writeString(message);
        if (result == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(result);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<RexResponse> CREATOR = new Parcelable.Creator<RexResponse>() {
        @Override
        public RexResponse createFromParcel(Parcel in) {
            return new RexResponse(in);
        }

        @Override
        public RexResponse[] newArray(int size) {
            return new RexResponse[size];
        }
    };

    public List<RexCoinData> getDataFromResponse(){
        return result;
    }
}