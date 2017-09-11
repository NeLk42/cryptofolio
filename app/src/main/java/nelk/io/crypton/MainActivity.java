package nelk.io.crypton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import nelk.io.crypton.recyclerview.CoinAdapter;
import nelk.io.crypton.retrofit.BittrexAPI;
import nelk.io.crypton.retrofit.models.Coin;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    private List<Coin> mCoinList = new ArrayList<>();
    private RecyclerView mCoinRecyclerView;
    private CoinAdapter mCoinAdapter;
    BittrexAPI bittrexAPI = new BittrexAPI();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCoinRecyclerView = (RecyclerView) findViewById(R.id.rv_coins_grid);
        mCoinAdapter = new CoinAdapter(this, mCoinList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        mCoinRecyclerView.setLayoutManager(layoutManager);
        mCoinRecyclerView.setAdapter(mCoinAdapter);
        mCoinRecyclerView.setHasFixedSize(true);

        bittrexAPI.getSummaries(mCoinAdapter, mCoinList);
        bittrexAPI.getMarkets(mCoinAdapter, mCoinList);
    }
}
