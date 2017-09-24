package nelk.io.crypton.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Map;

import nelk.io.crypton.R;
import nelk.io.crypton.models.app.Balance;
import nelk.io.crypton.models.app.Market;
import nelk.io.crypton.models.app.Portfolio;
import nelk.io.crypton.models.app.User;
import nelk.io.crypton.models.enums.Cryptos;
import nelk.io.crypton.retrofit.bittrex.models.RexCoinData;

import static nelk.io.crypton.models.utils.MathOperations.calculateBalanceTotalValue;
import static nelk.io.crypton.models.utils.MathOperations.calculatePercentageChange;
import static nelk.io.crypton.models.utils.MathOperations.calculateValueChange;
import static nelk.io.crypton.models.utils.gridItemsWrapper.wrapEarnings;
import static nelk.io.crypton.models.utils.gridItemsWrapper.wrapPercentage;
import static nelk.io.crypton.models.utils.gridItemsWrapper.wrapSymbol;
import static nelk.io.crypton.recyclerview.helpers.BalanceAdapterHelper.adaptCoinDataToBalance;
import static nelk.io.crypton.recyclerview.helpers.BalanceAdapterHelper.getBrokerMarkets;
import static nelk.io.crypton.recyclerview.helpers.BalanceAdapterHelper.getNumberOfPortfolioItems;
import static nelk.io.crypton.recyclerview.helpers.GridItemHelper.setGridItemLogo;
import static nelk.io.crypton.recyclerview.helpers.GridItemHelper.setGridItemText;

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
    public int getItemCount() {
        return getNumberOfPortfolioItems(getPortfolio());
    }

    @Override
    public CoinViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View coinView = mInflater.inflate(R.layout.balance_grid_item, parent, false);
        return new CoinViewHolder(coinView);
    }

    @Override
    public void onBindViewHolder(CoinViewHolder holder, int position) {
        List<Balance> balances = getPortfolio().getBalances();
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

        if (hasLoadedMarket(name)){
            Market itemMarket = getMarket(item.getCurrencyName());

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

            setGridItemLogo(holder.coinLogo, gridItemLogo, mContext);
            setGridItemText(holder.balancePriceBought, gridItemPriceBought);
            setGridItemText(holder.balancePriceNow, gridItemPriceNow);
            setGridItemText(holder.balancePercentageChange, gridItemPercentageChange);
            setGridItemText(holder.balanceTotalSpent, gridItemTotalSpent);
            setGridItemText(holder.balanceTotalNow, gridItemTotalNow);
            setGridItemText(holder.balanceEarnings, gridItemEarnings);
        }
    }

    /* Formatting Methods */

    private boolean hasLoadedMarket(String itemName) {
        return getPortfolio().getBroker() != null && getMarket(itemName) != null;
    }

    private String wrapWithFiat(Double coinAmount) {
        return wrapSymbol(coinAmount, mUser.getBaseCurrency());
    }

    private String wrapWithFiatAndSign(Double earnings) {
        return wrapEarnings(earnings, mUser.getBaseCurrency());
    }


    // Auxiliary methods

    private Portfolio getPortfolio() {
        return mUser.getPortfolio(mPortfolioId);
    }

    private void setPortfolio(Portfolio portfolio) {
        this.mPortfolioId = portfolio.getName();
    }

    private Map<String, Market> getMarkets() {
        return getPortfolio().getMarkets();
    }

    private Market getMarket(String currencyName) {
        return getMarkets().get(currencyName);
    }

    private Double getMarketBTC() {
        return getMarket(Cryptos.BTC.getSymbol()).getLast();
    }


    // Rex Account Service - Balance Data

    public void updateBalances(Portfolio portfolio, List<RexCoinData> balanceList){
        setPortfolio(portfolio);
        Portfolio userPortfolio = getPortfolio();
        userPortfolio.setBalances(adaptCoinDataToBalance(balanceList));
        mUser.updatePortfolio(userPortfolio);
        notifyDataSetChanged();
    }

    // Rex Markets Service - Coin Markets Data

    public void updateBrokerMarkets(Portfolio portfolio, List<RexCoinData> brokerData){
        setPortfolio(portfolio);
        Portfolio userPortfolio = getPortfolio();
        userPortfolio.setMarkets(getBrokerMarkets(portfolio, brokerData));
        notifyDataSetChanged();
    }


}
