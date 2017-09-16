package nelk.io.crypton.models.rex;


import java.util.List;

import nelk.io.crypton.models.enums.Crypto;
import nelk.io.crypton.models.enums.Fiat;
import nelk.io.crypton.models.utils.Currency;
import nelk.io.crypton.models.utils.Value;

public class Portfolio implements Currency, Value{

    // Portfolio
    String name;
    Broker broker;
    Credentials credentials;
    List<Balance> balances;
    List<? extends Position> positions;
    List<? extends Transaction> deposits;
    List<? extends Transaction> withdrawals;

    public Portfolio(Broker broker){
        this.broker = broker;
    }

    // Base Currency
    String baseCurrency;

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
        this.credentials.setKey(credentials.getKey());
        this.credentials.setPrivateKey(credentials.getPrivateKey());
    }

    public List<Balance> getBalances() {
        return balances;
    }

    public void setBalances(List<Balance> balances) {
        this.balances = balances;
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

    public Double getCryptoValue(Crypto crypto) {
        return null;
    }

    public Double getFiatValue(Fiat fiat) {
        return null;
    }
}
