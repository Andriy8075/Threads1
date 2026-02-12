import java.util.Arrays;
import java.util.Random;

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

