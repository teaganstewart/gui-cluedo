/*This code was generated using the UMPLE 1.29.1.4581.2aeb7b5a5 modeling language!*/
package swen225;

import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import javax.swing.*;

import gui.Controller;

/**
 * Stores the actual mechanics of the game, and runs the game.
 * 
 * @author Teagan Stewart, Ethan Munn
 */
public class Game
{

	//------------------------
	// MEMBER VARIABLES
	//------------------------

	//Game Attributes
	private Tuple solution;
	private Tuple suggestion;

	//Game Associations
	private Board board;
	private List<Player> players;
	private List<Room> rooms;
	private List<Weapon> weapons;
	private List<Card> deck;

	private Queue<Player> order;
	private int activePlayers = 0;
	private int currentPlayer = 1;
	private int dieRoll = 0;
	private int movesLeft = 0;
	
	private boolean pickRandom = true;
	public boolean gameWon;
	
	private int playerHasCard;
	private Card cardPlayerHas;
	
	private Die die1 = new Die(), die2 = new Die();
	
	//------------------------
	// CONSTRUCTOR
	//------------------------

	public Game()
	{
	}

	//------------------------
	// INTERFACE
	//------------------------

	public void togglePickRandom() {
		pickRandom = !pickRandom;
	}
	
	/**
	 * Whether or not card selection is random
	 * @return
	 */
	public boolean pickingRandom() {
		return pickRandom;
	}
	
	public boolean setBoard(Board aBoard)
	{
		boolean wasSet = false;
		board = aBoard;
		wasSet = true;
		return wasSet;
	}
	
	public Board getBoard()
	{
		return board;
	}
	
	public Tuple getSolution()
	{
		return solution;
	}

	/**
	 * Sets the solution (for testing)
	 * @param aSolution
	 * @return
	 */
	public boolean setSolution(Tuple aSolution)
	{
		boolean wasSet = false;
		solution = aSolution;
		wasSet = true;
		return wasSet;
	}
	
	/**
	 * 
	 * @return Returns the list of players.
	 */
	public List<Player> getPlayers()
	{
		List<Player> newPlayers = Collections.unmodifiableList(players);
		return newPlayers;
	}

	/**
	 * 
	 * @return Returns the size of the players list.
	 */
	public int numberOfPlayers()
	{
		int number = players.size();
		return number;
	}
	
	/**
	 * 
	 * @return Returns the list of players.
	 */
	public List<Room> getRooms()
	{
		List<Room> newRooms = Collections.unmodifiableList(rooms);
		return newRooms;
	}

	/**
	 * 
	 * @return Returns the list of weapons.
	 */
	public List<Weapon> getWeapons()
	{
		List<Weapon> newWeapons = Collections.unmodifiableList(weapons);
		return newWeapons;
	}
	
	/**
	 * 
	 * @return Returns the list of players.
	 */
	public List<Card> getDeck()
	{
		return deck;
	}
	
	/**
	 * Sets the order (for testing)
	 * @param players
	 * 		The list of players
	 */
	public void setOrder(List<Player> players) {
		int numeric = 1;
		for (Player p : players) {
			order.offer(p);
			p.setNumeric(numeric++);
			p.setInPlay(true);
		}
	}
	
	/**
	 * 
	 * @return Returns the list of players in the order of play.
	 */
	public Queue<Player> getOrder()
	{
		return order;
	}
	
	public void addToOrder(Player p) {
		order.offer(p);
	}
	
	public int getActivePlayers() {
		return activePlayers;
	}
	
	public void setActivePlayers(int active) {
		activePlayers = active;
	}
	
	public boolean getGameWon() {	
		return gameWon;
	}
	
	public int getPlayerHasCard() {
		return playerHasCard;
	}
	
	public void setPlayerHasCard(int player) {
		playerHasCard = player;
	}
	
	public Card getCardPlayerHas() {
		return cardPlayerHas;
	}
	
	public void setCardPlayerHas(Card card) {
		cardPlayerHas = card;
	}
	
	public int getCurrentPlayer() {
		return currentPlayer;
	}

	public Die getDie1() {
		return die1;
	}
	
	public Die getDie2() {
		return die2;
	}
	
	public int getDieRoll() {
		return dieRoll;
	}

	public void setDieRoll(int dieRoll) {
		this.dieRoll = dieRoll;
	}

	public int getMovesLeft() {
		return movesLeft;
	}

	public void setMovesLeft(int movesLeft) {
		this.movesLeft = movesLeft;
	}
	
	/**
	 * A simple while loop that prompts the user to START or EXIT the game
	 */
	private void askToStart() {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String command = "";
		while (!command.equalsIgnoreCase("START")) {
			System.out.println("Type START to begin, or EXIT to quit!");
			try {
				command = input.readLine();
			} catch (IOException e) { }
			
			if (command.equalsIgnoreCase("EXIT")) System.exit(0);
		}
	}
	
