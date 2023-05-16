public class test{
    public static void main(String[] args) {
        int [] array1 = {1, 2, -3};
        int [] array2 = {-1, 2, 0, 2};
        int [] array3 = {};
        int [] array4 = {3, 2, 1, 2, 3};
        int [] array5 = {4};


        testMe(array1, "array1");
        testMe(array2, "array2");
        testMe(array3, "array3");
        testMe(array4, "array4");
        testMe(array5, "array5");


    }


    public static void testMe(int[] data, String name) {
        System.out.println(" Testing " + name + " has values " + arrayPrint(data, 0));
        System.out.println(" Count of nonzero elements in " + name + " is " + countOfNonZero(data, 0));
        System.out.println(" Product of " + name + " is " + productOf(data, 0));
        reverse(data, 0);
        System.out.println(" Reverse of " + name + " is " + arrayPrint(data, 0));
        reverse(data, 0);
        System.out.println(" Is " + name + " a palindrome? " + isPalindrome(data, 0));
        System.out.println();
    }


    public static boolean isPalindrome(int[] data, int position) {
        if (data.length/2 <= position) {
            return true;
        }
        boolean issame = data[position] == data[(data.length - position) - 1];
        return issame && isPalindrome(data, position + 1);
    }


    public static String arrayPrint(int[] data, int position) {
        String s = "";
        if (position >= data.length) {
            return s;
        }
        s += data[position];
        if (position != data.length - 1) {
            s += ", ";
        }


        return s + arrayPrint(data, position+1);
    }
    public static int countOfNonZero(int[] data, int position) {
        if (data.length <= position) {
            return 0;
        }
        int rest = countOfNonZero(data, position + 1);
        if (data[position] != 0) {
            rest += 1;
        }
        return rest;
    }


    public static int productOf(int[] data, int position) {
        if (data.length <= position) {
            return 1;
        }

        return data[position] * productOf(data, position+1);
    }


    public static void reverse(int[] data, int position) {
        if (data.length/2 <= position) {
            return;
        }
        int temp = data[position];
        data[position] = data[(data.length - position) - 1];
        data[(data.length - position) - 1] = temp;
        reverse(data, position + 1);
    }


    public static int factorial(int n) {
        int result = 0;
        System.out.println("Entering " + n);
        if (n == 0) {
            result = 1;
        } else {
            result = n * factorial(n-1);
        }
        System.out.println("Result is " + result);
        return result;
    }


    public static int fib(int n) {
        System.out.println("Entering " + n);
        int result = 0;
        if (n <= 1) {
            result = n;
        } else {
            result = fib(n-1) + fib(n - 2);
        }
        System.out.println("Result is " + result);
        return result;
    }
}

