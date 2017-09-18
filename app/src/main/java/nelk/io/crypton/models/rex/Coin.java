package nelk.io.crypton.models.rex;

public class Coin {

    private String name;
    private String longName;
    private String logoUrl;

    public Coin(String name, String longName, String logoUrl){
        this.name = name;
        this.longName = longName;
        this.logoUrl = logoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

}
