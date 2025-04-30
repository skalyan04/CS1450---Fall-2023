/* Name: Sreya Kalyan
 * Course: 1450 Fall 2023 - Monday/Wednesday (1:40pm - 2:55pm)
 * Due Date: November 1st, 2023 at 1:40pm
 * Assignment Number: 8
 * Programme Description: This programme reads from a set of files, and then moves the files into 
 * a queue and an array list, both of which have iterators which we use to manipulate them. We send
 * them information from the files and use it to take the message we are given and decode it 
 * based on the grid locations of a two-dimensional array before printing both the encoded and decoded
 * versions by calling the methods to print and decode these structures. 
 */

//Import the necessary java packages.
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Queue;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class KalyanSreyaAssignment8_F23 {

	public static void main(String[] args) throws IOException {
		
		// Open the files for reading
		File openCodeGrid = new File("CodeGrid.txt");
		File openMessage = new File("Message.txt");
		
		// File openTestMessage = new File("TestMessage.txt");
		
		// Read in the information from the files
		Scanner codeGridFile = new Scanner(openCodeGrid);
		Scanner messageFile = new Scanner(openMessage);
		
		// Scanner testMessageFile = new Scanner(openTestMessage);
		
		/////////////////////////////////////////////////////////////////////////
		
		// Get the row and column information and create an
		// instance of the CodeMachine class in main
		int codeGridRows = codeGridFile.nextInt();
		int codeGridColumns = codeGridFile.nextInt();
		CodeMachine codeMachine = new CodeMachine(codeGridRows, codeGridColumns);
		
		/////////////////////////////////////////////////////////////////////////
		
		// Create an ArrayList to hold the characters that are read in
		ArrayList<Character> codeGridKey = new ArrayList<Character>();
		
		// Iterate through the file and store all of the 
		// characters inside of the ArrayList created above
		String codeGridFileString = codeGridFile.nextLine();
		for(int i = 0; i < codeGridFileString.length(); i++) {
			char codeGridChar = codeGridFileString.charAt(i);
			codeGridKey.add(codeGridChar);
		}
		
		// Create an iterator to go through the ArrayList created above
		Iterator<Character> codeGridKeyIterator = codeGridKey.iterator();
		
		/////////////////////////////////////////////////////////////////////////
		
		// Create a Queue of CodeElement objects with information about grid locations
		Queue<CodeElement> messageFileInformation = new LinkedList<CodeElement>();
		// Iteate through the file
		while(messageFile.hasNext()) {
			// Store the two integers from the same line separately.
			int firstPart = messageFile.nextInt();
			int secondPart = messageFile.nextInt();
			// Create an instance of the CodeMachine to store the values
			CodeElement value = new CodeElement(firstPart, secondPart);
			// Store the value in the Queue that was created
			messageFileInformation.offer(value);
		}
		
		// Create an Iterator to go through the now filled Queue of CodeElement objects
		Iterator<CodeElement> messageFileInfoIterator = messageFileInformation.iterator();
		
		/////////////////////////////////////////////////////////////////////////
		
		// Call the method to load the code grid with values
		codeMachine.loadCodeGrid(codeGridKeyIterator);
		// Call the method to print the encoded values
		codeMachine.printCodeGrid();
		
		System.out.println("------------------------------");
		
		// Call the method to decode the message in the iterator
		codeMachine.decode(messageFileInfoIterator);
		// Call the method to print the now decoded message
		codeMachine.printCodeGrid();
		
		// Close the files that were used
		codeGridFile.close();
		messageFile.close();

	} // End of Main Method


} // End of Public Assignment Class

// CodeElement class 
class CodeElement {
	// Private data fields for row and column
	private int row;
	private int column;
	
	// Constructor 
	public CodeElement (int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	// Getter that returns the row
	public int getRow() {
		return row;
	}
	// Getter that return the column
	public int getColumn() {
		return column;
	}
	
} // End of CodeElement Class


// CodeMachine class
class CodeMachine {
	// Private data fields for number of rows and columns
	// and also the 2-D code grid array
	private Character[][] codeGrid;
	private int numRows;
	private int numColumns;
	
	// Constructor
	public CodeMachine (int numRows, int numColumns) {
		this.numRows = numRows;
		this.numColumns = numColumns;
		codeGrid = new Character[numRows][numColumns];
	}
	
	// Method to load information into the array from the iterator provided
	public void loadCodeGrid (Iterator<Character> characterIterator) {
		while(characterIterator.hasNext()) {
			Character iteratorValue = characterIterator.next();
			for(int j = 0; j < numRows; j++) {
				for(int k = 0; k < numColumns; k++) {
					codeGrid[j][k] = iteratorValue;
				}
			}
		}
	} // End of loadCodeGrid Method
	
	// Method to get a value in a location of the iterator and move it to 
	// the created ArrayList using the getter methods defined earlier.
	public Iterator<Character> decode (Iterator<CodeElement> messageIterator) {
		ArrayList<Character> decodedMessage = new ArrayList<Character>();
		while(messageIterator.hasNext()) {
			CodeElement iteratorGrid = messageIterator.next();
			int row = iteratorGrid.getRow();
			int column = iteratorGrid.getColumn();
			decodedMessage.add(codeGrid[row][column]);
		}
		
		// Creating an iterator for the ArrayList
		Iterator<Character> decodedMessageIterator = decodedMessage.iterator();
		// Return the iterator
		return decodedMessageIterator;
		
	} // End of decode Method
	
	// Method to print the code grid array
	public void printCodeGrid() {	
		
		// Iterate and print the array row by row
		for (int n = 0; n < codeGrid.length; n++) {
			System.out.println(codeGrid[n]);
		}
		
	} // End of printCodeGrid Method
	
} // End of CodeMachine Class



