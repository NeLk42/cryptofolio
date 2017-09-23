package nelk.io.crypton.models.utils;

import java.text.DecimalFormat;

import nelk.io.crypton.models.enums.Cryptos;
import nelk.io.crypton.models.EnumCurrencies;

import static nelk.io.crypton.models.utils.MathOperations.calculatePercentageChange;


public class gridItemsWrapper {


    /* Percentages */

    public static String wrapPercentage(Double percentage){
        return new StringBuilder()
                .append(assignSign(percentage))
                .append(toDoubleDecimal(Math.abs(percentage)))
                .append("%")
                .toString();
    }

    public static String wrapPercentage(Double before, Double now){
        return new StringBuilder()
                .append(assignSign(before, now))
                .append(toDoubleDecimal(calculatePercentageChange(before, now)))
                .append("%")
                .toString();
    }


    /* Currency */

    public static String wrapEarnings(Double earnings, EnumCurrencies baseCurrencySymbol){
        return new StringBuilder()
                .append(assignSign(earnings))
                .append(addSymbol(earnings, baseCurrencySymbol))
                .toString();
    }

    public static String wrapEarnings(Double before, Double now, Double balanceValue, EnumCurrencies baseCurrencySymbol){
        double earnings = calculatePercentageChange(before, now) * balanceValue  / 100;

        return new StringBuilder()
                .append(assignSign(earnings))
                .append(addSymbol(earnings, baseCurrencySymbol))
                .toString();
    }

    public static String wrapSymbol(Double number, EnumCurrencies baseCurrencySymbol){

        return new StringBuilder()
                .append(addSymbol(number, baseCurrencySymbol))
                .toString();
    }


    /* Formatters */

    public static String toSatoshiDecimal(Double d) {
        DecimalFormat formatter = new DecimalFormat("####0.00000000");
        return formatter.format(d);
    }

    public static String toDoubleDecimal(Double d) {
        DecimalFormat formatter = new DecimalFormat("####0.00");
        return formatter.format(d);
    }

    private static String addSymbol(Double number, EnumCurrencies base){
        Double absNumber = Math.abs(number);
        StringBuilder sb = new StringBuilder();

        if (base instanceof Cryptos){
            return sb
                .append(toSatoshiDecimal(absNumber))
                .append(" ")
                .append(base.getSymbol())
                .toString();
        } else {
            return sb
                .append(base.getSymbol())
                .append(toDoubleDecimal(absNumber))
                .toString();
        }
    }

    private static String assignSign(Double number){
        String result = "";
        if (number < 0){
            result = "-";
        }
        if (number > 0){
            result = "+";
        }
        return result;
    }

    private static String assignSign(Double before, Double now){
        String result = "";
        if (now < before){
            result = "-";
        }
        if (before > now){
            result = "+";
        }
        return result;
    }
}
