package nelk.io.crypton.models;

import java.util.List;

public interface User {

    // Attributes

    String getUsername();

    void setUsername(String username);

    String getKey();

    void setKey(String key);

    String getPrivateKey();

    void setPrivateKey(String key);

    List<Portfolio> getPortfolios();

    void setPortfolios(List<Portfolio> portfolios);

    void addPortfolio(Portfolio portfolio);

    String getDesiredFiatCurrency();

    void setDesiredFiatCurrency(String desiredFiatCurrency);

    String getPortfolioInvestedValue();

    void updatePortfolioInvestedValue(String portfolioInvestedValue);


    // Calculations

    String getPortfolioInitialFiatValue();

    String getPortfolioFiatDifference();

    String getInvestmentDifference();

}
