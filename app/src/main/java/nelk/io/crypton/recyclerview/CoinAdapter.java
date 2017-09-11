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
import java.util.Set;

import nelk.io.crypton.R;
import nelk.io.crypton.retrofit.models.Coin;

public class CoinAdapter extends RecyclerView.Adapter<CoinAdapter.CoinViewHolder> {
    public static final String TAG = CoinAdapter.class.getSimpleName();

    private List<Coin> mCoinList;
    private LayoutInflater mInflater;
    private Context mContext;


    public CoinAdapter(Context context, List<Coin> coinList) {
        mInflater = LayoutInflater.from(context);
        mCoinList = coinList;
        mContext = context;
    }

    @Override
    public CoinViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View coinView = mInflater.inflate(R.layout.market_grid_item, parent, false);

        CoinViewHolder coinViewHolder = new CoinViewHolder(coinView);
        return coinViewHolder;
    }

    @Override
    public void onBindViewHolder(CoinViewHolder holder, int position) {
        final Coin coin = mCoinList.get(position);

        TextView marketName = holder.marketName;
        TextView volume = holder.volume;
        TextView high = holder.high;
        TextView low = holder.low;
        ImageView logo = holder.logoUrl;

        String volumeToday = coin.getVolume();
        String highToday = "High : " + coin.getHigh();
        String lowToday = "Low : " + coin.getLow();

        marketName.setText(coin.getMarketName());
        volume.setText(volumeToday);
        high.setText(highToday);
        low.setText(lowToday);
        Picasso.with(mContext)
                .load(coin.getLogoUrl())
                .into(logo);

        Log.d(TAG, "Storing " + coin.getMarketName() + ", " +
                coin.getVolume() + ", " +
                coin.getHigh() + ", " +
                coin.getLow() + ".");

//        holder.itemView.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(mContext, DetailsActivity.class);
//                intent.putExtra(APIConfig.PARAM_MOVIE, movie);
//                mContext.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        int numItems = 0;
        if (mCoinList != null){
            numItems = mCoinList.size();
        }
        return numItems;
    }

    public void updateCoinList(CoinAdapter coinAdapter, List<Coin> coinsList){
        this.mCoinList = updateList(coinsList);
        coinAdapter.notifyDataSetChanged();
    }

    @NonNull
    private List<Coin> updateList(List<Coin> coinsList) {
        Map<String, Coin> resultMap = new HashMap<>();

        for (Coin existingCoin : mCoinList) {
            resultMap.put(existingCoin.getMarketName(), existingCoin);
        }

        for (Coin newCoin : coinsList) {
            String coinId = newCoin.getMarketName();
            if (resultMap.get(coinId) != null) {
                Coin combinedCoin = resultMap.get(coinId).addData(newCoin);
                resultMap.put(coinId, combinedCoin);
            } else {
                resultMap.put(newCoin.getMarketName(), newCoin);
            }
        }

        List<Coin> result = new ArrayList<>();
        Set<String> marketsSet = resultMap.keySet();

        for (String coinId : marketsSet) {
            result.add(resultMap.get(coinId));
        }
        return result;
    }

    public class CoinViewHolder extends RecyclerView.ViewHolder{
        TextView marketName;
        TextView high;
        TextView low;
        TextView volume;
        TextView last;
        TextView bid;
        TextView ask;
        TextView displayMarketName;
        TextView marketCurrency;
        TextView baseCurrency;
        TextView marketCurrencyLong;
        TextView baseCurrencyLong;
        TextView minTradeSize;
        TextView isActive;
        TextView created;
        TextView notice;
        TextView isSponsored;
        ImageView logoUrl;

        public CoinViewHolder(View itemView) {
            super(itemView);
            this.marketName = (TextView) itemView.findViewById(R.id.marketName);
            this.volume = (TextView) itemView.findViewById(R.id.volume);
            this.high = (TextView) itemView.findViewById(R.id.high);
            this.low = (TextView) itemView.findViewById(R.id.low);
            this.logoUrl = (ImageView) itemView.findViewById(R.id.logo);
        }
    }
}
