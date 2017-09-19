package nelk.io.crypton.retrofit.utils;

import android.support.annotation.Nullable;
import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

public class NonceUtils {
    public static String generateNonce() {
        String dateTimeString = Long.toString(new Date().getTime());
        byte[] nonceByte = dateTimeString.getBytes();
        return Base64.encodeToString(nonceByte, Base64.NO_WRAP);
    }

    @Nullable
    public static String encodeNonce(String value) {
        String encodedValue = null;
        try {
            encodedValue = URLEncoder.encode(value, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodedValue;
    }
}