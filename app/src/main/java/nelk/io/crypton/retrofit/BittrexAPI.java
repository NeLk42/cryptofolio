package nelk.io.crypton.retrofit;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import nelk.io.crypton.MainActivity;
import nelk.io.crypton.retrofit.models.Response;
import nelk.io.crypton.retrofit.models.Summary;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BittrexAPI implements Callback<Response> {
    public static final String TAG = BittrexAPI.class.getSimpleName();

    public void getSummaries(){
        RetrofitService mRetrofitService = getRetrofitService();
        Call<Response> call = mRetrofitService.getSummaries();
        call.enqueue(this);
    }

    private RetrofitService getRetrofitService(){
        Retrofit retrofit = getRetrofit();
        return retrofit.create(RetrofitService.class);
    }

    private Retrofit getRetrofit(){
        return new Retrofit.Builder()
                .baseUrl("https://bittrex.com/api/v1.1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
        if(response.isSuccessful()){
            Gson gson = new Gson();
            String summaries = gson.toJson(response.body().getResult());
        } else {
            try {
                Log.d(TAG, response.errorBody().string());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onFailure(Call<Response> call, Throwable t) {
        t.printStackTrace();
    }
}
