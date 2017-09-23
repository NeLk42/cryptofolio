package nelk.io.crypton.models.utils;

import java.math.BigDecimal;

public class MathOperations {


    public static Double calculatePercentageChange(Double before, Double now) {
        return calculateValueChange(before, now) * 100 / before;
    }

    public static Double calculateAbsoluteValueChange(Double before, Double now){
        return Math.abs(before - now);
    }

    public static Double calculateValueChange(Double before, Double now){
        return before - now;
    }

    public static Double calculateBalanceTotalValue(Double costPerUnit, Double coinBalance){
        BigDecimal rate = BigDecimal.valueOf(costPerUnit);
        BigDecimal balance = BigDecimal.valueOf(coinBalance);

        return rate.multiply(balance).doubleValue();
    }
}
