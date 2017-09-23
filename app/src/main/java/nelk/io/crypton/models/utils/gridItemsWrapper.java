package nelk.io.crypton.models.utils;

import java.text.DecimalFormat;

import static nelk.io.crypton.models.utils.MathOperations.calculatePercentageChange;


public class gridItemsWrapper {

    public static String wrapEarnings(Double earnings, String baseCurrencySymbol){
        return new StringBuilder()
                .append(assignSymbol(earnings))
                .append(baseCurrencySymbol)
                .append(toDoubleDecimal(earnings))
                .toString();
    }

    public static String wrapEarnings(Double before, Double now, Double balanceValue, String baseCurrencySymbol){
        double earnings = calculatePercentageChange(before, now) * balanceValue  / 100;

        return new StringBuilder()
                .append(assignSymbol(before, now))
                .append(baseCurrencySymbol)
                .append(toDoubleDecimal(earnings))
                .toString();
    }

    public static String wrapPercentage(Double percentage){
        return new StringBuilder()
                .append(assignSymbol(percentage))
                .append(toDoubleDecimal(Math.abs(percentage)))
                .append("%")
                .toString();
    }

    public static String wrapPercentage(Double before, Double now){
        return new StringBuilder()
                .append(assignSymbol(before, now))
                .append(toDoubleDecimal(calculatePercentageChange(before, now)))
                .append("%")
                .toString();
    }

    public static String wrapSymbol(Double value, String baseCurrencySymbol){
        return new StringBuilder()
                .append(baseCurrencySymbol)
                .append(toDoubleDecimal(value))
                .toString();
    }

    public static String toDoubleDecimal(Double d) {
        DecimalFormat formatter = new DecimalFormat("####0.00");
        return formatter.format(d);
    }

    private static String assignSymbol(Double percentage){
        if (percentage < 0){
            return "-";
        } else {
            return "+";
        }
    }

    private static String assignSymbol(Double before, Double now){
        if (before > now){
            return "-";
        } else {
            return "+";
        }
    }
}
