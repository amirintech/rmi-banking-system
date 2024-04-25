package services;

import java.util.Arrays;
import java.util.List;

public class BranchesService {
    public static boolean isWestBranch(String branch) {
        List<String> westBranches = Arrays.stream(BranchesService.WestBranches.values())
                .map(WestBranches::getName)
                .toList();

        for (String b : westBranches)
            if (b.equals(branch))
                return true;

        return false;
    }

    public enum WestBranches {
        CALIFORNIA("California"),
        VANCOUVER("Vancouver"),
        AUCKLAND("Auckland");

        private final String name;

        WestBranches(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }

    public enum EastBranches {
        CAIRO("Cairo"),
        DUBAI("Dubai"),
        SYDNEY("Sydney");

        private final String name;

        EastBranches(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }
}
