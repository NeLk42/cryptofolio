package nelk.io.crypton.retrofit.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class BRexResponse implements Parcelable {

    String success;
    String message;
    List<Data> result;

    protected BRexResponse(Parcel in) {
        success = in.readString();
        message = in.readString();
        if (in.readByte() == 0x01) {
            result = new ArrayList<Data>();
            in.readList(result, Data.class.getClassLoader());
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
    public static final Parcelable.Creator<BRexResponse> CREATOR = new Parcelable.Creator<BRexResponse>() {
        @Override
        public BRexResponse createFromParcel(Parcel in) {
            return new BRexResponse(in);
        }

        @Override
        public BRexResponse[] newArray(int size) {
            return new BRexResponse[size];
        }
    };

    public List<Data> getDataFromResponse(){
        return result;
    }
}