package nelk.io.crypton.models.utils;


public interface Currency {

    // Configure desired currency ($, £, €,...)

    abstract String getBaseCurrency();

    abstract void setBaseCurrency(String baseCurrency);

}
