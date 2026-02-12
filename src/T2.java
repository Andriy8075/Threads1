import java.util.Random;

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

