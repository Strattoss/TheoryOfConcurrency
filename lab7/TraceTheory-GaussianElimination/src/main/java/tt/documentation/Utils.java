package tt.documentation;

import tt.operations.A;
import tt.operations.Operation;
import tt.operations.Pair;

import java.util.*;

public class Utils {
    public static void printLatexifiedFoataClasses(Map<Integer, List<Operation>> foataClasses) {
        for (var entrySet : foataClasses.entrySet()) {
            int foataClassIndex = entrySet.getKey();
            List<Operation> operations = entrySet.getValue();

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("F_").append(foataClassIndex).append(" = \\{");

            operations.forEach(operation -> {
                stringBuilder.append(operation).append(", ");
            });

            stringBuilder.append("\\}");
            System.out.println(stringBuilder);
        }
    }

    public static Map<Integer, List<Operation>> sortTopologically(int matrixRows, Set<Pair> diekertEdges) {
        Set<Pair> leftEdges = new HashSet<>(diekertEdges); // edges that haven't been removed yet

        Map<Integer, List<Operation>> foataClasses = new HashMap<>();
        int currentFoataClass = 0;

        Set<Operation> candidates = new HashSet<>();    // vertices which will be checked if they should belong to the current Foata layer
        for (int k = 2; k < matrixRows + 1; k++) {
            candidates.add(new A(1, k));
        }

        while (!candidates.isEmpty()) {
            currentFoataClass++;
            foataClasses.put(currentFoataClass, new ArrayList<>());

            Set<Operation> candidatesToBeRemoved = new HashSet<>();    // vertices which will be removed, because no other existing vertex has an edge directed to them

            for (Operation candidate : candidates) {
                boolean shouldBeRemoved = true;
                for (Pair edge : leftEdges) {
                    if (edge.op2().equals(candidate)) {
                        shouldBeRemoved = false;
                        break;
                    }
                }
                if (shouldBeRemoved){
                    candidatesToBeRemoved.add(candidate);
                }
            }

            int finalCurrentFoataClass = currentFoataClass;
            candidatesToBeRemoved.forEach(foataClasses.get(finalCurrentFoataClass)::add);

            List<Pair> edgesToBeRemoved = new ArrayList<>();
            for (Operation candidateToBeRemoved : candidatesToBeRemoved) {
                for (Pair edge : leftEdges) {
                    if (edge.op1().equals(candidateToBeRemoved)) {
                        edgesToBeRemoved.add(edge);
                    }
                }
            }

            candidatesToBeRemoved.forEach(candidates::remove);

            edgesToBeRemoved.forEach(edge -> {
                        leftEdges.remove(edge);
                        candidates.add(edge.op2());
            }
            );
        }

        return foataClasses;
    }
}
