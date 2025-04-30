/* Name: Sreya Kalyan
 * Course: 1450 Fall 2023 - Monday/Wednesday (1:40pm - 2:55pm)
 * Due Date: August 30th, 2023 at 1:40pm
 * Assignment Number: 1
 * Programme Description: This programme creates a single dimension array of fixed values
 * and then sorts them, finds pairs within them which add to 10 alongside the index number,
 * and then creates a text file to then write the array information to, - one line for every
 * index in the array. The programme also finds the mode of the array by iterating through the
 * array with two for-loops to find which number has occurred the most frequently, what number 
 * it is, and how many times it occurred before printing that information. The programme then 
 * creates a two dimensional array and a scanner to open and read the text file again. It 
 * then iterates backwards through the two dimensional array to place the values from text 
 * file into it in what will then be printed out as the descending order of the numbers.
 */

//Import the necessary java packages.
import java.io.PrintWriter;
import java.io.IOException;
import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

// Create the public assignment class.
public class KalyanSreyaAssignment1_F23 {
	// Create the public main method for the assignment.
	public static void main(String[] args) throws IOException {
		// Constant Numbers for checking sum of pairs within the single dimension array
		// and also for the number of rows and columns in the two dimensional array for 
		// the later part of the assignment.
		final int ARRAY_SUM_CHECK = 10;
		final int NUM_ROWS = 3;
		final int NUM_COLUMNS = 5;
		
		// Create a single dimensional array with a given set of numbers.
		int[] numberArray = {17, 10, 2, 16, 8, 15, 9, 17, 14, 18, 1, 19, 17, 2, 0};
		// Sort the numbers in the array.
		Arrays.sort(numberArray);
		// Print the sorted array in the console.
		System.out.println("");
		System.out.println("Sorted Number Array: ");
		for(int i = 0; i < numberArray.length; i++) {
			System.out.print(numberArray[i] + " ");
		}
		System.out.println("");
		System.out.println("");
		
		// Display all pairs of values from the one dimension array whose sum is 10.
		System.out.println("Number Pairs That Add to Ten: ");
		// Create a loop to iterate through the array.
		for(int j = 0; j < numberArray.length; j++) {
			// Create another loop to compare the numbers from the first iteration
			// with the ones from the second iteration to try all combinations.
			for(int k = j + 1; k < numberArray.length; k++) {
				// If the numbers from the first and second loops add to 10 then
				// print the values as a pair along with their array index number.
				if(numberArray[j] + numberArray[k] == ARRAY_SUM_CHECK) {
					System.out.println(numberArray[j] + " + " + 
					numberArray[k] + " = " + ARRAY_SUM_CHECK);
					int indexJ = j;
					int indexK = k;
					System.out.println("Index Values: (" + indexJ + ", " + indexK + ")");
				}
			}
		}
		System.out.println("");
		
		
		// Find the mode - mode frequent number - of the one dimension array.
		// Create an integer to hold the mode value when it's found.
		int arrayMode = 0;
		// Create an integer to keep count of how many times the mode occurred.
		int mostFreqElementCount = 0;
		// Create a loop to to iterate through the array.
		for (int l = 0; l < numberArray.length; l++) {
			// Create a counter to keep track of how many times a number occurs. 
			int arrayCount = 0;
			// Like previously, create a second iterator but instead of checking
			// for a sum, we check to see if any of the first iteration numbers match 
			// the second iteration numbers. If they do, add to the number counter.
			for(int m = 0; m < numberArray.length; m++) {
				if(numberArray[l] == numberArray[m]) {
					arrayCount++;
				}
			}
			// If a number occurs more often, then it becomes the new most frequent
			// count and we log the number and how many times in the array it occurred.
			if(arrayCount > mostFreqElementCount) {
				mostFreqElementCount = arrayCount;
				arrayMode = numberArray[l];
			}
		}
		// Print all of the information we gathered above.
		System.out.println("Mode of the Array: " + arrayMode);
		System.out.println("Frequency of Mode: " + mostFreqElementCount);
		System.out.println("");
		
		
		// Create a file to store the information from the above one dimension array.
		File firstAssignmentFile = new File("assignment1.txt");
		// Create a printwriter to write to the file we just created.
		PrintWriter outputFile = new PrintWriter(firstAssignmentFile);
		// Iterate through the array and write each index value in one line of the
		// file and loop this process until you reach the end of the array.
		for(int n = 0; n < numberArray.length; n++) {
			outputFile.println(numberArray[n]);
		}
		// Always close the file after a certain action, reopen as needed.
		outputFile.close();
		
		
		// Create a scanner to read from file which now has information stored in it.
		Scanner readFirstAssignmentFile = new Scanner(firstAssignmentFile);
		// Create a two dimensional array using the row and column information
		// previously given to us. 
		int[][] twoDimensionalArray = new int[NUM_ROWS][NUM_COLUMNS];
		// While the file has information in the next line, then for each line
		// of text, iterate backwards through the two dimensional array while
		// filling in the file information until either you reach the end of 
		// the text file or the front end of the two dimensional array.
		while(readFirstAssignmentFile.hasNext()) {
			for(int o = NUM_ROWS - 1; o >= 0; o--) {
				for(int p = NUM_COLUMNS - 1; p >= 0; p--) {
					twoDimensionalArray[o][p] = readFirstAssignmentFile.nextInt();
				}
			}
		}
		// Close the text file once done reading in information from it.
		readFirstAssignmentFile.close();
		
		
		// Print the two dimensional array to show the values which you have just 
		// placed in it backwards from the text file - bottom to top. 
		System.out.println("File Number Values Inserted Backwards Into 2D Array: ");
		for(int q = 0; q < NUM_ROWS; q++) {
			for(int r = 0; r < NUM_COLUMNS; r++) {
				System.out.print(twoDimensionalArray[q][r] + " ");
			}
			System.out.println();
		}
		System.out.println("");
		
	} // End of main method.

} // End of public assignment class.
