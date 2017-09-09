package nelk.io.crypton.retrofit.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class BittrexResponse implements Parcelable {

    String success;
    String message;
    List<Summary> result;

    protected BittrexResponse(Parcel in) {
        success = in.readString();
        message = in.readString();
        if (in.readByte() == 0x01) {
            result = new ArrayList<Summary>();
            in.readList(result, Summary.class.getClassLoader());
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
    public static final Parcelable.Creator<BittrexResponse> CREATOR = new Parcelable.Creator<BittrexResponse>() {
        @Override
        public BittrexResponse createFromParcel(Parcel in) {
            return new BittrexResponse(in);
        }

        @Override
        public BittrexResponse[] newArray(int size) {
            return new BittrexResponse[size];
        }
    };

    public List<Summary> getSummariesFromResponse(){
        return result;
    }
}