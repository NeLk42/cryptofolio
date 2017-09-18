package nelk.io.crypton.retrofit.Bittrex;

import android.net.Uri;

import nelk.io.crypton.models.rex.Credentials;
import nelk.io.crypton.utils.NonceUtils;
import nelk.io.crypton.utils.Sha512Utils;

import static nelk.io.crypton.utils.NonceUtils.encodeNonce;

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