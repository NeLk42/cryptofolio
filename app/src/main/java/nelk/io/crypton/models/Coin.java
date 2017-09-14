package nelk.io.crypton.models;


import java.util.List;

import nelk.io.crypton.models.impl.RexMarket;

public interface Coin extends Capital, Currency{

    String getName();

    void setName(String name);

    String getLongName();

    void setLongName(String name);

    String getLogoUrl();

    void setLogoUrl(String logoUrl);

    String getBalance();

    void setBalance(String balance);

    List<RexMarket> getRexMarkets();

    void setRexMarkets(List<RexMarket> rexMarketList);

    List<Order> getOrders();

    void setOrders(List<Order> orderList);

    void addOrder(Order order);

}
