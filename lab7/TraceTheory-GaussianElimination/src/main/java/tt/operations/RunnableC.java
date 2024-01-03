package tt.operations;

public class RunnableC implements Runnable {
    private final double[][] M;
    private final double[][][] n;
    private final int i, j, k;

    public RunnableC(double[][] M, double[][][] n, int i, int j, int k) {
        this.M = M;
        this.n = n;
        this.i = i;
        this.j = j;
        this.k = k;
    }

    @Override
    public void run() {
        M[k-1][j-1] -= n[i-1][j-1][k-1];
    }
}
