package nelk.io.crypton.models.rex;


import nelk.io.crypton.models.enums.Crypto;
import nelk.io.crypton.models.enums.Fiat;
import nelk.io.crypton.models.utils.Value;

public class Transaction{

    String paymentUuid;
    String currency;
    String amount;
    String address;
    String opened;
    String authorized;
    String pendingPayment;
    String txCost;
    String txId;
    String canceled;
    String invalidAddress;

    public String getPaymentUuid() {
        return paymentUuid;
    }

    public void setPaymentUuid(String paymentUuid) {
        this.paymentUuid = paymentUuid;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOpened() {
        return opened;
    }

    public void setOpened(String opened) {
        this.opened = opened;
    }

    public String getAuthorized() {
        return authorized;
    }

    public void setAuthorized(String authorized) {
        this.authorized = authorized;
    }

    public String getPendingPayment() {
        return pendingPayment;
    }

    public void setPendingPayment(String pendingPayment) {
        this.pendingPayment = pendingPayment;
    }

    public String getTxCost() {
        return txCost;
    }

    public void setTxCost(String txCost) {
        this.txCost = txCost;
    }

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }

    public String getCanceled() {
        return canceled;
    }

    public void setCanceled(String canceled) {
        this.canceled = canceled;
    }

    public String getInvalidAddress() {
        return invalidAddress;
    }

    public void setInvalidAddress(String invalidAddress) {
        this.invalidAddress = invalidAddress;
    }

}
