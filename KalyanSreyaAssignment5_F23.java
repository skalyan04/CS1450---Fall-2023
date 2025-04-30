/* Name: Sreya Kalyan
 * Course: 1450 Fall 2023 - Monday/Wednesday (1:40pm - 2:55pm)
 * Due Date: October 4th, 2023 at 1:40pm
 * Assignment Number: 5
 * Programme Description: This programme creates a regular stack which has values placed into it from
 * a normal one-dimensional array. This array is then manipulated to have values added to it after a 
 * certain occurrence and then have that printed. This programme also creates four generic stacks and
 * stored information in them as read from the files we read. These stacks are then manipulate to be
 * read and printed to the console. There are two stacks of Integers and two stacks of Strings. We take
 * the two stacks at a time of the same type and use constants to sort them as greater than or equal to
 * and less than that value and place them back in the stacks after. We also sort sort them in 
 * descending order. All of these changes are also printed to the console every time.
 */

//Import the necessary java packages.
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Scanner;

public class KalyanSreyaAssignment5_F23 {

	public static void main(String[] args) throws IOException {
		
		// Declare Two Constants to Be Used in the rearrangeStacks Method
		final int REARRANGE_NUMBER = 15;
		final String REARRANGE_STRING = "Durango";
		
		// Open the Two Integer and Two String Files for Reading
		File openNumbersOneFile = new File("numbers1.txt");
		File openNumbersTwoFile = new File("numbers2.txt");
		File openCitiesOneFile = new File("cities1.txt");
		File openCitiesTwoFile = new File("cities2.txt");
		
		// Create Scanner Objects to Read in Information From the Files
		Scanner numbersOneFile = new Scanner(openNumbersOneFile);
		Scanner numbersTwoFile = new Scanner(openNumbersTwoFile);
		Scanner citiesOneFile = new Scanner(openCitiesOneFile);
		Scanner citiesTwoFile = new Scanner(openCitiesTwoFile);
	
		////////////////////////////////////////////////////////////////////////////////////
		
		// Create an Array With Given Values
		int[] values = {10, 1, 42, 15, 62, 8, 17, 2};
		// Create a Predefined Stack Using the Java Collections Framework
		Stack<Integer> collectionsStackOne = new Stack<Integer>();
		// Iterate and Push Array Values Into the Stack
		for(int i = 0; i < values.length; i++) {
			collectionsStackOne.push(values[i]);
		}
		
		// Call Method to Add a Zero After Every Even Value in the Stack
		addZeroAfterEvenValues(collectionsStackOne);
		System.out.println("");
		System.out.println("Java Predefined Stack After Adding Zeros After Even Numbers: ");
		System.out.println("---------------------------------------------------------------------------");
		
		// Print the Stack After the Method Has Been Called
		printStack(collectionsStackOne);
		System.out.println("");
		System.out.println("//////////////////////////////");
		System.out.println("");
		
		////////////////////////////////////////////////////////////////////////////////////
		
		// Create Two Generic Objects of Integer Type
		GenericStack<Integer> numberStackOne = new GenericStack<Integer>();
		GenericStack<Integer> numberStackTwo = new GenericStack<Integer>();
		// Create Two Generic Objets of String Type
		GenericStack<String> cityStackOne = new GenericStack<String>();
		GenericStack<String> cityStackTwo = new GenericStack<String>();
		
		// Call the fillGenericNumberStack Method to Add Values From the File Into 
		// the Two Integer Type Generic Objects We Created Above
		fillGenericNumberStack(numbersOneFile, numberStackOne);
		fillGenericNumberStack(numbersTwoFile, numberStackTwo);
		
		// Repeat the Stack Filling Process for the String Files and Generic Stacks
		fillGenericStringStack(citiesOneFile, cityStackOne);
		fillGenericStringStack(citiesTwoFile, cityStackTwo);
		
		System.out.println("First Generic Number Stack With Values From the First Numbers File: ");
		System.out.println("---------------------------------------------------------------------------");
		// Print the First Number Stack After it Has Been Filled
		printGenericStack(numberStackOne);
		
		System.out.println("");
		System.out.println("//////////////////////////////");
		System.out.println("");
		
		System.out.println("Second Generic Number Stack With Values From the Second Numbers File: ");
		System.out.println("---------------------------------------------------------------------------");
		// Print the Second Number Stack After it Has Been Filled
		printGenericStack(numberStackTwo);
		
		System.out.println("");
		System.out.println("//////////////////////////////");
		System.out.println("");

		System.out.println("First Generic String Stack With Values From the First Cities File: ");
		System.out.println("---------------------------------------------------------------------------");
		// Print the First City Stack After it Has Been Filled
		printGenericStack(cityStackOne);
		
		System.out.println("");
		System.out.println("//////////////////////////////");
		System.out.println("");

		System.out.println("Second Generic String Stack With Values From the Second Cities File: ");
		System.out.println("---------------------------------------------------------------------------");
		// Print the Second City Stack After it Has Been Filled
		printGenericStack(cityStackTwo);
		
		System.out.println("");
		System.out.println("//////////////////////////////");
		System.out.println("");
		
		// Call the rearrangeStacks Method to Sort Two Stacks of the Same Type Based
		// on Either Greater Than or Equal to a Value or Less Than a Value
		rearrangeGenericStacks(numberStackOne, numberStackTwo, REARRANGE_NUMBER);
		rearrangeGenericStacks(cityStackOne, cityStackTwo, REARRANGE_STRING);
		
		// Create Generic Stack Objects of Integer and String Type to Store 
		// Stacks That Have Been Sorted in Increasing Order
		GenericStack<Integer> sortedNumberStackOne = sortStack(numberStackOne);
		GenericStack<Integer> sortedNumberStackTwo = sortStack(numberStackTwo);
		GenericStack<String> sortedCityStackOne = sortStack(cityStackOne);
		GenericStack<String> sortedCityStackTwo = sortStack(cityStackTwo);

		System.out.println("Final Sorted First Stack of Numbers Greater Than Fifteen: ");
		System.out.println("---------------------------------------------------------------------------");
		// Print the First Sorted Number Stack
		printGenericStack(sortedNumberStackOne);
		
		System.out.println("");
		System.out.println("//////////////////////////////");
		System.out.println("");
		
		System.out.println("Final Sorted Second Stack of Numbers Greater Than or Equal to Fifteen: ");
		System.out.println("---------------------------------------------------------------------------");
		// Print the Second Sorted Number Stack
		printGenericStack(sortedNumberStackTwo);
		
		System.out.println("");
		System.out.println("//////////////////////////////");
		System.out.println("");
		
		System.out.println("Final Sorted First Stack of Cities Less Than Durango: ");
		System.out.println("---------------------------------------------------------------------------");
		// Print the First Sorted City Stack
		printGenericStack(sortedCityStackOne);
		
		System.out.println("");
		System.out.println("//////////////////////////////");
		System.out.println("");
		
		System.out.println("Final Sorted Second Stack of Cities Greater Than or Equal to Durango: ");
		System.out.println("---------------------------------------------------------------------------");
		// Print the Second Sorted City Stack
		printGenericStack(sortedCityStackTwo);
		
		System.out.println("");
		System.out.println("//////////////////////////////");
		System.out.println("");
		

	} // End of Main Method
	
