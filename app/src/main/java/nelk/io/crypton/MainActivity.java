package nelk.io.crypton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import nelk.io.crypton.recyclerview.DataAdapter;
import nelk.io.crypton.retrofit.BRexService;
import nelk.io.crypton.retrofit.models.Data;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    private List<Data> mDataList = new ArrayList<>();
    private RecyclerView mDataRecyclerView;
    private DataAdapter mDataAdapter;
    BRexService bRexService = new BRexService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDataRecyclerView = (RecyclerView) findViewById(R.id.rv_coins_grid);
        mDataAdapter = new DataAdapter(this, mDataList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        mDataRecyclerView.setLayoutManager(layoutManager);
        mDataRecyclerView.setAdapter(mDataAdapter);
        mDataRecyclerView.setHasFixedSize(true);

        bRexService.getAccountBalance();
        bRexService.getSummaries(mDataAdapter, mDataList);
        bRexService.getMarkets(mDataAdapter, mDataList);
    }
}
