/* Name: Sreya Kalyan
 * Course: 1450 Fall 2023 - Monday/Wednesday (1:40pm - 2:55pm)
 * Due Date: September 27th, 2023 at 1:40pm
 * Assignment Number: 4
 * Programme Description: This programme reads information from a file and stored them in variables. It
 * then iterates until the file has more information and uses them to create individual Player objects which
 * get stored in an array of Player objects, - a team roster. We then manipulate this array to get the 
 * information that we need. We create a Comparable method the Player class to compare two players and sort
 * them based on priority, the smaller rank number being a higher position on the roster. We iterate through 
 * a one dimensional array and print all of the players on the roster as read from the file including spots that 
 * do not contain a player at all. We then create an array list and iterate through this array to move only the
 * players to the array list after which we use the Collections.sort to sort them in order by their ranking. We
 * then print this all of this information in main. This programme also contains two classes aside from the 
 * public assignment class, - a Team class and a Player Class in which we set Getters and perform the Compare
 * To Method as well as the toString Method and the Display Team Method while the array list roster method is
 * in the public assignment class right after the end of the main method. The array list roster method must
 * list the players in highest to lowest order by their roster ranking using the compare to method.
 */


//Import the necessary java packages.
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class KalyanSreyaAssignment4_F23 {

	public static void main(String[] args) throws IOException {
		
		// Open the "Team.txt" File for Reading
		File openTeamFile = new File("Team.txt");
		Scanner teamFile = new Scanner(openTeamFile);
		
		// Read the Team Name and Team Size From the First Two Lines
		String teamName = teamFile.nextLine();
		int teamSize = teamFile.nextInt();
		
		// Create a Team Object That Contains the Team Name and Size
		Team teamRoster = new Team(teamName, teamSize);
		
		// While the Team File Has Another Line, Iterate Through and
		// Read in the Player Rank, Roster Location, and Name by Line
		while(teamFile.hasNext()) {
			
			int playerRanking = teamFile.nextInt();
			int rosterSpot = teamFile.nextInt();
			String playerName = teamFile.nextLine().trim();
			Player player = new Player(teamName, playerRanking, playerName);
			// Add the Newly Created Player Object in a Specified
			// Location in the Team Roster Array using addPlayer Method
			teamRoster.addPlayer(rosterSpot, player);
			
		} // End of While-Loop

		System.out.println("");
		System.out.println("   Team Roster for Wildcats    ");
		System.out.println("-------------------------------");
		System.out.println("Spot     Player Name");
		System.out.println("-------------------------------");
		// Call the displayTeam Method to Iterate Through the Team Roster
		// Array and Print the Index Number of Each Spot on the Roster as
		// Well as a Player Name in Each Spot, if There, or Print a Blank
		// Space if There is No Player in That Array Location
		teamRoster.displayTeam();
		System.out.println("-------------------------------");
		System.out.println("");
		
		
		System.out.println("");
		System.out.println("--------------------------------------------------");
		System.out.println("              Players on the Team                 ");
		System.out.println("       (From Highest to Lowest Ranking)           ");
		System.out.println("--------------------------------------------------");
		// Call the printRosterByRanking Method to Iterate Through the Team Roster
		// Array, Add the Players Alone to an ArrayList and Then Print the Player
		// Roster After it's Been Sorted in Order From Highest to Lowest Ranked Player
		printRosterByRanking(teamRoster);
		System.out.println("--------------------------------------------------");
		
		
		// Close the "Teams.txt" After All Information has Been Read.
		teamFile.close();
		
	} // End of Main Method
	
	
	// Create Method to Create an ArrayList That Holds Players With Their Specific
	// Information Like Roster Ranking. Iterate Through the One-Dimensional Array
	// and Find the Index Values That Hold Players, Add Those Players to the 
	// ArrayList and then use the Collections Sort Method to Sort Them From Highest
	// to Lowest Roster Rankings and Then Iterate Through the ArrayList to Print Them.
	public static void printRosterByRanking (Team roster) {
		// Create an ArrayList to Hold the Ranked Players
		ArrayList<Player> rankedPlayers = new ArrayList<Player>();
		
		// Iterate Through the One-Dimensional Array
		for(int j = 0; j < roster.getTeamSize(); j++) {
			
			// Create a Player Object to Represent an Index Value
			Player getPlayerIndex = roster.getPlayer(j);
			
			// As Long as That Index Value Has a Player in it, Then
			// Add it to the ArrayList Using That Index Number
			if(getPlayerIndex != null) {
				rankedPlayers.add(getPlayerIndex);
			}
			
		} // End of For-Loop
		
		// Sort the ArrayList By Highest to Lowest Roster Ranking
		Collections.sort(rankedPlayers);
		
		// Iterate Through and Print the ArrayList
		for(int k = 0; k < rankedPlayers.size(); k++) {
			System.out.println(rankedPlayers.get(k).toString());
		}
		
	} // End of printRosterByRanking Method

} // End of Public Assignment Class


