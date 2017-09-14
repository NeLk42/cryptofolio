package nelk.io.crypton.models;


import java.text.SimpleDateFormat;

public interface Position {

    String getMarketName();

    void setMarketName(String marketName);

    SimpleDateFormat getDate();

    void setDate(SimpleDateFormat date);

    Double getQuantity();

    void setQuantity(Double quantity);

    Double getRate();

    void setRate(Double rate);

}
