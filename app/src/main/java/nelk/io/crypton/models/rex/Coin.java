package nelk.io.crypton.models.rex;

import nelk.io.crypton.models.enums.Crypto;
import nelk.io.crypton.models.enums.Fiat;
import nelk.io.crypton.models.utils.Value;

public class Coin implements Value{

    String name;
    String longName;
    String logoUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Double getCryptoValue(Crypto crypto) {
        return null;
    }

    public Double getFiatValue(Fiat fiat) {
        return null;
    }

}
