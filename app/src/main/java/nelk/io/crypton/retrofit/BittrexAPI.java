package nelk.io.crypton.retrofit;

import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import nelk.io.crypton.recyclerview.CoinAdapter;
import nelk.io.crypton.retrofit.models.BittrexResponse;
import nelk.io.crypton.retrofit.models.Coin;
import nelk.io.crypton.utils.SHA512;
import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static nelk.io.crypton.utils.SHA512.generateNonce;

public class BittrexAPI implements Callback<BittrexResponse> {
    static final String TAG = BittrexAPI.class.getSimpleName();
    static String BASE_URL= "https://bittrex.com/api/v1.1/";

    List<Coin> coinList;
    private CoinAdapter mCoinAdapter;
    RetrofitService mRetrofitService = getRetrofitService();

    public List<Coin> getAccountBalance() {

        String nonce = generateNonce();
        String apiKey = APIConf.API_KEY;
        String secretApiKey = APIConf.API_SECRET_KEY;
        String baseBalancesUrl = "https://bittrex.com/api/v1.1/account/getbalances";
        String encodeNonce = encodeNonce(nonce);

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

        Call<BittrexResponse> call = mRetrofitService.getEncodedBalances(apiKey, nonce, signedHeader);
        try {
            Response<BittrexResponse> response = call.execute();
            BittrexResponse bRexResponse = response.body();
            coinList = bRexResponse.getDataFromResponse();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return coinList;
    }

    @Nullable
    private String encodeNonce(String value) {
        String encodedValue = null;
        try {
            encodedValue = URLEncoder.encode(value, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodedValue;
    }

    public void getTicker(CoinAdapter coinAdapter, Coin coin) {
        mCoinAdapter = coinAdapter;
        Call<BittrexResponse> call = mRetrofitService.getTicker(coin.getMarketName());
        call.enqueue(this);
    }

    public void getSummaries(CoinAdapter coinAdapter, List<Coin> mCoinList) {
        mCoinAdapter = coinAdapter;
        coinList = mCoinList;
        Call<BittrexResponse> call = mRetrofitService.getSummaries();
        call.enqueue(this);
    }

    public void getMarkets(CoinAdapter coinAdapter, List<Coin> mCoinList) {
        mCoinAdapter = coinAdapter;
        coinList = mCoinList;
        Call<BittrexResponse> call = mRetrofitService.getMarkets();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<BittrexResponse> call, Response<BittrexResponse> response) {
        if(response.isSuccessful()){
            coinList = getResponseCoins(response);
            mCoinAdapter.updateCoinList(mCoinAdapter, coinList);
        } else {
            try {
                Log.d(TAG, response.errorBody().string());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private List<Coin> getResponseCoins(Response<BittrexResponse> response) {
        BittrexResponse bittrexResponseModel = response.body();
        return bittrexResponseModel.getDataFromResponse();
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
