package nelk.io.crypton.models.app;

import java.util.HashMap;
import java.util.Map;

public class User {

    // User
    private String name;
    private Map<String, Portfolio> portfolios;

    // Currency
    private String baseCurrency;

    public User(String name, String baseCurrency){
        this.name = name;
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

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
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