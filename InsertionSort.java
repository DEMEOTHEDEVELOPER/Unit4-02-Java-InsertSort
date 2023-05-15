import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
/**
* This program calculates amount of mean median and mode.
*
* @author  Alex De Meo
* @version 1.0
* @since   2023/02/08
*/

public final class InsertionSort {
    /**
    * This is a private constructor used to satisfy the
    * style checker.
    *
    * @exception IllegalStateException Utility Class
     */
    private InsertionSort() {
        throw new IllegalStateException("Utility Class");
    }
    /**
    * This is the main method.
    *
    * @param args Unused
    * @throws Exception if something goes wrong
    */

    public static void main(String[] args) throws Exception {
        // Initializing variables
        String line;
        final String err = "Error";
        final String splitBy = " ";
        try {
            // new file object
            final File input = new File("input.txt");

            // Creating the writer
            final FileWriter writer = new FileWriter("ouput.txt");

            try {
                // Creating the scanner.
                final Scanner scanner = new Scanner(input);

                // ArrayList to hold the lines
                final ArrayList<String> lines = new ArrayList<>();
                // Getting the input from the first file
                while (scanner.hasNextLine()) {
                    // getting next line and putting it in the interim list
                    line = scanner.nextLine();
                    lines.add(line);
                }
                for (String aLine : lines) {
                    if (aLine.length() != 0) {
                        try {
                            // Reference, Keiden showed me how to code it like
                            // this.
                            final int[] numbers = Arrays.stream(
                                    aLine.split(splitBy)
                                ).mapToInt(Integer::parseInt).toArray();
                            final int[] sorted = insertSort(numbers);
                            for (int num : sorted) {
                                writer.write(num + splitBy);
                            }
                            writer.write("\n");
                        } catch (NumberFormatException error) {
                            writer.write("ERROR: Invalid number\n");
                        }
                    } else {
                        writer.write("ERROR: Empty line\n");
                    }
                }
            } catch (IOException error) {
                System.out.println(err + error.getMessage());
            }
            // closes the writer
            writer.close();
        } catch (IOException error) {
            System.out.println(err + error.getMessage());
        }
    }
    /**
    * This is the method reverseRecs the string.
    *
    * @param numbers This is the array to be sorted
    * @return the sorted array
    */

    public static int[] insertSort(int[] numbers) {
        // first loop is for how many times to go through the array
        for (int counter1 = 1; counter1 < numbers.length; counter1++) {
            // setting the number that will be compared to everything else
            final int number = numbers[counter1];
            // setting the second counter, this one is used to go down through
            // the array to compare numbers.
            int counter2 = counter1 - 1;
            // This loop goes through the list and compares numbers until either
            // A - it is not bigger than the next number or B - it has hit i = 0
            while (counter2 >= 0 && numbers[counter2] > number) {
                // comparing the numbers
                numbers[counter2 + 1] = numbers[counter2];
                counter2--;
            }
            // resetting the number for comparison
            numbers[counter2 + 1] = number;
        }
        return numbers;
    }
}
