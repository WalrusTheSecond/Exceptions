import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FormatChecker{
    public static void main(String[] args) {
        String animals[] = {"valid1.dat"};
        for (String arg : animals) {
            System.out.println(arg);
            File file = new File(arg);
            isValid checker = new isValid(file);
            System.out.println(isValid(file));
        }


        
    }//End of main

    public String isValid(File file) {
        String str = "Valid";
        try(Scanner scan = new Scanner(file)){
            if(!file.exists()){
            
            }
            String arrayDimensions = scan.nextLine().trim();
            String[] value = arrayDimensions.split("\\s+");
            for (String test : value) {
                System.out.println(test);
            }
            int row = Integer.parseInt(value[0]);
            int col = Integer.parseInt(value[1]);
            double[][] grid = new double [row][col];
            int lineCount = 0;
            while (scan.hasNextLine()) {
                String line = scan.nextLine().trim();
                if (!line.isEmpty()) {
                    String[] values = line.split("\\s+");// Split by spaces
                    for (int i = 0; i < col; i++) {
                        grid[lineCount][i] = Double.parseDouble(values[i]);
                    }
                    lineCount++;
                }
            }
        
    
        } catch (FileNotFoundException e){

        }
        return str;
    }//End of isValid
}//End of Format Checker
