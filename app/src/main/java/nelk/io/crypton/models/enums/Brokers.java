package nelk.io.crypton.models.enums;


public enum Brokers {

    BITTREX("Bittrex", "https://bittrex.com/api/v1.1/", "account/getbalances"),
    POLONIEX("Poloniex", "http://dummy/", "dummyBalance");

    private final String name;
    private final String baseUrl;
    private final String balancesUrl;

    Brokers(String name, String baseUrl, String balancesUrl) {
        this.name = name;
        this.baseUrl = baseUrl;
        this.balancesUrl = balancesUrl;
    }

    public String getName() {
        return name;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getBalancesUrl() {
        return balancesUrl;
    }
}
