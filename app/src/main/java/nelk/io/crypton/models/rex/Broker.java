package nelk.io.crypton.models.rex;

import java.util.List;

public class Broker {

    String name;
    String baseUrl;
    String balancesUrl;
    List<Market> markets;

    public Broker(String name, String baseUrl, String balancesUrl){
        this.name = name;
        this.baseUrl = baseUrl;
        this.balancesUrl = balancesUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getBalancesUrl() {
        return balancesUrl;
    }

    public void setBalancesUrl(String balancesUrl) {
        this.balancesUrl = balancesUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public List<Market> getMarkets() {
        return markets;
    }

    public void setMarkets(List<Market> markets) {
        this.markets = markets;
    }

}
