package nelk.io.crypton.models;


import java.util.List;

import nelk.io.crypton.models.impl.Market;

public interface ICoin {

    String getName();

    void setName(String name);

    String getLongName();

    void setLongName(String name);

    String getLogoUrl();

    void setLogoUrl(String logoUrl);

    String getBalance();

    void setBalance(String balance);

    List<Market> getMarkets();

    void setMarkets(List<Market> marketList);

    List<IOrder> getOrders();

    void setOrders(List<IOrder> orderList);

    void addOrder(IOrder order);


    // Investment, Fiat and Earnings

    String getInvestedValue();

    void setInvestedValue(String investedValue);

    String getFiatValueNow();

    void setFiatValueNow(String valueNow);


    // Calculations

    String getFiatEarnings();

    String getPercentageEarnings();

}
