package nelk.io.crypton.models.utils;

import java.text.DecimalFormat;

import static nelk.io.crypton.models.utils.ValueUtils.getBTCValue;

public class Increase {

    public static String valueChange(Double before, Double now, String baseCurrencySymbol){
        DecimalFormat formatter = new DecimalFormat("####0.00");
        return new StringBuilder()
                .append(assignSymbol(before, now))
                .append(baseCurrencySymbol)
                .append(formatter.format(difference(before, now)*100/before))
                .toString();
    }

    public static String percentageChange(Double before, Double now){
        DecimalFormat formatter = new DecimalFormat("####0.00");
        return new StringBuilder()
                .append(assignSymbol(before, now))
                .append(formatter.format(difference(before, now)*100/before))
                .append("%")
                .toString();
    }

    public static String assignSymbol(Double before, Double now){
        if (before < now){
            return "-";
        } else {
            return "+";
        }
    }

    public static Double difference(Double before, Double now){
        return Math.abs(before - now);
    }
}
