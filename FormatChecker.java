import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Checks if a file passed in through args, is of the correct format,
 * if it is it prints "VALID", otherwise it prints what was wrong aswell as
 * "INVALID".
 *
 * @author Evan Wallace
 */

public class FormatChecker {
    /**
     * Main method of FormatChecker. Loops through the args array and checks
     * each one to see if it has a valid format. Then prints the result
     * 
     * @param args
     */
    public static void main(String[] args) {
        for (String arg : args) {
            System.out.println(arg);
            File file = new File(arg);
            try {
                String result = isValid(file); //Call isValid
                System.out.println(result + "\n");
            } catch (Exception e) { //isValid() throws a exception if the file is invalid
                System.out.println(e.toString()); 
                System.out.println("INVALID\n");
            }
        }
    }// End of main

    /**
     * isValid() is a method which checks to see if the file is valid.
     * If it is then it returns "VALID" otherwise a exception is triggered and
     * isValid() returns "INVALID".
     * 
     * @param file
     * @return
     * @throws Exception
     */
    public static String isValid(File file) throws Exception {
        try (Scanner scan = new Scanner(file)) {
            if (!file.exists()) {
                throw new FileNotFoundException("File: " + file + " not found.");
            }
            if (!scan.hasNextLine()) {
                throw new InputMismatchException("File: " + file + " is empty.");
            }
            String arrayDimensions = scan.nextLine().trim();
            String[] value = arrayDimensions.split("\\s+");
            if (value.length != 2) {
                throw new InputMismatchException("Improper array dimension input.");
            }
            int row = Integer.parseInt(value[0]);
            int col = Integer.parseInt(value[1]);
            if (row < 0 || col < 0) {
                throw new InputMismatchException("Row and Column input must be positive.");
            }
            double[][] grid = new double[row][col];
            int lineCount = 0;
            while (scan.hasNextLine()) {
                String line = scan.nextLine().trim();
                if (!line.isEmpty()) {
                    String[] values = line.split("\\s+");// Split by spaces
                    if (values.length != col) {
                        throw new InputMismatchException("Invalid row length at line: " + lineCount);
                    }
                    for (int i = 0; i < col; i++) {
                        grid[lineCount][i] = Double.parseDouble(values[i]);
                    }
                    lineCount++;
                }
            }
            if (lineCount != row) {
                throw new InputMismatchException("Row count mismatch. Expected " + row + " but found " + lineCount);
            }
        } catch (Exception e) {
            System.out.println(e);
            return "INVALID";
        }
        return "VALID";
    }// End of isValid
}// End of Format Checker