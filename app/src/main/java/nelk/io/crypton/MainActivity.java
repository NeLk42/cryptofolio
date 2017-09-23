package nelk.io.crypton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import nelk.io.crypton.models.app.User;
import nelk.io.crypton.recyclerview.BalanceAdapter;

import static nelk.io.crypton.activityhelpers.MainActivityHelper.initializeBalanceView;
import static nelk.io.crypton.activityhelpers.MainActivityHelper.setUser;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    // Android OS
    private RecyclerView mBalanceRecyclerView;
    private BalanceAdapter mBalanceAdapter;

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
    }
}
