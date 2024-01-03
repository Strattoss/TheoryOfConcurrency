package tt.operations;

import java.util.Formatter;
import java.util.Objects;

public record A(int i, int k) implements  Operation {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        A a = (A) o;
        return i == a.i && k == a.k;
    }

    @Override
    public String toDotFormat() {
        return new Formatter().format("A_%d_%d", i, k).toString();
    }

    @Override
    public String toLatexFormat() {
        return new Formatter().format("A_{%d, %d}", i, k).toString();
    }

    @Override
    public Runnable getRunnable(double[][] M, double[][] m, double[][][] n) {
        return new RunnableA(M, m, i, k);
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, k);
    }

    @Override
    public String toString() {
        return toLatexFormat();
    }
}

