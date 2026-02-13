import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

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
            Scanner sc = new Scanner(System.in);

            System.out.println("T3: enter vector S (" + n + " numbers), they will effectively be treated as 3s:");
            S = Data.inputVectorKeyboard(n, 3, sc);

            System.out.println("T3: enter matrix MP (" + n + "x" + n + " numbers):");
            MP = Data.inputMatrixKeyboard(n, 3, sc);

            System.out.println("T3: enter matrix MR (" + n + "x" + n + " numbers):");
            MR = Data.inputMatrixKeyboard(n, 3, sc);
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

