package nelk.io.crypton.retrofit;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nelk.io.crypton.recyclerview.RexDataAdapter;
import nelk.io.crypton.retrofit.models.Bittrex.RexResponse;
import nelk.io.crypton.retrofit.models.Bittrex.RexData;
import nelk.io.crypton.utils.NonceUtils;
import nelk.io.crypton.utils.Sha512Utils;
import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RexService implements Callback<RexResponse> {
    static final String TAG = RexService.class.getSimpleName();
    private final RetrofitConnection retrofitConnection = new RetrofitConnection();

    List<RexData> rexDataList;
    private RexDataAdapter mRexDataAdapter;
    RexApi mRexApi = retrofitConnection.getRetrofitService();

    public List<RexData> getAccountBalance() {

        String nonce = NonceUtils.generateNonce();
        String apiKey = APIConf.API_KEY;
        String secretApiKey = APIConf.API_SECRET_KEY;
        String baseBalancesUrl = "https://bittrex.com/api/v1.1/account/getbalances";
        String encodeNonce = NonceUtils.encodeNonce(nonce);

        String data = new StringBuilder(baseBalancesUrl)
                .append("?")
                .append("apikey")
                .append("=")
                .append(apiKey)
                .append("&")
                .append("nonce")
                .append("=")
                .append(encodeNonce)
                .toString();

        String signedHeader = Sha512Utils.calculateHMAC(data, secretApiKey);

        List<String> apiKeyList = new ArrayList<>();
        apiKeyList.add(apiKey);

        List<String> nonceList = new ArrayList<>();
        nonceList.add(nonce);


        HttpUrl.Builder url = new HttpUrl.Builder();
        url.encodedQuery(data);

        Call<RexResponse> call = mRexApi.getEncodedBalances(apiKey, nonce, signedHeader);
        try {
            Response<RexResponse> response = call.execute();
            RexResponse rexResponse = response.body();
            rexDataList = rexResponse.getDataFromResponse();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rexDataList;
    }

    public void getTicker(RexDataAdapter rexDataAdapter, RexData rexData) {
        Call<RexResponse> call = mRexApi.getTicker(rexData.getMarketName());
        mRexDataAdapter = rexDataAdapter;
        call.enqueue(this);
    }

    public void getDataList(RexDataAdapter rexDataAdapter, List<RexData> rexDataList) {

    }

    public void getSummaries(RexDataAdapter rexDataAdapter, List<RexData> mRexDataList) {
        Call<RexResponse> call = mRexApi.getSummaries();
        mRexDataAdapter = rexDataAdapter;
        rexDataList = mRexDataList;
        call.enqueue(this);
    }

    public void getMarkets(RexDataAdapter rexDataAdapter, List<RexData> mRexDataList) {
        Call<RexResponse> call = mRexApi.getMarkets();
        mRexDataAdapter = rexDataAdapter;
        rexDataList = mRexDataList;
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<RexResponse> call, Response<RexResponse> response) {
        if(response.isSuccessful()){
            rexDataList = getResponseCoins(response);
            mRexDataAdapter.updateCoinList(mRexDataAdapter, rexDataList);
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
