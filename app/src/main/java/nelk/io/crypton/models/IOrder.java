package nelk.io.crypton.models;


public interface IOrder {

    String getMarketName();

    void setMarketName(String marketName);

    String getQuantity();

    void setQuantity(String quantity);

    String getRate();

    void setRate(String rate);

}
