package nelk.io.crypton.models;


public interface Coin extends Value {

    String getName();

    void setName(String name);

    String getLongName();

    void setLongName(String longName);

    String getLogoUrl();

    void setLogoUrl(String logoUrl);

}
