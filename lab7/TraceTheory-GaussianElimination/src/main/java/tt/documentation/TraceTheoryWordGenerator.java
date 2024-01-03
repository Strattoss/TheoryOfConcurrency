package tt.documentation;

import tt.operations.A;
import tt.operations.B;
import tt.operations.C;

public class TraceTheoryWordGenerator {
    public static String getGaussEliminationAsTraceTheoryWord(int matrixRows) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 1; i < matrixRows; i++) {
            for (int k = i+1; k < matrixRows + 1; k++) {
                A a = new A(i, k);
                stringBuilder.append(a).append(" \\frown ");
                for (int j = i; j < matrixRows + 2; j++) {
                    B b = new B(i, j, k);
                    C c = new C(i, j, k);
                    stringBuilder.append(b).append(" \\frown ").append(c).append(" \\frown ");
                }
            }
        }

        return stringBuilder.toString();
    }
}
