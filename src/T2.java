import java.util.Random;
import java.util.Scanner;

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
            synchronized (Data.keyboardLock) {
                Scanner sc = new Scanner(System.in);

                System.out.println("T2: enter matrix MF (" + n + "x" + n + " numbers), they will effectively be treated as 2s:");
                MF = Data.inputMatrixKeyboard(n, 2, sc);

                System.out.println("T2: enter matrix MG (" + n + "x" + n + " numbers):");
                MG = Data.inputMatrixKeyboard(n, 2, sc);

                k = 2;
            }
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

