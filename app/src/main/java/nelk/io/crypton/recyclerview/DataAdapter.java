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
import nelk.io.crypton.retrofit.models.Data;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.CoinViewHolder> {
    public static final String TAG = DataAdapter.class.getSimpleName();

    private List<Data> mDataList;
    private LayoutInflater mInflater;
    private Context mContext;


    public DataAdapter(Context context, List<Data> dataList) {
        mInflater = LayoutInflater.from(context);
        mDataList = dataList;
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
        final Data data = mDataList.get(position);

        TextView marketName = holder.marketName;
        TextView volume = holder.volume;
        TextView high = holder.high;
        TextView low = holder.low;
        ImageView logo = holder.logoUrl;

        String volumeToday = data.getVolume();
        String highToday = "High : " + data.getHigh();
        String lowToday = "Low : " + data.getLow();

        marketName.setText(data.getMarketName());
        volume.setText(volumeToday);
        high.setText(highToday);
        low.setText(lowToday);
        Picasso
                .with(mContext)
                .load(data.getLogoUrl())
                .resize(50,50)
                .onlyScaleDown()
                .into(logo);

        Log.d(TAG, "Storing " + data.getMarketName() + ", " +
                data.getVolume() + ", " +
                data.getHigh() + ", " +
                data.getLow() + ".");

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailsActivity.class);
                intent.putExtra("data", data);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        int numItems = 0;
        if (mDataList != null){
            numItems = mDataList.size();
        }
        return numItems;
    }

    public void updateCoinList(DataAdapter dataAdapter, List<Data> coinsList){
        this.mDataList = updateList(coinsList);
        dataAdapter.notifyDataSetChanged();
    }

    @NonNull
    private List<Data> updateList(List<Data> coinsList) {
        Map<String, Data> resultMap = new HashMap<>();

        for (Data existingData : mDataList) {
            resultMap.put(existingData.getMarketName(), existingData);
        }

        for (Data newData : coinsList) {
            String coinId = newData.getMarketName();
            if (resultMap.get(coinId) != null) {
                Data combinedData = resultMap.get(coinId).addData(newData);
                resultMap.put(coinId, combinedData);
            } else {
                resultMap.put(newData.getMarketName(), newData);
            }
        }

        List<Data> result = new ArrayList<>();
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
