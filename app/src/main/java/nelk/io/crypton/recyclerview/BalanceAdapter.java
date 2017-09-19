package nelk.io.crypton.recyclerview;

import android.content.Context;
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
import nelk.io.crypton.models.enums.Crypto;
import nelk.io.crypton.models.app.Balance;
import nelk.io.crypton.models.app.Broker;
import nelk.io.crypton.models.app.Market;
import nelk.io.crypton.models.app.Portfolio;
import nelk.io.crypton.models.app.User;
import nelk.io.crypton.retrofit.Bittrex.models.RexCoinData;

import static nelk.io.crypton.models.utils.Increase.percentageChange;
import static nelk.io.crypton.models.utils.Increase.valueChange;
import static nelk.io.crypton.models.utils.ValueUtils.getCoinInFiat;
import static nelk.io.crypton.models.utils.ValueUtils.getFiatValue;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class BalanceAdapter extends RecyclerView.Adapter<BalanceAdapter.CoinViewHolder> {
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
        Double coinBalance = coin.getBalance();

        // Balance
        setGridItemText(holder.coinName, coinName);
        setGridItemText(holder.coinAmount, Double.toString(coin.getBalance()));

        // Markets
        Broker broker = getUserPortfolio().getBroker();
        if (broker != null){

            Market market = broker.getMarkets().get(coinName);

            if (market != null){
                setGridItemText(holder.coinLongName, market.getMarketCoin().getLongName());
                setGridItemLogo(holder.coinLogo, market.getMarketCoin().getLogoUrl());
                setGridItemText(holder.coinValue,
                        getCoinInFiat(
                                coinBalance, coinName, mUser, mPortfolioId));
                setGridItemText(holder.coinBaseCurrencyIncrease,
                        valueChange(
                                market.getPrevDay(), market.getLast(),
                                getFiatValue(coinBalance, coinName, mUser, mPortfolioId),
                                mUser.getBaseCurrency()));
                setGridItemText(holder.coinPercentageIncrease,
                        percentageChange(
                                market.getPrevDay(), market.getLast()));
            }
        }
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

    class CoinViewHolder extends RecyclerView.ViewHolder{
        // pullMarketsData
        ImageView coinLogo;
        TextView coinLongName;
        TextView coinBaseCurrencyIncrease;
        TextView coinPercentageIncrease;
        TextView coinValue;

        // getBalance
        TextView coinName;
        TextView coinAmount;

        CoinViewHolder(View itemView) {
            super(itemView);

            // pullMarketsData
            this.coinLogo = (ImageView) itemView.findViewById(R.id.coin_logo);
            this.coinLongName = (TextView) itemView.findViewById(R.id.coin_name_long);
            this.coinBaseCurrencyIncrease = (TextView) itemView.findViewById(R.id.coin_base_currency_increase);
            this.coinPercentageIncrease = (TextView) itemView.findViewById(R.id.coin_percentage_increase);
            this.coinValue = (TextView) itemView.findViewById(R.id.coin_value);

            // getBalance
            this.coinName = (TextView) itemView.findViewById(R.id.coin_name);
            this.coinAmount = (TextView) itemView.findViewById(R.id.coin_amount);

        }
    }


    // Auxiliary methods

    private void setWorkingPortfolio(Portfolio portfolio) {
        this.mPortfolioId = portfolio.getName();
    }

    private Portfolio getUserPortfolio() {
        return mUser.getPortfolio(mPortfolioId);
    }

    private Map<String, Market> getUserMarkets() { return getUserPortfolio().getBroker().getMarkets(); }

    private Double getBTCNow() {
        return getUserMarkets().get(Crypto.BTC.getCryptoName()).getLast();
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
        Broker broker = userPortfolio.getBroker();
        broker.setMarkets(getBrokerMarkets(portfolio, brokerData));

        notifyDataSetChanged();
    }

    private Map<String, Market> getBrokerMarkets(Portfolio portfolio, List<RexCoinData> brokerMarketsList) {
        Map<String, Market> marketsMap = portfolio.getBroker().getMarkets();
        boolean isCombinedMap = false;

        for (RexCoinData coinData : brokerMarketsList) {
            String coinId = coinData.getMarketName();

            if (coinData.getMarketName().contains(Crypto.BTC.getCryptoName())){
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
