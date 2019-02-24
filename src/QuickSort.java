import java.io.*;
import java.util.*;

public class QuickSort {

  /**
   * Sorts the given array in ascending order.
   * DO NOT MODIFY THIS METHOD SIGNATURE.
   * @param array The array to be sorted.
   * @return The sorted array.
   */
  private static String[] sort(String[] array) {
	if(array.length <= 1) {
		return array;
	}
	String pivot = array[array.length / 2];
	
	int leftsize = 0;
	for(int i = 0; i < array.length; i++) {
		if(array[i].compareTo(pivot) < 0) {
			leftsize++;
		}
	}
	String [] left = new String[leftsize];
	String [] right = new String[array.length - leftsize - 1];
	
	for (int i = 0, l = 0, r = 0; i < array.length; i++) {
		if(array[i].compareTo(pivot) < 0) {
			left[l] = array[i];
			l++;
		}
		else if(array[i].compareTo(pivot) > 0) {
			right[r] = array[i];
			r++;
		}
		else {
			
		}
	}

	String [] total  = new String[right.length + left.length + 1];
	String [] newLeft = sort(left);
	String [] newRight = sort(right);
	
	for(int i = 0; i < left.length; i++) {
		total[i] = newLeft[i];
	}
	total[left.length] = pivot;
	for(int i = left.length + 1, r = 0; i < total.length; i++, r++) {
		total[i] = newRight[r];
	}
	return total;
		 
  }


  /**
   * Reads an entire file into an array of Strings, where each element
   * represents a new line in the file. Assumes the file's first line is an
   * integer indicating the number of following lines.
   * @param filename The filename of the file to read.
   * @return An array of strings read from the file.
   * @throws IOException If the file doesn't exist or an error occurs during the
   *     reading.
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