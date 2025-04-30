/* Name: Sreya Kalyan
 * Course: 1450 Fall 2023 - Monday/Wednesday (1:40pm - 2:55pm)
 * Due Date: September 13th, 2023 at 1:40pm
 * Assignment Number: 3
 * Programme Description: This programme reads from a file, sorts the items into objects
 * of a specific type of insect class that extends an abstract parent insect class and implements
 * interfaces with abstract methods, and places them in an array. It overrides the abstract 
 * methods in the respective child classes and uses all of the information to manipulate 
 * the Insect array created earlier. This programme uses all of this to print a table of
 * information. It also creates an array list and adds only predators and pollinators to that
 * before printing that out as well. And finally, it manipulates the array to find the most
 * capable insect with the highest numbers in the largest set of skills.
 */

//Import the necessary java packages.
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

// Public Assignment Class
public class KalyanSreyaAssignment3_F23 {

	// Public Main Method
	public static void main(String[] args) throws IOException {
		// Open Text File for Reading and Create Scanner Object to Read.
		File openInsectsFile = new File("Insects.txt");
		Scanner insectFile = new Scanner(openInsectsFile);
		
		// Read First Kine to Log How Many Insects Are in the File.
		int numInsects = insectFile.nextInt();
		
		// Create an Array to Hold the Insect Objects.
		Insect[] insects = new Insect[numInsects];
		
		// Iterate Through the Array and Read in All of the File Information.
		for(int i = 0; i < insects.length; i++) {
			String type = insectFile.next();
			int decomposeAbility = insectFile.nextInt();
			int predatorAbility = insectFile.nextInt();
			int buildAbility = insectFile.nextInt();
			int pollinateAbility = insectFile.nextInt();
			String name = insectFile.nextLine().trim();
			
			// Use Switch Case to Separate Each Object Based on Their 
			// Respective Types Like Honeybee, Ladybug, Ant, PrayingMantis.
			switch (type) {
			case "honeybee" :
				insects[i] = new Honeybee(buildAbility, pollinateAbility, name);
				break;
			case "ladybug" :
				insects[i] = new Ladybug(predatorAbility, pollinateAbility, name);
				break;
			case "ant" :
				insects[i] = new Ant(decomposeAbility, predatorAbility, buildAbility, name);
				break;
			case "prayingmantis" :
				insects[i] = new PrayingMantis(predatorAbility, name);
			} // End of Switch-Case
		
		} // End of For-Loop
		
		System.out.println("List of Insects and All of Their Properties: ");
		System.out.println("---------------------------------------------");
		// Method to Display All Insects and Their Properties
		displayInsects(insects);
		System.out.println("---------------------------------------------");
		System.out.println("");
		
		// Method to Find and Print Only the Predators and Pollinators
		ArrayList<Insect> predatorsAndPollinators = findPredatorsAndPollinators(insects);
		System.out.println("List of Predator and Pollinator Insects and Their Properties: ");
		System.out.println("---------------------------------------------");
		for(int l = 0; l < predatorsAndPollinators.size(); l++) {
			Insect predAndPoll = predatorsAndPollinators.get(l);
			System.out.println(predAndPoll.getName() + " the " + predAndPoll.getType());
			if(predAndPoll instanceof Predator && (predAndPoll instanceof Pollinator)) {
				System.out.println("Pollinate Ability: " + ((Pollinator)predAndPoll).pollinate());
				System.out.println("Predator Ability: " + ((Predator)predAndPoll).predator());
			}
			System.out.println("");
		}
		System.out.println("---------------------------------------------");

		// Method to Find the Most Capable Insect
		// Create an Insect Object and Call the Method so That
		// The Insect Found is Stored in mostCapableInsect.
		Insect mostCapableInsect = findMostCapable(insects);
		System.out.println(mostCapableInsect.getName() + " the " + mostCapableInsect.getType());
		System.out.println("Decompose Ability: " + ((Decomposer)mostCapableInsect).decompose());
		System.out.println("Predator Ability: " + ((Predator)mostCapableInsect).predator());
		System.out.println("Build Ability: " + ((Builder)mostCapableInsect).build());
	
		// Close the Insect File
		insectFile.close();
		
	} // End of Main Assignment Method
	