// Start of Team Class
class Team {
	// Private Data Fields
	private String teamName;
	private int teamSize;
	private Player[] teamRoster;
	
	// Constructor for Team Class
	public Team (String teamName, int teamSize) {
		this.teamName = teamName;
		this.teamSize = teamSize;
		// Use Incoming Values to Allocate Memory for Roster Array 
		teamRoster = new Player[teamSize];
	}
	
	// Getter for Team Name
	public String getTeamName() {
		return teamName;
	}
	// Getter for Team Size
	public int getTeamSize() {
		return teamSize;
	}
	
	// Method to Add Player to a Specific Spot in the Array
	public void addPlayer(int spot, Player player) {
		teamRoster[spot] = player;
	}
	// Public Method to Return the Player in a Specific Location
	public Player getPlayer(int spot) {
		return teamRoster[spot];
	}
	
	
	// Method to Iterate Through and Print All of the
	// Values in the Team Roster Array
	public void displayTeam() {
		for(int i = 0; i < teamSize; i++) {
			Player indexValue = teamRoster[i];
			
			// If There is a Player in an Index, Return the Player's Name
			if(indexValue != null) {
				System.out.println(i + "       " + indexValue.getPlayerName());
			}
			// If There is No Player in an Index, Return a Blank Space
			else {
				System.out.println(i + "        -------");
			}
		}
	} // End of displayTeam Method
	
} // End of Team Class


// Start of Player Class That Implements Comparable Player
class Player implements Comparable<Player>{
	// Private Data Fields
	private String playerTeam;
	private int playerRanking;
	private String playerName;
	
	// Constructor for Player Class
	public Player(String playerTeam, int playerRanking, String playerName) {
		this.playerTeam = playerTeam;
		this.playerRanking = playerRanking;
		this.playerName = playerName;
	}
	// Getter for Team of the Player
	public String getPlayerTeam() {
		return playerTeam;
	}
	// Getter for Ranking of a Player
	public int getPlayerRanking() {
		return playerRanking;
	}
	// Getter for a Player's Name
	public String getPlayerName() {
		return playerName;
	}
	
	
	// Overrides the toString Method in the Object Class and Will Instead
	// Return the Player's Team, Ranking, and Name in a Specific Index Location
	public String toString() {
		return String.format("%s\t%d\t\t%-10s", playerTeam, playerRanking, playerName);
	}
	
	
	// Overrides the Comparable Method in Comparable and Compares to Values
	// and Returns a Value as an Integer Value of 0, 1, or -1
	public int compareTo (Player otherPlayer) {
		// If Player A has a Lower Ranking Than Player B
		if(this.playerRanking < otherPlayer.playerRanking) {
			// They Are of Lower Priority
			return -1;
		}
		// If Player A has a Higher Ranking Than Player B
		else if(this.playerRanking > otherPlayer.playerRanking) {
			// They Are of Higher Priority
			return 1;
		}
		// If Player A and Player B Have the Same Ranking
		else {
			// They Are of Equal Priority
			return 0;
		}
		
		
	} // End of compareTo Method
	
	
	
} // End of Player Class


