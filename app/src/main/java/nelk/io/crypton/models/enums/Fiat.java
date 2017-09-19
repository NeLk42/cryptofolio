package nelk.io.crypton.models.enums;

public enum Fiat {

    GBP("£", "Pound"),
    USD("$", "Dollar"),
    EUR("€", "Euro");

    private String symbol;
    private String fiatName;

    Fiat(String symbol, String fiatName){
        this.fiatName = fiatName;
        this.symbol = symbol;
    }

    public String getSymbol(){
        return symbol;
    }

}
