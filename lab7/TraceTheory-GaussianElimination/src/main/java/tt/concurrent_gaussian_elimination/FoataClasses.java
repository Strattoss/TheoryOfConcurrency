package tt.concurrent_gaussian_elimination;

import tt.operations.A;
import tt.operations.B;
import tt.operations.C;
import tt.operations.Operation;

import java.util.*;

public class FoataClasses {
    public static List<Set<Operation>> generateFoataClasses(int matrixRows) {
        List<Set<Operation>> foataClasses = new LinkedList<>();

        for (int r = 1; r < matrixRows; r++) {
            foataClasses.add(foataA(matrixRows, r));
            foataClasses.add(foataB(matrixRows, r));
            foataClasses.add(foataC(matrixRows, r));
        }

        return foataClasses;
//
//        Map<Integer, Set<Operation>> foataClasses = new HashMap<>();
//
//        for (int r = 1; r < matrixRows; r++) {
//            foataClasses.put(3*(r-1) + 1, foataA(matrixRows, r));
//            foataClasses.put(3*(r-1) + 2, foataB(matrixRows, r));
//            foataClasses.put(3*(r-1) + 3, foataC(matrixRows, r));
//        }
//
//        return foataClasses;
    }

    private static Set<Operation> foataA(int matrixRows, int r) {
        Set<Operation> operations = new HashSet<>();

        for (int k = r+1; k < matrixRows+1; k++) {
            operations.add(new A(r, k));
        }

        return operations;
    }

    private static Set<Operation> foataB(int matrixRows, int r) {
        Set<Operation> operations = new HashSet<>();

        for (int s = r; s < matrixRows+2; s++) {
            for (int t = r+1; t < matrixRows+1; t++) {
                operations.add(new B(r, s, t));
            }
        }

        return operations;
    }

    private static Set<Operation> foataC(int matrixRows, int r) {
        Set<Operation> operations = new HashSet<>();

        for (int s = r; s < matrixRows+2; s++) {
            for (int t = r+1; t < matrixRows+1; t++) {
                operations.add(new C(r, s, t));
            }
        }

        return operations;
    }
}
