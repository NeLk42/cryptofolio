package nelk.io.crypton.models.rex;


import java.text.SimpleDateFormat;

import nelk.io.crypton.models.Position;

public class RexPosition implements Position {

    String marketName;
    SimpleDateFormat date;
    Double quantity;
    Double rate;

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public SimpleDateFormat getDate() {
        return date;
    }

    public void setDate(SimpleDateFormat date) {
        this.date = date;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
