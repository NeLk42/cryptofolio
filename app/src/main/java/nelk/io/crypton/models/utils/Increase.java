package nelk.io.crypton.models.utils;

import java.text.DecimalFormat;

public class Increase {
    public static String valueChange(Double before, Double now, Double balanceValue, String baseCurrencySymbol){
        DecimalFormat formatter = new DecimalFormat("####0.00");

        double number = getPercentageChangeValue(before, now) * balanceValue  / 100;
        return new StringBuilder()
                .append(assignSymbol(before, now))
                .append(baseCurrencySymbol)
                .append(formatter.format(number))
                .toString();
    }

    public static String percentageChange(Double before, Double now){
        DecimalFormat formatter = new DecimalFormat("####0.00");
        return new StringBuilder()
                .append(assignSymbol(before, now))
                .append(formatter.format(getPercentageChangeValue(before, now)))
                .append("%")
                .toString();
    }

    public static Double getPercentageChangeValue(Double before, Double now) {
        return difference(before, now) * 100 / before;
    }

    private static String assignSymbol(Double before, Double now){
        if (before > now){
            return "-";
        } else {
            return "+";
        }
    }

    private static Double difference(Double before, Double now){
        return Math.abs(before - now);
    }
}
