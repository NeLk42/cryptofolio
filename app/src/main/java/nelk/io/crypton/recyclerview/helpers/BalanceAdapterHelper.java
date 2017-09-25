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

    public static int getNumberOfPortfolioItems(Portfolio userPortfolio) {
        int numItems = 0;
        Portfolio portfolio = userPortfolio;
        if (portfolio == null) {
            return numItems;
        } else {
            List<Balance> balances = portfolio.getBalances();
            if (balances != null) {
                numItems = balances.size();
            }
        }
        return numItems;
    }
}