	/**
	 *  The main loop for the game, continues as long as there are players left or the game has been won. Otherwise resets the game.
	 */
	private void playScenario() {
		// How commands are read by the program, and saved into the command string
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String command = "";
		
		// The values used to determine how many steps a player has each turn before their turn is over
		int dieRoll = roll();
		movesLeft = dieRoll;
		
		Player player = order.peek();
		
		// resets the x and y positions the player has stepped on this turn, and adds the current x/y to this list
		player.resetXYPositions(dieRoll+1);
		player.setXYPosition(player.getX(), player.getY());
		
		// prints out the board
		printBoard();
		System.out.println("============================================================================");
	
		while (activePlayers>0) {
	
			player = order.peek();
			
			if (player.getRoom() != null && movesLeft>0) {
				boolean canLeave = exitRoom(player);
				
				if (canLeave) {
					// as one move is already taken away by exiting the room
					player.setXYPosition(player.getX(), player.getY());	
					movesLeft--;
				} else {
					
					System.out.println("All exit doors of " + player.getRoom() + " are blocked! " +
										"Player " + getCurrentPlayer() + " (" + player.toString() + ") is trapped inside for a turn!\n");
					
					// asks if they want to accuse
					askToAccuse();
					
					// resets the turn
					dieRoll = newTurn();
					movesLeft = dieRoll;
					continue;
					
				}
	
			}			
	
			System.out.println("============================================================================\n");
	
			printPlayerStatus(player, dieRoll, movesLeft);
	
			// asks for a command
			try {
				command = input.readLine().toLowerCase();
			} catch (IOException e) {}
	
			// --------------------- //
			// COMMANDS FOR MOVEMENT //
			// --------------------- //
			
			if (command.startsWith("left ") || command.startsWith("right ") || command.startsWith("up ") || command.startsWith("down ")) {
				Move move = parseMove(command);
	
				if (move != null) {
					int movesMade = performMove(move, movesLeft, player);
					System.out.println("Player " + getCurrentPlayer() + " moves " + move.getDir() + " " + movesMade + " step" + (movesMade != 1 ? "s" : ""));
	
					movesLeft -= movesMade;
					printBoard();
				}
	
			}
			
			// ---------------------------------- //
			// COMMANDS FOR SUGGESTION/ACCUSATION //
			// ---------------------------------- //
			
			// SUGGEST
			else if(command.startsWith("suggest")) System.out.println("You must be in a room to make a suggestion! \n");
			// ACCUSE
			else if(command.startsWith("accuse")) {
				
				suggestion = parseSuggestion(command,true);
				if(suggestion==null) askSuggestion();
				else checkSuggestion(suggestion,true);
	
			}
	
			// ----------------------------------------- //
			// COMMANDS THAT DON'T SKIP THE PLAYERS TURN //
			// ----------------------------------------- //
			
			// HELP
			else if (command.equals("help")) printHelp();
			// BOARD
			else if (command.equals("board")) printBoard();
			// PARSE CODES
			else if(command.startsWith("codes")) parseCodes(command);
			// TOGGLE LABELS
			else if (command.equals("toggle labels")) {
				board.toggleLabels();
				printBoard();
			}
			else if (command.equals("toggle players")) {
				board.togglePlayers();
				printBoard();
			}
			// RESET
			else if (command.equals("reset")) break;
			// QUIT
			else if (command.equals("exit")) System.exit(0);
	
			// Resets the command typed
			command = "";
			if (player.getRoom() != null && player.isInPlay()) {
	
				// SUGGESTION CODE
				System.out.println("Player " + getCurrentPlayer() + " (" + player.toString() + ") " + " is in room " + player.getRoom());
	
				System.out.println("Room contents: " +
				"\nWeapons: " + player.getRoom().getWeapons() +
				"\nPlayers: " + player.getRoom().getPlayers() + "\n");
		
					askSuggestion();
					movesLeft = 0;
	
			}
			
			if (movesLeft <= 0 || playerTrapped(player, movesLeft) || !player.isInPlay()) {
				
				System.out.println("============================================================================");
				
				// changes the players turn to the next person
				dieRoll = newTurn();
				movesLeft = dieRoll;
				
			}
	
		}
		
	
		System.out.println("\n============================================================================\n" +
						   "============================================================================\n");
	
	}
	
	/**
	 * A simple yes/no on whether the player would like to accuse this turn
	 * Is called if the player traps themselves or if they are trapped in a room
	 */
	private void askToAccuse() {
		
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String choice = "";
		
		while(!(choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("no"))) {
			try {
				System.out.println("Would you like to make an accusation before your turn ends, Player " + getCurrentPlayer() + "? Yes or No.");
				choice = input.readLine().toLowerCase();
			} catch (IOException e) {}
		}
		
		if (choice.equals("yes")) {
			trappedAccuse();
			printBoard();
		}
		
	}
	
	/**
	 * A separate method for dealing with accusing after being trapped
	 * Is in a separate method so the player can still back out of this
	 */
	private void trappedAccuse() {

		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String command = "";
		boolean valid = false;
		
		while (!valid) {
			
			try {
				System.out.println("Make your accusation now (or type \"back\" to return)");
				command = input.readLine().toLowerCase();
			} catch (IOException e) {}
			
			if (command.equals("back")) return;
			else if (command.startsWith("accuse ")) {
				suggestion = parseSuggestion(command,true);
				checkSuggestion(suggestion,true);
				valid = true;
			}
		}
		
	}

	/**
	 * Does all of the checks to make sure the next player in the order is in play,
	 * and sets up their x and y positions
	 * @return
	 * 		the random die roll the player got on this turn
	 */
	public int newTurn() {
		
		// uses a do while that way nextPlayer is called at least once
		do {
			nextPlayer();
		} while (!order.peek().isInPlay());
		
		int dieRoll = roll();
		
		// uses the above die roll to recreate the arrays of player x and y positions
		// + 1 to include the tile they are on, as they shouldn't be able to go forward
		// and back onto their starting position
		Player temp = order.peek();
		temp.resetXYPositions(dieRoll+1);
		temp.setXYPosition(temp.getX(), temp.getY());
		
		return dieRoll;
		
	}
	
