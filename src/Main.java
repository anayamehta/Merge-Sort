import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    /**
     * Sorts the given array in ascending order.
     * DO NOT MODIFY THIS METHOD SIGNATURE.
     *
     * @param array The array to be sorted.
     * @return The sorted array.
     */
    private static String[] sort(String[] array) {
        if (array.length <= 1) {
            return array;
        }
        //this will determine the middle
        int middle = ((array.length / 2));
        //decided the length of each array
        String[] left = new String[middle];
        String[] right = new String[array.length - middle];
        //create the arrays
        for (int i = 0; i < left.length; i++) {
            left[i] = array[i];
        }
        for (int i = 0; i < right.length; i++) {
            right[i] = array[middle + i];
        }
        //this will call a merge sort on both
        sort(left);
        sort(right);
        //now it will merge
        int l = 0;
        int r = 0;
        for (int i = 0; i < array.length; i++) {
            // if it is at the end of left or right array
            if (r >= right.length) {
                array[i] = left[l];
                l++;
            } else if (l >= left.length) {
                array[i] = right[r];
                r++;
            }
            // now it will find the minimum of the left and right array
            else if (left[l].compareTo(right[r]) < 0) {
                array[i] = left[l];
                l++;
            } else {
                array[i] = right[r];
                r++;
            }

        }
        return array;
    }
    /**
     * Reads an entire file into an array of Strings, where each element
     * represents a new line in the file. Assumes the file's first line is an
     * integer indicating the number of following lines.
     *
     * @param filename The filename of the file to read.
     * @return An array of strings read from the file.
     * @throws IOException If the file doesn't exist or an error occurs during the
     *                     reading.
     */
    private static String[] load(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String[] lines = new String[Integer.parseInt(reader.readLine())];
        for (int i = 0; i < lines.length; i++) {
            lines[i] = reader.readLine();
        }
        return lines;
    }
    public static void main(String[] args) throws IOException {
        String[] prefixes = {"small", "medium", "large"};
        String[] suffixes = {"ascending.txt", "descending.txt", "random.txt"};
        for (String prefix : prefixes) {
            for (String suffix : suffixes) {
                String filename = prefix + "_" + suffix;
                String[] array = load(filename);
                long start, end;

                System.out.println("Sorting " + filename + " with " + array.length + " words");

                String[] copy = Arrays.copyOf(array, array.length);
                start = System.currentTimeMillis();
                Arrays.sort(copy);
                end = System.currentTimeMillis();
                System.out.println("\tArrays.sort() took:\t" + (end - start) + " ms");

                start = System.currentTimeMillis();
                String[] sortedArray = sort(array);
                end = System.currentTimeMillis();
                if (Arrays.equals(sortedArray, copy)) {
                    System.out.println("\tYour sort method took:\t" + (end - start) + " ms");
                } else {
                    System.err.println("\tYour sort does not properly sort the array in ascending order!");
                }

                System.out.println();
            }
        }
    }
}

