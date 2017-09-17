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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nelk.io.crypton.R;
import nelk.io.crypton.models.enums.Fiat;
import nelk.io.crypton.models.rex.Balance;
import nelk.io.crypton.models.rex.Broker;
import nelk.io.crypton.models.rex.Market;
import nelk.io.crypton.models.rex.Portfolio;
import nelk.io.crypton.models.rex.User;
import nelk.io.crypton.retrofit.Bittrex.models.RexCoinData;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class BalanceAdapter extends RecyclerView.Adapter<BalanceAdapter.CoinViewHolder> {
    public static final String TAG = BalanceAdapter.class.getSimpleName();
    public static final String BTC = "BTC";

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
                setGridItemText(holder.coinValue, getFiatValue(coin, market, mUser.getBaseCurrency()));
            }
        }
    }

    private void setGridItemText(TextView textView, String coinParam) {
        textView.setText(coinParam);
    }

    private void setGridItemLogo(ImageView imageView, String coinParam) {
        Picasso.with(mContext)
                .load(coinParam)
                .resize(50,50)
                .onlyScaleDown()
                .into(imageView);
    }

    @NonNull
    private String getFiatValue(Balance coin, Market market, String baseCurrency) {
        Double result = coin.getBalance();
        boolean isBTC = BTC.equals(coin.getCurrencyName());

        if (!isBTC){
            result = result * Double.valueOf(market.getLast());
        }
        result = result * getPortfolioMarkets().get(BTC).getLast();

        DecimalFormat formatter = new DecimalFormat("####0.00");

        return new StringBuilder()
                .append(baseCurrency)
                .append(formatter.format(result))
                .toString();
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
        TextView coinAmount;
        TextView coinValue;
        TextView coin;

        // getBalance
        TextView coinName;
        TextView coinLongName;

        CoinViewHolder(View itemView) {
            super(itemView);

            // pullMarketsData
            this.coinLogo = (ImageView) itemView.findViewById(R.id.logo);
            this.coinValue = (TextView) itemView.findViewById(R.id.value);
            this.coin = (TextView) itemView.findViewById(R.id.coin);
            this.coinLongName = (TextView) itemView.findViewById(R.id.coinLongName);

            // getBalance
            this.coinName = (TextView) itemView.findViewById(R.id.coinName);
            this.coinAmount = (TextView) itemView.findViewById(R.id.amount);

        }
    }


    // Auxiliary methods

    private void setWorkingPortfolio(Portfolio portfolio) {
        this.mPortfolioId = portfolio.getName();
    }

    private Portfolio getUserPortfolio() {
        return mUser.getPortfolio(mPortfolioId);
    }

    private Map<String, Market> getPortfolioMarkets() {
        return getUserPortfolio().getBroker().getMarkets();
    }


    // Rex Account Service - Balance Data

    public void updateBalances(Portfolio portfolio, List<RexCoinData> balanceList){
        setWorkingPortfolio(portfolio);
        Portfolio userPortfolio = getUserPortfolio();
        userPortfolio.setBalances(getUserBalance(portfolio, balanceList));
        mUser.updatePortfolio(userPortfolio);
        notifyDataSetChanged();
    }

    private List<Balance> getUserBalance(Portfolio portfolio, List<RexCoinData> balanceList) {
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
        boolean returnCombinedMap = false;

        for (RexCoinData coinData : brokerMarketsList) {
            String coinId = coinData.getMarketName();

            if (coinData.getMarketName().contains(BTC)){
                if (marketsMap.get(coinId) == null) {
                    marketsMap.put(coinData.getMarketName(), new Market(coinData));
                } else {
                    returnCombinedMap = true;
                    Market existingMarket = marketsMap.get(coinId);
                    marketsMap.put(coinId, existingMarket.addData(coinData));
                }
            }
        }

        return getFinalMap(returnCombinedMap, marketsMap);
    }

    private Map<String, Market> getFinalMap(boolean process, Map<String, Market> marketsMap) {
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
