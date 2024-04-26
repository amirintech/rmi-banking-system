package backend;

import exceptions.InvalidAmountException;
import models.Account;
import models.Transaction;
import services.DatabaseService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocalMaster extends UnicastRemoteObject implements rmi.LocalMaster {
    private final Map<String, WorkerServer> workers;

    public LocalMaster(List<String> branches, DatabaseService.DBLocation dbLocation, DatabaseService.DBLocation dbReplicaLocation) throws RemoteException {
        this.workers = new HashMap<>();

        DatabaseService databaseService = new DatabaseService(dbLocation, dbReplicaLocation);
        for (String branch : branches) {
            WorkerServer workerServer = new WorkerServer(branch, databaseService);
            this.workers.put(workerServer.getId(), workerServer);
        }
    }

    @Override
    public Account login(String branch, String email, String password) throws RemoteException {
        WorkerServer workerServer = getWorkerByBranch(branch);
        return workerServer.login(email, password);
    }

    @Override
    public Account createAccount(String name, String email, String password, String branch) throws RemoteException {
        WorkerServer worker = getWorkerByBranch(branch);
        return worker.createAccount(name, email, password);
    }

    @Override
    public Transaction deposit(String id, double amount) throws RemoteException {
        WorkerServer workerServer = getWorkerFromClientId(id);
        try {
            return workerServer.deposit(id, amount);
        } catch (InvalidAmountException e) {
            System.err.println("Invalid amount exception");
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Transaction withdraw(String id, double amount) throws RemoteException {
        WorkerServer workerServer = getWorkerFromClientId(id);
        try {
            return workerServer.withdraw(id, amount);
        } catch (InvalidAmountException e) {
            System.err.println("Invalid amount exception");
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Transaction> inquiry(String id) throws RemoteException {
        WorkerServer workerServer = getWorkerFromClientId(id);
        return workerServer.inquiry(id);
    }

    private WorkerServer getWorkerFromClientId(String id) throws RemoteException {
        // Every client id is prefixed by the name of its worker
        // seperated by a colon
        String workerId = id.split(":")[0];
        return workers.get(workerId);
    }

    private WorkerServer getWorkerByBranch(String branch) throws RemoteException {
        for (WorkerServer worker : workers.values())
            if (worker.getId().equals(branch)) return worker;

        return null;
    }
}
