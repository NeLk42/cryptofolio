package nelk.io.crypton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import nelk.io.crypton.recyclerview.CoinAdapter;
import nelk.io.crypton.retrofit.BittrexAPI;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private CoinAdapter mCoinAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
