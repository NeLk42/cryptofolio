package nelk.io.crypton.models;


public interface Currency {

    // Configure desired currency ($, £, €,...)

    String getDesiredFiatCurrency();

    void setDesiredFiatCurrency(String desiredFiatCurrency);

}
