package nelk.io.crypton.retrofit.bittrex;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import nelk.io.crypton.models.app.Credentials;
import nelk.io.crypton.models.app.Portfolio;
import nelk.io.crypton.recyclerview.BalanceAdapter;
import nelk.io.crypton.retrofit.bittrex.models.RexCoinData;
import nelk.io.crypton.retrofit.bittrex.models.RexResponse;
import nelk.io.crypton.retrofit.RexApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static nelk.io.crypton.retrofit.bittrex.RexUtils.generateSignedHeader;
import static nelk.io.crypton.retrofit.utils.NonceUtils.generateNonce;

public class RexAccountService implements Callback<RexResponse> {

    private static final String TAG = RexAccountService.class.getSimpleName();
    private final RetrofitConnection retrofitConnection = new RetrofitConnection();

    private RexApi mRexApi;
    private BalanceAdapter mBalanceAdapter;
    private Portfolio portfolio;

    public RexAccountService(Portfolio portfolio, BalanceAdapter balanceAdapter){
        this.mRexApi = retrofitConnection.getRetrofitService(portfolio.getBroker().getBaseUrl());
        this.mBalanceAdapter = balanceAdapter;
        this.portfolio = portfolio;
    }

    public void updateAccountBalance() {
        Call<RexResponse> call = generateBalanceCall();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<RexResponse> call, Response<RexResponse> response) {
        if(response.isSuccessful()){
            List<RexCoinData> rexDataList = getResponseCoins(response);
            mBalanceAdapter.updateBalances(this.portfolio, rexDataList);
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

    private Call<RexResponse> generateBalanceCall() {
        String nonce = generateNonce();
        Credentials credentials = portfolio.getCredentials();
        String brokerBalancesUrl = Uri.parse(portfolio.getBroker().getBaseUrl())
                .buildUpon()
                .appendEncodedPath(portfolio.getBroker().getBalancesUrl())
                .build().toString();

        String signedHeader = generateSignedHeader(brokerBalancesUrl, credentials, nonce);

        return mRexApi.getBalances(credentials.getKey(), nonce, signedHeader);
    }

    private List<RexCoinData> getResponseCoins(Response<RexResponse> response) {
        RexResponse rexResponseModel = response.body();
        return rexResponseModel.getDataFromResponse();
    }

}
