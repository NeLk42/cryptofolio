package nelk.io.crypton.models;


public interface Transaction {

    String getPaymentUuid();

    void setPaymentUuid(String paymentUuid);

    String getCurrency();

    void setCurrency(String currency);

    String getAmount();

    void setAmount(String amount);

    String getAddress();

    void setAddress(String address);

    String getOpened();

    void setOpened(String opened);

    String getAuthorized();

    void setAuthorized(String authorized);

    String getPendingPayment();

    void setPendingPayment(String pendingPayment);

    String getTxCost();

    void setTxCost(String txCost);

    String getTxId();

    void setTxId(String txId);

    String getCanceled();

    void setCanceled(String canceled);

    String getInvalidAddress();

    void setInvalidAddress(String invalidAddress);

}
