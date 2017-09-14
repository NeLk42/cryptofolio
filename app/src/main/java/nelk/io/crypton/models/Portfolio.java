package nelk.io.crypton.models;

import java.util.List;

public interface Portfolio extends Currency, Value {

    public String getName();

    public void setName(String name);

    public Broker getBroker();

    public void setBroker(Broker broker);

    public Credentials getCredentials();

    public void setCredentials(Credentials credentials);

    public List<? extends Position> getPositions();

    public void setPositions(List<? extends Position> positions);

    public List<? extends Transaction> getDeposits();

    public void setDeposits(List<? extends Transaction> deposits);

    public List<? extends Transaction> getWithdrawals();

    public void setWithdrawals(List<? extends Transaction> withdrawals);

}
