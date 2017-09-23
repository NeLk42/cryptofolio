package nelk.io.crypton.recyclerview;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import nelk.io.crypton.R;

class CoinViewHolder extends RecyclerView.ViewHolder{

    //    ###################   ~   ###############################
    //    # LTC          50 #   ~   # LTC                  amount #
    //    # $30       $1500 #   ~   # priceBought      totalSpent #
    //    # $40       $2000 #   ~   # priceNow           totalNow #
    //    # +25%      +$500 #   ~   # pricePercIncrease  earnings #
    //    ###################   ~   ###############################

    //    ###################   ~   ##########################
    //    # LTC          50 #   ~   # LTC             amount #
    //    # +25%      +$500 #   ~   # percentage    earnings #
    //    ###################   ~   ##########################

    ImageView coinLogo;

    TextView balanceCoinName;
    TextView balancePriceBought;
    TextView balancePriceNow;
    TextView balancePercentageChange;

    TextView balanceCoinAmount;
    TextView balanceTotalSpent;
    TextView balanceTotalNow;
    TextView balanceEarnings;

    CoinViewHolder(View itemView) {
        super(itemView);

        // pullMarketsData
        this.coinLogo = (ImageView) itemView.findViewById(R.id.coin_logo);

        this.balanceCoinName = (TextView) itemView.findViewById(R.id.coin_name);
        this.balancePriceBought = (TextView) itemView.findViewById(R.id.price_bought);
        this.balancePriceNow = (TextView) itemView.findViewById(R.id.price_now);
        this.balancePercentageChange = (TextView) itemView.findViewById(R.id.percentage_change);

        this.balanceCoinAmount = (TextView) itemView.findViewById(R.id.coin_amount);
        this.balanceTotalSpent = (TextView) itemView.findViewById(R.id.total_spent);
        this.balanceTotalNow = (TextView) itemView.findViewById(R.id.total_now);
        this.balanceEarnings = (TextView) itemView.findViewById(R.id.earnings);
    }
}
