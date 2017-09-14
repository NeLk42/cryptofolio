package nelk.io.crypton.models.rex;

import java.util.List;

import nelk.io.crypton.models.Broker;
import nelk.io.crypton.models.Market;

class RexBroker implements Broker {

    String name;
    List<Market> markets;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Market> getMarkets() {
        return markets;
    }

    public void setMarkets(List<Market> markets) {
        this.markets = markets;
    }

}
