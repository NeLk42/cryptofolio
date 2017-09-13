package nelk.io.crypton.models;


import java.util.Map;

public interface IPortfolio {

    // Attributes

    String getName();

    void setName(String name);

    Map<String, ICoin> getAllBalances();

    void setAllBalances(Map<String, ICoin> coinMap);

    void setCoinBalance(String coinName, String balance);

    void setCoinBalance(ICoin coin);

    void addCoin(ICoin coin);

    String getDesiredFiatCurrency();

    void setDesiredFiatCurrency(String desiredFiatCurrency);


    // Investment, Fiat and Earnings

    String getInvestedValue();

    void setInvestedValue(String investedValue);

    String getFiatValueNow();

    void setFiatValueNow(String valueNow);


    // Calculations

    ICoin getCoinBalance(String coinName);

    String getFiatEarnings();

    String getPercentageEarnings();
}
