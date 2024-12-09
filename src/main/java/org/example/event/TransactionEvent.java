package org.example.event;

public class TransactionEvent {
    private String txnId;
    private int amount;

    public TransactionEvent() {
    }

    public TransactionEvent(String txnId, int amount) {
        this.txnId = txnId;
        this.amount = amount;
    }

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TransactionEvent{" +
                "txnId='" + txnId + '\'' +
                ", amount=" + amount +
                '}';
    }
}
