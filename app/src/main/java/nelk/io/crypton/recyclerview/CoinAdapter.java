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
import nelk.io.crypton.retrofit.Bittrex.models.RexData;

public class CoinAdapter extends RecyclerView.Adapter<CoinAdapter.CoinViewHolder> {
    public static final String TAG = CoinAdapter.class.getSimpleName();

    private List<RexData> mRexDataList;
    private LayoutInflater mInflater;
    private Context mContext;


    public CoinAdapter(Context context, List<RexData> rexDataList) {
        mInflater = LayoutInflater.from(context);
        mRexDataList = rexDataList;
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
        final RexData rexData = mRexDataList.get(position);

        TextView marketName = holder.marketName;
        TextView volume = holder.volume;
        TextView high = holder.high;
        TextView low = holder.low;
        ImageView logo = holder.logoUrl;

        String volumeToday = rexData.getVolume();
        String highToday = "High : " + rexData.getHigh();
        String lowToday = "Low : " + rexData.getLow();

        marketName.setText(rexData.getMarketName());
        volume.setText(volumeToday);
        high.setText(highToday);
        low.setText(lowToday);
        Picasso
                .with(mContext)
                .load(rexData.getLogoUrl())
                .resize(50,50)
                .onlyScaleDown()
                .into(logo);

        Log.d(TAG, "Storing " + rexData.getMarketName() + ", " +
                rexData.getVolume() + ", " +
                rexData.getHigh() + ", " +
                rexData.getLow() + ".");

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailsActivity.class);
                intent.putExtra("rexData", rexData);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        int numItems = 0;
        if (mRexDataList != null){
            numItems = mRexDataList.size();
        }
        return numItems;
    }

    public void updateCoinList(CoinAdapter coinAdapter, List<RexData> coinsList){
        this.mRexDataList = updateList(coinsList);
        coinAdapter.notifyDataSetChanged();
    }

    @NonNull
    private List<RexData> updateList(List<RexData> coinsList) {
        Map<String, RexData> resultMap = new HashMap<>();

        for (RexData existingRexData : mRexDataList) {
            resultMap.put(existingRexData.getMarketName(), existingRexData);
        }

        for (RexData newRexData : coinsList) {
            String coinId = newRexData.getMarketName();
            if (resultMap.get(coinId) != null) {
                RexData combinedRexData = resultMap.get(coinId).addData(newRexData);
                resultMap.put(coinId, combinedRexData);
            } else {
                resultMap.put(newRexData.getMarketName(), newRexData);
            }
        }

        List<RexData> result = new ArrayList<>();
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
