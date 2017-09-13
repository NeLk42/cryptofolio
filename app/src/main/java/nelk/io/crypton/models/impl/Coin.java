package nelk.io.crypton.models.impl;

import java.util.List;

import nelk.io.crypton.models.ICoin;
import nelk.io.crypton.models.IOrder;

class Coin implements ICoin {

    // Attributes
    String name;
    String longName;
    String logoUrl;
    String balance;
    List<Market> markets;
    List<IOrder> orders;

    // Investment, Fiat and Earnings
    String investedValue;
    String fiatValueNow;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getLongName() {
        return longName;
    }

    @Override
    public void setLongName(String longName) {
        this.longName = longName;
    }

    @Override
    public String getLogoUrl() {
        return logoUrl;
    }

    @Override
    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    @Override
    public String getBalance() {
        return balance;
    }

    @Override
    public void setBalance(String balance) {
        this.balance = balance;
    }

    @Override
    public List<Market> getMarkets() {
        return markets;
    }

    @Override
    public void setMarkets(List<Market> markets) {
        this.markets = markets;
    }

    @Override
    public List<IOrder> getOrders() {
        return orders;
    }

    @Override
    public void setOrders(List<IOrder> orders) {
        this.orders = orders;
    }

    @Override
    public void addOrder(IOrder order) {
        // TODO
    }

    @Override
    public String getInvestedValue() {
        return investedValue;
    }

    public void setInvestedValue(String investedValue) {
        this.investedValue = investedValue;
    }

    @Override
    public String getFiatValueNow() {
        return fiatValueNow;
    }

    @Override
    public void setFiatValueNow(String fiatValueNow) {
        this.fiatValueNow = fiatValueNow;
    }

    @Override
    public String getFiatEarnings() {
        // TODO
        return null;
    }

    @Override
    public String getPercentageEarnings() {
        // TODO
        return null;
    }
}
