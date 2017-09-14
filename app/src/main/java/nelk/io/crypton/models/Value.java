package nelk.io.crypton.models;


import nelk.io.crypton.models.enums.Crypto;
import nelk.io.crypton.models.enums.Fiat;

public interface Value {

    // Methods

    Double getCryptoValue(Crypto crypto);

    Double getFiatValue(Fiat fiat);

}
