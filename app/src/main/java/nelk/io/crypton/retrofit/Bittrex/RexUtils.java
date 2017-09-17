package nelk.io.crypton.retrofit.Bittrex;

import android.net.Uri;

import nelk.io.crypton.models.rex.Credentials;
import nelk.io.crypton.utils.NonceUtils;
import nelk.io.crypton.utils.Sha512Utils;

import static nelk.io.crypton.utils.NonceUtils.encodeNonce;

public class RexUtils {

    public static String getSignedHeader(String brokerUrl, Credentials credentials, String nonce) {

        Uri data = Uri.parse(brokerUrl)
                .buildUpon()
                .appendQueryParameter("apikey", credentials.getKey())
                .appendQueryParameter("nonce", nonce)
                .build();

        return Sha512Utils.calculateHMAC(data.toString(), credentials.getPrivateKey());
    }
}