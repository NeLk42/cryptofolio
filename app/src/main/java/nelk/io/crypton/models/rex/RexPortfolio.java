package nelk.io.crypton.models.rex;


import java.util.List;

import nelk.io.crypton.models.Broker;
import nelk.io.crypton.models.Credentials;
import nelk.io.crypton.models.Portfolio;
import nelk.io.crypton.models.Position;
import nelk.io.crypton.models.Transaction;

public class RexPortfolio implements Portfolio {

    // Attributes
    String name;
    Broker broker;
    Credentials credentials;
    List<? extends Position> positions;
    List<? extends Transaction> deposits;
    List<? extends Transaction> withdrawals;

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public Broker getBroker() {
        return null;
    }

    @Override
    public void setBroker(Broker broker) {

    }

    @Override
    public Credentials getCredentials() {
        return null;
    }

    @Override
    public void setCredentials(Credentials credentials) {

    }

    @Override
    public List<? extends Position> getPositions() {
        return null;
    }

    @Override
    public void setPositions(List<? extends Position> positions) {

    }

    @Override
    public List<? extends Transaction> getDeposits() {
        return null;
    }

    @Override
    public void setDeposits(List<? extends Transaction> deposits) {

    }

    @Override
    public List<? extends Transaction> getWithdrawals() {
        return null;
    }

    @Override
    public void setWithdrawals(List<? extends Transaction> withdrawals) {

    }

    @Override
    public String getDesiredFiatCurrency() {
        return null;
    }

    @Override
    public void setDesiredFiatCurrency(String desiredFiatCurrency) {

    }
}
