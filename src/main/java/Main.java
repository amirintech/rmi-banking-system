import backend.LocalMaster;
import services.BranchesService;
import services.DatabaseService;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        List<String> westBranches = Arrays.stream(BranchesService.WestBranches.values())
                .map(BranchesService.WestBranches::getName)
                .toList();
        LocalMaster westLocalMaster = new LocalMaster(westBranches, DatabaseService.DBLocation.WEST);

        List<String> eastBranches = Arrays.stream(BranchesService.EastBranches.values())
                .map(BranchesService.EastBranches::getName)
                .toList();
        LocalMaster eastLocalMaster = new LocalMaster(eastBranches, DatabaseService.DBLocation.EAST);

        Registry registry = LocateRegistry.createRegistry(1099);
        registry.bind("WestLocalMaster", westLocalMaster);
        registry.bind("EastLocalMaster", eastLocalMaster);
    }
}









