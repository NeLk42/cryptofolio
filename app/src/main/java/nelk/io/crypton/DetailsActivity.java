package nelk.io.crypton;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import nelk.io.crypton.recyclerview.CoinAdapter;
import nelk.io.crypton.retrofit.BittrexAPI;
import nelk.io.crypton.retrofit.models.Coin;

public class DetailsActivity extends AppCompatActivity {
    public static final String TAG = DetailsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent storage = getIntent();

        if (storage.hasExtra("coin")){
            Coin coin = (Coin) storage.getExtras().get("coin");

            ImageView logo = (ImageView) findViewById(R.id.fab_coin_icon);
            Picasso
                    .with(this)
                    .load(coin.getLogoUrl())
                    .resize(80,80)
                    .onlyScaleDown()
                    .into(logo);

            TextView coinName = (TextView) findViewById(R.id.tv_coin_name);
            coinName.setText(coin.getMarketCurrencyLong());

            TextView volume = (TextView) findViewById(R.id.tv_details_volume);
            volume.setText(getString(R.string.volume) + coin.getVolume());

            TextView yesterday = (TextView) findViewById(R.id.tv_details_yesterday);
            yesterday.setText(getString(R.string.yesterday) + coin.getPrevDay());

            TextView highToday = (TextView) findViewById(R.id.tv_details_high_today);
            highToday.setText(getString(R.string.high_today) + coin.getHigh());

            TextView lowToday = (TextView) findViewById(R.id.tv_details_low_today);
            lowToday.setText(getString(R.string.low_today) + coin.getLow());

            TextView bid = (TextView) findViewById(R.id.tv_details_bid);
            bid.setText(getString(R.string.bid) + coin.getBid());

            TextView ask = (TextView) findViewById(R.id.tv_details_ask);
            ask.setText(getString(R.string.ask) + coin.getAsk());

        } else {
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}