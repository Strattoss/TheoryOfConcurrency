package tt.documentation;

import tt.operations.Operation;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static tt.documentation.Utils.sortTopologically;

public class AnalyzeMatrix {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.print("Enter matrix size: ");
        int matrixRows = reader.nextInt();
        System.out.println();

        analyzeMatrix(matrixRows);
    }

    public static void analyzeMatrix(int matrixRows) {
        System.out.println("Generated alphabet:");
        System.out.println(new Alphabet(matrixRows));
        System.out.println();

        System.out.println("Gauss elimination algorithm as alphabet word:");
        System.out.println(TraceTheoryWordGenerator.getGaussEliminationAsTraceTheoryWord(matrixRows));
        System.out.println();

        DependencyRelation dependencyRelationGen = new DependencyRelation(matrixRows);
        System.out.println("Minimal dependency relation components (Diekert graph edges):");
        System.out.println("J_1 = " + dependencyRelationGen.getJ1());
        System.out.println("J_2 = " + dependencyRelationGen.getJ2());
        System.out.println("J_3 = " + dependencyRelationGen.getJ3());
        System.out.println("J_4 = " + dependencyRelationGen.getJ4());
        System.out.println("J_5 = " + dependencyRelationGen.getJ5());
        System.out.println("J = E = " + dependencyRelationGen.getJ());
        System.out.println();

        System.out.println("Dependency relation in latex format:");
        System.out.println(dependencyRelationGen.getDependencyRelationLatex());
        System.out.println();

        System.out.println("Diekert Graph with color-coded Foata classes:");
        System.out.println(new DiekertGraph(matrixRows).generateColoredGraph());

        System.out.println("Foata classes:");
        var edges = new HashSet<>(new DependencyRelation(matrixRows).getJ());
        Map<Integer, List<Operation>> foataClasses = sortTopologically(matrixRows, edges);
        Utils.printLatexifiedFoataClasses(foataClasses);
    }
}
