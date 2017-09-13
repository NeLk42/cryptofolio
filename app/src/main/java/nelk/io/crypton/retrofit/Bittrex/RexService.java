package nelk.io.crypton.retrofit.Bittrex;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import nelk.io.crypton.retrofit.models.CoinData;
import nelk.io.crypton.recyclerview.CoinAdapter;
import nelk.io.crypton.retrofit.Bittrex.models.RexData;
import nelk.io.crypton.retrofit.Bittrex.models.RexResponse;
import nelk.io.crypton.retrofit.RexApi;
import nelk.io.crypton.utils.NonceUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RexService implements Callback<RexResponse> {
    static final String TAG = RexService.class.getSimpleName();
    private final RexConnection rexConnection = new RexConnection();
    private final RexUtils rexUtils = new RexUtils();

    List<? extends CoinData> rexDataList;
    private CoinAdapter mCoinAdapter;
    RexApi mRexApi = rexConnection.getRetrofitService();

    public RexService(CoinAdapter coinAdapter){
        mCoinAdapter = coinAdapter;
    }

    public void getDataList(List<CoinData> rexDataList) {
        // TODO: Generalize all call init below into this method.
    }


    public void getAccountBalance() {
        String nonce = NonceUtils.generateNonce();
        String signedHeader = rexUtils.getSignedHeader(RexConf.API_KEY, nonce);

        Call<RexResponse> call = mRexApi.getBalances(RexConf.API_KEY, nonce, signedHeader);
        call.enqueue(this);
    }

    public void getTicker(RexData rexData) {
        Call<RexResponse> call = mRexApi.getTicker(rexData.getMarketName());
        call.enqueue(this);
    }

    public void getSummaries(List<CoinData> mRexDataList) {
        Call<RexResponse> call = mRexApi.getSummaries();
        rexDataList = mRexDataList;
        call.enqueue(this);
    }

    public void getMarkets(List<CoinData> mRexDataList) {
        Call<RexResponse> call = mRexApi.getMarkets();
        rexDataList = mRexDataList;
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<RexResponse> call, Response<RexResponse> response) {
        if(response.isSuccessful()){
            rexDataList = getResponseCoins(response);
            mCoinAdapter.updateBalances(mCoinAdapter, rexDataList);
//            mCoinAdapter.updateCoinList(mCoinAdapter, rexDataList);
        } else {
            try {
                Log.d(TAG, response.errorBody().string());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private List<RexData> getResponseCoins(Response<RexResponse> response) {
        RexResponse rexResponseModel = response.body();
        return rexResponseModel.getDataFromResponse();
    }

    @Override
    public void onFailure(Call<RexResponse> call, Throwable t) {
        t.printStackTrace();
    }

}
