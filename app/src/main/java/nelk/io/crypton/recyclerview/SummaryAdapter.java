package nelk.io.crypton.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import nelk.io.crypton.R;
import nelk.io.crypton.retrofit.models.Summary;

public class SummaryAdapter extends RecyclerView.Adapter<SummaryAdapter.SummaryViewHolder> {
    public static final String TAG = SummaryAdapter.class.getSimpleName();

    private List<Summary> mSummaryList;
    private LayoutInflater mInflater;
    private Context mContext;


    public SummaryAdapter(Context context, List<Summary> summaryList) {
        mInflater = LayoutInflater.from(context);
        mSummaryList = summaryList;
        mContext = context;
    }

    @Override
    public SummaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View coinView = mInflater.inflate(R.layout.market_grid_item, parent, false);

        SummaryViewHolder summaryViewHolder = new SummaryViewHolder(coinView);
        return summaryViewHolder;
    }

    @Override
    public void onBindViewHolder(SummaryViewHolder holder, int position) {
        final Summary summary = mSummaryList.get(position);

        TextView marketName = holder.marketName;
        TextView volume = holder.volume;
        TextView high = holder.high;
        TextView low = holder.low;


        marketName.setText(summary.getMarketName());
        volume.setText(summary.getVolume());
        high.setText(summary.getHigh());
        low.setText(summary.getLow());

        Log.d(TAG, "Storing " + summary.getMarketName() + ", " +
                summary.getVolume() + ", " +
                summary.getHigh() + ", " +
                summary.getLow() + ".");

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
        if (mSummaryList != null){
            numItems = mSummaryList.size();
        }
        return numItems;
    }

    public void updateSummaryList(SummaryAdapter summaryAdapter, List<Summary> summaryList){
        this.mSummaryList.clear();
        this.mSummaryList.addAll(summaryList);
        summaryAdapter.notifyDataSetChanged();
    }

    public class SummaryViewHolder extends RecyclerView.ViewHolder{
        TextView marketName;
        TextView high;
        TextView low;
        TextView volume;
        TextView last;
        TextView bid;
        TextView ask;
        TextView displayMarketName;

        public SummaryViewHolder(View itemView) {
            super(itemView);
            this.marketName = (TextView) itemView.findViewById(R.id.marketName);
            this.volume = (TextView) itemView.findViewById(R.id.volume);
            this.high = (TextView) itemView.findViewById(R.id.high);
            this.low = (TextView) itemView.findViewById(R.id.low);
        }
    }
}
