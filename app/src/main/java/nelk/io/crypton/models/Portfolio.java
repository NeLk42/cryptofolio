package nelk.io.crypton.models;


import java.util.Map;

public interface Portfolio extends Capital, Currency{

    // Attributes

    String getName();

    void setName(String name);

    Map<String, Coin> getAllBalances();

    void setAllBalances(Map<String, Coin> coinMap);

    void setCoinBalance(String coinName, String balance);

    void setCoinBalance(Coin coin);

    void addCoin(Coin coin);


    // Calculations

    Coin getCoinBalance(String coinName);

}
