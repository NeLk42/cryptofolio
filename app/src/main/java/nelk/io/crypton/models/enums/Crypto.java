package nelk.io.crypton.models.enums;

public enum Crypto {

    BTC("bitcoin"),
    LTC("litecoin"),
    ETH("ethereum");

    private String cryptoName;

    Crypto(String cryptoName){
        this.cryptoName = cryptoName;
    }

    public String getCryptoName(){
        return cryptoName;
    }

}
