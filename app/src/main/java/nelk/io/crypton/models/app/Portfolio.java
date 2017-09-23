package nelk.io.crypton.models.app;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nelk.io.crypton.models.enums.Brokers;

public class Portfolio {

    // Portfolio
    private String name;
    private Broker broker;
    private Credentials credentials;
    private List<Balance> balances;
    private Map<String, Market> markets;
    private List<? extends Position> positions;
    private List<? extends Transaction> deposits;
    private List<? extends Transaction> withdrawals;

    public Portfolio(String name, Brokers broker, Credentials portfolioCredentials){
        this.name = name;
        this.markets = new HashMap<>();
        this.broker = new Broker(broker.getName(),
                                 broker.getBaseUrl(),
                                 broker.getBalancesUrl());
        credentials = new Credentials(portfolioCredentials.getKey(),
                                      portfolioCredentials.getPrivateKey());
    }

    private String baseCurrency;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Broker getBroker() {
        return broker;
    }

    public void setBroker(Broker broker) {
        this.broker = broker;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(String key, String privateKey) {
        this.credentials.setKey(key);
        this.credentials.setPrivateKey(privateKey);
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = new Credentials(credentials);
    }

    public List<Balance> getBalances() {
        return balances;
    }

    public void setBalances(List<Balance> balances) {
        this.balances = balances;
    }

    public Map<String, Market> getMarkets() {
        return markets;
    }

    public void setMarkets(Map<String, Market> markets) {
        this.markets = markets;
    }

    public List<? extends Position> getPositions() {
        return positions;
    }

    public void setPositions(List<? extends Position> positions) {
        this.positions = positions;
    }

    public List<? extends Transaction> getDeposits() {
        return deposits;
    }

    public void setDeposits(List<? extends Transaction> deposits) {
        this.deposits = deposits;
    }

    public List<? extends Transaction> getWithdrawals() {
        return withdrawals;
    }

    public void setWithdrawals(List<? extends Transaction> withdrawals) {
        this.withdrawals = withdrawals;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }
}
