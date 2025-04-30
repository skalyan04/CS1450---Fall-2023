/* Name: Sreya Kalyan
 * Course: 1450 Fall 2023 - Monday/Wednesday (1:40pm - 2:55pm)
 * Due Date: November 13th, 2023 at 1:40pm
 * Assignment Number: 9
 * Programme Description: This programme reads in values from a file and places them in a singly 
 * linked list. It then uses the other classes and methods to perform a serious of manupulations
 * including checking if the words are abecedarian, removing them if they are not, and printing
 * them after they have been manipulated and changed. The remaining abecedarian values are then
 * moved into a douly linked list and as they are moved, they are also removed from the singly linked
 * list. There are some manipulations performed on the new linked list as well before finally ending.
 */


//Import the necessary java packages.
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class KalyanSreyaAssignment9_F23 {

	public static void main(String[] args) throws IOException {
		
		// Open the File and Scanner to Read From the Opened File
		File openWordsFile = new File("Words.txt");
		Scanner wordsFile = new Scanner(openWordsFile);
		
		// Create a Singly Linked List
		WordLinkedList singleLinkedList = new WordLinkedList();
		
		// While the File Has Information, Read From it and
		// Create a Word to Add to the Front of the Singly Linked List
		while(wordsFile.hasNext()) {
			String readWord = wordsFile.nextLine().trim();
			Word newWord = new Word(readWord);
			singleLinkedList.addInFront(newWord);
		}
		
		// Print the Full Singly Linked List
		singleLinkedList.printList();
		System.out.println("------------------------------");
		// Call the Method to Remove the Words That Aren't Abecedarians 
		singleLinkedList.removeNonAbecedarianWords();
		System.out.println("------------------------------");
		// Call the Bubble Sort Method for the Singly Linked List
		singleLinkedList.bubbleSort();
		System.out.println("------------------------------");
		// Print the Now Sorted List With Only Abecedarian Words
		singleLinkedList.printList();
		
		
		// Create a Doubly Linked List
		DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
		
		// Call the Build Method to Remove the Nodes From the 
		// Singly Linked List and Place Them in the Doubly Linked One
		doubleLinkedList.build(singleLinkedList);
		System.out.println("------------------------------");
		// Call the Method to Print the Doubly Linked List Backwards
		doubleLinkedList.printListBackwards();
		System.out.println("------------------------------");
		// Print the Singly Linked List One Last Time to Show 
		// That the Print Backwards Method Correctly Removed All
		// of the Nodes Before Placing Them in the Doubly Linked List
		singleLinkedList.printList();
		
		
		// Close the Text File
		wordsFile.close();
		
	} // End of Main Method

} // End of Public Assignment Class


class Word implements Comparable<Word> {
	// Private Data Fields
	private String letters;
	private boolean abecedarian;
	
	public Word (String letters) {
		this.letters = letters;
		this.abecedarian = abecedarianTest();
	} // End of Word Constructor
	
	public boolean isAbecedarian() {
		// Calls the Private Abecedarian Test Method
		return abecedarianTest();
	} // End of isAbecedarian Boolean
	
	private boolean abecedarianTest() {
		// Store the Length of the Word in a Variable
		// So That it Remains Unchanged for Safety
		 int length = letters.length();
		 // Create a Boolean That Returns True/False
		 boolean valid = false;
		 for(int i = 0; i < length; i++) {
			 // Iterate Through the Length of the Word
			 // Store Chars of a Location and One Plus a Location
			 char current = letters.charAt(i);
			 char next = letters.charAt(i + 1);
			 // If the Current Location is Greater Than the One After
			 if(current > next) {
				 // Set the Boolean to False
				 valid = false;
			 }
			 else {
				 // If the Next Location is Greater Than the Current One
				 // Set the Boolean to be True
				 valid = true;
			 }
		 }
		 // Return the Boolean as Either True or False
		 return valid;
	} // End of abecedarianTest Method
	
	public String print() {
		return String.format("%s\t\t%b", letters, abecedarian);
	} // End of print() Method
	
	public int compareTo (Word otherWord) {
		int wordSize = 0;
		// Create a Variable to Hold the Length of a Current Word and Then
		// Create Another Variable to Hold the Length of the Other Word
		int wordLength = this.letters.length();
		int otherWordLength = otherWord.letters.length();
		
		// Compare the Current Word and Other Word
		// If the Other Word is Bigger
		if(wordLength < otherWordLength) {
			// Set the Word Size to the Smaller One (Current Word)
			wordSize = wordLength;
		}
		// If They Are of Equal Length
		else if(wordLength == otherWordLength) {
			// Either Word Will Work
			wordSize = wordLength;
		}
		// If the Other Word is Smaller Than the Current
		else {
			// Set the Other Word to be the Word Size
			wordSize = otherWordLength;
		}
		
		int returnValue = 0;
		
		// Iterate Through the Number Stored in Word Size
		for(int i = 0; i < wordSize; i++) {
			// Create Two Chars to Store a Location in a Word
			// One for the Current Word and One for the Other Word
			char thisChar = this.letters.charAt(i);
			char otherChar = otherWord.letters.charAt(i);
			
			// If the Other Char is Greater Than the Current Char
			if(thisChar < otherChar) {
				returnValue = -1;
			}
			// If the Current Char is Greater Than the Other Char
			else if(thisChar > otherChar) {
				returnValue = 1;
			}
			// If The Current Char and Other Char are Equal
			else {
				returnValue = 0;
			}
			
		} // End of For-Loop	
		
		// Return the Integer Comparison
		return returnValue;
		
	} // End of compareTo Method
	
} // End of Word Class


class WordLinkedList {
	// Private Data Fields
	private Node head;
	int size;
	
