package tt.documentation;

import tt.operations.A;
import tt.operations.B;
import tt.operations.C;
import tt.operations.Operation;

import java.util.ArrayList;
import java.util.List;

public class Alphabet {
    private final List<Operation> operations;

    public Alphabet(int matrixRows) {
        this.operations = new ArrayList<>();

        for (int i = 1; i < matrixRows; i++) {
            for (int k = i+1; k < matrixRows + 1; k++) {
                operations.add(new A(i, k));
                 for (int j = i; j < matrixRows + 2; j++) {
                    operations.add(new B(i, j, k));
                    operations.add(new C(i, j, k));
                }
            }
        }
    }

    public List<Operation> getOperations() {
        return operations;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Operation operation : operations) {
            stringBuilder.append(operation.toString());
            stringBuilder.append(", ");
        }

        return "\\Sigma = \\{" +
                stringBuilder +
                "\\}";
    }
}
