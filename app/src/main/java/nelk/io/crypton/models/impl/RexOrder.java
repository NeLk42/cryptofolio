package nelk.io.crypton.models.impl;

import nelk.io.crypton.models.Order;

public class RexOrder implements Order {

    // Attributes
    String marketName;
    String quantity;
    String rate;

    @Override
    public String getMarketName() {
        return marketName;
    }

    @Override
    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    @Override
    public String getQuantity() {
        return quantity;
    }

    @Override
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Override
    public String getRate() {
        return rate;
    }

    @Override
    public void setRate(String rate) {
        this.rate = rate;
    }
}
