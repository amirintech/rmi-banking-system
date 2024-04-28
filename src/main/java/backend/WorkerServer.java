package backend;

import exceptions.InvalidAmountException;
import models.Account;
import models.Transaction;
import services.DatabaseService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

public class WorkerServer extends UnicastRemoteObject implements rmi.WorkerServer {
    private final String id;
    private final Map<String, Account> clients;
    private final DatabaseService databaseService;

    public WorkerServer(String name, DatabaseService databaseService) throws RemoteException {
        this.id = name;
        this.databaseService = databaseService;
        this.clients = databaseService.getWorkerAccounts(name);
    }

    @Override
    public Account login(String email, String password) throws RemoteException {
        Account account = null;
        for (var acc : clients.values())
            if (acc.getEmail().equals(email) && acc.getPassword().equals(password))
                account = acc;

        return account;
    }

    @Override
    public Account createAccount(String name, String email, String password) throws RemoteException {
        Account account = new Account(name, email, password, id);
        clients.put(account.getId(), account);
        databaseService.addAccount(id, account);

        return account.getSecure(account);
    }

    @Override
    public Transaction deposit(String id, double amount) throws RemoteException, InvalidAmountException {
        Account account = clients.get(id);
        account.deposit(amount);
        Transaction transaction = new Transaction(
                Transaction.TransactionAction.DEPOSIT,
                id,
                account.getBalance(),
                amount);
        databaseService.addTransaction(id, transaction);

        return transaction;
    }

    @Override
    public Transaction withdraw(String id, double amount) throws RemoteException, InvalidAmountException {
        Account account = clients.get(id);
        account.withdraw(amount);
        Transaction transaction = new Transaction(
                Transaction.TransactionAction.WITHDRAW,
                id,
                account.getBalance(),
                amount);
        databaseService.addTransaction(id, transaction);

        return transaction;
    }

    @Override
    public List<Transaction> inquiry(String clientId) throws RemoteException {
        return databaseService
                .getAccountTransactions(clientId)
                .values()
                .stream()
                .toList();
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "WorkerServer{" +
                "id='" + id + '\'' +
                ", name='" + id + '\'' +
                ", clients=" + clients +
                ", databaseService=" + databaseService +
                '}';
    }
}
