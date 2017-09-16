package nelk.io.crypton.models.rex;

import java.util.List;

public class User {

    // User
    String name;
    List<? extends Portfolio> portfolios;

    // Currency
    String baseCurrency;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<? extends Portfolio> getPortfolios() {
        return portfolios;
    }

    public void setPortfolios(List<? extends Portfolio> portfolios) {
        this.portfolios = portfolios;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }


    // Methods

    public void addPortfolio(Portfolio portfolio) {

        Portfolio portfolio1 = portfolios.get(1);

    }

}