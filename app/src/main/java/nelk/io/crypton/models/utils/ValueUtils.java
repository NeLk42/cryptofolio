package nelk.io.crypton.models.utils;


import java.text.DecimalFormat;
import java.util.Map;

import nelk.io.crypton.models.enums.Crypto;
import nelk.io.crypton.models.rex.Balance;
import nelk.io.crypton.models.rex.Market;
import nelk.io.crypton.models.rex.User;

public class ValueUtils {

    public static String getCoinInBTCs(Balance coin, User mUser, String mPortfolioId){
        return new StringBuilder()
                .append(getBTCValue(coin, mUser, mPortfolioId))
                .append(Crypto.BTC.getCryptoName())
                .toString();
    }

    public static Double getBTCValue(Balance coin, User mUser, String mPortfolioId){
        Market market = getMarket(coin, mUser, mPortfolioId);

        Double result = coin.getBalance();
        boolean isBTC = Crypto.BTC.getCryptoName().equals(coin.getCurrencyName());

        if (!isBTC){
            result = result * market.getLast();
        }

        return result;
    }

    public static String getCoinInFiat(Balance coin, User mUser, String mPortfolioId){
        DecimalFormat formatter = new DecimalFormat("####0.00");
        Double result = getFiatValue(coin, mUser, mPortfolioId);

        return new StringBuilder()
                .append(mUser.getBaseCurrency())
                .append(formatter.format(result))
                .toString();
    }


    public static Double getFiatValue(Balance coin, User mUser, String mPortfolioId) {
        Map<String, Market> markets = getMarkets(mUser, mPortfolioId);

        Double result = getBTCValue(coin, mUser, mPortfolioId);

        return result * markets.get(Crypto.BTC.getCryptoName()).getLast();
    }

//    public abstract String getCryptoValue();


    // Auxiliary methods

    private static Map<String, Market> getMarkets(User mUser, String mPortfolioId) {
        return mUser.getPortfolio(mPortfolioId).getBroker().getMarkets();
    }

    private static Market getMarket(Balance coin, User mUser, String mPortfolioId) {
        return getMarkets(mUser, mPortfolioId).get(coin.getCurrencyName());
    }

}