	/**
	 * Prints out instructional help for the player to read
	 */
	private void printHelp() {
		System.out.println("------------------\n" +
				"Welcome to Cluedo! Move your players around the estate and crack the case\n" +
				"on whodunnit! You can use the commands below to control a character on your\n" +
				"turn, by moving your player across the board and into rooms to make suggestions.\n" +
				"You can also accuse players of committing the crime but be careful! If you\n" +
				"accuse incorrectly, you are out of the game!\n\n" +
				"VALID COMMANDS:\n" +
				"help: Brings this menu up again\n" +
				"board: Prints out the board again\n" +
				"toggle labels: Toggles the labels on the rooms on the board\n" +
				"toggle players: Toggles the labels on the players on the board\n" +
				"reset: Restarts the game\n" +
				"exit: Exits the game\n\n"+
				"codes [type]: Used to get the codes for the rooms, players or weapons\n"+
				"[type] is ether  \"room\", \"player\" or \"weapon\" \n \n" +
				"[dir] [steps]: Moves your player that many steps in that direction\n" +
				"[dir] is either \"left\", \"right\", \"up\" or \"down\"\n" +
				"[steps] is any valid number given the die roll.\n\n" +
				"suggest [person] [weapon] [room]: Must be in said room to suggest.\n" +
				"accuse [person] [weapon] [room]: Accuse a person, if wrong player loses. \n" +
				"[person] is the initals or whole name of the person\n" +
				"[weapon] is the initals or whole name of the weapon\n" +
				"[room] is the initals or whole name of the room\n" +
				"------------------\n");

	}
	
	/**
	 * Decides whether or not the card selection upon suggesting a player is random
	 */
	private void cardMode() {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String choice = "";
		
		System.out.println("--------------");
		while(!(choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("no"))) {
			try {
				System.out.println("Would you like to be able to choose what card to show upon suggestion? If no it's random. Yes or No.");
				choice = input.readLine().toLowerCase();
			} catch (IOException e) {}
		}
		pickRandom = (choice.equals("no"));
	}
	
