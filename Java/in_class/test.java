public class test{
    public static void main(String[] args) {
        int pizzas = 1;
        int cansOfDew = 5;
        boolean happiness = (pizzas >= 2) || (cansOfDew > 4);

        System.out.println("Happy?: " + happiness);

        String formatString = "[%5d%-10d]";
        System.out.printf(formatString, 42, 100);
    }
}

