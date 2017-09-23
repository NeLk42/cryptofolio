package nelk.io.crypton.activityhelpers;

import android.support.annotation.NonNull;

import nelk.io.crypton.models.app.Credentials;
import nelk.io.crypton.models.app.Portfolio;
import nelk.io.crypton.models.app.User;
import nelk.io.crypton.models.enums.Brokers;
import nelk.io.crypton.models.enums.Cryptos;
import nelk.io.crypton.models.enums.Fiats;
import nelk.io.crypton.recyclerview.BalanceAdapter;
import nelk.io.crypton.retrofit.bittrex.RexAccountService;
import nelk.io.crypton.retrofit.bittrex.RexConf;
import nelk.io.crypton.retrofit.bittrex.RexPublicService;


public class MainActivityHelper {

    @NonNull
    public static User setUser() {
        return new User("user1",
                Cryptos.BTC);
//                            Cryptos.SAT);
//                            Fiats.USD);
    }

    public static User initializeBalanceView(User user, BalanceAdapter mBalanceAdapter) {
        user.getPortfolios().clear();

        // User is prompted for a portfolio 'name'
        String portfolioName = "My Portfolio";

        // User is prompted for a broker
        Brokers portfolioBroker = Brokers.BITTREX;

        // User is prompted for bittrex api keys
        Credentials portfolioCredentials = new Credentials(RexConf.API_KEY, RexConf.API_SECRET_KEY);

        // New portfolio is created and populated with data from Bittrex

        return createPortfolio(user, mBalanceAdapter, portfolioName, portfolioBroker, portfolioCredentials);
    }

    private static User createPortfolio(User user, BalanceAdapter mBalanceAdapter, String name, Brokers broker, Credentials credentials) {
        // New portfolio is assigned previously captured name and credentials
        Portfolio rexPortfolio = new Portfolio(name, broker, credentials);

        // New portfolio is added to user object.
        user.updatePortfolio(rexPortfolio);

        // Pull markets Information from Broker
        initializeBrokerMarketsData(rexPortfolio, mBalanceAdapter);

        // Pull user balance from Broker
        initializeBrokerUserBalance(rexPortfolio, mBalanceAdapter);

        return user;
    }

    private static void initializeBrokerMarketsData(Portfolio rexPortfolio, BalanceAdapter mBalanceAdapter) {
        // Once portfolio has been assigned against the user, a connection has to be made to
        // pull information, first we load all the markets info.
        RexPublicService rexPublicService = new RexPublicService(rexPortfolio, mBalanceAdapter);
        rexPublicService.pullSummariesData();
        rexPublicService.pullMarketsData();
    }

    private static void initializeBrokerUserBalance(Portfolio rexPortfolio, BalanceAdapter mBalanceAdapter) {
        RexAccountService rexAccountService = new RexAccountService(rexPortfolio, mBalanceAdapter);
        rexAccountService.updateAccountBalance();
    }
}
