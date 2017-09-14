package nelk.io.crypton.models;


public interface Capital {

    // Investment, Fiat and Earnings

    String getTotalInvestedValue();

    void setTotalInvestedValue(String investedValue);

    String getTotalFiatValueNow();

    void setTotalFiatValueNow(String valueNow);


    // Calculations

    String getTotalFiatEarnings();

    String getTotalPercentageEarnings();

}
