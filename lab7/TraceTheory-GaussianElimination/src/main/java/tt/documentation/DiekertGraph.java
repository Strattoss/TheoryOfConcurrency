package tt.documentation;

import tt.operations.Operation;
import tt.operations.Pair;

import java.awt.*;
import java.util.*;
import java.util.List;

public class DiekertGraph {
    private final int matrixRows;
    private final Set<Pair> diekertEdges;

    public DiekertGraph(int matrixRows) {
        this.matrixRows = matrixRows;
        this.diekertEdges = new HashSet<>(new DependencyRelation(matrixRows).getJ());
    }

    /**
     * Generate Diekert graph in dot format
     * @return dot represantation of the Diekert graph
     */
    public String generateGraph() {
        StringBuilder diekertGraph = new StringBuilder();

        diekertGraph.append("digraph DiekertGraph {\n");
        for (Pair pair : diekertEdges) {
            diekertGraph
                    .append("\t")
                    .append(pair.op1().toDotFormat())
                    .append(" -> ")
                    .append(pair.op2().toDotFormat())
                    .append("\n");
        }
        diekertGraph.append("}");

        return diekertGraph.toString();
    }

    /**
     * Generate Diekert graph in dot format with nodes colored according to the Foata classes
     * @return dot represantation of the Diekert graph
     */
    public String generateColoredGraph() {
        Map<Integer, List<Operation>> foataClasses = Utils.sortTopologically(matrixRows, this.diekertEdges);

        StringBuilder diekertGraph = new StringBuilder();

        diekertGraph.append("digraph DiekertGraphWithFoataClasses {\n");
        foataClasses.forEach((foataClass, operations) -> {
            float h = (float) (foataClass - 1) / foataClasses.size();
            Color color = Color.getHSBColor(h, 0.7f, 1);
            var s = String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());;
            diekertGraph.append("\tnode [style = filled, fillcolor = \"")
                    .append(s)
                    .append("\"]\n");

            if (!operations.isEmpty()) {
                diekertGraph.append("\t");
                for (Operation operation : operations) {
                    diekertGraph.append(operation.toDotFormat());
                    diekertGraph.append(", ");
                }

                // remove the trailing ", "
                diekertGraph.setLength(diekertGraph.length() - 2);
                diekertGraph.append("\n");
            }
        });
        for (Pair pair : diekertEdges) {
            diekertGraph
                    .append("\t")
                    .append(pair.op1().toDotFormat())
                    .append(" -> ")
                    .append(pair.op2().toDotFormat())
                    .append("\n");
        }

        diekertGraph.append("}");

        return diekertGraph.toString();
    }
}
