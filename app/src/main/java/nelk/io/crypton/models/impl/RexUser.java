package nelk.io.crypton.models.impl;

import java.util.List;

import nelk.io.crypton.models.User;
import nelk.io.crypton.models.Portfolio;


class RexUser implements User {

    // Attributes
    public String name;
    public String key;
    public String privateKey;
    public List<Portfolio> portfolios;
    public String desiredFiatCurrency;
    public List<RexDeposit> rexDeposits;
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
    public List<Portfolio> getPortfolios() {
        return portfolios;
    }

    @Override
    public void setPortfolios(List<Portfolio> portfolios) {
        this.portfolios = portfolios;
    }

    @Override
    public void addPortfolio(Portfolio portfolio) {
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

    public List<RexDeposit> getRexDeposits() {
        return rexDeposits;
    }

    public void setRexDeposits(List<RexDeposit> rexDeposits) {
        this.rexDeposits = rexDeposits;
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