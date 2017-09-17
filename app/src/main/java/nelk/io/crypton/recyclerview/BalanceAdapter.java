package nelk.io.crypton.recyclerview;

import android.content.Context;
import android.content.Intent;
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

import nelk.io.crypton.DetailsActivity;
import nelk.io.crypton.R;
import nelk.io.crypton.models.rex.Balance;
import nelk.io.crypton.models.rex.Broker;
import nelk.io.crypton.models.rex.Market;
import nelk.io.crypton.models.rex.Portfolio;
import nelk.io.crypton.models.rex.User;
import nelk.io.crypton.retrofit.Bittrex.models.RexCoinData;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class BalanceAdapter extends RecyclerView.Adapter<BalanceAdapter.CoinViewHolder> {
    public static final String TAG = BalanceAdapter.class.getSimpleName();

    // Android OS
    private LayoutInflater mInflater;
    private Context mContext;

    // Data
    private String portfolioId;
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
        final Balance coinBalance = balances.get(position);

        TextView currency = holder.currency;
        TextView balance = holder.balance;
        ImageView logo = holder.logoUrl;

        String balanceCoinCurrency = coinBalance.getCurrency();
        currency.setText(balanceCoinCurrency);

        String balanceCoinBalance = Double.toString(coinBalance.getBalance());
        balance.setText(balanceCoinBalance);

        Broker broker = getUserPortfolio().getBroker();
        if (broker != null){
            Map<String, Market> markets = broker.getMarkets();
            Market market = markets.get(balanceCoinCurrency);
            if (market != null){
                String balanceCoinLogoUrl = market
                            .getMarketCoin()
                            .getLogoUrl();

                Picasso
                        .with(mContext)
                        .load(balanceCoinLogoUrl)
                        .resize(50,50)
                        .onlyScaleDown()
                        .into(logo);

            }
        }




        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailsActivity.class);
                intent.putExtra("coinData", coinBalance);
                mContext.startActivity(intent);
            }
        });
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
        TextView marketName;
        ImageView logoUrl;

        // getBalance
        TextView currency;
        TextView balance;

        CoinViewHolder(View itemView) {
            super(itemView);

            // pullMarketsData
            this.marketName = (TextView) itemView.findViewById(R.id.marketName);
            this.logoUrl = (ImageView) itemView.findViewById(R.id.logo);

            // getBalance
            this.currency = (TextView) itemView.findViewById(R.id.currency);
            this.balance = (TextView) itemView.findViewById(R.id.balance);

        }
    }


    // Auxiliary methods

    private void setWorkingPortfolio(Portfolio portfolio) {
        this.portfolioId = portfolio.getName();
    }

    private Portfolio getUserPortfolio() {
        return mUser.getPortfolio(portfolioId);
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
        Map<String, Market> resultMap = new HashMap<>();

        boolean mapAll = false;

        for (RexCoinData coinData : brokerMarketsList) {
            String coinId = coinData.getMarketName();
            if (marketsMap.get(coinId) == null) {
                marketsMap.put(coinData.getMarketName(), new Market(coinData));
            } else {
                mapAll = true;
                Market existingMarket = marketsMap.get(coinId);
                marketsMap.put(coinId, existingMarket.addData(coinData));
            }
        }

        List<Market> combinedQuery = new ArrayList<>(marketsMap.values());

        if (mapAll){
            for (Market combinedMarket : combinedQuery) {
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
