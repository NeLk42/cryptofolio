package nelk.io.crypton.models.utils;


import nelk.io.crypton.models.enums.Crypto;
import nelk.io.crypton.models.enums.Fiat;

public interface Value {

    // Methods

    abstract Double getCryptoValue(Crypto crypto);

    abstract Double getFiatValue(Fiat fiat);

}
