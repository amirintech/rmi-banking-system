package models;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

public class Transaction implements Serializable {
    private final String id;
    private final long timestamp;
    private final TransactionAction action;
    private final String accountId;
    private final double currentBalance;
    private final double amount;

    private Transaction() {
        this(TransactionAction.INQUIRY, "", 0, 0);
    }

    public Transaction(TransactionAction action, String accountId, double balance) {
        this(action, accountId, balance, 0);
    }

    public Transaction(TransactionAction action, String accountId, double balance, double amount) {
        this.id = UUID.randomUUID().toString();
        this.timestamp = Instant.now().toEpochMilli();
        this.action = action;
        this.accountId = accountId;
        this.currentBalance = balance;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public TransactionAction getAction() {
        return action;
    }

    public String getAccountId() {
        return accountId;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public double getAmount() {
        return amount;
    }

    public enum TransactionAction {
        DEPOSIT,
        WITHDRAW,
        INQUIRY
    }
}
