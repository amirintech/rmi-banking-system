package models;

import exceptions.InvalidAmountException;

import java.io.Serializable;
import java.util.UUID;

public class Account implements Serializable {
    private String id;
    private String name;
    private String email;
    private final String password;
    private double balance;

    public Account() {
        this("", "", "", "");
    }

    public Account(String name, String email, String password, String workerId) {
        id = workerId + ":" + UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Account getSecure(Account account) {
        Account secure = new Account();
        secure.id = id;
        secure.name = name;
        secure.email = email;

        return secure;
    }

    public void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0)
            throw new InvalidAmountException("Deposit amount must be greater than zero");

        balance += amount;
    }

    public void withdraw(double amount) throws InvalidAmountException {
        if (amount <= 0)
            throw new InvalidAmountException("Deposit amount must be greater than zero");

        if (amount > balance)
            throw new InvalidAmountException("Insufficient balance");

        balance -= amount;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                '}';
    }
}
