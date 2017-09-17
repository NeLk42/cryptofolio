package nelk.io.crypton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import nelk.io.crypton.models.rex.Broker;
import nelk.io.crypton.models.rex.Credentials;
import nelk.io.crypton.models.rex.Portfolio;
import nelk.io.crypton.models.rex.User;
import nelk.io.crypton.retrofit.Bittrex.RexConf;
import nelk.io.crypton.recyclerview.BalanceAdapter;
import nelk.io.crypton.retrofit.Bittrex.RexAccountService;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    // Android OS
    private RecyclerView mBalanceRecyclerView;
    private BalanceAdapter mBalanceAdapter;

    // Data
    RexAccountService rexAccountService;
    private User user = new User("user1");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBalanceRecyclerView = (RecyclerView) findViewById(R.id.rv_balance_grid);
        mBalanceAdapter = new BalanceAdapter(this, user);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        mBalanceRecyclerView.setLayoutManager(layoutManager);
        mBalanceRecyclerView.setAdapter(mBalanceAdapter);
        mBalanceRecyclerView.setHasFixedSize(true);

        initializeBalanceView();
    }

    private void initializeBalanceView() {
        // In theory options for user should be
        Broker poloniex = new Broker("Poloniex", "http://dummy/", "dummyBalance");
        Broker bittrex = new Broker("Bittrex", "https://bittrex.com/api/v1.1/", "account/getbalances");

        // User decides to go for bittrex
        Portfolio rexPortfolio = new Portfolio(bittrex);

        // User is prompted for a portfolio 'name'
        String portfolioName = "My Portfolio";

        // User is prompted for bittrex api keys
        Credentials portfolioCredentials = new Credentials(RexConf.API_KEY, RexConf.API_SECRET_KEY);

        // New portfolio is created and populated with data from Bittrex
        createPortfolio(rexPortfolio, portfolioName, portfolioCredentials);

    }

    private void createPortfolio(Portfolio rexPortfolio, String portfolioName, Credentials portfolioCredentials) {
        // New portfolio is assigned previously captured name and credentials
        rexPortfolio.setName(portfolioName);
        rexPortfolio.setCredentials(portfolioCredentials);

        // New portfolio is added to user object.
        user.updatePortfolio(rexPortfolio);

        // Pull markets Information from Broker
        initializeBrokerMarketsData();

        // Pull user balance from Broker
        initializeBrokerUserBalance(rexPortfolio);
    }

    private void initializeBrokerMarketsData() {
        // Once portfolio has been assigned against the user, a connection has to be made to
        // pull information, first we load all the markets info.
//        RexPublicService rexPublicService = new RexPublicService(user, mBalanceAdapter);
//        rexPublicService.getSummaries(portfolioName);
//        rexPublicService.getMarkets(portfolioName);
    }

    private void initializeBrokerUserBalance(Portfolio rexPortfolio) {
        rexAccountService = new RexAccountService(rexPortfolio, mBalanceAdapter);
        rexAccountService.updateAccountBalance(mBalanceAdapter);
    }

}
