package nelk.io.crypton.models.utils;

import java.text.DecimalFormat;

import static nelk.io.crypton.models.utils.MathOperations.calculatePercentageChange;


public class gridItemsWrapper {

    public static String wrapEarnings(Double before, Double now, Double balanceValue, String baseCurrencySymbol){
        double earnings = calculatePercentageChange(before, now) * balanceValue  / 100;

        return new StringBuilder()
                .append(assignSymbol(before, now))
                .append(baseCurrencySymbol)
                .append(normalizeToDoubleDecimal(earnings))
                .toString();
    }

    public static String wrapPercentage(Double before, Double now){
        return new StringBuilder()
                .append(assignSymbol(before, now))
                .append(normalizeToDoubleDecimal(calculatePercentageChange(before, now)))
                .append("%")
                .toString();
    }

    private static String wrapWithCurrencySymbol(Double value, String baseCurrencySymbol){
        return new StringBuilder()
                .append(baseCurrencySymbol)
                .append(value)
                .toString();
    }

    private static String normalizeToDoubleDecimal(Double d) {
        DecimalFormat formatter = new DecimalFormat("####0.00");
        return formatter.format(d);
    }

    private static String assignSymbol(Double before, Double now){
        if (before > now){
            return "-";
        } else {
            return "+";
        }
    }
}