	public WordLinkedList() {
		head = null;
		size = 0;
	} // End of WordLinkedList Constructor
	
	public int getSize() {
		return size;
	} // End of getSize Getter
	
	public void addInFront (Word wordToAdd) {
		// Declare and Initialize a New Node With Incoming Word
		Node newNode = new Node(wordToAdd);
		// Move the Previous Head to the Next Node
		newNode.next = head;
		// Make the New Node the Current Head
		head = newNode;
		// Increment the Size
		size++;
	} // End of addInFront Method
	
	public int removeNonAbecedarianWords() {
		// Variable to Keep Count of Number of Words Removed
		int removedWords = 0;
		
		// Declare and Initialize Two Nodes That
		// Represent the Current and Previous Locations
		Node previous = null;
		Node current = null;
		while(current != null) {
			// Call the Abecedarian Method on the Current Node
			// If the Current Node is Not an Abecedarian Word
			if(current.word.isAbecedarian() == false) {
				// Add to the Counter for Removed Words
				removedWords++;
				// Now Remove that Node From the List
				// If There is No Node Behind The Current One
				if(previous == null) {
					// Simply Change the Reference to the Head Node
					head = head.next;
					// Decrement the Size
					size--;
				}
				// If There is a Node Behind the Current One
				else {
					// Make the Previous Reference the Next One
					previous.next = current.next;
					// Decrement the Size
					size--;
				}
				// Move to the Next Node
				current = current.next;
			} // End of Outer If-Statement
		} // End of While-Loop
		
		// Return the Final Resutl of How Many Words Were Removed
		return removedWords;
		
	} // End of removeNonAbecedarainWords Method
	
	public Word removeFirstNode() {
		// Create a New Node to Hold the Head Reference
		Node tempNode = head;
		// Move Through the Head so That the Reference Changes
		head = head.next;
		// Decrement the Size
		size--;
		// Print out and Return the Removed Word
		System.out.println("Removed First Element: " + tempNode.word);
		return tempNode.word;
	} // End of removeFirstNode Method
	
	public void bubbleSort() {
		// If the List is Empty, There is Nothing to Be Done
		if (head == null) {
            System.out.println("Nothing to Sort.");
        }
		
		// Initialize a Count Integer and a Head Node Reference
        int count = 0;
        Node beginning = head;
        
        while (beginning != null) {
        	// Iterate and Add to the Count
            count++;
            beginning = beginning.next;
        }
        
        // Walk Through All Nodes of Linked List
        for (int i = 0; i < count; i++) {
        	Node currentNode = head;
            
            while (currentNode != null && currentNode.next != null) {
                // Swap Adjacent Nodes
                if (currentNode.word.compareTo(currentNode.next.word) == 1) {
                	// Use the swapNodeData Method
                    swapNodeData(currentNode, currentNode.next);
                }
                // Iterate Through to the Next
                currentNode = currentNode.next;
            } // End of While-Loop
            
        } // End of For-Loop
		
	} // End of bubbleSort Method
	
	public void swapNodeData (Node node1, Node node2) {		
		// Nothing to Do if Node1 and Node2 Are the Same
        if (node1 == node2) {
            System.out.println("Values are the same.");
        }
        // When Node1 and Node2 Are Different
        else {
        	// Initialize Temporary Node With One Value
        	Word temp = node1.word;
        	// Swap the Two Nodes
        	node1.word = node2.word;
        	node2.word = temp;
        }     
		
	} // End of swapNodeData Method
	
	public void printList() {
		Node tempNode = head;
		while (tempNode != null) {
			// Iterate Through Linked List and Call Print
			// Method to Print in Formatted Order
			tempNode.word.print();
			tempNode = tempNode.next;
		}
		System.out.println("-- End of List --");
	} // End of printList Method
	
	
	private static class Node {
		// Private Data Fiels
		private Word word;
		private Node next;
		
		public Node (Word word) {
			this.word = word;
			next = null;
		} // End of Node Constructor
			
	} // End of Private Node Class
	
	
} // End of WordLinkedList Class


class DoubleLinkedList {
	// Private Data Fields
	private Node head;
	private Node tail;
	
	public DoubleLinkedList() {
		head = null;
		tail = null;
	} // End of DoubleLinkedList Constructor
	
	public void build (WordLinkedList wordList) {
		 int size = wordList.getSize();
		 for (int i = 0; i < size; i++) {
			 // Remove a Node from the Singly Linked List
			 Word removedNode = wordList.removeFirstNode();
			 // Create a New Node for the Doubly Linked List
			 Node newNode = new Node(removedNode);
			 // Add the New Node to the End of the Doubly Linked List
			 if (head == null) {
				 // If the Doubly Linked List is Empty, Set New Node as Head
				 head = tail = newNode; 
			 } 
			 // If the List is Not Empty
			 else {
				 // Add Node to the End
				 tail.next = newNode;
				 // Make the Previous Node Location the Previous Tail Value
				 newNode.previous = tail;
				 // Make the New Tail the New Node
				 tail = newNode;
			 } 
		 } // End of For-Loop
		 
	}  // End of build Method
	
	public void printListBackwards() {
		Node current = tail;
		// Traverse to the last node (the tail)
		// Print the words starting from the tail to the head
		while (current != null) {
			
			current.word.print();
			// Move to the previous node
			current = current.previous;
		}
	} // End of printListBackwards
	
	
	private static class Node {
		// Private Data Fields
		private Word word;
		private Node previous;
		private Node next;
		
		public Node (Word word) {
			this.word = word;
			previous = null;
			next = null;
		} // End of Node Constructor
		
	} // End of Private Node Class
	
	
} // End of DoubleLinkedList Class

