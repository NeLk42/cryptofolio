package nelk.io.crypton.models;

import java.util.List;

public interface User extends Capital, Currency{

    // Attributes

    String getName();

    void setName(String name);

    String getKey();

    void setKey(String key);

    String getPrivateKey();

    void setPrivateKey(String key);

    List<Portfolio> getPortfolios();

    void setPortfolios(List<Portfolio> portfolios);

    void addPortfolio(Portfolio portfolio);

}
