package nelk.io.crypton.models;

import java.util.List;

public interface Broker {

    public String getName();

    public void setName(String name);

    public List<Market> getMarkets();

    public void setMarkets(List<Market> markets);

}
