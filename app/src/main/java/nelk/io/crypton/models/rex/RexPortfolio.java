package nelk.io.crypton.models.rex;


import java.util.List;

import nelk.io.crypton.models.Broker;
import nelk.io.crypton.models.Credentials;
import nelk.io.crypton.models.Portfolio;
import nelk.io.crypton.models.Position;
import nelk.io.crypton.models.Transaction;
import nelk.io.crypton.models.enums.Crypto;
import nelk.io.crypton.models.enums.Fiat;

public class RexPortfolio implements Portfolio {

    // Portfolio
    String name;
    Broker broker;
    Credentials credentials;
    List<? extends Position> positions;
    List<? extends Transaction> deposits;
    List<? extends Transaction> withdrawals;

    // Base Currency
    String baseCurrency;

    public String getBaseCurrency() {
        return null;
    }

    public void setBaseCurrency(String baseCurrency) {

    }

    public String getName() {
        return null;
    }

    public void setName(String name) {

    }

    public Broker getBroker() {
        return null;
    }

    public void setBroker(Broker broker) {

    }

    public Credentials getCredentials() {
        return null;
    }

    public void setCredentials(Credentials credentials) {

    }

    public List<? extends Position> getPositions() {
        return null;
    }

    public void setPositions(List<? extends Position> positions) {

    }

    public List<? extends Transaction> getDeposits() {
        return null;
    }

    public void setDeposits(List<? extends Transaction> deposits) {

    }

    public List<? extends Transaction> getWithdrawals() {
        return null;
    }

    public void setWithdrawals(List<? extends Transaction> withdrawals) {

    }

    public Double getCryptoValue(Crypto crypto) {
        return null;
    }

    public Double getFiatValue(Fiat fiat) {
        return null;
    }

}
