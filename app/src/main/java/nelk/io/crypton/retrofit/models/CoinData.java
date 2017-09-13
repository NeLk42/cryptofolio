package nelk.io.crypton.retrofit.models;


import android.os.Parcelable;

public interface CoinData extends Parcelable {

    CoinData addData(CoinData coinData);

    String getLogoUrl();
    String getMarketName();
    String getPrevDay();
    String getHigh();
    String getLow();
    String getBid();
    String getAsk();
    String getVolume();
    String getMarketCurrencyLong();
    String getMarketCurrency();
    String getBaseCurrencyLong();
    String getBaseCurrency();
    String getMinTradeSize();
    String getIsActive();
    String getNotice();
    String getIsSponsored();
    String getLast();
    String getBaseVolume();
    String getTimeStamp();
    String getOpenBuyOrders();
    String getOpenSellOrders();
    String getCreated();

    // getBalance
    String getCurrency();
    Double getBalance();
    Double getAvailable();
    Double getPending();
    String getCryptoAddress();
}
