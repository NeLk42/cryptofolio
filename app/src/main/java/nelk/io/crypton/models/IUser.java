package nelk.io.crypton.models;

import java.util.List;

import nelk.io.crypton.models.impl.Deposit;

public interface IUser {

    // Attributes

    String getName();

    void setName(String name);

    String getKey();

    void setKey(String key);

    String getPrivateKey();

    void setPrivateKey(String key);

    List<IPortfolio> getPortfolios();

    void setPortfolios(List<IPortfolio> portfolios);

    void addPortfolio(IPortfolio portfolio);

    String getDesiredFiatCurrency();

    void setDesiredFiatCurrency(String desiredFiatCurrency);


    // Investment, Fiat and Earnings

    List<Deposit> getDeposits();

    void setDeposits(List<Deposit> deposits);

    String getTotalInvestedValue();

    void setTotalInvestedValue(String investedValue);

    String getTotalFiatValueNow();

    void setTotalFiatValueNow(String valueNow);


    // Calculations

    String getTotalFiatEarnings();

    String getTotalPercentageEarnings();

}
