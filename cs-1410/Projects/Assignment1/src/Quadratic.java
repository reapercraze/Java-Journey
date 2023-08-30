import java.util.Scanner;

public class Quadratic {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter a, b, c: ");

        //Get input
        double a = input.nextDouble();
        double b = input.nextDouble();
        double c = input.nextDouble();

        //Calculate discriminate
        double discriminate = (Math.pow(b,2) - (4 * a * c));

        //Calculates roots
        double root1 = ((-b + Math.sqrt(discriminate))/(2 * a));
        double root2 = ((-b - Math.sqrt(discriminate))/(2 * a));

        //If there is two roots
        if (discriminate > 0) {
            System.out.println("There are two roots for the quadratic equation with these coefficients.");

            //Print roots
            System.out.println("r1 = " + root1 + "\nr2 = " + root2);
        }
        //If there is one root
        else if (discriminate == 0) {
            System.out.println("There is one root for the quadratic equation with these coefficients.");

            //Print roots
            System.out.println("r1 = " + root1);
        }
        //Else there is no roots
        else {
            System.out.println("There are no roots for the quadratic equation with these coefficients.");
        }
        input.close();
    }
}