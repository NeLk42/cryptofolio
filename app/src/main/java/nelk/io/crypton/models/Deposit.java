package nelk.io.crypton.models;

public interface Deposit {

    String getPaymentUuid();

    String getCurrency();

    String getAmount();

    String getAddress();

    String getOpened();

    String getAuthorized();

    String getPendingPayment();

    String getTxCost();

    String getTxId();

    String getCanceled();

    String getInvalidAddress();
}
