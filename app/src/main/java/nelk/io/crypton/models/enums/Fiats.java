package nelk.io.crypton.models.enums;

public enum Fiats {

    GBP("£", "Pound"),
    USD("$", "Dollar"),
    EUR("€", "Euro");

    private String symbol;
    private String fiatName;

    Fiats(String symbol, String fiatName){
        this.fiatName = fiatName;
        this.symbol = symbol;
    }

    public String getSymbol(){
        return symbol;
    }

}
