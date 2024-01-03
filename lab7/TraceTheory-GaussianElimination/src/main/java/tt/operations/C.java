package tt.operations;

import java.util.Formatter;
import java.util.Objects;

public record C(int i, int j, int k) implements Operation{
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        C c = (C) o;
        return i == c.i && j == c.j && k == c.k;
    }

    @Override
    public String toDotFormat() {
        return  new Formatter().format("C_%d_%d_%d", i, j, k).toString();
    }

    @Override
    public String toLatexFormat() {
        return  new Formatter().format("C_{%d, %d, %d}", i, j, k).toString();
    }

    @Override
    public Runnable getRunnable(double[][] M, double[][] m, double[][][] n) {
        return new RunnableC(M, n, i, j, k);
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, j, k);
    }

    @Override
    public String toString() {
        return toLatexFormat();
    }
}
