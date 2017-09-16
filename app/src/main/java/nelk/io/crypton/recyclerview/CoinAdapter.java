package nelk.io.crypton.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nelk.io.crypton.DetailsActivity;
import nelk.io.crypton.R;
import nelk.io.crypton.models.rex.Balance;
import nelk.io.crypton.models.rex.Portfolio;
import nelk.io.crypton.models.rex.User;
import nelk.io.crypton.retrofit.models.CoinData;

public class CoinAdapter extends RecyclerView.Adapter<CoinAdapter.CoinViewHolder> {
    public static final String TAG = CoinAdapter.class.getSimpleName();

    // Android OS
    private LayoutInflater mInflater;
    private Context mContext;

    // Data
    private String portfolioId;
    private User mUser;

    public CoinAdapter(Context context, User user) {
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
        Portfolio portfolio = mUser.getPortfolio(portfolioId);
        List<Balance> balances = portfolio.getBalances();
        final Balance coinBalance = balances.get(position);

        TextView currency = holder.currency;
        TextView balance = holder.balance;

        currency.setText(coinBalance.getCurrency());
        balance.setText(Double.toString(coinBalance.getBalance()));

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
        Portfolio portfolio = mUser.getPortfolio(portfolioId);
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

    // Rex Account Service
    public void updateBalances(User user, String portfolioId, List<? extends CoinData> balanceList){
        this.mUser = user;
        updateUserPortfolio(portfolioId, balanceList);
        notifyDataSetChanged();
    }

    private void updateUserPortfolio(String portfolioId, List<? extends CoinData> balanceList) {
        this.portfolioId = portfolioId;
        Portfolio portfolio = mUser.getPortfolio(portfolioId);
        List<Balance> updatedBalance = new ArrayList<>();

        for (CoinData balanceData : balanceList) {
            if (balanceData.getBalance() > 0){
                updatedBalance.add(new Balance(balanceData));
            }
        }

        portfolio.setBalances(updatedBalance);
        mUser.updatePortfolio(portfolio);
    }


    public class CoinViewHolder extends RecyclerView.ViewHolder{
        // getMarkets
        TextView marketName;
        ImageView logoUrl;

        // getBalance
        TextView currency;
        TextView balance;

        public CoinViewHolder(View itemView) {
            super(itemView);

            // getMarkets
            this.marketName = (TextView) itemView.findViewById(R.id.marketName);
            this.logoUrl = (ImageView) itemView.findViewById(R.id.logo);

            // getBalance
            this.currency = (TextView) itemView.findViewById(R.id.currency);
            this.balance = (TextView) itemView.findViewById(R.id.balance);

        }
    }
}
