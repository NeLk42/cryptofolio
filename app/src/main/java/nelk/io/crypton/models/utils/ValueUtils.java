package nelk.io.crypton.models.utils;


import java.text.DecimalFormat;
import java.util.Map;

import nelk.io.crypton.models.enums.Cryptos;
import nelk.io.crypton.models.app.Market;
import nelk.io.crypton.models.app.User;

public class ValueUtils {

    public static String getCoinInSATs(Double amount, String coin, User mUser, String mPortfolioId){
        DecimalFormat formatter = new DecimalFormat("####0.00");
        Double result = getBTCValue(amount, coin, mUser, mPortfolioId);

        result = result * 100 * 1000;

        return new StringBuilder()
                .append(formatter.format(result))
                .append("k")
                .append(" ")
                .append(Cryptos.SAT.getSymbol())
                .toString();
    }

    public static String getCoinInBTCs(Double amount, String coin, User mUser, String mPortfolioId){
        DecimalFormat formatter = new DecimalFormat("####0.00000000");
        Double result = getBTCValue(amount, coin, mUser, mPortfolioId);

        return new StringBuilder()
                .append(formatter.format(result))
                .append(" ")
                .append(Cryptos.BTC.getSymbol())
                .toString();
    }

    public static Double getBTCValue(Double amount, String coin, User mUser, String mPortfolioId){
        Market market = getMarket(coin, mUser, mPortfolioId);

        Double result = amount;
        boolean isBTC = Cryptos.BTC.getSymbol().equals(coin);

        if (!isBTC){
            result = result * market.getLast();
        }

        return result;
    }

    public static String getCoinInFiat(Double amount, String coin, User mUser, String mPortfolioId){
        DecimalFormat formatter = new DecimalFormat("####0.00");
        Double result = getFiatValue(amount, coin, mUser, mPortfolioId);

        return new StringBuilder()
                .append(mUser.getBaseCurrency())
                .append(formatter.format(result))
                .toString();
    }


    public static Double getFiatValue(Double amount, String coin, User mUser, String mPortfolioId) {
        Map<String, Market> markets = getMarkets(mUser, mPortfolioId);

        Double result = getBTCValue(amount, coin, mUser, mPortfolioId);

        return result * markets.get(Cryptos.BTC.getSymbol()).getLast();
    }


//    public abstract String getCryptoValue();


    // Auxiliary methods

    private static Map<String, Market> getMarkets(User mUser, String mPortfolioId) {
        return mUser.getPortfolio(mPortfolioId).getMarkets();
    }

    private static Market getMarket(String coin, User mUser, String mPortfolioId) {
        return getMarkets(mUser, mPortfolioId).get(coin);
    }

}
