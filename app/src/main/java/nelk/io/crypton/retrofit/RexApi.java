package nelk.io.crypton.retrofit;

import nelk.io.crypton.retrofit.Bittrex.models.RexResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface RexApi {

    @GET("public/getticker")
    Call<RexResponse>getTicker(@Query("market") String market);

    @GET("public/getmarkets")
    Call<RexResponse>getMarkets();

    @GET("public/getmarketsummary")
    Call<RexResponse>getBTCLTC(@Query("market") String market);

    @GET("public/getmarketsummaries")
    Call<RexResponse>getSummaries();

    @GET("account/getbalances")
    Call<RexResponse> getBalances(
            @Query(value = "apikey") String apikey,
            @Query(value = "nonce", encoded = true) String nonce,
            @Header("apisign") String signedHeader);
}
