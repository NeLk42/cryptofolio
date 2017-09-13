package nelk.io.crypton.retrofit;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nelk.io.crypton.recyclerview.DataAdapter;
import nelk.io.crypton.retrofit.models.BRexResponse;
import nelk.io.crypton.retrofit.models.Data;
import nelk.io.crypton.utils.SHA512;
import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static nelk.io.crypton.utils.SHA512.generateNonce;

public class BRexService implements Callback<BRexResponse> {
    static final String TAG = BRexService.class.getSimpleName();
    private final RetrofitConnection retrofitConnection = new RetrofitConnection();

    List<Data> dataList;
    private DataAdapter mDataAdapter;
    BRexApi mBRexApi = retrofitConnection.getRetrofitService();

    public List<Data> getAccountBalance() {

        String nonce = generateNonce();
        String apiKey = APIConf.API_KEY;
        String secretApiKey = APIConf.API_SECRET_KEY;
        String baseBalancesUrl = "https://bittrex.com/api/v1.1/account/getbalances";
        String encodeNonce = SHA512.encodeNonce(nonce);

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

        String signedHeader = SHA512.calculateHMAC(data, secretApiKey);

        List<String> apiKeyList = new ArrayList<>();
        apiKeyList.add(apiKey);

        List<String> nonceList = new ArrayList<>();
        nonceList.add(nonce);


        HttpUrl.Builder url = new HttpUrl.Builder();
        url.encodedQuery(data);

        Call<BRexResponse> call = mBRexApi.getEncodedBalances(apiKey, nonce, signedHeader);
        try {
            Response<BRexResponse> response = call.execute();
            BRexResponse bRexResponse = response.body();
            dataList = bRexResponse.getDataFromResponse();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataList;
    }

    public void getTicker(DataAdapter dataAdapter, Data data) {
        mDataAdapter = dataAdapter;
        Call<BRexResponse> call = mBRexApi.getTicker(data.getMarketName());
        call.enqueue(this);
    }

    public void getSummaries(DataAdapter dataAdapter, List<Data> mDataList) {
        mDataAdapter = dataAdapter;
        dataList = mDataList;
        Call<BRexResponse> call = mBRexApi.getSummaries();
        call.enqueue(this);
    }

    public void getMarkets(DataAdapter dataAdapter, List<Data> mDataList) {
        mDataAdapter = dataAdapter;
        dataList = mDataList;
        Call<BRexResponse> call = mBRexApi.getMarkets();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<BRexResponse> call, Response<BRexResponse> response) {
        if(response.isSuccessful()){
            dataList = getResponseCoins(response);
            mDataAdapter.updateCoinList(mDataAdapter, dataList);
        } else {
            try {
                Log.d(TAG, response.errorBody().string());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private List<Data> getResponseCoins(Response<BRexResponse> response) {
        BRexResponse bRexResponseModel = response.body();
        return bRexResponseModel.getDataFromResponse();
    }

    @Override
    public void onFailure(Call<BRexResponse> call, Throwable t) {
        t.printStackTrace();
    }

}
