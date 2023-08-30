public class Recursion {
    public static void main(String[] args) {

        int[] sumMe = { 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89 };
        System.out.printf("Array Sum: %d\n", arraySum(sumMe, 0));

        int[] minMe = { 0, 1, 1, 2, 3, 5, 8, -42, 13, 21, 34, 55, 89 };
        System.out.printf("Array Min: %d\n", arrayMin(minMe, 0));

        String[] amISymmetric =  {
                "You can cage a swallow can't you but you can't swallow a cage can you",
                "I still say cS 1410 is my favorite class"
        };
        for (String test : amISymmetric) {
            String[] words = test.toLowerCase().split(" ");
            System.out.println();
            System.out.println(test);
            System.out.printf("Is word symmetric: %b\n", isWordSymmetric(words, 0, words.length - 1));
        }

        double[][] masses = {
                { 51.18 },
                { 55.90, 131.25 },
                { 69.05, 133.66, 132.82 },
                { 53.43, 139.61, 134.06, 121.63 }
        };
        System.out.println();
        System.out.println("--- Weight Pyramid ---");
        for (int row = 0; row < masses.length; row++) {
            for (int column = 0; column < masses[row].length; column++) {
                double weight = computePyramidWeights(masses, row, column);
                System.out.printf("%.2f ", weight);
            }
            System.out.println();
        }


        char[][] image = {
                {'*','*',' ',' ',' ',' ',' ',' ','*',' '},
                {' ','*',' ',' ',' ',' ',' ',' ','*',' '},
                {' ',' ',' ',' ',' ',' ','*','*',' ',' '},
                {' ','*',' ',' ','*','*','*',' ',' ',' '},
                {' ','*','*',' ','*',' ','*',' ','*',' '},
                {' ','*','*',' ','*','*','*','*','*','*'},
                {' ',' ',' ',' ',' ',' ',' ',' ','*',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ','*',' '},
                {' ',' ',' ','*','*','*',' ',' ','*',' '},
                {' ',' ',' ',' ',' ','*',' ',' ','*',' '}
        };
        int howMany = howManyOrganisms(image);
        System.out.println();
        System.out.println("--- Labeled Organism Image ---");
        for (char[] line : image) {
            for (char item : line) {
                System.out.print(item);
            }
            System.out.println();
        }
        System.out.printf("There are %d organisms in the image.\n", howMany);

    }

    public static boolean isWordSymmetric(String[] words, int start, int end) {
        if ((end - start) <= 0) {
            return true;
        }
        return words[start].equalsIgnoreCase(words[end]) && isWordSymmetric(words, start+1, end-1);
    }

    public static long arraySum(int[] data, int position) {
        if ((data.length - 1) < position) {
            return 0;
        }
        return data[position] + arraySum(data, position+1);
    }

    public static int arrayMin(int[] data, int position) {
        if ((data.length - 1) <= position) {
            return data[position];
        }
        int min = arrayMin(data, position+1);
        if (min > data[position]) {
            min = data[position];
        }
        return min;
    }


    public static double computePyramidWeights(double[][] masses, int row, int column) {
        if (row < 0 || column < 0 || row >= masses.length || column >= masses[row].length) {
            return 0;
        }
        double weight = masses[row][column];
        if (row > 0) {
            if (column > 0) {
                weight += 0.5 * computePyramidWeights(masses, row - 1, column - 1);
            }
            if (column < masses[row - 1].length) {
                weight += 0.5 * computePyramidWeights(masses, row - 1, column);
            }
        }
        return weight;
    }

    public static int howManyOrganisms(char[][] image) {
        char label = 'a';
        int count = 0;
        for (int x = 0; x < image.length; x++) {
            for (int y = 0; y < image[x].length; y++) {
                if (image[x][y] == '*') {
                    labelOrganism(image, x, y, label);
                    label += 1;
                    count += 1;
                }
            }
        }
        return count;
    }

    public static void labelOrganism(char[][] image, int X, int Y, char label) {
        if (X < 0 || Y < 0 || X >= image.length || Y >= image[X].length) {
            return;
        }
        else if (image[X][Y] == ' ') {
            return;
        } else if (image[X][Y] == label) {
            return;
        }
        image[X][Y] = label;

        // Check along the X column
        labelOrganism(image, X-1, Y, label);
        labelOrganism(image, X+1, Y, label);

        // Check along the Y row
        labelOrganism(image, X, Y-1, label);
        labelOrganism(image, X, Y+1, label);
    }
}
