import java.util.Arrays;
/**
 * Assignment 4 for CS 1410
 * This program evaluates the linear and binary searching, along
 * with comparing performance difference between the selection sort
 * and the built-in java.util.Arrays.sort.
 *
 * @author James Dean Mathias
 */
public class EvaluationDriver {
    static final int MAX_VALUE = 1_000_000;
    static final int MAX_ARRAY_SIZE = 100_000;
    static final int ARRAY_SIZE_START = 20_000;
    static final int ARRAY_SIZE_INCREMENT = 20_000;
    static final int NUMBER_SEARCHES = 50_000;

    public static void main(String[] args) {

        // Drivers to run tests
        demoLinearSearchUnsorted();
        demoLinearSearchSorted();
        demoBinarySearchSelectionSort();
        demoBinarySearchFastSort();
    }

    public static int[] generateNumbers(int howMany, int maxValue) {
        // Check if howMany is a valid length
        if (howMany <= 0) {
            return null;
        }

        int[] list = new int[howMany];
        // Generate random numbers
        for (int i = 0; i < list.length; i++) {
            list[i] = (int)(Math.random() * maxValue);
        }

        return list;
    }

    public static boolean linearSearch(int[] data, int search) {
        for (int i : data) {
            if (i == search) {
                return true;
            }
        }
        return false;
    }

    public static boolean binarySearch(int[] data, int search) {
        int low = 0;
        int high = data.length - 1;
        int mid = (low + high) / 2;

        //Loop until low is equal to high
        while (low <= high) {
            mid = (low + high) / 2;
            if (search == data[mid]) {
                return true;
            }
            //If number is higher than top half else lower half
            else if (search > data[mid]) {
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }

        return false;
    }

    public static void selectionSort(int[] data) {

        for (int start = 0; start < (data.length - 1); start++) {
            int minPos = start;
            int remaining = data.length;

            for (int scan = start + 1; scan < remaining; scan++) {
                if (data[scan] < data[minPos]) {
                    minPos = scan;
                }
            }

            int temp = data[minPos];
            data[minPos] = data[start];
            data[start] = temp;
        }
    }

    public static void demoLinearSearchUnsorted() {
        System.out.println("--- Linear Search Timing (unsorted) ---");
        long total;
        int arraySize = ARRAY_SIZE_START;
        int count = 0;

        //Loop until array size is equal to Max array
        while (arraySize <= MAX_ARRAY_SIZE) {
            // Generate numbers
            int[] list = generateNumbers(arraySize, MAX_VALUE);

            // Start test
            long start = System.currentTimeMillis();
            for (int i = 0; i < NUMBER_SEARCHES; i++) {
                int[] num = generateNumbers(1, MAX_VALUE);

                // Search for random number
                if (linearSearch(list, num[0]) == true) {
                    count++;
                }
            }
            long end = System.currentTimeMillis();
            total = end - start;

            System.out.printf("Number of items\t\t: %d\n", arraySize);
            System.out.printf("Times value was found\t: %d\n", count);
            System.out.printf("Total search time\t\t: %dms\n\n", total);

            arraySize += ARRAY_SIZE_INCREMENT;
        }

    }

    public static void demoLinearSearchSorted() {
        System.out.println("--- Linear Search Timing (sorted) ---");
        long total;
        int arraySize = ARRAY_SIZE_START;
        int count = 0;

        //Loop until array size is equal to Max array
        while (arraySize <= MAX_ARRAY_SIZE) {
            // Generate numbers
            int[] list = generateNumbers(arraySize, MAX_VALUE);

            // Start test
            long start = System.currentTimeMillis();

            // Sort array
            selectionSort(list);
            for (int i = 0; i < NUMBER_SEARCHES; i++) {
                int[] num = generateNumbers(1, MAX_VALUE);

                // Search for random number
                if (linearSearch(list, num[0]) == true) {
                    count++;
                }
            }
            long end = System.currentTimeMillis();
            total = end - start;

            System.out.printf("Number of items\t\t: %d\n", arraySize);
            System.out.printf("Times value was found\t: %d\n", count);
            System.out.printf("Total search time\t\t: %dms\n\n", total);

            arraySize += ARRAY_SIZE_INCREMENT;
        }
    }

    public static void demoBinarySearchSelectionSort() {
        System.out.println("--- Binary Search Timing (Selection Sort) ---");
        long total;
        int arraySize = ARRAY_SIZE_START;
        int count = 0;

        //Loop until array size is equal to Max array
        while (arraySize <= MAX_ARRAY_SIZE) {
            // Generate numbers
            int[] list = generateNumbers(arraySize, MAX_VALUE);

            // Start test
            long start = System.currentTimeMillis();

            // Sort array
            selectionSort(list);
            for (int i = 0; i < NUMBER_SEARCHES; i++) {
                int[] num = generateNumbers(1, MAX_VALUE);

                // Search for random number
                if (binarySearch(list, num[0]) == true) {
                    count++;
                }
            }
            long end = System.currentTimeMillis();
            total = end - start;

            System.out.printf("Number of items\t\t: %d\n", arraySize);
            System.out.printf("Times value was found\t: %d\n", count);
            System.out.printf("Total search time\t\t: %dms\n\n", total);

            arraySize += ARRAY_SIZE_INCREMENT;
        }
    }

    public static void demoBinarySearchFastSort() {
        System.out.println("--- Binary Search Timing (Arrays.sort) ---");
        long total;
        int arraySize = ARRAY_SIZE_START;
        int count = 0;

        //Loop until array size is equal to Max array
        while (arraySize <= MAX_ARRAY_SIZE) {
            // Generate numbers
            int[] list = generateNumbers(arraySize, MAX_VALUE);

            // Start test
            long start = System.currentTimeMillis();

            // Sort array
            Arrays.sort(list);
            for (int i = 0; i < NUMBER_SEARCHES; i++) {
                int[] num = generateNumbers(1, MAX_VALUE);

                // Search for random number
                if (binarySearch(list, num[0]) == true) {
                    count++;
                }
            }
            long end = System.currentTimeMillis();
            total = end - start;

            System.out.printf("Number of items\t\t: %d\n", arraySize);
            System.out.printf("Times value was found\t: %d\n", count);
            System.out.printf("Total search time\t\t: %dms\n\n", total);

            arraySize += ARRAY_SIZE_INCREMENT;
        }
    }
}