	// Method to Iterate Through the Array and Display Each Insect
	// as Well as the Type of Insect That it is
	public static void displayInsects (Insect[] insects) {
		// Create For-Loop to Iterate Through the Array
		for(int j = 0; j < insects.length; j++) {
			// Create Variables to Hold Information Gained by
			// Casting to the Specific Interfaces to Get the Abilities
			int decomposeAbility = 0;
			int predatorAbility = 0;
			int buildAbility = 0;
			int pollinateAbility = 0;
			
			Insect insect = insects[j];
			
			// Based on What Insect Type is in a Specific Index,
			// Cast Appropriately and Get the Information on Abilities
			if(insect instanceof Decomposer) {
				decomposeAbility = ((Decomposer)insect).decompose();
			}
			if(insect instanceof Predator) {
				predatorAbility = ((Predator)insect).predator();
			}
			if(insect instanceof Builder) {
				buildAbility = ((Builder)insect).build();
			}
			if(insect instanceof Pollinator) {
				pollinateAbility = ((Pollinator)insect).pollinate();
			}
			
			// Print Information on All Insects in the Array
			System.out.println(insect.getName() + " the " + insect.getType());
			System.out.println(insect.purpose());
			System.out.println("Decompose Ability: " + decomposeAbility);
			System.out.println("Predator Ability: " + predatorAbility);
			System.out.println("Build Ability: " + buildAbility);
			System.out.println("Pollinate Ability: " + pollinateAbility);
			System.out.println("");
			
			
		} // End of For-Loop
		
	} // End of displayInsects Method
	public static ArrayList<Insect> findPredatorsAndPollinators(Insect[] insects) {
		
		// Create an ArrayList to Hold Insects of a Particular Qualification
		ArrayList<Insect> predatorsAndPollinators = new ArrayList<Insect>();
		
		for(int k = 0; k < insects.length; k++) {
			// Iterate Through the Array and Find Insects That Meet the Requirements
			if(insects[k] instanceof Predator &&(insects[k] instanceof Pollinator)) {
				// If These Insects Meet the Requirements, Add to the ArrayList
				predatorsAndPollinators.add(insects[k]);
			}
		}
		
		// Return the ArrayList with Insects
		return predatorsAndPollinators;
		
	} // End of findPredatorsAndPollinators Method
	
	// Method to Iterate Through the Array and Find the Insect
	// That Has the Highest Numbers in the Most Number of Abilities
	public static Insect findMostCapable(Insect[] insects) {
		// Create Variables to Hold the Best Values as Well 
		// as the Other Values to Compare When Iterating
		int bestDecomposeAbility = 0;
		int bestPredatorAbility = 0;
		int bestBuildAbility = 0;
		int decomposeAbility = 0;
		int predatorAbility = 0;
		int buildAbility = 0;
		int mostCapable = 0;
		
		for(int m = 0; m < insects.length; m++) {
			// If-Statement to Check if the Insect is an Instance of the 
			// Interfaces Required to Be the Most Able 
			if(insects[m] instanceof Decomposer && (insects[m] instanceof Predator
					&& (insects[m] instanceof Builder))) {
				// Store the Insect's Abilities in the Ability Variables
				// So it Has Something to Compare With When it Iterates
				decomposeAbility = ((Decomposer)insects[m]).decompose();
				predatorAbility = ((Predator)insects[m]).predator();
				buildAbility = ((Builder)insects[m]).build();
				
				// If the New Iteration Insect's Abilities are Better Than
				// What is Stored in the Best Variables, Update Them.
				if(decomposeAbility > bestDecomposeAbility) {
					bestDecomposeAbility = decomposeAbility;
				}
				if(predatorAbility > bestPredatorAbility) {
					bestPredatorAbility = predatorAbility;
				}
				if(buildAbility > bestBuildAbility){
					bestBuildAbility = buildAbility;
				}
				// Store the Index of the Most Capable Insect 
				mostCapable = m;
			} // End of If-Statement
			
		} // End For-Loop
		
		// Return the Array Index of the Most Capable Insect
		return insects[mostCapable];
	}

} // End of Public Assignment Class

// Decomposer Interface
interface Decomposer {
	public int decompose();
} // End of Decomposer Interface

// Predator Interface
interface Predator {
	public int predator();
} // End of Predator Interface

// Builder Interface
interface Builder {
	public int build();
} // End of Builder Interface

// Pollinator Interface
interface Pollinator {
	public int pollinate();
} // End of Pollinator Interface

// Absract Parent Class Insect
abstract class Insect {
	// Private Data Fields That All Insect Will Have
	private String type;
	private String name;
	
