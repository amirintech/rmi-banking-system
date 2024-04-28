package backend;

import exceptions.InvalidAmountException;
import models.Account;
import models.Transaction;
import services.DatabaseService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocalMaster extends UnicastRemoteObject implements rmi.LocalMaster {
    //    private final Map<String, WorkerServer> workers;
    private final int port;

    public LocalMaster(List<String> branches, DatabaseService.DBLocation dbLocation, DatabaseService.DBLocation dbReplicaLocation) throws RemoteException {
//        this.workers = new HashMap<>();
        this.port = dbLocation == DatabaseService.DBLocation.EAST ? 6000 : 6500;

        Registry registry = LocateRegistry.createRegistry(this.port);
        DatabaseService databaseService = new DatabaseService(dbLocation, dbReplicaLocation);
        for (String branch : branches) {
            WorkerServer workerServer = new WorkerServer(branch, databaseService);
            registry.rebind(branch, workerServer);
        }
    }

    @Override
    public Account login(String branch, String email, String password) throws RemoteException {
        rmi.WorkerServer workerServer = getWorkerByBranch(branch);
        return workerServer.login(email, password);
    }

    @Override
    public Account createAccount(String name, String email, String password, String branch) throws RemoteException {
        rmi.WorkerServer worker = getWorkerByBranch(branch);
        return worker.createAccount(name, email, password);
    }

    @Override
    public Transaction deposit(String id, double amount) throws RemoteException {
        rmi.WorkerServer workerServer = getWorkerFromClientId(id);
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
        rmi.WorkerServer workerServer = getWorkerFromClientId(id);
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
        rmi.WorkerServer workerServer = getWorkerFromClientId(id);
        return workerServer.inquiry(id);
    }

    private rmi.WorkerServer getWorkerFromClientId(String id) throws RemoteException {
        // Every client id is prefixed by the name of its worker
        // seperated by a colon
        String branch = id.split(":")[0];
        return getWorkerByBranch(branch);
    }

    private rmi.WorkerServer getWorkerByBranch(String branch) throws RemoteException {
        Registry registry = LocateRegistry.getRegistry("localhost", this.port);
        try {
            return (rmi.WorkerServer) registry.lookup(branch);
        } catch (NotBoundException e) {
            throw new RuntimeException(e);
        }
    }
}
