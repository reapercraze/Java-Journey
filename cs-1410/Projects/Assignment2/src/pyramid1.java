import java.util.Scanner;

public class pyramid1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the number of lines: ");

        int lines = input.nextInt();
        String str = "" + lines;
        int length = str.length() + 1;
        int totallength = length * (lines-1);

        // Loop to print outside of pyramid
        for (int i = 1; i <= lines; i++) {
            String format = "%" + (totallength + length) + "d";
            System.out.printf(format, i);

            // Loop to print inner pyramid going down
            for (int x = i; x > 1; x--) {
                String innnerformat = "%" + length + "d";
                System.out.printf(innnerformat, x-1);
            }

            // Loop to print inner pyramid going up
            for (int x = 2; x <= i; x++) {
                String innnerformat = "%" + length + "d";
                System.out.printf(innnerformat, x);
            }

            System.out.println();
            totallength -= length;
        }
    }
}