import java.util.Arrays;
import java.util.Random;

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

