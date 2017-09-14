package nelk.io.crypton.models.enums;


public enum Fiat {

    GBP("pound"),
    USD("dollar"),
    EUR("euro");

    private String fiat;

    Fiat(String fiat){
        this.fiat = fiat;
    }

    public String getFiat(){
        return fiat;
    }

}
