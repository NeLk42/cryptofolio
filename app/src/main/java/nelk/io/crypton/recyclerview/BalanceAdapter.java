package nelk.io.crypton.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nelk.io.crypton.R;
import nelk.io.crypton.models.enums.Cryptos;
import nelk.io.crypton.models.app.Balance;
import nelk.io.crypton.models.app.Market;
import nelk.io.crypton.models.app.Portfolio;
import nelk.io.crypton.models.app.User;
import nelk.io.crypton.retrofit.bittrex.models.RexCoinData;

import static nelk.io.crypton.models.utils.MathOperations.calculateBalanceTotalValue;
import static nelk.io.crypton.models.utils.MathOperations.calculatePercentageChange;
import static nelk.io.crypton.models.utils.MathOperations.calculateValueChange;
import static nelk.io.crypton.models.utils.gridItemsWrapper.wrapEarnings;
import static nelk.io.crypton.models.utils.gridItemsWrapper.wrapPercentage;
import static nelk.io.crypton.models.utils.gridItemsWrapper.wrapSymbol;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class BalanceAdapter extends RecyclerView.Adapter<CoinViewHolder> {
    private static final String TAG = BalanceAdapter.class.getSimpleName();

    // Android OS
    private LayoutInflater mInflater;
    private Context mContext;

    // Data
    private String mPortfolioId;
    private User mUser;

    public BalanceAdapter(Context context, User user) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        mUser = user;
    }

    @Override
    public CoinViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View coinView = mInflater.inflate(R.layout.balance_grid_item, parent, false);
        return new CoinViewHolder(coinView);
    }

    @Override
    public void onBindViewHolder(CoinViewHolder holder, int position) {
        List<Balance> balances = getUserPortfolio().getBalances();
        final Balance coin = balances.get(position);
        String coinName = coin.getCurrencyName();
        Double coinBalance = coin.getCurrencyBalance();

        List<Balance> balanceItems = getUserPortfolio().getBalances();

        final Balance item = balances.get(position);
        // TODO: Sort Balances.
        // TODO: Add Portfolio item (with Balance totals) at index = 0

        /* Balance data */
        String name = item.getCurrencyName();
        Double coinAmount = item.getCurrencyBalance();

        setGridItemText(holder.balanceCoinName, name);
        setGridItemText(holder.balanceCoinAmount, Double.toString(coinAmount));

        /* Market data */
        String logoUrl;
        Double priceBought;
        Double priceNow;
        Double percentageChange;
        Double totalSpent;
        Double totalNow;
        Double earnings;

        if (hasMarketLoaded(name)){
            Market itemMarket = getPortfolioMarket(item.getCurrencyName());

            logoUrl = itemMarket.getMarketCoin().getLogoUrl();
            priceBought = itemMarket.getPrevDay();
            priceNow = itemMarket.getLast();
            percentageChange = calculatePercentageChange(priceBought, priceNow);
            totalSpent = calculateBalanceTotalValue(priceBought, coinAmount);
            totalNow = calculateBalanceTotalValue(priceNow, coinAmount);
            earnings = calculateValueChange(totalSpent, totalNow);

            String gridItemLogo = logoUrl;
            String gridItemPriceBought = wrapWithFiat(priceBought);
            String gridItemPriceNow = wrapWithFiat(priceNow);
            String gridItemPercentageChange = wrapPercentage(percentageChange);
            String gridItemTotalSpent = wrapWithFiat(totalSpent);
            String gridItemTotalNow = wrapWithFiat(totalNow);
            String gridItemEarnings = wrapWithFiatAndSign(earnings);

            setGridItemLogo(holder.coinLogo, gridItemLogo);
            setGridItemText(holder.balancePriceBought, gridItemPriceBought);
            setGridItemText(holder.balancePriceNow, gridItemPriceNow);
            setGridItemText(holder.balancePercentageChange, gridItemPercentageChange);
            setGridItemText(holder.balanceTotalSpent, gridItemTotalSpent);
            setGridItemText(holder.balanceTotalNow, gridItemTotalNow);
            setGridItemText(holder.balanceEarnings, gridItemEarnings);
        }
    }

    @NonNull
    private String wrapWithFiatAndSign(Double earnings) {
        return wrapEarnings(earnings, mUser.getBaseCurrency());
    }

    private String wrapWithFiat(Double coinAmount) {
        return wrapSymbol(coinAmount, mUser.getBaseCurrency());
    }

    private boolean hasMarketLoaded(String itemName) {
        return getUserPortfolio().getBroker() != null && getPortfolioMarket(itemName) != null;
    }

    private Market getPortfolioMarket(String currencyName) {
        return getUserMarkets().get(currencyName);
    }

    @Override
    public int getItemCount() {
        return getNumberOfPortfolioItems();
    }

    private int getNumberOfPortfolioItems() {
        int numItems = 0;
        Portfolio portfolio = getUserPortfolio();
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

    // Auxiliary methods

    private void setWorkingPortfolio(Portfolio portfolio) {
        this.mPortfolioId = portfolio.getName();
    }

    private Portfolio getUserPortfolio() {
        return mUser.getPortfolio(mPortfolioId);
    }

    private Map<String, Market> getUserMarkets() { return getUserPortfolio().getMarkets(); }

    private Double getBTCNow() {
        return getPortfolioMarket(Cryptos.BTC.getSymbol()).getLast();
    }

    private void setGridItemText(TextView textView, String coinParam) {
        textView.setText(coinParam);
    }

    private void setGridItemLogo(ImageView imageView, String coinParam) {
        Picasso.with(mContext)
                .load(coinParam)
                .resize(128,128)
                .onlyScaleDown()
                .into(imageView);
    }

    // Rex Account Service - Balance Data

    public void updateBalances(Portfolio portfolio, List<RexCoinData> balanceList){
        setWorkingPortfolio(portfolio);
        Portfolio userPortfolio = getUserPortfolio();
        userPortfolio.setBalances(adaptCoinDataToBalance(balanceList));
        mUser.updatePortfolio(userPortfolio);
        notifyDataSetChanged();
    }

    private List<Balance> adaptCoinDataToBalance(List<RexCoinData> balanceList) {
        List<Balance> updatedBalance = new ArrayList<>();

        for (RexCoinData balanceData : balanceList) {
            if (balanceData.getBalance() > 0){
                updatedBalance.add(new Balance(balanceData));
            }
        }

        return updatedBalance;
    }

    // Rex Markets Service - Coin Markets Data

    public void updateBrokerMarkets(Portfolio portfolio, List<RexCoinData> brokerData){
        setWorkingPortfolio(portfolio);
        Portfolio userPortfolio = getUserPortfolio();
        userPortfolio.setMarkets(getBrokerMarkets(portfolio, brokerData));

        notifyDataSetChanged();
    }

    private Map<String, Market> getBrokerMarkets(Portfolio portfolio, List<RexCoinData> brokerMarketsList) {
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

    private Map<String, Market> combineMapResults(boolean process, Map<String, Market> marketsMap) {
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

}
