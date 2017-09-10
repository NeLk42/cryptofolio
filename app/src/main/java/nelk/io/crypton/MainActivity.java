package nelk.io.crypton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import nelk.io.crypton.recyclerview.SummaryAdapter;
import nelk.io.crypton.retrofit.BittrexAPI;
import nelk.io.crypton.retrofit.models.Summary;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    private List<Summary> mSummaryList = new ArrayList<>();
    private RecyclerView mSummaryRecyclerView;
    private SummaryAdapter mSummaryAdapter;
    BittrexAPI bittrexAPI = new BittrexAPI();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSummaryRecyclerView = (RecyclerView) findViewById(R.id.rv_coins_grid);
        mSummaryAdapter = new SummaryAdapter(this, mSummaryList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        mSummaryRecyclerView.setLayoutManager(layoutManager);
        mSummaryRecyclerView.setAdapter(mSummaryAdapter);
        mSummaryRecyclerView.setHasFixedSize(true);

        bittrexAPI.getSummaries(mSummaryAdapter, mSummaryList);
    }
}
