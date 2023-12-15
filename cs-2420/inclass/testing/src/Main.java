public class Main {
    public static void main(String[] args) {
        printPattern(16);
        System.out.println();
        printPattern(10);
    }

    public static void printPattern(int m) {

        if (m > 0) {
            System.out.print(" " + m);
            printPattern(m - 5);
        }
        System.out.print(" " + m);
    }

    public static void doIt(int n) {
        if (n <= 1) return;
        System.out.print("*");
        doIt(n / 2);
        doIt(n / 2);
    }
}
