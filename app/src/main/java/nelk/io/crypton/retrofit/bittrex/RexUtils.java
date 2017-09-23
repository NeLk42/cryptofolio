package nelk.io.crypton.retrofit.bittrex;

import android.net.Uri;

import nelk.io.crypton.models.app.Credentials;
import nelk.io.crypton.retrofit.utils.Sha512Utils;

class RexUtils {

    private static final String APIKEY = "apikey";
    private static final String NONCE = "nonce";

    static String generateSignedHeader(String brokerUrl, Credentials credentials, String nonce) {

        Uri data = Uri.parse(brokerUrl)
                .buildUpon()
                .appendQueryParameter(APIKEY, credentials.getKey())
                .appendQueryParameter(NONCE, nonce)
                .build();

        return Sha512Utils.calculateHMAC(data.toString(), credentials.getPrivateKey());
    }
}