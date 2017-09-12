package nelk.io.crypton.retrofit;

import nelk.io.crypton.retrofit.models.BittrexResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface RetrofitService {

    @GET("public/getmarketsummaries")
    Call<BittrexResponse>getSummaries();

    @GET("public/getmarkets")
    Call<BittrexResponse>getMarkets();

    @GET("public/getmarketsummary")
    Call<BittrexResponse>getBTCLTC(@Query("market") String market);

    @GET("public/getticker")
    Call<BittrexResponse>getTicker(@Query("market") String market);

    @GET("account/getbalances")
    Call<BittrexResponse>getEncodedBalances(
            @Query(value = "apikey") String apikey,
            @Query(value = "nonce", encoded = true) String nonce,
            @Header("apisign") String signedHeader);
}