	// Method to Print a Non-Generic Stack. This Will Create a Temporary Stack,
	// Iterate Through the Original Stack, Pop and Print Each Value, Before Finally
	// Pushing it Onto the Temporary Stack for Storing. It Will Then Iterate Through
	// the Temporary Stack and Pop Each Value Back Off to Place Back in the Original Stack.
	public static void printStack(Stack<Integer> stack) {
		Stack<Integer>tempStack = new Stack<Integer>();
		
		while(!stack.isEmpty()) {
			// Pop the Value Off
			int stackValue = stack.pop();
			// Print the Value
			System.out.println(stackValue);
			// Place it on the Temporary Stack
			tempStack.push(stackValue);
		}
		
		while(!tempStack.isEmpty()) {
			// Pop the Value Off
			int tempStackValue = tempStack.pop();
			// Place it Back on the Original Stack
			stack.push(tempStackValue);
		}
		
	} // End of printStackMethod
	
	// Method to Iterate Through a Non-Generic Stack and Add a Zero to 
	// Every Location After an Even Value Has Occurred.
	public static void addZeroAfterEvenValues(Stack<Integer> stack) {
		// Create a Temporary Stack
		Stack<Integer> tempStack = new Stack<Integer>();
		// Get the Stack Size
		int stackLength = stack.size();
		// Iterate Through Original Stack
		for(int j = 0; j < stackLength; j++) {
			// Pop the Value Off
			int stackValue = stack.pop();
			// If an Even Number is Found
			if(stackValue%2 == 0) {
				// Push the Value on to the Temporary Stack First
				tempStack.push(stackValue);
				// Then Add a Zero to the Temporary Stack After it
				tempStack.push(0);
			}
			// If an Even Does Not Occur
			else {
				// Simply Push the Value Onto the Stack
				tempStack.push(stackValue);
			}
		}
		// Place All of the Values Back Onto the Original Stack
		// Which Will Now Have the Added Zeros After Every Even Number
		while(!tempStack.isEmpty()) {
			stack.push(tempStack.pop());
		}
		
	} // End of addZeroAfterEvenValues Method
	
	// Method to Fill a Generic Stack of Type Integer With Values Read From a File
	public static void fillGenericNumberStack (Scanner numFile, GenericStack<Integer> numStack) {
		// While You Have Not Reached the End of the File
		while(numFile.hasNext()) {
			// Declare and Initialize a Variable With the Next Value
			int numValue = numFile.nextInt();
			// Push That Value Onto the Stack
			numStack.push(numValue);
		}
	} // End of fillGenericNumberStack Method
	
