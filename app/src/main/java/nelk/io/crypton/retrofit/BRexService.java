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

import static nelk.io.crypton.utils.SHA512.generateNonce;

public class BRexService implements Callback<BittrexResponse> {
    static final String TAG = BRexService.class.getSimpleName();
    private final RetrofitConnection retrofitConnection = new RetrofitConnection();

    List<Coin> coinList;
    private CoinAdapter mCoinAdapter;
    BRexApi mBRexApi = retrofitConnection.getRetrofitService();

    public List<Coin> getAccountBalance() {

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

        Call<BittrexResponse> call = mBRexApi.getEncodedBalances(apiKey, nonce, signedHeader);
        try {
            Response<BittrexResponse> response = call.execute();
            BittrexResponse bRexResponse = response.body();
            coinList = bRexResponse.getDataFromResponse();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return coinList;
    }

    public void getTicker(CoinAdapter coinAdapter, Coin coin) {
        mCoinAdapter = coinAdapter;
        Call<BittrexResponse> call = mBRexApi.getTicker(coin.getMarketName());
        call.enqueue(this);
    }

    public void getSummaries(CoinAdapter coinAdapter, List<Coin> mCoinList) {
        mCoinAdapter = coinAdapter;
        coinList = mCoinList;
        Call<BittrexResponse> call = mBRexApi.getSummaries();
        call.enqueue(this);
    }

    public void getMarkets(CoinAdapter coinAdapter, List<Coin> mCoinList) {
        mCoinAdapter = coinAdapter;
        coinList = mCoinList;
        Call<BittrexResponse> call = mBRexApi.getMarkets();
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

}
