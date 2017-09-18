package nelk.io.crypton.retrofit.Bittrex;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import nelk.io.crypton.models.rex.Portfolio;
import nelk.io.crypton.recyclerview.BalanceAdapter;
import nelk.io.crypton.retrofit.Bittrex.models.RexResponse;
import nelk.io.crypton.retrofit.Bittrex.models.RexCoinData;
import nelk.io.crypton.retrofit.RexApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RexPublicService implements Callback<RexResponse> {

    private static final String TAG = RexPublicService.class.getSimpleName();
    private final RetrofitConnection retrofitConnection = new RetrofitConnection();
    private final Portfolio portfolio;

    private BalanceAdapter mBalanceAdapter;
    private RexApi mRexApi;

    public RexPublicService(Portfolio portfolio, BalanceAdapter balanceAdapter){
        this.mRexApi = retrofitConnection.getRetrofitService(portfolio.getBroker().getBaseUrl());
        this.mBalanceAdapter = balanceAdapter;
        this.portfolio = portfolio;
    }

    public void pullSummariesData() {
        Call<RexResponse> call = mRexApi.getSummaries();
        call.enqueue(this);
    }

    public void pullMarketsData() {
        Call<RexResponse> call = mRexApi.getMarkets();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<RexResponse> call, Response<RexResponse> response) {
        if(response.isSuccessful()){
            List<RexCoinData> rexDataList = getResponseCoins(response);
            mBalanceAdapter.updateBrokerMarkets(portfolio, rexDataList);
        } else {
            try {
                Log.d(TAG, response.errorBody().string());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onFailure(Call<RexResponse> call, Throwable t) {
        t.printStackTrace();
    }


    // Auxiliary methods

    private List<RexCoinData> getResponseCoins(Response<RexResponse> response) {
        RexResponse rexResponseModel = response.body();
        return rexResponseModel.getDataFromResponse();
    }
}