	// Getter for Insect Type
	public String getType() {
		return type;
	}
	// Getter for Insect Name
	public String getName() {
		return name;
	}
	// Setter for Insect Type
	public void setType(String type) {
		this.type = type;
	}
	// Setter for Insect Name
	public void setName(String name) {
		this.name = name;
	}
	// Abstract Method to Return a Statement About the
	// Insect's Purpose Which Will be Overridden in the
	// Child Class Along With the Interface Methods
	public abstract String purpose();
	
} // End of Insect Class

// Child Class for Honeybee
class Honeybee extends Insect implements Builder, Pollinator {
	// Private Data Fields Specific to Honeybee
	private int buildAbility;
	private int pollinateAbility;
	// Constructor Which Calls Type and Name From Parent
	public Honeybee (int buildAbility, int pollinateAbility, String name) {
		setType("Honeybee");
		setName(name);
		this.buildAbility = buildAbility;
		this.pollinateAbility = pollinateAbility;
	}
	
	// Overriding the Abstract Method in Builder Interface
	@Override 
	public int build() {
		return buildAbility;
	}
	// Overriding the Abstract Method in Pollinator Interface
	@Override 
	public int pollinate() {
		return pollinateAbility;
	}
	// Overriding the Purpose Method in the Insect Class
	@Override
	public String purpose() {
		return "I produce honey and pollinate 35% of the crops! Without me, 1/3 of the "
				+ "food you eat would not be available";
	}
	
} // End of Honeybee Class

// Child Class for Ladybug
class Ladybug extends Insect implements Predator, Pollinator {
	// Private Data Fields Specific to Ladybug
	private int predatorAbility;
	private int pollinateAbility;
	// Constructor Which Calls Type and Name From Parent
	public Ladybug (int predatorAbility, int pollinateAbility, String name) {
		setType("Ladybug");
		setName(name);
		this.predatorAbility = predatorAbility;
		this.pollinateAbility = pollinateAbility;
		
	}
	
	// Overriding the Abstract Method in Predator Interface
	@Override
	public int predator() {
		return predatorAbility;
	}
	// Overriding the Abstract Method in Pollinator Interface
	@Override 
	public int pollinate() {
		return pollinateAbility;
	}
	// Overriding the Purpose Method in Insect Class
	@Override
	public String purpose() {
		return "Named after the Virgin Mary, I'm considered good luck if I land on you! I'm "
				+ "a pest control expert eating up to 5,000 plant pests during my life span.";
	}
} // End of Ladybug Class

// Child Class for Ant
class Ant extends Insect implements Decomposer, Predator, Builder { 
	// Private Data Fields Specific to Ant
	private int decomposeAbility;
	private int predatorAbility;
	private int buildAbility;
	// Constructor Which Calls Type and Name From Parent
	public Ant (int decomposeAbility, int predatorAbility, int buildAbility, String name) {
		setType("Ant");
		setName(name);
		this.decomposeAbility = decomposeAbility;
		this.predatorAbility = predatorAbility;
		this.buildAbility = buildAbility;
	}
	
	// Overriding the Abstract Method in Decomposer Interface
	@Override
	public int decompose() {
		return decomposeAbility;
	}
	// Overriding the Abstract Method in Predator Interface
	@Override 
	public int predator() {
		return predatorAbility;
	}
	// Overriding the Abstract Method in Builder Interface
	@Override 
	public int build() {
		return buildAbility;
	}
	// Overriding the Purpose Method in Insect Class
	@Override
	public String purpose() {
		return "Don't squash me, I'm an ecosystem engineer! Me and my 20 million friends "
				+ "accelerate decomposition of dead wood, aerate soil, improve drainage, and "
				+ "eat insects like ticks and termites!";
	}
	
} // End of Ant Class

// Child Class for PrayingMantis
class PrayingMantis extends Insect implements Predator {
	// Private Data Fields Specific to PrayingMantis
	private int predatorAbility;
	// Constructor Which Calls Type and Name From Parent
	public PrayingMantis (int predatorAbility, String name) {
		setType("Praying Mantis");
		setName(name);
		this.predatorAbility = predatorAbility;
	}
	
	// Overriding the Abstract Method in Predator Interface
	@Override
	public int predator() {
		return predatorAbility;
	}
	// Overriding the Purpose Method in Insect Class
	@Override
	public String purpose() {
		return "I'm an extreme predator quick enough to catch a fly. Release me in a "
				+ "garden and I'll eat beetles, grasshoppers, crickets, and even pesky moth";
	}
	
} // End of PrayingMantis Class


