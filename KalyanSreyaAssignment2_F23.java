/* Name: Sreya Kalyan
 * Course: 1450 Fall 2023 - Monday/Wednesday (1:40pm - 2:55pm)
 * Due Date: September 6th, 2023 at 1:40pm
 * Assignment Number: 2
 * Programme Description: This programme creates an array which is filled with shark objects
 * read in from a text file. The sharks are then split into different types with subclasses.
 * The programme also creates an aquarium class and array within which you iterate through
 * the shark objects and find instances of tiger and zebra sharks which are then added to the
 * aquarium array. The subclasses of the shark class also override their parent class's physical
 * description string with their own specific sentence which is called when printing out all
 * of the information to the console.
 */

//Import the necessary java packages.
import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class KalyanSreyaAssignment2_F23 {

	public static void main(String[] args) throws IOException {
		
		// Open the Sharks.txt test file for reading.
		File openSharkFile = new File("Sharks.txt");
		// Create a Scanner object to read through the text file.
		Scanner input = new Scanner(openSharkFile);
		// Read in the first line of the file to get the number of 
		// sharks being stored in the text file without hard-coding.
		int numSharks = input.nextInt();
		
		// Create a regular array to hold all the sharks coming in
		// from the text file in their own indexes.
		Shark[] sharks = new Shark[numSharks];
		
		// Create a for loop to iterate until you reach the number
		// of sharks being read in from the text file using the 
		// numSharks variable used to read the first line from earlier.
		for(int i = 0; i < numSharks; i++) {
			// Read in the type, age, and name of the sharks.
			String type = input.next();
			int age = input.nextInt();
			String name = input.nextLine().trim();
			
			// Use the switch case to check what type of shark
			// each object is and create the correct type to store.
			switch (type) {
			case "greatwhite": 
				sharks[i] = new GreatWhite(age, name);
				break;
			case "hammerhead":
				sharks[i] = new HammerHead(age, name);
				break;
			case "tiger":
				sharks[i] = new Tiger(age, name);
				break;
			case "zebra":
				sharks[i] = new Zebra(age, name);
			} // End of switch case.
			
			
		} // End of for-loop
		System.out.println("");
		
		System.out.println("Sharks Sorted By Type, Age, Name, and Physical Description: ");
		System.out.println("------------------------------------------------------------");
		// Create a for-loop to iterate through the shark array and print all
		// information like type, age, name, and physical description using
		// getters and the overridden physical description string in each subclass.
		for(int j = 0; j < sharks.length; j++) { 
			System.out.printf("%-15s", sharks[j].getType());
			System.out.printf("%-10s", sharks[j].getAge());
			System.out.printf("%-15s", sharks[j].getName());
			System.out.printf("%-10s", sharks[j].physicalDescription());
			System.out.println("");
		} // End of for-loop
		System.out.println("------------------------------------------------------------");
		System.out.println("");
		
		// Create an aquariumSharks object in main and then fill
		// the aquarium with tiger and zebra sharks from the sharks
		// array. Finally, print all of the aquarium details.
		Aquarium aquariumSharks = new Aquarium();
		aquariumSharks.fillAquarium(sharks);
		aquariumSharks.printAquariumDetails();
		
		// Close the file we just read from.
		input.close();

	} // End of main assignment class.

} // End of public assignment class.

// A parent class for all shark objects.
class Shark {
	// Private data fields for the type, age, and name.
	private String type;
	private int age;
	private String name;
	
	// Public constructor to create shark objects using incoming
	// values from the file like type, age, name.
	public Shark(String type, int age, String name) {
		this.type = type;
		this.age = age;
		this.name = name;
	}
	
	// Getters to access to private data fields.
	public String getType() {
		return type;
	}
	public int getAge() {
		return age;
	}
	public String getName() {
		return name;
	}
	
	// String holding physical description that will 
	// be overridden accordingly in the sub-classes.
	public String physicalDescription() {
		return "Physical description of each of the sharks.";
	}
} // End of Shark class

class GreatWhite extends Shark {
	// Create constructor for shark object with string of shark
	// type, age, and name values to send directly to super-class.
	public GreatWhite(int age, String name) {
		super("GreatWhite", age, name);
	}
	
