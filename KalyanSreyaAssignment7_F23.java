/* Name: Sreya Kalyan
 * Course: 1450 Fall 2023 - Monday/Wednesday (1:40pm - 2:55pm)
 * Due Date: October 25th, 2023 at 1:40pm
 * Assignment Number: 7
 * Programme Description: This programme takes code from a previous assignment and adds to it. There
 * is a new Escape Room object that is created to simulate a game. We create two queues around it, one
 * that is normal and one that is a priority queue. The regular queue that lets players into the game
 * as a first-in-first-out format and the second is a priority queue that ranks players based on scores
 * that they earn within the escape room. When the are all done with the escape room and all of the
 * players have been printed based on score, the game ends when both of the queues are empty. 
 */

//Import the necessary java packages.
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Scanner;

public class KalyanSreyaAssignment7_F23 {

	public static void main(String[] args) throws IOException {
		
		// Open the "Team.txt" File for Reading
		File openTeamFile = new File("Team7.txt");
		Scanner teamFile = new Scanner(openTeamFile);
		
		// Read the Team Name and Team Size From the First Two Lines
		String teamName = teamFile.nextLine();
		int teamSize = teamFile.nextInt();
		
		// Create a Team Object That Contains the Team Name and Size
		Team7 teamRoster = new Team7(teamName, teamSize);
		
		// While the Team File Has Another Line, Iterate Through and
		// Read in the Player Rank, Roster Location, and Name by Line
		while(teamFile.hasNext()) {
			
			int playerRanking = teamFile.nextInt();
			int rosterSpot = teamFile.nextInt();
			String playerName = teamFile.nextLine().trim();
			Player7 player = new Player7(teamName, playerRanking, playerName);
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
		
		
		// NEW
		GameController gameController = new GameController();
		Game gameObj = new Game(null, null, null);
		
		gameController.movePlayersIntoGame(teamRoster, gameObj);
		gameController.simulateGame(gameObj);
		gameController.displayResults(gameObj);
		gameController.isGameOver(gameObj);
		
		// Close the "Teams.txt" After All Information has Been Read.
		teamFile.close();
		
		
	} // End of Main Method
	
	
	// Create Method to Create an ArrayList That Holds Players With Their Specific
	// Information Like Roster Ranking. Iterate Through the One-Dimensional Array
	// and Find the Index Values That Hold Players, Add Those Players to the 
	// ArrayList and then use the Collections Sort Method to Sort Them From Highest
	// to Lowest Roster Rankings and Then Iterate Through the ArrayList to Print Them.
	public static void printRosterByRanking (Team7 roster) {
		// Create an ArrayList to Hold the Ranked Players
		ArrayList<Player7> rankedPlayers = new ArrayList<Player7>();
		
		// Iterate Through the One-Dimensional Array
		for(int j = 0; j < roster.getTeamSize(); j++) {
			
			// Create a Player Object to Represent an Index Value
			Player7 getPlayerIndex = roster.getPlayer(j);
			
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

// NEW

class EscapeRoom {
	
	// Return a hash of the key. Key can be any length.
	// Returns an integer >= 0
	private int hash(String key) {
		int hash = 0;
		for (int i = 0; i < key.length(); i++) {
			hash += key.charAt(i);
			hash += (hash << 10);
			hash ^= (hash >> 6);
		}
		hash += (hash << 3);
		hash ^= (hash >> 11);
		hash += (hash << 15);
		return Math.abs(hash);
	} // hash method
	
	
	public int tryToEscape (String playerName, int playerRanking) {
		
		String key = String.format(playerName + playerName);
		
		int score = hash(key) % (101);
		return score;
	}
	
} // End of Escape Room Class

// NEW
class Game {
	private Queue <Player7> waitingToPlayQ;
	private PriorityQueue<Player7> resultsQ;
	private EscapeRoom escapeRoom;
	
	public Game (Queue<Player7> waitingToPlayQ, PriorityQueue<Player7> resultsQ, EscapeRoom escapeRoom) {
		this.waitingToPlayQ = new LinkedList<Player7>();
		this.resultsQ = new PriorityQueue<Player7>();
		this.escapeRoom = new EscapeRoom();
	}
	

	public boolean isWaitingToPlayQEmpty() {
		return waitingToPlayQ.isEmpty();
	}
	public void addPlayerToWaitingToPlayQ (Player7 player) {
		waitingToPlayQ.offer(player);
	}
	public Player7 removePlayerFromWaitingToPlayQ() {
		return waitingToPlayQ.remove();
	}
	
	public boolean isResultsQEmpty() {
		return resultsQ.isEmpty();
	}
	public void addPlayerToResultsQ (Player7 player) {
		resultsQ.offer(player);
	}
	public Player7 removePlayerFromResultsQ () {
		return resultsQ.remove();
	}
	
	public Player7 peekResultsQ() {
		return resultsQ.peek();
	}
	
	public int tryToEscape (String playerName, int playerRanking) {
		int score = tryToEscape(playerName, playerRanking);
		return score;
	}
	
	
} // End of Game Class

class GameController {
	
	public void movePlayersIntoGame (Team7 team, Game game) {	
		for(int i = 0; i < team.getTeamSize(); i++) {
			
			Player7 newPlayer = team.getPlayer(i);
			game.addPlayerToWaitingToPlayQ(newPlayer);
			
			System.out.println("Moved to Waiting Queue: " + newPlayer.getPlayerName() +
					"in Roster Spot " + newPlayer.getPlayerRanking());
	
		} 
	} // End movePlayersIntoGame Method
	
	public void simulateGame (Game game) {
		while(!game.isWaitingToPlayQEmpty()) {
			Player7 removedPlayer = game.removePlayerFromWaitingToPlayQ();
			game.tryToEscape(removedPlayer.getPlayerName(), removedPlayer.getPlayerRanking());
			game.addPlayerToResultsQ(removedPlayer);
			System.out.println("Player: " + removedPlayer.getPlayerName());
			System.out.println("Score: " + removedPlayer.getScore());
			System.out.println("------------------------------");
			System.out.println("Current Leader: " + game.peekResultsQ());
			System.out.println("Leader's Score: " + game.peekResultsQ().getScore());
		}
		
	} // End simulateGame Method
	
	public void displayResults (Game game) {
		while(!game.isResultsQEmpty()) {
			int j = 1;
			Player7 finalPlayer = game.removePlayerFromResultsQ();
			System.out.println("Player" + j + ": " + finalPlayer.getPlayerName());
			System.out.println("--> Score for Player" + j + ": " + finalPlayer.getScore());
		}
		
	} // End displayResuts Method
	
	public boolean isGameOver(Game game) {
		if(game.isWaitingToPlayQEmpty() && (game.isResultsQEmpty())) {
			return true;
		}
		else {
			return false;
		}
	}
	
}


// Start of Team Class
class Team7 {
	// Private Data Fields
	private String teamName;
	private int teamSize;
	private Player7[] teamRoster;
	
	// Constructor for Team Class
	public Team7 (String teamName, int teamSize) {
		this.teamName = teamName;
		this.teamSize = teamSize;
		// Use Incoming Values to Allocate Memory for Roster Array 
		teamRoster = new Player7[teamSize];
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
	public void addPlayer(int spot, Player7 player) {
		teamRoster[spot] = player;
	}
	// Public Method to Return the Player in a Specific Location
	public Player7 getPlayer(int spot) {
		return teamRoster[spot];
	}
	
	
	// Method to Iterate Through and Print All of the
	// Values in the Team Roster Array
	public void displayTeam() {
		for(int i = 0; i < teamSize; i++) {
			Player7 indexValue = teamRoster[i];
			
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
class Player7 implements Comparable<Player7>{
	// Private Data Fields
	private String playerTeam;
	private int playerRanking;
	private String playerName;
	// NEW
	private int score;
	
	// Constructor for Player Class
	public Player7(String playerTeam, int playerRanking, String playerName) {
		this.playerTeam = playerTeam;
		this.playerRanking = playerRanking;
		this.playerName = playerName;
		// NEW
		this.score = 0;
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
	
	// NEW
	public int getScore() {
		return score;
	}
	public void setScore() {
		this.score = score;
	}
	
	
	// Overrides the toString Method in the Object Class and Will Instead
	// Return the Player's Team, Ranking, and Name in a Specific Index Location
	public String toString() {
		return String.format("%s\t%d\t\t%-10s", playerTeam, playerRanking, playerName);
	}
	
	
	// Overrides the Comparable Method in Comparable and Compares to Values
	// and Returns a Value as an Integer Value of 0, 1, or -1
	public int compareTo (Player7 otherPlayer) {
		// If Player A has a Lower Ranking Than Player B
		if(this.score < otherPlayer.score) {
			// They Are of Lower Priority
			return -1;
		}
		// If Player A has a Higher Ranking Than Player B
		else if(this.score > otherPlayer.score) {
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


