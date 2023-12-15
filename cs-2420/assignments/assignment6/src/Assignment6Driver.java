import java.io.File;
import java.util.Scanner;

public class Assignment6Driver {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED = "\u001B[31m";
    public static void main(String[] args) {

        testGame();
        playGame("moves1.txt");
        System.out.println();
        playGame("moves2.txt");
    }

    private static void playGame(String filename) {
        HexGame game = new HexGame(11);
        File file = new File(filename);
        try (Scanner input = new Scanner(file)) {

            boolean isBlueTurn = true;
            while (input.hasNextInt()) {

                int position = input.nextInt();
                if (isBlueTurn) {
                    if (game.playBlue(position, false)) {
                        System.out.println("Blue wins with move at position " + position + "!!");
                        break;
                    }
                } else {
                    if (game.playRed(position, false)) {
                        System.out.println("Red wins with move at position " + position + "!!");
                        break;
                    }
                }
                isBlueTurn = !isBlueTurn; // Toggle turn
            }
            printGrid(game);
        } catch (java.io.IOException ex) {
            System.out.println("An error occurred trying to read the moves file: " + ex);
        }
    }

    private static void testGame() {
        HexGame game = new HexGame(11);

        System.out.println("--- red ---");
        game.playRed(1, true);
        game.playRed(11, true);
        game.playRed(122 - 12, true);
        game.playRed(122 - 11, true);
        game.playRed(122 - 10, true);
        game.playRed(121, true);
        game.playRed(61, true);

        System.out.println("--- blue ---");
        game.playBlue(1, true);
        game.playBlue(2, true);
        game.playBlue(11, true);
        game.playBlue(12, true);
        game.playBlue(121, true);
        game.playBlue(122 - 11, true);
        game.playBlue(62, true);

        System.out.println();
        printGrid(game);
    }

    private static void printGrid(HexGame game) {
        HexGame.Color[] grid = game.getGrid();
        int size = game.getSize();

        for (int row = 0; row < size; row++) {
            // Print leading spaces for alignment
            for (int i = 0; i < row; i++) {
                System.out.print(" ");
            }

            // Print each cell in the row
            for (int col = 0; col < size; col++) {

                int index = row * size + col;
                if (grid[index] == HexGame.Color.NONE) {
                    System.out.print("0 ");
                } else if (grid[index] == HexGame.Color.RED) {
                    System.out.print(ANSI_RED + "R " + ANSI_RESET);
                } else if (grid[index] == HexGame.Color.BLUE) {
                    System.out.print(ANSI_BLUE + "B " + ANSI_RESET);
                }
            }
            System.out.println(); // New line after each row
        }
        System.out.println();
    }

}
