package nelk.io.crypton.retrofit.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Response implements Parcelable {

    String success;
    String message;
    List<Summary> result;

    protected Response(Parcel in) {
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
    public static final Parcelable.Creator<Response> CREATOR = new Parcelable.Creator<Response>() {
        @Override
        public Response createFromParcel(Parcel in) {
            return new Response(in);
        }

        @Override
        public Response[] newArray(int size) {
            return new Response[size];
        }
    };

    public List<Summary> getResult() {
        return result;
    }
}