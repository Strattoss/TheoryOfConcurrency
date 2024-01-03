package tt.operations;

public interface Operation {
    public String toDotFormat();
    public String toLatexFormat();

    public Runnable getRunnable(double[][] M, double[][] m, double[][][] n);
}
