public class Main {
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
        System.out.println("Main: N=" + Data.N + ", inputMode(Large)=" + Data.inputModeLarge);

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
        System.out.println("Main finished.");
    }
}

