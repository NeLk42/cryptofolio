package nelk.io.crypton.retrofit;

import nelk.io.crypton.retrofit.models.BittrexResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitService {

    @GET("public/getmarketsummaries")
    Call<BittrexResponse>getSummaries();


    @GET("public/getmarketsummary?market=btc-ltc")
    Call<BittrexResponse>getBTCLTC();
}
