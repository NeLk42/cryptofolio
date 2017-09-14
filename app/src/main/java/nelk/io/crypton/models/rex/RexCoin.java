package nelk.io.crypton.models.rex;

import nelk.io.crypton.models.Coin;
import nelk.io.crypton.models.enums.Crypto;
import nelk.io.crypton.models.enums.Fiat;

class RexCoin implements Coin {

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

    @Override
    public Double getCryptoValue(Crypto crypto) {
        return null;
    }

    @Override
    public Double getFiatValue(Fiat fiat) {
        return null;
    }
}
