package nelk.io.crypton.models.impl;


import java.util.Map;

import nelk.io.crypton.models.ICoin;
import nelk.io.crypton.models.IPortfolio;

public class Portfolio implements IPortfolio{

    // Attributes
    String name;
    Map<String, ICoin> balances;
    String desiredFiatCurrency;

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
    public Map<String, ICoin> getAllBalances() {
        return balances;
    }

    @Override
    public void setAllBalances(Map<String, ICoin> balances) {
        this.balances = balances;
    }

    public void setCoinBalance(String coinName, String balance){
        // TODO
    }

    public void setCoinBalance(ICoin coin){
        // TODO
    }

    public void addCoin(ICoin coin){
        // TODO
    }

    @Override
    public String getDesiredFiatCurrency() {
        return desiredFiatCurrency;
    }

    @Override
    public void setDesiredFiatCurrency(String desiredFiatCurrency) {
        this.desiredFiatCurrency = desiredFiatCurrency;
    }

    @Override
    public String getInvestedValue() {
        return investedValue;
    }

    @Override
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
    public ICoin getCoinBalance(String coin){
        // TODO
        return null;
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
