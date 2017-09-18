package nelk.io.crypton.models.enums;

public enum Crypto {


    BTC("BTC", "bitcoin"),
    LTC("LTC", "litecoin"),
    ETH("ETH", "ethereum");

    private final String cryptoLongName;
    private String cryptoName;

    Crypto(String cryptoName, String cryptoLongName){
        this.cryptoLongName = cryptoLongName;
        this.cryptoName = cryptoName;
    }

    public String getCryptoName(){
        return cryptoName;
    }

}
