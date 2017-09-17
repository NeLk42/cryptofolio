package nelk.io.crypton.retrofit.Bittrex;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import nelk.io.crypton.models.rex.Credentials;
import nelk.io.crypton.models.rex.Portfolio;
import nelk.io.crypton.recyclerview.BalanceAdapter;
import nelk.io.crypton.retrofit.Bittrex.models.RexResponse;
import nelk.io.crypton.retrofit.Bittrex.models.RexResponseData;
import nelk.io.crypton.retrofit.RexApi;
import nelk.io.crypton.retrofit.models.CoinData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static nelk.io.crypton.retrofit.Bittrex.RexUtils.getSignedHeader;
import static nelk.io.crypton.utils.NonceUtils.generateNonce;

public class RexAccountService implements Callback<RexResponse> {
    private static final String TAG = RexAccountService.class.getSimpleName();
    private final RetrofitConnection retrofitConnection = new RetrofitConnection();

    private BalanceAdapter mBalanceAdapter;
    private RexApi mRexApi;
    private Portfolio portfolio;

    public RexAccountService(Portfolio portfolio, BalanceAdapter balanceAdapter){
        this.mRexApi = retrofitConnection.getRetrofitService(portfolio.getBroker().getBaseUrl());
        this.mBalanceAdapter = balanceAdapter;
        this.portfolio = portfolio;
    }

    public void updateAccountBalance() {
        String nonce = generateNonce();
        Credentials credentials = portfolio.getCredentials();
        String brokerBalancesUrl = Uri.parse(portfolio.getBroker().getBaseUrl())
                .buildUpon()
                .appendEncodedPath(portfolio.getBroker().getBalancesUrl())
                .build().toString();

        String signedHeader = getSignedHeader(brokerBalancesUrl, credentials, nonce);

        Call<RexResponse> call = mRexApi.getBalances(credentials.getKey(), nonce, signedHeader);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<RexResponse> call, Response<RexResponse> response) {
        if(response.isSuccessful()){
            List<? extends CoinData> rexDataList = getResponseCoins(response);
            mBalanceAdapter.updateBalances(this.portfolio, rexDataList);
        } else {
            try {
                Log.d(TAG, response.errorBody().string());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private List<RexResponseData> getResponseCoins(Response<RexResponse> response) {
        RexResponse rexResponseModel = response.body();
        return rexResponseModel.getDataFromResponse();
    }

    @Override
    public void onFailure(Call<RexResponse> call, Throwable t) {
        t.printStackTrace();
    }

}
