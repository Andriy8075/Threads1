import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

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
            Scanner sc = new Scanner(System.in);

            System.out.println("T1: enter vector B (" + n + " numbers), they will effectively be treated as 1s:");
            B = Data.inputVectorKeyboard(n, 1, sc);

            System.out.println("T1: enter matrix MA (" + n + "x" + n + " numbers):");
            MA = Data.inputMatrixKeyboard(n, 1, sc);

            System.out.println("T1: enter matrix MD (" + n + "x" + n + " numbers):");
            MD = Data.inputMatrixKeyboard(n, 1, sc);

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

