package nelk.io.crypton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import nelk.io.crypton.models.app.Credentials;
import nelk.io.crypton.models.app.Portfolio;
import nelk.io.crypton.models.app.User;
import nelk.io.crypton.models.enums.Brokers;
import nelk.io.crypton.models.enums.Cryptos;
import nelk.io.crypton.models.enums.Fiats;
import nelk.io.crypton.recyclerview.BalanceAdapter;
import nelk.io.crypton.retrofit.Bittrex.RexAccountService;
import nelk.io.crypton.retrofit.Bittrex.RexConf;
import nelk.io.crypton.retrofit.Bittrex.RexPublicService;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    // Android OS
    private RecyclerView mBalanceRecyclerView;
    private BalanceAdapter mBalanceAdapter;

    // Data
//    private User user = new User("user1", Fiats.USD);
    private User user = new User("user1", Cryptos.BTC);
//    private User user = new User("user1", Cryptos.SAT);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBalanceRecyclerView = (RecyclerView) findViewById(R.id.rv_balances_grid);
        mBalanceAdapter = new BalanceAdapter(this, user);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        mBalanceRecyclerView.setLayoutManager(layoutManager);
        mBalanceRecyclerView.setAdapter(mBalanceAdapter);
        mBalanceRecyclerView.setHasFixedSize(true);

        initializeBalanceView();
    }

    private void initializeBalanceView() {
        // TODO : Remove this!
        user.getPortfolios().clear();

        // User is prompted for a portfolio 'name'
        String portfolioName = "My Portfolio";

        // User is prompted for a broker
        Brokers portfolioBroker = Brokers.BITTREX;

        // User is prompted for bittrex api keys
        Credentials portfolioCredentials = new Credentials(RexConf.API_KEY, RexConf.API_SECRET_KEY);

        // New portfolio is created and populated with data from Bittrex
        createPortfolio(portfolioName, portfolioBroker, portfolioCredentials);
    }

    private void createPortfolio(String name, Brokers broker, Credentials credentials) {
        // New portfolio is assigned previously captured name and credentials
        Portfolio rexPortfolio = new Portfolio(name, broker, credentials);

        // New portfolio is added to user object.
        user.updatePortfolio(rexPortfolio);

        // Pull markets Information from Broker
        initializeBrokerMarketsData(rexPortfolio);

        // Pull user balance from Broker
        initializeBrokerUserBalance(rexPortfolio);
    }

    private void initializeBrokerMarketsData(Portfolio rexPortfolio) {
        // Once portfolio has been assigned against the user, a connection has to be made to
        // pull information, first we load all the markets info.
        RexPublicService rexPublicService = new RexPublicService(rexPortfolio, mBalanceAdapter);
        rexPublicService.pullSummariesData();
        rexPublicService.pullMarketsData();
    }

    private void initializeBrokerUserBalance(Portfolio rexPortfolio) {
        RexAccountService rexAccountService = new RexAccountService(rexPortfolio, mBalanceAdapter);
        rexAccountService.updateAccountBalance();
    }

}
