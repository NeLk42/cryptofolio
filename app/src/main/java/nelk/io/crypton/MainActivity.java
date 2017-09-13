package nelk.io.crypton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import nelk.io.crypton.models.CoinData;
import nelk.io.crypton.recyclerview.CoinAdapter;
import nelk.io.crypton.retrofit.Bittrex.RexService;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    private List<CoinData> mRexDataList = new ArrayList<>();
    private RecyclerView mDataRecyclerView;
    private CoinAdapter mCoinAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDataRecyclerView = (RecyclerView) findViewById(R.id.rv_coins_grid);
        mCoinAdapter = new CoinAdapter(this, mRexDataList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        mDataRecyclerView.setLayoutManager(layoutManager);
        mDataRecyclerView.setAdapter(mCoinAdapter);
        mDataRecyclerView.setHasFixedSize(true);

        RexService rexService = new RexService(mCoinAdapter);
//        rexService.getAccountBalance();
        rexService.getSummaries(mCoinAdapter, mRexDataList);
        rexService.getMarkets(mCoinAdapter, mRexDataList);
    }
}
