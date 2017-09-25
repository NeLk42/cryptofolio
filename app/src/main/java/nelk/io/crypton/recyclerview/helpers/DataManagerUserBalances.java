package nelk.io.crypton.recyclerview.helpers;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import nelk.io.crypton.models.app.Balance;
import nelk.io.crypton.models.app.Portfolio;
import nelk.io.crypton.recyclerview.BalanceAdapter;
import nelk.io.crypton.recyclerview.models.GridItem;
import nelk.io.crypton.retrofit.bittrex.models.RexCoinData;

import static nelk.io.crypton.MainActivity.updateDisplay;
import static nelk.io.crypton.MainActivity.updatePortfolio;

public class DataManagerUserBalances {
    private static final String TAG = DataManagerUserBalances.class.getSimpleName();

    public static void storeAccountBalanceData(BalanceAdapter adapter, Portfolio outdatedPortfolio, List<RexCoinData> rexDataList){
        Portfolio portfolio = outdatedPortfolio;
        portfolio.setBalances(adaptCoinDataToBalance(rexDataList));
        updatePortfolio(portfolio);
                                    adapter.updatePortfolio(portfolio);
        adapter.notifyDataSetChanged();

//        Portfolio portfolio = outdatedPortfolio;
//        String id = outdatedPortfolio.getName();
//
//        List<Balance> balanceList = adaptCoinDataToBalance(rexDataList);
//        portfolio.setBalances(balanceList);
//        updatePortfolio(portfolio);
//
//        // TODO : Extract this to an Interface & method
//        List<GridItem> gridItems = updateDisplay(id);
//        adapter.setPortfolio(id);
//        adapter.setmGridItems(gridItems);
//        adapter.notifyDataSetChanged();
    }

    public static List<Balance> adaptCoinDataToBalance(List<RexCoinData> balanceList) {
        List<Balance> updatedBalance = new ArrayList<Balance>();

        for (RexCoinData balanceData : balanceList) {
            if (balanceData.getBalance() > 0) {
                Log.d(DataManagerUserBalances.TAG, "Added Balance: " + balanceData.getCurrency());
                updatedBalance.add(new Balance(balanceData));
            }
        }
        return updatedBalance;
    }
}