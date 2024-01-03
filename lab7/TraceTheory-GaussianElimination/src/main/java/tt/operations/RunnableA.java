package tt.operations;

public class RunnableA implements Runnable {
    private final double[][] M;
    private final double[][] m;
    private final int i, k;

    public RunnableA(double[][] M, double[][] m, int i, int k) {
        this.M = M;
        this.m = m;
        this.i = i;
        this.k = k;
    }

    @Override
    public void run() {
        m[i-1][k-1] = M[k-1][i-1] / M[i-1][i-1];
    }
}
