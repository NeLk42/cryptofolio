package nelk.io.crypton.recyclerview.helpers;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import nelk.io.crypton.recyclerview.BalanceAdapter;

public class GridItemHelper {

    public static void setGridItemText(TextView textView, String coinParam) {
        textView.setText(coinParam);
    }

    public static void setGridItemLogo(ImageView imageView, String coinParam, Context mContext) {
        Picasso.with(mContext)
                .load(coinParam)
                .resize(128, 128)
                .onlyScaleDown()
                .into(imageView);
    }
}