	// Method to Fill a Generic Stack of Type String With Read From a File
	public static void fillGenericStringStack (Scanner strFile, GenericStack<String> strStack) {
		// While You Have Not Reaches the End of the File
		while(strFile.hasNext()) {
			// Declare and Initialize a Variable With the Next Line
			String strValue = strFile.nextLine();
			// Push That Value Onto the Stack
			strStack.push(strValue);;
		}
	} // End of fillGenericStringStackMethod
	
	// Method to Print a Generic Stakc of Any Type Including Integer and String
	public static <E> void printGenericStack(GenericStack<E> genericStack) {
		// Create a Temporary Generic Stack 
		GenericStack<E> tempGenericStack = new GenericStack<E>();
		
		// Declare and Initialize a Variable to Hold the Size of the Stack
		int genericStackLength = genericStack.getSize();
		
		// Iterate Through the Stack
		for(int m = 0; m < genericStackLength; m++) {
			// Declare and Initialize a Variable With the Value You Pop
			E genericStackValue = genericStack.pop();
			// Print the Value Out
			System.out.println(genericStackValue);
			// Push it to the Temporary Stack
			tempGenericStack.push(genericStackValue);
		}
		
		// Now Iterate Through the Temporary Stack
		while(!tempGenericStack.isEmpty()) {
			// Declare and Initialize a Variable with the Value You Pop Off
			E tempGenericStackValue = tempGenericStack.pop();
			// Push that Value Back Onto the Original Stack
			genericStack.push(tempGenericStackValue);
		}
		
	} // End of printGenericStack Method
	
	// Method to Rearrange Two Generic Stacks at a Time of Any Type Including String and Integer
	// Using a Constant Variable Defined Earlier in the Programme as a BenchMark
	public static <E extends Comparable <E>> void rearrangeGenericStacks(GenericStack<E> genericStackOne, 
			GenericStack<E> genericStackTwo, E rearrangeValue) {
		// Create a Temporary Generic Stack to Hold Both Sets of 
		// Values From Both of the Original Stacks
		GenericStack<E> tempGenericTransferStack = new GenericStack<E>();
		
		// Iterate Through the First Stack
		while(!genericStackOne.isEmpty()) {
			E stackValueOne = genericStackOne.pop();
			// Push the Values Onto the Temporary Stack
			tempGenericTransferStack.push(stackValueOne);
		}
		
		// Iterate Through the Second Stack
		while(!genericStackTwo.isEmpty()) {
			E stackValueTwo = genericStackTwo.pop();
			// Push These Values Onto the Temporary Stack Too
			tempGenericTransferStack.push(stackValueTwo);
		}
		
		// Iterate Through the Temporary Stack
		while(!tempGenericTransferStack.isEmpty()) {
			// Declare and Initialize a Variable with the Value You Pop Off
			E tempValue = tempGenericTransferStack.pop();
			// If the Popped Value is Less Than the Benchmark Value
			if(tempValue.compareTo(rearrangeValue) < 0) {
				// Move it to the First Generic Stack
				genericStackOne.push(tempValue);
			}
			// If the Popped Value is Greater Than or Equal to the Benchmark
			else {
				// Move it to the Second Generic Stack
				genericStackTwo.push(tempValue);
			}
		} // End of Temporary Stack While-Loop
		
		
	} // End of rearrangeGenericStacks Method
	
	// Method to Sort a Stack in Ascending Order and Return the Sorted Stack in Main
	public static <E extends Comparable <E>> GenericStack<E> sortStack(GenericStack<E> unsortedGenericStack) {
		// Create a Temporary Generic Stack to Help With the Sorting 
		GenericStack<E> tempSortingStack = new GenericStack<E>();
		
		// While the Original Stack is Not Empty
		while(!unsortedGenericStack.isEmpty()) {
			// Declare and Initialize a Variable with the Value You Pop Off
			E currentValue = unsortedGenericStack.pop();
			
			// Move Elements to the Stack Until a Smaller Element is Found 
			while (!tempSortingStack.isEmpty() && 
					(currentValue.compareTo(tempSortingStack.peek()) >= 0)) {
                unsortedGenericStack.push(tempSortingStack.pop());
            }
			// Push the Variable Element Onto the Stack
			tempSortingStack.push(currentValue);
		}
		
		// Return the Temporary Sorted Stack
		return tempSortingStack;
		
	} // End of sortStack Method
	

} // End of Public Assignment Class


// Start of GenericStack Class
class GenericStack<E> {
	// Private Data Field
	private ArrayList<E> list;
	
	// Constructor
	public GenericStack() {
		list = new ArrayList<E>();
	}
	
	// Public Method to Return True if Stack is Empty
	public boolean isEmpty() {
		return list.isEmpty();
	}
	// Public Method to Return the Size of the Stack
	public int getSize() {
		return list.size();
	}
	// Public Method to Return the Top Value Without Removing It
	public E peek() {
		return list.get(getSize()-1);
	}
	// Public Method to Remove and Return the Top Value
	public E pop() {
		E value = list.get(getSize()-1);
		list.remove(getSize()-1);
		return value;
	}
	// Public Method to Add a Value to the Top of the Stack
	public void push(E value) {
		list.add(value);
	}
} // End of Generic Stack Class

