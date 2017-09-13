package nelk.io.crypton.retrofit;

import nelk.io.crypton.retrofit.Bittrex.RexApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConnection {
    static String BASE_URL = "https://bittrex.com/api/v1.1/";

    public RetrofitConnection() {
    }

    public RexApi getRetrofitService() {
        Retrofit retrofit = getRetrofit();
        return retrofit.create(RexApi.class);
    }

    Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}