	/**
	 * Returns whether or not the string entered can be parsed as a valid number
	 * @param command
	 * 		A command to be parsed eg 2
	 * @return
	 * 		Whether or not the String parsed is a valid number
	 */
	public boolean parseNumber(String command) {
		try {
			Integer.parseInt(command);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * For each of the different code displays, interprets and parses the command sent over and determines
	 * which code list to print out, if any.
	 * @param command
	 * 		The input sent over
	 */
	public boolean parseCodes(String command) {
		// PLAYER CODES
		if(command.substring(6,Math.min(12, command.length())).equalsIgnoreCase("player")) printPlayerCodes();
		// WEAPON CODES
		else if(command.substring(6,Math.min(12, command.length())).equalsIgnoreCase("weapon")) printWeaponCodes();
		// ROOM CODES
		else if(command.substring(6,Math.min(10, command.length())).equalsIgnoreCase("room")) printRoomCodes();
		else {return false;}
		return true;
	}
	
	/**
	 * Prints the list of player codes.
	 */
	private void printPlayerCodes() {
		System.out.println("--------------\nPlayer codes:");
		for (Player p : players) {
			System.out.println(p.getInitial() + " = " + p.toString());
		}
		System.out.println("--------------");
	}

	/**
	 * Prints the list of weapon codes.
	 */
	private void printWeaponCodes() {
		System.out.println("--------------\nWeapon codes:");
		for (Weapon w : weapons) {
			System.out.println(w.getInitial() + " = " + w.toString());
		}
		System.out.println("--------------");

	}

	/**
	 * Prints the list of room codes.
	 */
	private void printRoomCodes() {
		System.out.println("--------------\nRoom codes:");
		for (Room r : rooms) {
			System.out.println(r.getInitial() + " = " + r.toString());
		}
		System.out.println("--------------");

	}

	/**
	 * @param command
	 * 		A command to be parsed eg left 2
	 * @return 
	 * 		Returns a Move object containing a Direction and step count, or null if the parse failed
	 */
	public Move parseMove(String command) {
		int steps = 0;
		Direction dir = null;
		if (command.startsWith("left ") && command.length() > 5) {
			command = command.substring(5);
			dir = Direction.LEFT;
		} else if (command.startsWith("right ") && command.length() > 6) {
			command = command.substring(6);
			dir = Direction.RIGHT;
		} else if (command.startsWith("up ") && command.length() > 3) {
			command = command.substring(3);
			dir = Direction.UP;
		} else if (command.startsWith("down ") && command.length() > 5) {
			command = command.substring(5);
			dir = Direction.DOWN;
		}

		// returns null, the equivalent of returning a false so the current player definitely remains the same
		if (!parseNumber(command)) { 
			System.out.println("Invalid number of steps... " + command);
			return null;
		}

		steps = Integer.parseInt(command); 
		return new Move(steps, dir);

	}

	/**
	 * Performs the move on a player passed over to it. Returns the amount of moves actually performed by the method
	 * @param move
	 * 		The move object containing the steps taken and the direction the steps are taken in
	 * @param movesLeft
	 * 		The amount of moves left from their dice roll
	 * @param player
	 * 		The player being moved
	 * @return
	 * 		The amount of steps the player was actually able to take via the command given
	 */
	public int performMove(Move move, int movesLeft, Player player) {
		Direction dir = move.getDir();
		int x = player.getX();
		int y = player.getY();

		for (int stepsTaken = 0; stepsTaken < move.getMoves(); stepsTaken++) {
			
			// If/Else case to set the x/y to its new position
			x = changeX(x,dir);
			y = changeY(y,dir);
			
			Tile nextTile = board.getTile(y,x);

			// If/Else case to understand what to do depending on the board tile
			
			// if the type isn't safe to stand on, the player has already been here, or it is the players last turn and they can't stand on another player
			if (typeIsntSafe(nextTile) || player.checkXYPosition(x, y) || playerOccupied(x,y)) {
				return stepsTaken;
			} else if (nextTile.getType() == Tile.TileType.DOOR) {
				// This is safe as we know it's a door
				DoorTile door = (DoorTile) nextTile;
				// makes sure the room wasn't the last room entered, and that the door entry direction is correct
				if (door.getDoorTo() != player.getRoomLastIn() && door.getEntryDir() == dir) {
					door.getDoorTo().addPlayer(player);
					player.setRoom(door.getDoorTo());
					player.setX(x);
					player.setY(y);

					// it doesn't matter how many moves they have left, their turn is over
					return stepsTaken + 1;			
				} else {
					return stepsTaken;
				}
			} else {
				player.setX(x);
				player.setY(y);
				player.setXYPosition(x, y);
			}

			// performs moves in while loop, decrementing movesLeft by 1 each time
			movesLeft--;
			if (movesLeft == 0 || stepsTaken == move.getMoves()-1) {
				return stepsTaken + 1;
			}
		}
		return 0;
	}

	/**
	 * A method that forces this player to exit the room they are currently in
	 * This method is only ever called if the player is inside a room to begin with
	 * 
	 * If it were required, this would also be the method that would handle the 
	 * secret passageway shortcuts between rooms
	 * 
	 * @param player
	 * 		The player that is currently being requested to leave a room
	 */
	public boolean exitRoom(Player player) {

		// index of the room the player is exiting
		int index = 1;
		Room roomIn = player.getRoom();

		// A status message to alert the player that they're not in the hallway
		System.out.println("\nPlayer " + getCurrentPlayer() + " (" + player.toString() + ") " + " is in room " + roomIn + "...");
		
		// checks if doors are free
		int freeDoors = 0;
		for (DoorTile door : roomIn.getDoors()) {
			freeDoors += doorIsFree(door);
		}
		if (freeDoors == 0) return false;

		// if there is more than one door, the player needs to choose the door they wish to leave
		if (roomIn.numberOfDoors() > 1) {

			int chosen = 0;

			// stays trapped in this loop while the door chosen is too high or too low
			while (chosen < 1 || chosen > roomIn.numberOfDoors()) {
				// Input variables
				BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
				String command = "";

				// prints a layout of the board
				printBoard();
				System.out.println("From left to right, please select the door in room " + roomIn +
						" that you would like to exit, Player " + getCurrentPlayer() +
						" (between 1 - " + roomIn.numberOfDoors() + ")");

				try { command = input.readLine();
				} catch (IOException e) {}

				// if the value isn't a number, just repeats the loop
				if (!parseNumber(command)) {
					System.out.println("Input not a number...");
					continue;
				}

				// determines whether or not this number is valid, now that we know the number is safe to use
				chosen = Integer.parseInt(command);
				if (chosen < 1) System.out.println("Door number too low...");
				else if (chosen > roomIn.numberOfDoors()) System.out.println("Door number too high...");
				
				// determines whether this door is actually free
				if (doorIsFree(roomIn.getDoorTile(chosen-1)) == 0) {
					System.out.println("That door is blocked by another player! Please select a different door");
					chosen = 0;
				}
				
			}
			index = chosen;

		}

		// grabs the door and the inverse direction the player needs to move to exit from that door
		DoorTile door = roomIn.getDoorTile(index-1);
		Direction inverse = door.getEntryDir().inverse();

		// sets the players location to where the door is located
		player.setX(door.getCol());
		player.setY(door.getRow());
		player.setRoom(null);
		player.setRoomLastIn(roomIn);
		roomIn.removePlayer(player);
		
		// just uses the perform move method to shift the player
		performMove(new Move(1,inverse),1,player);

		printBoard();
		System.out.println("Player " + getCurrentPlayer() + " (" + player.toString() + ") " + " has exited via Door " + index + "!");
		
		return true;
	}
	
	/**
	 * Checks whether or not the door has a free tile in front of it
	 * @param door
	 * 		the door tile
	 * @return
	 * 		1 if free, 0 if not
	 */
	public int doorIsFree(DoorTile door) {

		Direction dir = door.getEntryDir().inverse();
		int x = changeX(door.getCol(),dir);
		int y = changeY(door.getRow(),dir);
		
		if (!playerOccupied(x,y)) return 1;
		else return 0;
	}
	
	/**
	 * Checks to see if the player has trapped themselves in all four directions, by either tiles
	 * that aren't hallway tiles or tiles that the player has already stepped on
	 * @param player
	 * 		the current player
	 * @param movesLeft
	 * 		the amount of movesLeft of the turn
	 * @return
	 * 		whether or not the player is trapped
	 */
	public boolean playerTrapped(Player player, int movesLeft) {
		
		// gets the x and y values for usage below
		int x = player.getX();
		int y = player.getY();
		
		// Uses ternaries to grab all of the tiles around the player
		// If the player has been on this tile before or if it's the last move and a player is occupying the tile, sets it to null,
		// else, it grabs whatever tile is there instead
		Tile up = getTileInGame(player, x, y-1, movesLeft);
		Tile down = getTileInGame(player, x, y+1, movesLeft);
		Tile left = getTileInGame(player, x-1, y, movesLeft);
		Tile right = getTileInGame(player, x+1, y, movesLeft);
		
		// does a null check first before confirming its type, as to not throw NullPointerExceptions
		if ((up == null || !typeIsSafe(up, Direction.UP, player.getRoomLastIn()))
		    && (down == null || !typeIsSafe(down, Direction.DOWN, player.getRoomLastIn()))
		    && (left == null || !typeIsSafe(left, Direction.LEFT, player.getRoomLastIn()))
		    && (right == null || !typeIsSafe(right, Direction.RIGHT, player.getRoomLastIn()))) {
			System.out.println("Player " + getCurrentPlayer() + " (" + player.toString() + ") " + " has trapped themselves! Their turn ends early.\n");
			return true;
		}
		return false;
		
	}
	
	/**
	 * Grabs the tile on the board in relation to the current game being played.
	 * @param player
	 * 		The current player
	 * @param x
	 * 		The x pos sent over
	 * @param y
	 * 		The y pos sent over
	 * @param movesLeft
	 * 		how many moves the player has left
	 * @return
	 * 		null or the tile at this position
	 */
	private Tile getTileInGame(Player player, int x, int y, int movesLeft) {
		if (player == null || board == null) return null;
		return player.checkXYPosition(x, y) || playerOccupied(x,y) ? null : board.getTile(y, x);
	}
	
	/**
	 * Checks to confirm if the type of tile can be moved on or not
	 * @param tile
	 * 		the tile object passed over
	 * @param dir
	 * 		the direction this tile is in
	 * @param last
	 * 		the room the player was in last
	 * @return
	 * 		whether the player can stand on the tile, or not
	 */
	private boolean typeIsSafe(Tile tile, Direction dir, Room last) {
		if (tile.getType() == Tile.TileType.DOOR) {
			DoorTile door = (DoorTile) tile;
			return door.getEntryDir() == dir && door.getDoorTo() != last;
		}
		return tile.getType() == Tile.TileType.HALLWAY;
	}
	
	/**
	 * Inverse of the method above, but exclusively checks tile types only
	 * @param tile
	 * 		the tile object passed over
	 * @return
	 * 		whether the player can stand on the tile, or not
	 */
	private boolean typeIsntSafe(Tile tile) {
		return tile == null || tile.getType() == Tile.TileType.ROOM || tile.getType() == Tile.TileType.NULL;
	}
	
	/**
	 * Determines whether or not this y value changes
	 * @param y
	 * 		the current y
	 * @param dir
	 * 		the direction of movement
	 * @return
	 * 		the new y
	 */
	private int changeY(int y, Direction dir) {
		if (dir == Direction.DOWN)			return y+1;
		else if (dir == Direction.UP)		return y-1;
		else return y;
	}
	
	/**
	 * Determines whether or not this x value changes
	 * @param x
	 * 		the current x
	 * @param dir
	 * 		the direction of movement
	 * @return
	 * 		the new x
	 */
	private int changeX(int x, Direction dir) {
		if (dir == Direction.RIGHT)			return x+1;
		else if (dir == Direction.LEFT)		return x-1;
		else return x;
	}
	
	/**
	 * Whether or not a player is occupying this tile
	 * @param x
	 * 		the x position
	 * @param y
	 * 		the y position
	 * @return
	 * 		the result of checking all player positions to see if a player exists on this tile
	 */
	private boolean playerOccupied(int x, int y) {
		for (Player player : players) {
			if (player.getRoom() == null && player.getX() == x && player.getY() == y) return true;
		}
		return false;
	}
	
	/*
	 * How the player changes to the next
	 */
	public void nextPlayer() {
		int next = getCurrentPlayer() + 1;
		currentPlayer = (next <= order.size() ? next : 1);
		Player temp = order.poll();
		order.offer(temp);
	}

	/**
	 * Rolls two dice, returns their sum
	 * @return
	 * 		the sum of two dice rolls
	 */
	public Integer roll(){
		die1 = new Die(); die2 =new Die();
		return die1.getRoll() + die2.getRoll();
	}

	/**
	 * Resets and constructs rooms
	 */
	public void resetRooms() {

		rooms = new ArrayList<Room>();
		for (Room.Area area : Room.Area.values()) {
			rooms.add(new Room(area));
		}

	}

	/**
	 * Resets and constructs weapons
	 */
	public void resetWeapons() {

		weapons = new ArrayList<Weapon>();
		for (Weapon.Item item : Weapon.Item.values()) {

			Room room = roomWithoutWeapon();
			Weapon weapon = new Weapon(item, room);
			weapons.add(weapon);
			room.addWeapon(weapon);

		}


	}

	/**
	 * Returns a room that doesn't have a weapon inside of it
	 * @return a room
	 */
	private Room roomWithoutWeapon() {

		Random rand = new Random();
		// because we only have 6 weapons, and 9 rooms, it is impossible to get trapped here
		while (true) {
			Room room = rooms.get(rand.nextInt(9));
			if ((room.numberOfWeapons()==0)) return room;
		}

	}

	/**
	 * Resets and constructs all the players
	 */
	public void resetPlayers() {
		players = new ArrayList<Player>();
		
		// have to do these individually as position on the board needs to be uniquely set
		players.add(new Player(Player.Person.SCARLETT,  7, 24));
		players.add(new Player(Player.Person.MUSTARD,   0, 17));
		players.add(new Player(Player.Person.WHITE,     9,  0));
		players.add(new Player(Player.Person.GREEN,    14,  0));
		players.add(new Player(Player.Person.PEACOCK,  23,  6));
		players.add(new Player(Player.Person.PLUM,     23, 19));

		order = new ArrayDeque<Player>();
	}

	/**
	 * Gets each player to select their character
	 */
	private void choosePlayers(){
		
		// needs to confirm how many players are going to participate in this game
		activePlayers = askPlayerCount();
		printPlayerCodes();

		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String choice = "";
		
		// starts from 1 instead of 0 so the printout uses "Player 1", not "Player 0"
		for (int i = 1; i <= activePlayers; i++) {
			
			// uses this outer syntax so that way when "break outer" is called, it jumps out of this while loop
			outer : while (true) {
				System.out.println("Please select your character, Player " + i + ":");

				try {
					choice = input.readLine().toUpperCase();
				} catch (IOException e) {}
				
				for (int j = 0; j < players.size(); j++) {
					
					// Ensures a unique character for each player is chosen
					if ((choice.equals(players.get(j).toString()) || (choice.equals(players.get(j).getInitial())))
							&& !players.get(j).isInPlay()) {

						System.out.println("Player " + i + " character chosen: " + players.get(j).toString() + "!");

						players.get(j).setInPlay(true);
						players.get(j).setNumeric(i);
						order.offer(players.get(j));
						break outer;

					}
				}
				
				System.out.println("Character " + choice + " invalid...");
			}
		}
	}

	/**
	 * Asks for the number of players in this round and checks whether the input is valid or not.
	 * @return 
	 * 		the count of players in this round (between 3-6)
	 */
	private int askPlayerCount() {
		int count = 0;

		System.out.println("--------------");
		while (count < 3 || count > 6) {
			
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			String choice = "";

			System.out.println("How many players? (between 3-6):");

			try {
				choice = input.readLine();
			} catch (IOException e) {}

			// First checks if the input is a valid number, then is determines whether this number is too low/high
			if (!parseNumber(choice)) {
				System.out.println("Input not a number...");
				continue;
			}

			count = Integer.parseInt(choice);
			if (count < 3) System.out.println("Too few players...");
			else if (count > 6) System.out.println("Too many players...");

		}

		return count;
	}

	/**
	 *  Resets the card deck
	 */
	public void resetCards() {
		deck = new ArrayList<Card>();
		for(Room room : rooms) {

			Card c = new Card(room);
			deck.add(c);
		}
		for(Player player : players) {
			Card c = new Card(player);
			deck.add(c);

		}
		for(Weapon weapon : weapons) {
			Card c = new Card(weapon);
			deck.add(c);

		}

	}

	/**
	 *  Chooses a person, room and weapon to put in the Tuple, these are the correct answers when players start to guess.
	 */
	public void resetSolution() {

		Random rand = new Random(); 

		// choose random room for solution
		int value = rand.nextInt(9);
		Room murderRoom = (Room)deck.get(value).getType();
		deck.remove(value);


		// choose random player for solution
		value = rand.nextInt(6) +8;
		Player murderer = (Player)deck.get(value).getType();
		deck.remove(value);


		// choose random player for solution
		value = rand.nextInt(6) + 13;
		Weapon murderWeapon = (Weapon)deck.get(value).getType();
		deck.remove(value);

		solution = new Tuple(murderer, murderWeapon, murderRoom);
	}

	/**
	 * Deals the remaining cards in the deck out to the players that are in play.
	 * 
	 */
	public void dealCards() {
		//easy way to randomize deck
		Collections.shuffle(deck);

		// While there are cards left remove the first card and assign it to the first player.
		while(!deck.isEmpty()) {
			Card currCard = deck.remove(0);
			Player cardPlayer = order.poll();
			cardPlayer.addToHand(currCard);
			order.offer(cardPlayer);
		}

	}

	/**
	 *  A method that is used whenever a player is in a room, they can make a guess as to what is in the Tuple to question 
	 *  other players or they can make an accusation to check what they have guessed against the cards in the Tuple
	 *  
	 * @param suggested
	 * 		The tuple containing player, weapon and room.
	 * @param player 
	 * 		The player that the current player has accused/ guessed..
	 * @param weapon 
	 * 		The weapon they chose.
	 * @param room 
	 * 		The room they chose as the murder room.
	 * @param check 
	 * 		Whether or not it was a suggestion or an accusation.
	 * @param accuse
	 * 		True if is an accusation, false if a suggestion.
	 */
	public void checkSuggestion(Tuple suggested, boolean accuse){
		Player player = suggested.getMurderer();
		Weapon weapon = suggested.getWeapon();
		Room room = suggested.getRoom();
		
	
		int playerCount = getCurrentPlayer();

	
		if(!validSuggestion(room, accuse)) {
			System.out.println("That was not a valid " + ((accuse) ? "accusation! " : "suggestion!") + " Try again. \n");
			askSuggestion();
			
		} 
		else {
			
			if(!accuse) {
				
				weapon.getRoom().removeWeapon(weapon);
				weapon.setRoom(room);
				room.addWeapon(weapon);
				room.addPlayer(player);
				if(player.getRoom()!=null) player.getRoom().removePlayer(player);
				player.setRoom(room);
				player.setX(order.peek().getX());
				player.setY(order.peek().getY());
				// makes sure only one card is shown.
				boolean found = false;
				Random rand = new Random();

				// loops through all the players that were chosen at the start whether or not they are in play or out.
				for(Player p : order) {

					List<Card> multipleInHand = new ArrayList<Card>();
					if(!found) {
						if(p.equals(order.peek())) {playerCount = getCurrentPlayer(); continue;}

						// Needed in order to say what player had the card.
						playerCount = playerCount+1;
						if(playerCount>activePlayers) playerCount=1;

						// All the cards in the players hand.
						List<Card> hand = p.getHand();

						//this is used to randomise the order to we don't always get the same type of cards returned first.
						List<String> typesLeft = new ArrayList<String>();
						List<String> types = new ArrayList<String>();
						typesLeft.add("player");					
						typesLeft.add("weapon");
						typesLeft.add("room");

						for(int i=3; i>0; i--) {
							int cardType = (rand.nextInt(i));
							types.add(typesLeft.remove(cardType));	
						}

						// looks for the first card it finds that matches prints that then breaks. Acts like a player kind of.
						for(String type : types) {
							if(type.equals("player")) {
								for(Card c : hand) {
									if(c.getType().toString().equalsIgnoreCase(player.toString())) {
										if(!found) {
											if(pickRandom) {
												System.out.println("Player " + playerCount + " has the " + player.toString() + " card.\n");
												playerHasCard = playerCount;
												cardPlayerHas = c;
												found = true;
												break;
											}else multipleInHand.add(c);
										}


									}
								}
							}
							else if(type.equals("weapon")) {
								for(Card c : hand) {
									if(c.getType().toString().equalsIgnoreCase(weapon.toString())) {
										if(!found) {
											if(pickRandom) {
												System.out.println("Player " + playerCount + " has the "  + weapon.toString() + " card.\n");
												playerHasCard = playerCount;
												cardPlayerHas = c;
												found = true;
												break;
											} else multipleInHand.add(c);
										}


									}
								}

							}
							else if(type.equals("room")) {
								for(Card c : hand) {
									if(c.getType().toString().equalsIgnoreCase(room.toString())) {
										if(!found) {
											if(pickRandom) {
												System.out.println("Player " + playerCount + " has the " +room.toString() + " card.\n");
												playerHasCard = playerCount;
												cardPlayerHas = c;
												found = true;
												 break;
											} else multipleInHand.add(c);
										}


									}
								}

							}


						}

					}
					if(multipleInHand.size()>1 && !pickRandom) {
						System.out.println("\nShowing cards, only player " + playerCount + " allowed to see!\n");
						sleep();
						System.out.println("Cards can show: " + multipleInHand + "\nPick the index of the one you want to show to PLAYER " + getCurrentPlayer());

						BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
						String command = "";

						int index = -1;
						while(index<0 || index>= multipleInHand.size() || !parseNumber(command)) {
							try {
								command = input.readLine();
							} catch (IOException e) { }

							if(parseNumber(command)) {
								index = Integer.parseInt(command);
								if(index<0 || index>= multipleInHand.size()) {
									index = -4;
									System.out.println("Pick the index of the one you want to show to PLAYER " + getCurrentPlayer() +", must be between 0 and " + (multipleInHand.size()-1) +".");
								}
								else {

									found = true;
									System.out.println("\n============================================================================\n");
									System.out.println("PLAYER " + playerCount + " has the " +multipleInHand.get(index).toString() + " card.");
									System.out.println("\n============================================================================");
								}


							}
						}
					}
					else if(multipleInHand.size()==1 && !pickRandom) {
						System.out.println(multipleInHand);
						System.out.println("Player " + playerCount + " has the " +multipleInHand.get(0).toString() + " card.\n");
						found = true;
					}
					multipleInHand.clear();
				}
				printBoard();
				
				if(!found) {
					System.out.println("No card found!");
				}
			}
			else { 

				//case where the accusation is the correct solution.
				if(player.toString().equals(solution.getMurderer().toString()) &&
				   weapon.toString().equals(solution.getWeapon().toString()) &&
				   room.toString().equals(solution.getRoom().toString())) {
					System.out.println("That accusation is correct! \n");
					gameOver(true);
				}
				else {
					System.out.println("That accusation is incorrect! Byee.\n");
					activePlayers--;
					order.peek().setInPlay(false);
					
					// moves the player into a random room
					order.peek().setRoom(rooms.get(new Random().nextInt(8)));
					
					if(activePlayers==0) {
						gameOver(false);
					} else {
						// prints the board with the character now not displayed on the map
						printBoard();
					}
				}
			}


		}
	}
	
	/**
	 * 
	 * @param room
	 * 		The room as the player needs to be in the room they are using in their suggestion.
	 * @param accuse
	 * 		Whether it is a suggestion or accusation.
	 * @return
	 */
	public boolean validSuggestion(Room room, boolean accuse) {
		Player currentPlayer = order.peek();
		if(currentPlayer.getRoom()!=room && !accuse) { return false; }
		return true;

	}

	/**
	 *  A method that keeps asking for a suggestion as long as the parser returns null, so basically
	 *  parsers until it gets a valid suggestion/ accusation. Also allows the user to print out codes
	 *  in case they forgot them.
	 */
	public void askSuggestion() {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String command = "";

		System.out.println("Time to suggest or accuse!");
		suggestion = null;

		while((suggestion==null)) {
			System.out.println("Input must follow [person] [weapon] [room] or codes [weapon/room/player]\n");
			try { command = input.readLine().toLowerCase();
			} catch (IOException e) {}

			if(command.startsWith("codes")) parseCodes(command);
			else if (command.startsWith("suggest ") ) {
				suggestion = parseSuggestion(command, false);
				if(suggestion!=null) checkSuggestion(suggestion, false);
			}
			else if(command.startsWith("accuse ")) {
				suggestion = parseSuggestion(command, true);
				if(suggestion!=null) checkSuggestion(suggestion, true);
			}

		}
	}

	/**
	 * A method to parse the suggestions and accusations to check whether they are valid or not.
	 * 
	 * @param command
	 * 		The string to be parsed.
	 * @param accuse
	 * 		Whether it was an accusation or suggestion (changes the length)
	 * @return
	 */
	public Tuple parseSuggestion(String command, boolean accuse) {
		Player accusedPerson = null;
		Weapon accusedWeapon = null;
		Room accusedRoom = null;


		int index = 0;
		if (command.startsWith("accuse ") && command.length() > 7) {
			index = 7;

		}
		else if(command.startsWith("suggest ") && command.length()>8) {
			index=8;
		}

		//checks whether the player, weapon and room suggested is valid, it can either be initials or full names.
		for(Player p : players) {
			if(p.toString().equalsIgnoreCase(command.substring(index,Math.min(command.length(), index+p.toString().length())))) {
				index = index+ p.toString().length()+1;
				accusedPerson = p;
			}
			//checks for initials + " " so that is is distinguishable from the full names
			else if((p.getInitial() + " ").equalsIgnoreCase(command.substring(index,Math.min(command.length(), index+p.getInitial().length()+1)))) {
				index = index+ p.getInitial().length()+1;
				accusedPerson = p;
			}
		}
		for(Weapon w : weapons) {
			if(w.toString().equalsIgnoreCase(command.substring(index,Math.min(command.length(), index+w.toString().length())))) {
				index = index+ w.toString().length()+1;
				accusedWeapon = w;
			}
			else if((w.getInitial()+" ").equalsIgnoreCase(command.substring(index,Math.min(command.length(), index+w.getInitial().length()))+ " ")) {
				index = index+ w.getInitial().length()+1;
				accusedWeapon = w;

			}
		}
		for(Room r : rooms) {
			if(r.toString().equalsIgnoreCase(command.substring(index,command.length())) 
					|| ((r.getInitial()+ " ").equalsIgnoreCase(command.substring(index,command.length()) +" "))) {
				accusedRoom = r;
			}
		}

		//if one of the guesses isnt valid return null (basically like a false case in a parser)
		if(accusedPerson==null || accusedWeapon == null || accusedRoom == null) {return null;}

		//return the tuple of the suggestion to be used in other methods
		System.out.println("You have " + (accuse ? "accused " : "suggested ") + accusedPerson + " with the " + accusedWeapon + " in the " + accusedRoom );
		return new Tuple(accusedPerson, accusedWeapon, accusedRoom);
	}


	/**
	 * Provides a method based on whether the game was won or not.
	 * 
	 * @param won
	 * 		true if game was won.
	 */
	public void gameOver(boolean won) {
		if(won) {
			System.out.println("Game over. Player " + getCurrentPlayer() + " (" + order.peek().toString() + ") won!");
			activePlayers = 0;
			gameWon = true;
		}
		else {
			System.out.println("Game over. The correct solution was " + solution.getMurderer().toString() +
							   " with the " + solution.getWeapon().toString() + " in the " + solution.getRoom().toString());
			gameWon = false;
		}
	}
	
	/**
	 * Provides a method based on whether the game was won or not.
	 * 
	 * @param won
	 * 		true if game was won.
	 */
	public void gameOverGUI(boolean won) {
		JPanel gameOverPanel  = new JPanel(new GridLayout(4,1));
		gameOverPanel.add(new JLabel(won ? "Game over. Player " + getCurrentPlayer() + " (" + order.peek().toString() + ") won!" : "Game over. The correct solution was " + solution.getMurderer().toString() + 
					" with the " + solution.getWeapon().toString() + " in the " + solution.getRoom().toString()));
		
		if(won) {
			JOptionPane.showMessageDialog(null, gameOverPanel);
			activePlayers = 0;
			gameWon = true;
		}
		else {
			JOptionPane.showMessageDialog(null, gameOverPanel);
			gameWon = false;
		}
	}

	/**
	 * Resets the game (upon game start up or game end)
	 */
	public void reset(){
		
		System.out.println("\nRestarting Game...");
		
		// Resets all entities at the start of the game, creating new objects for these
		resetRooms();
		resetWeapons();
		resetPlayers();
		
		// Allows the players to choose their characters
		choosePlayers();

		// Resets the reset of the objects dependent on these prior objects being created
		board = new Board(rooms, this);
		gameWon = false;
		resetCards();
		resetSolution();
		dealCards();
	
		cardMode();
		
		// Prints out a help status with all the possible commands a player can use, before prompting the player(s) to continue
		printHelp();
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String command = "";
		while (!command.equalsIgnoreCase("OK")) {
			System.out.println("Type OK to continue!");
			try {
				command = input.readLine();
			} catch (IOException e) { }
		}

	}

	/**
	 * Condenses the playScenario method by keeping this code separate, all it does
	 * is reveal the current players status (what their dieRoll was, how many moves
	 * they have left)
	 * @param player
	 * 		The Player object who's current turn it is
	 * @param dieRoll
	 * 		The roll this player had on the die
	 * @param movesLeft
	 * 		The amount of moves this player has remaining
	 */
	public void printPlayerStatus(Player player, int dieRoll, int movesLeft) {
		System.out.println(	"It is your turn, Player " + getCurrentPlayer() +
				" (" + player.toString() + "), " +
				"you have rolled a" + (dieRoll == 8 || dieRoll == 11 ? "n " : " ") + dieRoll + "!\n" +
				"You have " + movesLeft + " steps remaining. You can move " + getValidDirections(player, player.getX(), player.getY(), movesLeft) + "or you can accuse.");

		System.out.println("Your cards: " + player.getHand() + "\n");
	}
	
	/**
	 * Gets the valid directions the player can move in
	 * @param player
	 * 		the current player
	 * @param x
	 * 		the players x position
	 * @param y
	 * 		the player y position
	 * @return
	 * 		a String containing the valid directions the player can move in
	 */
	public String getValidDirections(Player player, int x, int y, int movesLeft) {
		StringBuffer res = new StringBuffer();
		
		// the Directions enum
		Direction[] dirs = Direction.values();
		
		// in the same order as the directions enum
		Tile[] tiles = new Tile[4];
		tiles[0] = getTileInGame(player, x, y-1, movesLeft);		// up
		tiles[1] = getTileInGame(player, x+1, y, movesLeft);		// right
		tiles[2] = getTileInGame(player, x, y+1, movesLeft);		// down
		tiles[3] = getTileInGame(player, x-1, y, movesLeft);		// left
		
		
		for (int i = 0; i < tiles.length; i++) {
			if (tiles[i] != null && typeIsSafe(tiles[i], dirs[i], player.getRoomLastIn())) {
				res.append(dirs[i] + ", ");
			}
		}
		
		return res.toString();
	}
	
	/**
	 * Because of it's command usage, we made a printBoard method which has the sole purpose of
	 * printing out the board whenever it is called, as long as the board is not null
	 */
	public void printBoard() {
		if (board != null) System.out.println("\nBoard:\n" + board.toString());
	}

	public static void main(String... args){
		Game game = new Game();
		
		System.out.println("-------------------------------\n    Cluedo! By confusedhonk\n(Teagan Stewart"
				+ " and Ethan Munn)\n-------------------------------");
	
		// these methods will essentially loop forever
		while (true) {
			game.askToStart();
			game.reset();
			game.playScenario();
		}
		
	}
	
	/**
	 * Method that just waits a default set amount of time
	 */
	public void sleep() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
		}
	}

}