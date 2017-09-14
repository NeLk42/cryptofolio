package nelk.io.crypton.models;


public interface Currency {

    // Configure desired currency ($, £, €,...)

    String getBaseCurrency();

    void setBaseCurrency(String baseCurrency);

}
