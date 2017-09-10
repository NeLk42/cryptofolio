package nelk.io.crypton.retrofit;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import nelk.io.crypton.MainActivity;
import nelk.io.crypton.recyclerview.CoinAdapter;
import nelk.io.crypton.retrofit.models.BittrexResponse;
import nelk.io.crypton.retrofit.models.Coin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BittrexAPI implements Callback<BittrexResponse> {
    public static final String TAG = BittrexAPI.class.getSimpleName();
    public static String BASE_URL= "https://bittrex.com/api/v1.1/";

    List<Coin> coinList;
    private CoinAdapter mCoinAdapter;

    public void getSummaries(CoinAdapter coinAdapter, List<Coin> mCoinList) {
        mCoinAdapter = coinAdapter;
        coinList = mCoinList;

        RetrofitService mRetrofitService = getRetrofitService();
        Call<BittrexResponse> call = mRetrofitService.getSummaries();
        call.enqueue(this);
    }

    public void getMarkets(CoinAdapter coinAdapter, List<Coin> mCoinList) {
        mCoinAdapter = coinAdapter;
        coinList = mCoinList;

        RetrofitService mRetrofitService = getRetrofitService();
        Call<BittrexResponse> call = mRetrofitService.getMarkets();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<BittrexResponse> call, Response<BittrexResponse> response) {
        if(response.isSuccessful()){
            BittrexResponse bittrexResponseModel = response.body();
            coinList = bittrexResponseModel.getSummariesFromResponse();
            mCoinAdapter.updateCoinList(mCoinAdapter, coinList);
        } else {
            try {
                Log.d(TAG, response.errorBody().string());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onFailure(Call<BittrexResponse> call, Throwable t) {
        t.printStackTrace();
    }

    private RetrofitService getRetrofitService(){
        Retrofit retrofit = getRetrofit();
        return retrofit.create(RetrofitService.class);
    }

    private Retrofit getRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
