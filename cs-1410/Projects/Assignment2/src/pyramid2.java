import java.util.Scanner;

public class pyramid2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the number of lines: ");

        int lines = input.nextInt();
        int digits = (int)Math.pow(2, lines-1);
        String str = "" + digits;
        int length = str.length() + 1;
        int totallength = length * (lines-1);

        // Loop to print outside of pyramid
        for (int i = 0; i < lines; i++) {
            String format = "%" + (totallength + length) + "s";
            System.out.printf(format, "");

            // Loop to print inner pyramid going up
            for (int x = 0; x <= i; x++) {
                String innnerformat = "%" + length + "d";
                System.out.printf(innnerformat, (int)Math.pow(2, x));
            }

            // Loop to print inner pyramid going down
            for (int x = i; x >= 1; x--) {
                String innnerformat = "%" + length + "d";
                System.out.printf(innnerformat, (int)Math.pow(2, x-1));
            }


            System.out.println();
            totallength -= length;
        }
    }
}
