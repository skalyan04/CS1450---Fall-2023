/* Name: Sreya Kalyan
 * Course: 1450 Fall 2023 - Monday/Wednesday (1:40pm - 2:55pm)
 * Due Date: November 29th, 2023 at 1:40pm
 * Assignment Number: 10
 * Programme Description: This programme reads from a file and places information into a binary tree. 
 * The programme then uses methods defined in the Parrot and Binary Tree classes to traverse the list
 * and find and display the information needed. The programme inserts parrots into the binary tree one
 * by one as it compares each of the parrots with the current node parrot's ID and inserts them
 * accordingly. There is also a method to visit all of the nodes in the list by order of level before 
 * moving to the next level and methods to visit the leaves of a node and display the parrot's name.
 */

// Import the Necessary Java Packages
import java.util.LinkedList;
import java.util.Queue;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class KalyanSreyaAssignment10_F23 {

	public static void main(String[] args) throws IOException {
		
		// Open the Files and Create Scanners to Read From Them
		File openParrotsFile = new File ("parrots.txt");
		File openParrotsTestFile = new File ("parrotsTest.txt");
		Scanner parrotsFile = new Scanner(openParrotsFile);
		Scanner parrotsTestFile = new Scanner(openParrotsTestFile);
		
		// Create an Instance of the Binary Search Tree 
		BinaryTree tree = new BinaryTree();
		
		// Read From the File 
		while (parrotsFile.hasNext()) {
			
			int id = parrotsFile.nextInt();
			String name = parrotsFile.next();
			String songPhrase = parrotsFile.next();
			
			// Place the Information in the Binary Search Tree
			Parrot aParrot = new Parrot(id, name, songPhrase);
			tree.insert(aParrot);
			
		} // End of While-Loop
		
		System.out.println("");
		System.out.println("Parrot Song");
		System.out.println("------------------------------");
		tree.levelOrder();
		System.out.println("------------------------------");
		System.out.println("");
		
		System.out.println("");
		System.out.println("Parrots on the Leaf Node of the Tree");
		System.out.println("------------------------------");
		tree.visitLeaves();
		System.out.println("------------------------------");
		System.out.println("");
		
		
		// Close the Files  After Reading
		parrotsFile.close();
		parrotsTestFile.close();

	} // End of Main Method

}


// Represents the Parrot Objects That Will be Placed in the Binary Search Tree
class Parrot implements Comparable<Parrot> {
	// Private Data Fields
	private int id;
	private String name;
	private String songPhrase;
	
	// Parrot Constructor
	public Parrot (int id, String name, String songPhrase) {
		this.id = id;
		this.name = name;
		this.songPhrase = songPhrase;
	}
	
	// Getter to Return the Parrot's Name
	public String getName() {
		return name;
	}
	// Getter to Return the Parrot's Song Phrase
	public String getSongPhrase() {
		return songPhrase;
	}
	
	// Override and Create a CompareTo Method That 
	// Compares Two Parrots Based on Their ID's
	@Override 
	public int compareTo (Parrot otherParrot) {
		if(this.id < otherParrot.id) { 
			return -1;
		}
		else if(this.id == otherParrot.id) {
			return 0;
		}
		else {
			return 1;
		}
		
	} // End of compareTo Method
	
} // End of Parrot Class


// Class That Represents the Binary Search Tree and its Methods
class BinaryTree {
	// Private Data Field(s)
	private TreeNode root;
	
	// Binary Tree Constructor 
	public BinaryTree() {
		root = null;
	}
	
	// Method That Inserts a New Parrot Into the Tree 
	public boolean insert (Parrot parrotToAdd) {
		// If There is Nothing in the Root, Root is the First Node
		if (root == null) {
			// Insert Parrot in the First Node
			root = new TreeNode(parrotToAdd);
		}
		else {
			// Create Reference Nodes to Help Locate Next 
			// Potential Parent Node for Incoming Parrot
			TreeNode parentNode = null;
			TreeNode currentNode = root;
			
			// While You Are Not at the End of the Tree
			while (currentNode != null) {
				
				// If Incoming Parrot ID is Less Than Current Node Parrot's ID
				if (parrotToAdd.compareTo(currentNode.parrot) < 0) {
					parentNode = currentNode;
					// Insert to Left of Current Node
					currentNode = currentNode.left;
				}
				// If Incoming Parrot ID is Greater Than Current Node Parrot's ID
				else if (parrotToAdd.compareTo(currentNode.parrot) > 0) {
					parentNode = currentNode;
					// Insert to Right of Current Node
					currentNode = currentNode.right;
				}
			
			} // End of While-Loop
			
			if (parrotToAdd.compareTo(parentNode.parrot) < 0) {
				parentNode.left = new TreeNode(parrotToAdd);
			}
			else {
				parentNode.right = new TreeNode(parrotToAdd);
			}
			
		} // End of Else-Statement
		
		return true;
		
	} // End of Insert Method
	
	// Method to Visit All of the Nodes on a Level Before Moving to the Next Level
	public void levelOrder() {
		// If There is a Tree With Values
		if (root != null) {
			// Queue to Hold Tree Nodes (Parrots)
			Queue<TreeNode> parrots = new LinkedList<TreeNode>();
			// Add the Root Node to the Queue
			parrots.offer(root);
			
			// While There Are Still Nodes in the Tree 
			while (!parrots.isEmpty()) {
				// Remove Next Node in Queue and Store it in a Temporary Node
				TreeNode tempNode = parrots.remove();
				// Print the Current Node Parrot's Song Phrase
				System.out.println(tempNode.parrot.getSongPhrase() + " ");
				
				// Place the Children of the Current Node Into the Queue
				// and Work Through Those One Just Like the Current Node
				if (tempNode.left != null) {
					parrots.offer(tempNode.left);
				}
				if (tempNode.right != null) {
					parrots.offer(tempNode.right);
				}
				
			} // End of While-Loop
			
		} // End of If-Statement
		
	} // End of levelOrder Method
	
	// Method to Travel Through the Leaves (Left to Right) and Visit the Nodes
	public void visitLeaves() {
		// Public Method That is Calling the Private Method and Sends it the Root
		visitLeaves(root);
	}
	
	// Method That is Called by the Public visitLeaves to Traverse the List
	private void visitLeaves (TreeNode aNode) {
		
		if (aNode != null) {
			// If a Node is a Child Then Display a Child's Name
			if (aNode.left == null && (aNode.right == null)) {
				System.out.println(aNode.parrot.getName());
			}
			else {
				// If The Node is Not a Leaf, Then Go to the Next Left/Right Node
				visitLeaves(aNode.left);
				visitLeaves(aNode.right);
			}
			
		} // End of If-Statement
		
	} // End of Private visitLeaves Method
	
	
	// Private Inner Class That Represents a Node in the Binary Search Tree
	private static class TreeNode {
		// Private Data Fields
		private Parrot parrot;
		private TreeNode left;
		private TreeNode right;
		
		// Tree Node Constructor
		public TreeNode (Parrot parrot) {
			this.parrot = parrot;
			left = null;
			right = null;
		}
		
	} // End of Private Inner TreeNode Class
	
} // End of BinaryTree Class

