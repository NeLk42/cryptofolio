package nelk.io.crypton.models.enums;

public enum Cryptos implements EnumCurrencies{

    SAT("SAT", "Satoshi"),
    BTC("BTC", "Bitcoin"),
    LTC("LTC", "Litecoin"),
    ETH("ETH", "Ethereum");

    private final String cryptoLongName;
    private final String symbol;

    Cryptos(String symbol, String cryptoLongName){
        this.cryptoLongName = cryptoLongName;
        this.symbol = symbol;
    }

    public String getSymbol(){
        return symbol;
    }

    public String getPosition() {  return "end"; }

}
