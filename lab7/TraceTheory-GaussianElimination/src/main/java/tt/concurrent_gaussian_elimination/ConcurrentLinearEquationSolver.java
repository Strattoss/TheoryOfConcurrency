package tt.concurrent_gaussian_elimination;

import tt.operations.Operation;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

import static java.lang.Math.abs;

public class ConcurrentLinearEquationSolver {
    public static double EPSILON = 0.00001;

    /**
     * @param args should be 2 strings with paths to the 2 files with matrices at given format
     * @throws FileNotFoundException if any given file does not exist
     * @throws IOException if there is some problem with IO
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        if (args.length != 2) {
            System.err.print("Wrong amount of arguments. Usage: [input file path] [output file path]");
            System.exit(1);
        }

        File inFile = new File(args[0]);
        Scanner reader = new Scanner(inFile);
        reader.useLocale(Locale.UK);

        File outFile = new File(args[1]);
        FileWriter writer = new FileWriter(outFile);

        int matrixRows; // named "N" in documentation
        double[][] M;

        matrixRows = reader.nextInt();  // read matrix size (N)
        M = new double[matrixRows][matrixRows+1];
        for (int i = 0; i < matrixRows; i++) {
            for (int j = 0; j < matrixRows; j++) {
                M[i][j] = reader.nextDouble();
            }
        }
        for (int i = 0; i < matrixRows; i++) {
            M[i][matrixRows] = reader.nextDouble();
        }

        // create auxiliary arrays for concurrency
        double[][][] n = new double[matrixRows-1][matrixRows+1][matrixRows];
        double[][] m = new double[matrixRows-1][matrixRows];

        performLowerGaussEliminationConcurrently(matrixRows, M, n, m);
//        System.out.println(Arrays.deepToString(M));
        performUpperGaussElimination(matrixRows, M);
//        System.out.println(Arrays.deepToString(M));
        makeMatrixDiagonalContainOnlyOnes(matrixRows, M);
//        System.out.println(Arrays.deepToString(M));

        writeMatrixIntoFile(matrixRows, M, EPSILON, writer);
        writer.close();

        reader.close();
    }

    public static void performLowerGaussEliminationConcurrently(int matrixRows, double[][] M, double[][][] n, double[][] m) {
        List<Set<Operation>> foataClasses = FoataClasses.generateFoataClasses(matrixRows);

        ExecutorService executor = Executors.newFixedThreadPool(matrixRows*matrixRows - 1);

        for (Set<Operation> foataClass : foataClasses) {
            List<Future<?>> futures = new ArrayList<>();
            for (Operation operation : foataClass) {
                futures.add(executor.submit(operation.getRunnable(M, m, n)));
            }

            // wait for all the tasks to finish
            for (Future<?> future : futures) {
                try {
                    future.get();
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        executor.shutdown();
    }

    public static void performUpperGaussElimination(int matrixRows, double[][] M) {
        for (int i = matrixRows - 1; i >= 0; i--) {
            for (int k = 0; k < i; k++) {
                double factor = M[k][i] / M[i][i];

                M[k][i] -= factor * M[i][i];
                M[k][matrixRows] -= factor * M[i][matrixRows];
            }
        }
    }

    public static void makeMatrixDiagonalContainOnlyOnes(int matrixRows, double[][] M) {
        for (int i = 0; i < matrixRows; i++) {
            M[i][matrixRows] /= M[i][i];
            M[i][i] = 1;
        }
    }

    public static void writeMatrixIntoFile(int matrixRows, double[][] M, double epsilon, FileWriter writer) throws IOException {
        writer.append(Integer.toString(matrixRows)).append("\n");

        for (int i = 0; i < matrixRows; i++) {
            StringBuilder rowBuilder = new StringBuilder();
            for (int j = 0; j < matrixRows; j++) {
                var v = abs(M[i][j]) > EPSILON ? M[i][j] : 0;
                rowBuilder.append(v).append(" ");
            }
            rowBuilder.setLength(rowBuilder.length() - 1);    // remove the last space
            rowBuilder.append("\n");

            writer.append(rowBuilder);
        }

        // print the vector of constant terms
        StringBuilder constantsBuilder = new StringBuilder();
        for (int i = 0; i < matrixRows; i++) {
            var v = abs(M[i][matrixRows]) > EPSILON ? M[i][matrixRows] : 0;
            constantsBuilder.append(v).append(" ");
        }
        constantsBuilder.setLength(constantsBuilder.length() - 1); // remove the last space
        constantsBuilder.append("\n");
        writer.append(constantsBuilder);
    }
}
