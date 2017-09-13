package nelk.io.crypton.recyclerview;

import android.content.Context;
import android.content.Intent;
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

import nelk.io.crypton.DetailsActivity;
import nelk.io.crypton.R;
import nelk.io.crypton.models.CoinData;

public class CoinAdapter extends RecyclerView.Adapter<CoinAdapter.CoinViewHolder> {
    public static final String TAG = CoinAdapter.class.getSimpleName();

    private List<CoinData> mCoinDataList;
    private LayoutInflater mInflater;
    private Context mContext;


    public CoinAdapter(Context context, List<CoinData> rexDataList) {
        mInflater = LayoutInflater.from(context);
        mCoinDataList = rexDataList;
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
        final CoinData coinData = mCoinDataList.get(position);

        TextView marketName = holder.marketName;
        TextView volume = holder.volume;
        TextView high = holder.high;
        TextView low = holder.low;
        ImageView logo = holder.logoUrl;

        String volumeToday = coinData.getVolume();
        String highToday = "High : " + coinData.getHigh();
        String lowToday = "Low : " + coinData.getLow();

        marketName.setText(coinData.getMarketName());
        volume.setText(volumeToday);
        high.setText(highToday);
        low.setText(lowToday);
        Picasso
                .with(mContext)
                .load(coinData.getLogoUrl())
                .resize(50,50)
                .onlyScaleDown()
                .into(logo);

        Log.d(TAG, "Storing " + coinData.getMarketName() + ", " +
                coinData.getVolume() + ", " +
                coinData.getHigh() + ", " +
                coinData.getLow() + ".");

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailsActivity.class);
                intent.putExtra("coinData", coinData);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        int numItems = 0;
        if (mCoinDataList != null){
            numItems = mCoinDataList.size();
        }
        return numItems;
    }

    public void updateCoinList(CoinAdapter coinAdapter, List<? extends CoinData> coinsList){
        this.mCoinDataList = updateList(coinsList);
        coinAdapter.notifyDataSetChanged();
    }

    @NonNull
    private List<CoinData> updateList(List<? extends CoinData> coinsList) {
        Map<String, CoinData> resultMap = new HashMap<>();

        for (CoinData existingCoinData : mCoinDataList) {
            resultMap.put(existingCoinData.getMarketName(), existingCoinData);
        }

        for (CoinData newCoinData : coinsList) {
            String coinId = newCoinData.getMarketName();
            if (resultMap.get(coinId) != null) {
                CoinData combinedCoinData = resultMap.get(coinId).addData(newCoinData);
                resultMap.put(coinId, combinedCoinData);
            } else {
                resultMap.put(newCoinData.getMarketName(), newCoinData);
            }
        }

        List<CoinData> result = new ArrayList<>();
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