	// Override the physical description string of the shark 
	// from the parent class accordingly with the specific description
	// for the type of shark in this sub-class.
	@Override
	public String physicalDescription() {
		return "Torpedo-shaped body with a white-coloured underside.";
	}
	
} // End of GreatWhite class

class HammerHead extends Shark {
	// Create constructor for shark object with string of shark
	// type, age, and name values to send directly to super-class.
	public HammerHead(int age, String name) {
		super("HammerHead", age, name);
	}
	
	// Override the physical description string of the shark 
	// from the parent class accordingly with the specific description
	// for the type of shark in this sub-class.
	@Override
	public String physicalDescription() {
		return "Flattened head that laterally extends into a hammer shape.";
	}
	
} // End of HammerHead class

class Tiger extends Shark {
	// Create constructor for shark object with string of shark
	// type, age, and name values to send directly to super-class.
	public Tiger(int age, String name) {
		super("Tiger", age, name);
	}
	
	// Override the physical description string of the shark 
	// from the parent class accordingly with the specific description
	// for the type of shark in this sub-class.
	@Override
	public String physicalDescription() {
		return "Tiger like stripes that fade as the the shark matures.";
	}
	
} // End of Tiger class

class Zebra extends Shark {
	// Create constructor for shark object with string of shark
	// type, age, and name values to send directly to super-class.
	public Zebra(int age, String name) {
		super("Zebra", age, name);
	}
	
	// Override the physical description string of the shark 
	// from the parent class accordingly with the specific description
	// for the type of shark in this sub-class.
	@Override
	public String physicalDescription() {
		return "Yellowish stripes on dark body that change to dark spots.";
	}
	
} // End of Zebra class

class Aquarium {
	// Private data fields to hold the number of tiger
	// and zebra sharks that will go in the aquarium.
	private int numTigerSharks;
	private int numZebraSharks;
	// Array of shark objects that will be in the aquarium.
	private Shark[] aquariumSharks;
	
	// Method to fill the aquarium with sharks by iterating
	// through the shark array and finding shark objects that
	// are an instance of either the Tiger or Zebra subclasses.
	// If they are instances, add to the tiger and zebra shark
	// total counters we created at the top of the Aquarium class.
	public void fillAquarium(Shark[] sharks) {
		for(int i = 0; i < sharks.length; i++) {
			if(sharks[i] instanceof Tiger) {
				numTigerSharks++;
			} 
			else if(sharks[i] instanceof Zebra) {
				numZebraSharks++;
			}			
		} // End of for-loop
		
		// Set the size of the array to hold aquarium sharks as the total
		// number of tiger and zebra sharks we counted earlier. This avoids
		// any hard-coding in the instance that the number of a specific
		// type of shark were to change at any point in the text file.
		aquariumSharks = new Shark[numTigerSharks + numZebraSharks];
		
		// Iterate through the shark array and aquarium index side by side and 
		// find  all instances of tiger and zebra sharks inside of it.
		int aquariumIndex = 0;
		for(int x = 0; x < sharks.length; x++) {
			// If the shark in an index is an instance of the Tiger or Zebra
			// subclass, then insert them in the specific index of the
			// aquarium array and iterate the index value.
			if(sharks[x]  instanceof Tiger || (sharks[x] instanceof Zebra)) {
				aquariumSharks[aquariumIndex] = sharks[x];
				aquariumIndex++;
			} // End of if-statement
		} // End of for-loop
		
	} // End of fillAquarium method
	
	public void printAquariumDetails() {
		System.out.println("Shark Aquarium");
		System.out.println("--------------");
		System.out.println("Number of Tiger Sharks in the Aquarium: " + numTigerSharks);
		System.out.println("Number of Zebra Sharks in the Aquarium: " + numZebraSharks);
		for(int k = 0; k < aquariumSharks.length; k++) {
			System.out.println(aquariumSharks[k].getType() + ": " + aquariumSharks[k].getName());
		}
		System.out.println("--------------");
	} // End of printAquariumDetails method
	
} // End of Aquarium class
