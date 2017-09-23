package nelk.io.crypton.models.enums;

public enum Cryptos {

    SAT("SAT", "Satoshi"),
    BTC("BTC", "Bitcoin"),
    LTC("LTC", "Litecoin"),
    ETH("ETH", "Ethereum");

    private final String cryptoLongName;
    private final String cryptoName;

    Cryptos(String cryptoName, String cryptoLongName){
        this.cryptoLongName = cryptoLongName;
        this.cryptoName = cryptoName;
    }

    public String getCryptoName(){
        return cryptoName;
    }

}
