package nelk.io.crypton.retrofit;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import nelk.io.crypton.MainActivity;
import nelk.io.crypton.recyclerview.SummaryAdapter;
import nelk.io.crypton.retrofit.models.BittrexResponse;
import nelk.io.crypton.retrofit.models.Summary;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BittrexAPI implements Callback<BittrexResponse> {
    public static final String TAG = BittrexAPI.class.getSimpleName();
    public static String BASE_URL= "https://bittrex.com/api/v1.1/";

    List<Summary> summaryList;
    private SummaryAdapter mSummaryAdapter;

    public void getSummaries(SummaryAdapter summaryAdapter, List<Summary> mSummaryList) {
        mSummaryAdapter = summaryAdapter;
        summaryList = mSummaryList;

        RetrofitService mRetrofitService = getRetrofitService();
        Call<BittrexResponse> call = mRetrofitService.getSummaries();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<BittrexResponse> call, Response<BittrexResponse> response) {
        if(response.isSuccessful()){
            BittrexResponse bittrexResponseModel = response.body();
            summaryList = bittrexResponseModel.getSummariesFromResponse();
            mSummaryAdapter.updateSummaryList(mSummaryAdapter, summaryList);
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
