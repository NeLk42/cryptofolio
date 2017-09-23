package nelk.io.crypton.models.app;

import java.util.HashMap;
import java.util.Map;

import nelk.io.crypton.models.enums.Cryptos;
import nelk.io.crypton.models.enums.EnumCurrencies;

public class User {

    // User
    private String name;
    private Map<String, Portfolio> portfolios;

    // Currency
    private EnumCurrencies baseCurrency;
    private String baseCurrencyPosition;

    public User(String name, EnumCurrencies baseCurrency){
        this.name = name;
        this.baseCurrencyPosition = (baseCurrency instanceof Cryptos) ? "end" : "start";

        this.baseCurrency = baseCurrency;
        this.portfolios = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Portfolio> getPortfolios() {
        return portfolios;
    }

    public void setPortfolios(Map<String, Portfolio> portfolios) {
        this.portfolios = portfolios;
    }

    public EnumCurrencies getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(EnumCurrencies baseCurrency) {
        this.baseCurrency = baseCurrency;
    }


    // Methods

    public void updatePortfolio(Portfolio portfolio) {

        this.portfolios.put(portfolio.getName(), portfolio);

    }

    public Portfolio getPortfolio(String portfolioId) {

        return this.portfolios.get(portfolioId);

    }

}