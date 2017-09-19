package nelk.io.crypton.models.enums;

public enum Crypto {

    SAT("SAT", "Satoshi"),
    BTC("BTC", "Bitcoin"),
    LTC("LTC", "Litecoin"),
    ETH("ETH", "Ethereum");

    private final String cryptoLongName;
    private final String cryptoName;

    Crypto(String cryptoName, String cryptoLongName){
        this.cryptoLongName = cryptoLongName;
        this.cryptoName = cryptoName;
    }

    public String getCryptoName(){
        return cryptoName;
    }

}
