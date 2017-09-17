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
        Broker poloniex = new Broker("Poloniex", "http://dummy", "balance");
        Broker bittrex = new Broker("Bittrex", "https://bittrex.com/api/v1.1/", "account/getbalances");
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
//        RexPublicService rexPublicService = new RexPublicService(user, mBalanceAdapter);
//        rexPublicService.getSummaries(portfolioName);
//        rexPublicService.getMarkets(portfolioName);

        rexAccountService = new RexAccountService(rexPortfolio, mBalanceAdapter);
        rexAccountService.updateAccountBalance(mBalanceAdapter);
    }

}
