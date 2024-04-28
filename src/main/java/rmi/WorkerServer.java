package rmi;

import exceptions.InvalidAmountException;
import models.Account;
import models.Transaction;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface WorkerServer extends Remote {
    Account login(String email, String password) throws RemoteException;

    Account createAccount(String name, String email, String password) throws RemoteException;

    Transaction deposit(String id, double amount) throws RemoteException, InvalidAmountException;

    Transaction withdraw(String id, double amount) throws RemoteException, InvalidAmountException;

    List<Transaction> inquiry(String id) throws RemoteException;
}
