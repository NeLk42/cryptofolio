package nelk.io.crypton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import nelk.io.crypton.models.rex.Broker;
import nelk.io.crypton.models.rex.Credentials;
import nelk.io.crypton.models.rex.Portfolio;
import nelk.io.crypton.models.rex.User;
import nelk.io.crypton.retrofit.Bittrex.RexConf;
import nelk.io.crypton.retrofit.models.CoinData;
import nelk.io.crypton.recyclerview.CoinAdapter;
import nelk.io.crypton.retrofit.Bittrex.RexAccountService;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    // Android OS
    private RecyclerView mDataRecyclerView;
    private CoinAdapter mCoinAdapter;

    // Data
    RexAccountService rexAccountService;
    private User user = new User("user1");
    private List<CoinData> mRexDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDataRecyclerView = (RecyclerView) findViewById(R.id.rv_coins_grid);
        mCoinAdapter = new CoinAdapter(this, user);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        mDataRecyclerView.setLayoutManager(layoutManager);
        mDataRecyclerView.setAdapter(mCoinAdapter);
        mDataRecyclerView.setHasFixedSize(true);


        // Steps
        // By default we have two brokers
        Broker poloniex = new Broker("Poloniex", "http://dummy");
        Broker bittrex = new Broker("Bittrex", "https://bittrex.com/api/v1.1/");
            // (Internally Broker should go to RexService.getRexConnection.setBaseURL)

        // Profiles will be able to use one of those Brokers. Let's assume that the User presses
        // a button to add a new Portfolio and Selects Bittrex
        Portfolio rexPortfolio = new Portfolio(bittrex);

        // He will then be prompted for a portfolio 'name' and 'Credentials' that we capture
        String portfolioName = "My Portfolio";
        Credentials portfolioCredentials = new Credentials(RexConf.API_KEY, RexConf.API_SECRET_KEY);

        // And store inside that portfolio.
        rexPortfolio.setName(portfolioName);
        rexPortfolio.setCredentials(portfolioCredentials);

        // We then add the portfolio to this user's account.
        user.updatePortfolio(rexPortfolio);

        // Once portfolio has been assigned against the user, a connection has to be made to
        // pull information, first we load all the markets info.
//        RexPublicService rexPublicService = new RexPublicService(user, mCoinAdapter);
//        rexPublicService.getSummaries(portfolioName);
//        rexPublicService.getMarkets(portfolioName);

        rexAccountService = new RexAccountService(user, mCoinAdapter);
        rexAccountService.updateAccountBalance(portfolioName, mCoinAdapter);

    }

}
