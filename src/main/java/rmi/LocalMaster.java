package rmi;

import models.Account;
import models.Transaction;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface LocalMaster extends Remote {
    Account login(String id, String email, String password) throws RemoteException;

    Account createAccount(String name, String email, String password, String branch) throws RemoteException;

    Transaction deposit(String id, double amount) throws RemoteException;

    Transaction withdraw(String id, double amount) throws RemoteException;

    List<Transaction> inquiry(String id) throws RemoteException;
}
