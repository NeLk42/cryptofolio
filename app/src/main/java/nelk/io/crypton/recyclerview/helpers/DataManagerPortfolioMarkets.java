package nelk.io.crypton.recyclerview.helpers;

import android.util.Log;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nelk.io.crypton.MainActivity;
import nelk.io.crypton.models.app.Market;
import nelk.io.crypton.models.app.Portfolio;
import nelk.io.crypton.models.enums.Cryptos;
import nelk.io.crypton.recyclerview.BalanceAdapter;
import nelk.io.crypton.recyclerview.models.GridItem;
import nelk.io.crypton.retrofit.bittrex.models.RexCoinData;

import static nelk.io.crypton.MainActivity.updateDisplay;
import static nelk.io.crypton.MainActivity.updatePortfolio;

public class DataManagerPortfolioMarkets {
    private static final String TAG = DataManagerPortfolioMarkets.class.getSimpleName();

    public static void storeMarketData(BalanceAdapter adapter, Portfolio outdatedPortfolio, List<RexCoinData> rexDataList) {
        Portfolio portfolio = outdatedPortfolio;
        portfolio.setMarkets(getBrokerMarkets(portfolio, rexDataList));
        updatePortfolio(portfolio);
                                    adapter.updatePortfolio(portfolio);
        adapter.notifyDataSetChanged();

//        Portfolio portfolio = outdatedPortfolio;
//        String id = outdatedPortfolio.getName();
//
//        Map<String, Market> markets = getBrokerMarkets(outdatedPortfolio, rexDataList);
//        portfolio.setMarkets(markets);
//        updatePortfolio(portfolio);
//
//        // TODO : Extract this to an Interface & method
//        List<GridItem> gridItems = updateDisplay(id);
//        adapter.setPortfolio(id);
//        adapter.setmGridItems(gridItems);
//        adapter.notifyDataSetChanged();
    }

    public static Map<String, Market> getBrokerMarkets(Portfolio portfolio, List<RexCoinData> brokerMarketsList) {
        Map<String, Market> marketsMap = portfolio.getMarkets();
        if (marketsMap == null){
            return new HashMap<>();
        }


        boolean isCombinedMap = false;

        for (RexCoinData coinData : brokerMarketsList) {
            String coinId = coinData.getMarketName();

            if (coinData.getMarketName().contains(Cryptos.BTC.getSymbol())) {
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

    private static Map<String, Market> combineMapResults(boolean process, Map<String, Market> marketsMap) {
        Map<String, Market> resultMap = new HashMap<String, Market>();
        List<Market> combinedMap = new ArrayList<Market>(marketsMap.values());

        if (process) {
            for (Market combinedMarket : combinedMap) {
                String coinName = combinedMarket.getMarketCoin().getName();
                if (!StringUtils.isBlank(coinName)) {
                    Log.d(DataManagerPortfolioMarkets.TAG, "Added to Broker markets:" + coinName);
                    resultMap.put(coinName, combinedMarket);
                }
            }
            marketsMap = resultMap;
        }

        return marketsMap;
    }
}