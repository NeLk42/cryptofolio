package nelk.io.crypton.models;

import java.util.List;

public interface User {

    String getName();

    void setName(String name);

    String getKey();

    void setKey(String key);

    String getPrivateKey();

    void setPrivateKey(String privateKey);

    List<? extends Portfolio> getPortfolios();

    void setPortfolios(List<? extends Portfolio> portfolios);

    String getBaseCurrency();

    void setBaseCurrency(String baseCurrency);

}
