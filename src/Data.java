import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

class Data {
    public static int N = 4;

    public static String inputModeLarge = "CONSTANT";

    public static double[][] matrixMultiply(double[][] A, double[][] B) {
        int n = A.length;
        double[][] C = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double sum = 0;
                for (int k = 0; k < n; k++) {
                    sum += A[i][k] * B[k][j];
                }
                C[i][j] = sum;
            }
        }
        return C;
    }

    public static double[] vectorTimesMatrix(double[] B, double[][] MA) {
        int n = B.length;
        double[] result = new double[n];
        for (int j = 0; j < n; j++) {
            double sum = 0;
            for (int k = 0; k < n; k++) {
                sum += B[k] * MA[k][j];
            }
            result[j] = sum;
        }
        return result;
    }

    public static double[] matrixTimesVector(double[][] MA, double[] S) {
        int n = MA.length;
        double[] result = new double[n];
        for (int i = 0; i < n; i++) {
            double sum = 0;
            for (int j = 0; j < n; j++) {
                sum += MA[i][j] * S[j];
            }
            result[i] = sum;
        }
        return result;
    }

    public static double[] scalarTimesVector(double d, double[] v) {
        double[] r = new double[v.length];
        for (int i = 0; i < v.length; i++) r[i] = d * v[i];
        return r;
    }

    public static double[][] scalarTimesMatrix(double k, double[][] M) {
        int n = M.length;
        double[][] r = new double[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                r[i][j] = k * M[i][j];
        return r;
    }

    public static double[][] sortMatrixRows(double[][] M) {
        int n = M.length;
        double[][] sorted = new double[n][n];
        for (int i = 0; i < n; i++) {
            double[] row = M[i].clone();
            Arrays.sort(row);
            sorted[i] = row;
        }
        return sorted;
    }

    public static double[] fillVectorConstant(int n, double value) {
        double[] v = new double[n];
        Arrays.fill(v, value);
        return v;
    }

    public static double[][] fillMatrixConstant(int n, double value) {
        double[][] M = new double[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(M[i], value);
        return M;
    }

    public static double[] fillVectorRandom(int n, Random rnd) {
        double[] v = new double[n];
        for (int i = 0; i < n; i++) v[i] = rnd.nextDouble();
        return v;
    }

    public static double[][] fillMatrixRandom(int n, Random rnd) {
        double[][] M = new double[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                M[i][j] = rnd.nextDouble();
        return M;
    }

    public static double[] inputVectorKeyboard(int n, double fillValue, Scanner sc) {
        double[] v = new double[n];
        if (n <= 4) {
            for (int i = 0; i < n; i++) {
                if (sc.hasNextDouble()) v[i] = sc.nextDouble();
                else v[i] = fillValue;
            }
        } else {
            Arrays.fill(v, fillValue);
        }
        return v;
    }

    public static double[][] inputMatrixKeyboard(int n, double fillValue, Scanner sc) {
        double[][] M = new double[n][n];
        if (n <= 4) {
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++) {
                    if (sc.hasNextDouble()) M[i][j] = sc.nextDouble();
                    else M[i][j] = fillValue;
                }
        } else {
            for (int i = 0; i < n; i++) Arrays.fill(M[i], fillValue);
        }
        return M;
    }

    public static double[] inputVectorLarge(int n, int threadId, Random rnd) {
        double fillValue = threadId;
        if ("RANDOM".equalsIgnoreCase(inputModeLarge)) {
            return fillVectorRandom(n, rnd);
        }
        if ("FILE".equalsIgnoreCase(inputModeLarge)) {
            try {
                return readVectorFromFile("data_vector_T" + threadId + ".txt", n);
            } catch (IOException e) {
                return fillVectorConstant(n, fillValue);
            }
        }
        return fillVectorConstant(n, fillValue);
    }

    public static double[][] inputMatrixLarge(int n, int threadId, Random rnd) {
        double fillValue = threadId;
        if ("RANDOM".equalsIgnoreCase(inputModeLarge)) {
            return fillMatrixRandom(n, rnd);
        }
        if ("FILE".equalsIgnoreCase(inputModeLarge)) {
            try {
                return readMatrixFromFile("data_matrix_T" + threadId + ".txt", n);
            } catch (IOException e) {
                return fillMatrixConstant(n, fillValue);
            }
        }
        return fillMatrixConstant(n, fillValue);
    }

    public static double[] readVectorFromFile(String path, int n) throws IOException {
        double[] v = new double[n];
        try (Scanner sc = new Scanner(new File(path))) {
            for (int i = 0; i < n && sc.hasNextDouble(); i++) v[i] = sc.nextDouble();
        }
        return v;
    }

    public static double[][] readMatrixFromFile(String path, int n) throws IOException {
        double[][] M = new double[n][n];
        try (Scanner sc = new Scanner(new File(path))) {
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n && sc.hasNextDouble(); j++)
                    M[i][j] = sc.nextDouble();
        }
        return M;
    }

    public static void writeVectorToFile(String path, double[] v) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path))) {
            for (double x : v) pw.print(x + " ");
        }
    }

    public static void writeMatrixToFile(String path, double[][] M) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path))) {
            for (double[] row : M) {
                for (double x : row) pw.print(x + " ");
                pw.println();
            }
        }
    }

    public static boolean useKeyboardInput() {
        return N <= 4;
    }
}

