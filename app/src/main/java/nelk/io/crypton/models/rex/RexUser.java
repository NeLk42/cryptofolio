package nelk.io.crypton.models.rex;

import java.util.List;

import nelk.io.crypton.models.Portfolio;
import nelk.io.crypton.models.User;

class RexUser implements User {

    // User
    String name;
    String key;
    String privateKey;
    List<? extends Portfolio> portfolios;

    // Currency
    String baseCurrency;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
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

}