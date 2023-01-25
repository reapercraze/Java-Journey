import java.util.Scanner;

public class ISBN {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the first 9 digits of an ISBN: ");

        // Get Number
        int isbn = input.nextInt();

        // Get digits
        int d9 = isbn % 10;
        isbn /= 10;
        int d8 = isbn % 10;
        isbn /= 10;
        int d7 = isbn % 10;
        isbn /= 10;
        int d6 = isbn % 10;
        isbn /= 10;
        int d5 = isbn % 10;
        isbn /= 10;
        int d4 = isbn % 10;
        isbn /= 10;
        int d3 = isbn % 10;
        isbn /= 10;
        int d2 = isbn % 10;
        isbn /= 10;
        int d1 = isbn % 10;

        int d10 = ((1*d1) + (2*d2) + (3*d3) + (4*d4) + (5*d5) + (6*d6) + (7*d7) + (8*d8) + (9*d9)) % 11;

        // Check if the checksum is 10
        if (d10 == 10){
            // Output ISBN 10 number with d10 = X
            char X = 'X';
            System.out.println("The ISBN-10 number is: " + d1 + d2 + d3 + d4 + d5 + d6 + d7 + d8 + d9 + X);
        } else {
            // Output ISBN 10 number with d10 = int
            System.out.println("The ISBN-10 number is: " + d1 + d2 + d3 + d4 + d5 + d6 + d7 + d8 + d9 + d10);
        }
        input.close();
    }
}
