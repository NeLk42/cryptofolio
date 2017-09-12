package nelk.io.crypton.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConnection {
    static String BASE_URL = "https://bittrex.com/api/v1.1/";

    public RetrofitConnection() {
    }

    BRexApi getRetrofitService() {
        Retrofit retrofit = getRetrofit();
        return retrofit.create(BRexApi.class);
    }

    Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}