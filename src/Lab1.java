import java.io.*;
import java.util.*;
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

class T1 extends Thread {
    private final Data data;
    private double[] A, B;
    private double[][] MA, MD;
    private double d;

    public T1(Data data) {
        super("T1");
        this.data = data;
    }

    @Override
    public void run() {
        int n = Data.N;
        if (Data.useKeyboardInput()) {
            B = Data.fillVectorConstant(n, 1);
            MA = Data.fillMatrixConstant(n, 1);
            MD = Data.fillMatrixConstant(n, 1);
            d = 1;
        } else {
            Random rnd = new Random(Thread.currentThread().getId());
            B = Data.inputVectorLarge(n, 1, rnd);
            MA = Data.inputMatrixLarge(n, 1, rnd);
            MD = Data.inputMatrixLarge(n, 1, rnd);
            d = 1;
        }
        double[][] MAMD = Data.matrixMultiply(MA, MD);
        double[] tmpVec = Data.vectorTimesMatrix(B, MAMD);
        A = Data.scalarTimesVector(d, tmpVec);
        System.out.println("T1 (F1 1.10) A = B*(MA*MD)*d: A = " + (n <= 10 ? Arrays.toString(A) : "[" + A[0] + ", ... length=" + n + "]"));
    }
}

class T2 extends Thread {
    private final Data data;
    private double[][] MF, MG;
    private double k;

    public T2(Data data) {
        super("T2");
        this.data = data;
    }

    @Override
    public void run() {
        int n = Data.N;
        if (Data.useKeyboardInput()) {
            MF = Data.fillMatrixConstant(n, 2);
            MG = Data.fillMatrixConstant(n, 2);
            k = 2;
        } else {
            Random rnd = new Random(Thread.currentThread().getId());
            MF = Data.inputMatrixLarge(n, 2, rnd);
            MG = Data.inputMatrixLarge(n, 2, rnd);
            k = 2;
        }
        double[][] MFMG = Data.matrixMultiply(MF, MG);
        MF = Data.scalarTimesMatrix(k, MFMG);
        System.out.println("T2 (F2 2.3) MF = MF*MG*k: MF[0][0]=" + MF[0][0] + " ... (N=" + n + "x" + n + ")");
    }
}

class T3 extends Thread {
    private final Data data;
    private double[] O, S;
    private double[][] MP, MR;

    public T3(Data data) {
        super("T3");
        this.data = data;
    }

    @Override
    public void run() {
        int n = Data.N;
        if (Data.useKeyboardInput()) {
            S = Data.fillVectorConstant(n, 3);
            MP = Data.fillMatrixConstant(n, 3);
            MR = Data.fillMatrixConstant(n, 3);
        } else {
            Random rnd = new Random(Thread.currentThread().getId());
            S = Data.inputVectorLarge(n, 3, rnd);
            MP = Data.inputMatrixLarge(n, 3, rnd);
            MR = Data.inputMatrixLarge(n, 3, rnd);
        }
        double[][] MPMR = Data.matrixMultiply(MP, MR);
        double[][] sorted = Data.sortMatrixRows(MPMR);
        O = Data.matrixTimesVector(sorted, S);
        System.out.println("T3 (F3 3.5) O = (SORT(MP*MR))*S: O = " + (n <= 10 ? Arrays.toString(O) : "[" + O[0] + ", ... length=" + n + "]"));
    }
}

public class Lab1 {
    public static void main(String[] args) {
        if (args.length >= 1) {
            try {
                Data.N = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                Data.N = 4;
            }
        }
        if (args.length >= 2) {
            Data.inputModeLarge = args[1];
        }
        System.out.println("Lab1: N=" + Data.N + ", inputMode(Large)=" + Data.inputModeLarge);

        Data data = new Data();
        Thread t1 = new T1(data);
        Thread t2 = new T2(data);
        Thread t3 = new T3(data);

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Interrupted");
        }
        System.out.println("Lab1 finished.");
    }
}
