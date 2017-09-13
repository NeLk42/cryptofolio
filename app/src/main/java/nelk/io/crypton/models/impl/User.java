package nelk.io.crypton.models.impl;

import java.util.List;

import nelk.io.crypton.models.IUser;
import nelk.io.crypton.models.IPortfolio;


class User implements IUser {

    // Attributes
    public String name;
    public String key;
    public String privateKey;
    public List<IPortfolio> portfolios;
    public String desiredFiatCurrency;
    public String totalInvestedValue;
    public String totalFiatValueNow;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String getPrivateKey() {
        return privateKey;
    }

    @Override
    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    @Override
    public List<IPortfolio> getPortfolios() {
        return portfolios;
    }

    @Override
    public void setPortfolios(List<IPortfolio> portfolios) {
        this.portfolios = portfolios;
    }

    @Override
    public void addPortfolio(IPortfolio portfolio) {
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
    public String getTotalInvestedValue() {
        return totalInvestedValue;
    }

    @Override
    public void setTotalInvestedValue(String totalInvestedValue) {
        this.totalInvestedValue = totalInvestedValue;
    }

    @Override
    public String getTotalFiatValueNow() {
        return totalFiatValueNow;
    }

    @Override
    public void setTotalFiatValueNow(String totalFiatValueNow) {
        this.totalFiatValueNow = totalFiatValueNow;
    }

    @Override
    public String getTotalFiatEarnings() {
        // TODO
        return null;
    }

    @Override
    public String getTotalPercentageEarnings() {
        // TODO
        return null;
    }

}