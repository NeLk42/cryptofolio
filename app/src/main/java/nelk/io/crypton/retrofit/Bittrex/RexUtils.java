package nelk.io.crypton.retrofit.Bittrex;

import nelk.io.crypton.utils.NonceUtils;
import nelk.io.crypton.utils.Sha512Utils;

public class RexUtils {
    public RexUtils() {
    }

    String getSignedHeader(String apiKey, String nonce) {
        String secretApiKey = RexConf.API_SECRET_KEY;
        String baseBalancesUrl = "https://bittrex.com/api/v1.1/account/getbalances";
        String encodeNonce = NonceUtils.encodeNonce(nonce);

        String data = new StringBuilder(baseBalancesUrl)
                .append("?")
                .append("apikey")
                .append("=")
                .append(apiKey)
                .append("&")
                .append("nonce")
                .append("=")
                .append(encodeNonce)
                .toString();

        return Sha512Utils.calculateHMAC(data, secretApiKey);
    }
}