package nelk.io.crypton.recyclerview.helpers;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nelk.io.crypton.models.app.Balance;
import nelk.io.crypton.models.app.Market;
import nelk.io.crypton.models.app.Portfolio;
import nelk.io.crypton.models.enums.Cryptos;
import nelk.io.crypton.recyclerview.BalanceAdapter;
import nelk.io.crypton.retrofit.bittrex.models.RexCoinData;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class BalanceAdapterHelper {
    private static final String TAG = BalanceAdapterHelper.class.getSimpleName();

    public static List<Balance> adaptCoinDataToBalance(List<RexCoinData> balanceList) {
        List<Balance> updatedBalance = new ArrayList<Balance>();

        for (RexCoinData balanceData : balanceList) {
            if (balanceData.getBalance() > 0) {
                updatedBalance.add(new Balance(balanceData));
            }
        }

        return updatedBalance;
    }


    public static Map<String, Market> getBrokerMarkets(Portfolio portfolio, List<RexCoinData> brokerMarketsList) {
        Map<String, Market> marketsMap = portfolio.getMarkets();
        boolean isCombinedMap = false;

        for (RexCoinData coinData : brokerMarketsList) {
            String coinId = coinData.getMarketName();

            if (coinData.getMarketName().contains(Cryptos.BTC.getSymbol())){
                if (marketsMap.get(coinId) == null) {
                    marketsMap.put(coinData.getMarketName(), new Market(coinData));
                } else {
                    isCombinedMap = true;
                    Market existingMarket = marketsMap.get(coinId);
                    marketsMap.put(coinId, existingMarket.addData(coinData));
                }
            }
        }

        return combineMapResults(isCombinedMap, marketsMap);
    }

    static Map<String, Market> combineMapResults(boolean process, Map<String, Market> marketsMap) {
        Map<String, Market> resultMap = new HashMap<>();
        List<Market> combinedMap = new ArrayList<>(marketsMap.values());

        if (process){
            for (Market combinedMarket : combinedMap) {
                String coinName = combinedMarket.getMarketCoin().getName();
                if (!isBlank(coinName)){
                    Log.d(TAG, "Added to Broker markets:" + coinName);
                    resultMap.put(coinName, combinedMarket);
                }
            }
            marketsMap = resultMap;
        }

        return marketsMap;
    }

    public static int getNumberOfPortfolioItems(Portfolio userPortfolio) {
        int numItems = 0;
        Portfolio portfolio = userPortfolio;
        if (portfolio == null) {
            return numItems;
        } else {
            List<Balance> balances = portfolio.getBalances();
            if (balances != null){
                numItems = balances.size();
            }
        }
        return numItems;
    }
}