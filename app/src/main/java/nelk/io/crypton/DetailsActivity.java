package nelk.io.crypton;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import nelk.io.crypton.retrofit.models.Bittrex.RexData;

public class DetailsActivity extends AppCompatActivity {
    public static final String TAG = DetailsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent storage = getIntent();

        if (storage.hasExtra("coin")){
            RexData rexData = (RexData) storage.getExtras().get("rexData");

            ImageView logo = (ImageView) findViewById(R.id.fab_coin_icon);
            Picasso
                    .with(this)
                    .load(rexData.getLogoUrl())
                    .resize(80,80)
                    .onlyScaleDown()
                    .into(logo);

            TextView coinName = (TextView) findViewById(R.id.tv_coin_name);
            coinName.setText(rexData.getMarketCurrencyLong());

            TextView volume = (TextView) findViewById(R.id.tv_details_volume);
            volume.setText(getString(R.string.volume) + rexData.getVolume());

            TextView yesterday = (TextView) findViewById(R.id.tv_details_yesterday);
            yesterday.setText(getString(R.string.yesterday) + rexData.getPrevDay());

            TextView highToday = (TextView) findViewById(R.id.tv_details_high_today);
            highToday.setText(getString(R.string.high_today) + rexData.getHigh());

            TextView lowToday = (TextView) findViewById(R.id.tv_details_low_today);
            lowToday.setText(getString(R.string.low_today) + rexData.getLow());

            TextView bid = (TextView) findViewById(R.id.tv_details_bid);
            bid.setText(getString(R.string.bid) + rexData.getBid());

            TextView ask = (TextView) findViewById(R.id.tv_details_ask);
            ask.setText(getString(R.string.ask) + rexData.getAsk());

        } else {
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}