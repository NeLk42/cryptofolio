package nelk.io.crypton.retrofit.Bittrex;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import nelk.io.crypton.models.rex.User;
import nelk.io.crypton.retrofit.Bittrex.models.RexResponseData;
import nelk.io.crypton.retrofit.models.CoinData;
import nelk.io.crypton.recyclerview.CoinAdapter;
import nelk.io.crypton.retrofit.Bittrex.models.RexResponse;
import nelk.io.crypton.retrofit.RexApi;
import nelk.io.crypton.utils.NonceUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RexAccountService implements Callback<RexResponse> {
    static final String TAG = RexAccountService.class.getSimpleName();
    private final RexConnection rexConnection = new RexConnection();
    private final RexUtils rexUtils = new RexUtils();
    private final User user;

    List<? extends CoinData> rexDataList;
    private CoinAdapter mCoinAdapter;
    RexApi mRexApi = rexConnection.getRetrofitService();

    public RexAccountService(User user, CoinAdapter coinAdapter){
        this.user = user;
        this.mCoinAdapter = coinAdapter;
    }

    public void getAccountBalance() {
        String nonce = NonceUtils.generateNonce();
        String signedHeader = rexUtils.getSignedHeader(RexConf.API_KEY, nonce);

        Call<RexResponse> call = mRexApi.getBalances(RexConf.API_KEY, nonce, signedHeader);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<RexResponse> call, Response<RexResponse> response) {
        if(response.isSuccessful()){
            rexDataList = getResponseCoins(response);
            mCoinAdapter.updateBalances(user, rexDataList);
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
