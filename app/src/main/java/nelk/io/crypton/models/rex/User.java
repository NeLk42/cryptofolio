package nelk.io.crypton.models.rex;

import java.util.HashMap;
import java.util.Map;

public class User {

    // User
    String name;
    Map<String, Portfolio> portfolios;

    // Currency
    String baseCurrency;

    public User(String name){
        this.name = name;
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

}