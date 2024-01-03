package tt.documentation;

import tt.operations.A;
import tt.operations.B;
import tt.operations.C;
import tt.operations.Pair;

import java.util.*;

public class DependencyRelation {
    private final int matrixRows;
    private Set<Pair> dependencyRelation;

    public DependencyRelation(int matrixRows) {
        this.matrixRows = matrixRows;
    }

    public Set<Pair> getDependencyRelation() {
        if (dependencyRelation == null) {
            HashSet<Pair> j = new HashSet<>(getJ());
            dependencyRelation = makeSymmetrical(transitiveComplement(j));
        }
        return dependencyRelation;
    }

    public String getDependencyRelationLatex() {
        Set<Pair> depRel = getDependencyRelation();
        StringBuilder depRelLatex = new StringBuilder();

        depRelLatex.append("D = \\{");
        depRel.forEach(pair ->
                depRelLatex.append(pair).append(", ")
        );
        depRelLatex.append("\\}");

        return depRelLatex.toString();
    }

    private static Set<Pair> transitiveComplement(Set<Pair> operations) {
        Set<Pair> oldPairs = new HashSet<>();
        Set<Pair> newPairs = operations;

        while (oldPairs.size() != newPairs.size()) {
            oldPairs = newPairs;
            newPairs = new HashSet<>(oldPairs);

            for (Pair pair1 : oldPairs) {
                for (Pair pair2 : oldPairs) {
                    if (pair1.equals(pair2)) continue;
                    Optional<Pair> transitionalPair = pair1.getTransitionPair(pair2);
                    transitionalPair.ifPresent(newPairs::add);
                }
            }
        }

        return newPairs;
    }

    private static Set<Pair> makeSymmetrical(Set<Pair> operations) {
        Set<Pair> result = new HashSet<>(operations);
        for (Pair pair : operations) {
            result.add(new Pair(pair.op2(), pair.op1()));
        }
        return result;
    }

    public List<Pair> getJ() {
        var result = getJ1();
        result.addAll(getJ2());
        result.addAll(getJ3());
        result.addAll(getJ4());
        result.addAll(getJ5());
        return result;
    }

    public List<Pair> getJ1() {
        List<Pair> operations = new ArrayList<>();

        for (int i = 1; i < matrixRows; i++) {
            for (int k = i+1; k < matrixRows + 1; k++) {
                A a = new A(i, k);
                for (int j = i; j < matrixRows + 2; j++) {
                    B b = new B(i, j, k);
                    operations.add(new Pair(a, b));
                }
            }
        }

        return operations;
    }

    public List<Pair> getJ2() {
        List<Pair> operations = new ArrayList<>();

        for (int i = 1; i < matrixRows; i++) {
            for (int k = i+1; k < matrixRows + 1; k++) {
                for (int j = i; j < matrixRows + 2; j++) {
                    B b = new B(i, j, k);
                    C c = new C(i, j, k);
                    operations.add(new Pair(b, c));
                }
            }
        }

        return operations;
    }

    public List<Pair> getJ3() {
        List<Pair> operations = new ArrayList<>();

        for (int i = 1; i < matrixRows; i++) {
            if (i == 1) continue;
            for (int k = i+1; k < matrixRows + 1; k++) {
                A a = new A(i, k);
                C c1 = new C(i-1, i, i);
                C c2 = new C(i-1, i, k);
                operations.add(new Pair(c1, a));
                operations.add(new Pair(c2, a));
            }
        }

        return operations;
    }

    public List<Pair> getJ4() {
        List<Pair> operations = new ArrayList<>();

        for (int i = 1; i < matrixRows; i++) {
            if (i == 1) continue;
            for (int j = i; j < matrixRows + 2; j++) {
                if (i == j) continue;
                for (int k = i+1; k < matrixRows + 1; k++) {
                    C c1 = new C(i-1, j, k);
                    C c2 = new C(i, j, k);
                    operations.add(new Pair(c1, c2));
                }
            }
        }

        return operations;
    }

    public List<Pair> getJ5() {
        List<Pair> operations = new ArrayList<>();

        for (int i = 1; i < matrixRows; i++) {
            if (i == 1) continue;
            for (int k = i+1; k < matrixRows + 1; k++) {
                if (k == 1) continue;
                for (int j = i; j < matrixRows + 2; j++) {
                    if (i == j) continue;
                    B b = new B(i, j, k);
                    C c = new C(i-1, j, k-1);
                    operations.add(new Pair(c, b));
                }
            }
        }

        return operations;
    }
}
