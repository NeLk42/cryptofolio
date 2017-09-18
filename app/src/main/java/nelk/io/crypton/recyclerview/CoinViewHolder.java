package nelk.io.crypton.recyclerview;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import nelk.io.crypton.R;

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
