package nelk.io.crypton;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import nelk.io.crypton.helpers.SwipeRefresher;
import nelk.io.crypton.models.app.User;
import nelk.io.crypton.recyclerview.BalanceAdapter;

import static nelk.io.crypton.helpers.MainActivityHelper.initializeBalanceView;
import static nelk.io.crypton.helpers.MainActivityHelper.setUser;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    // Android OS
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView mBalanceRecyclerView;
    private BalanceAdapter mBalanceAdapter;
    private SwipeRefresher swipeRefresher;

    // Data Store
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUser= setUser();

        mBalanceRecyclerView = (RecyclerView) findViewById(R.id.rv_balances_grid);
        mBalanceAdapter = new BalanceAdapter(this, mUser);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        mBalanceRecyclerView.setLayoutManager(layoutManager);
        mBalanceRecyclerView.setAdapter(mBalanceAdapter);
        mBalanceRecyclerView.setHasFixedSize(true);

        mUser = initializeBalanceView(mUser, mBalanceAdapter);

        swipeRefresher = new SwipeRefresher();
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.simpleSwipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mUser = swipeRefresher.refreshItems(swipeRefreshLayout, mUser, mBalanceAdapter);
            }
        });
    }


}
