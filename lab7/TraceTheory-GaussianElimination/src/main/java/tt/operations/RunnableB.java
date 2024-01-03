package tt.operations;

public class RunnableB implements Runnable {
    private final double[][] M;
    private final double[][] m;
    private final double[][][] n;
    private final int i, j, k;

    public RunnableB(double[][] M, double[][] m, double[][][] n, int i, int j, int k) {
        this.M = M;
        this.m = m;
        this.n = n;
        this.i = i;
        this.j = j;
        this.k = k;
    }

    @Override
    public void run() {
        n[i-1][j-1][k-1] = M[i-1][j-1] * m[i-1][k-1];
    }
}
