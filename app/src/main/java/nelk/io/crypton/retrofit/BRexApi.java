package nelk.io.crypton.retrofit;

import nelk.io.crypton.retrofit.models.BRexResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface BRexApi {

    @GET("public/getmarketsummaries")
    Call<BRexResponse>getSummaries();

    @GET("public/getmarkets")
    Call<BRexResponse>getMarkets();

    @GET("public/getmarketsummary")
    Call<BRexResponse>getBTCLTC(@Query("market") String market);

    @GET("public/getticker")
    Call<BRexResponse>getTicker(@Query("market") String market);

    @GET("account/getbalances")
    Call<BRexResponse>getEncodedBalances(
            @Query(value = "apikey") String apikey,
            @Query(value = "nonce", encoded = true) String nonce,
            @Header("apisign") String signedHeader);
}
