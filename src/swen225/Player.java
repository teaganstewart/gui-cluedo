/*This code was generated using the UMPLE 1.29.1.4581.2aeb7b5a5 modeling language!*/
package swen225;

import java.util.*;

import swen225.Player.Person;

/**
 * @author Teagan Stewart, Ethan Munn
 */
public class Player implements Entity
{

	//------------------------
	// ENUMERATIONS
	//------------------------

	public enum Person { SCARLETT, MUSTARD , WHITE , GREEN, PEACOCK, PLUM }

	//------------------------
	// MEMBER VARIABLES
	//------------------------

	//Player Attributes
	private Person player;
	private Room room, roomLastIn;
	private boolean inPlay;
	private int x;
	private int y;
	private String name;
	
	
	private Integer playerNum;
	private Coordinate[] positions;

	//Player Associations
	private List<Card> hand;

	//------------------------
	// CONSTRUCTOR
	//------------------------

	public Player(Person aName, int aX, int aY)
	{
		inPlay = false;
		player = aName;
		room = null;
		roomLastIn = null;
		x = aX;
		y = aY;
		
		hand = new ArrayList<Card>();
	}

	//------------------------
	// INTERFACE
	//------------------------

	
	/**
	 * 
	 * @param aInPlay Value that inPlay should be set to
	 * @return Returns whether the change was successful
	 */
	public boolean setInPlay(boolean aInPlay)
	{
		boolean wasSet = false;
		inPlay = aInPlay;
		wasSet = true;
		return wasSet;
	}
	
	/**
	 * 
	 * @param aInRoom Value that inRoom should be set to
	 * @return Returns whether the change was successful
	 */
	public boolean setRoom(Room aRoom)
	{
		boolean wasSet = false;
		room = aRoom;
		wasSet = true;
		return wasSet;
	}
	
	/**
	 * 
	 * @param aInRoom Value that roomLastIn should be set to
	 * @return Returns whether the change was successful
	 */
	public boolean setRoomLastIn(Room aRoom)
	{
		boolean wasSet = false;
		roomLastIn = aRoom;
		wasSet = true;
		return wasSet;
	}


	/**
	 * 
	 * @param aX New x location of the player
	 * @return Returns whether the change was successful
	 */
	public boolean setX(int aX)
	{
		boolean wasSet = false;
		x = aX;
		wasSet = true;
		return wasSet;
	}

	/**
	 * 
	 * @param aY New y location of the player
	 * @return Returns whether the change was successful
	 */
	public boolean setY(int aY)
	{
		boolean wasSet = false;
		y = aY;
		wasSet = true;
		return wasSet;
	}

	/**
	 * @return Returns the x location of the player.
	 */
	public int getX()
	{
		return x;
	}

	/**
	 * @return Returns the y location of the player.
	 */
	public int getY()
	{
		return y;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setPlayer(Person p) {
		this.player = p;
	}
	
	public Person getPlayer() {
		return player;
	}
	
	/**
	 * 
	 * @return Returns whether the player is in play or not (chosen by a player)
	 */
	public boolean isInPlay()
	{
		return inPlay;
	}

	/**
	 * 
	 * @return returns the room the player was in.
	 */
	public Room getRoom()
	{
		return room;
	}
	
	/**
	 * 
	 * @return returns the room the player was last in
	 */
	public Room getRoomLastIn()
	{
		return roomLastIn;
	}


	/**
	 * @return Returns the players hand.
	 */
	public List<Card> getHand()
	{
		List<Card> newHand = Collections.unmodifiableList(hand);
		return newHand;
	}


	/**
	 * 
	 * @param aHand is the card that needs to be added.
	 * @return Returns a boolean saying whether the adding was successful.
	 */
	public void addToHand(Card aHand)
	{
		hand.add(aHand);

	}


	
	/**
	 * Resets the x and y position arrays of the player to however many dieRolls they have
	 * @param dieRoll
	 * 		the die roll at the start of this players turn
	 */
	public void resetXYPositions(int dieRoll) {
		positions = new Coordinate[dieRoll];
	}
	
	/**
	 * Sets the next empty x and y position in the array to this tile.
	 * @param x
	 * 		x pos the player is on
	 * @param y
	 * 		y pos the player is on
	 */
	public void setXYPosition(int x, int y) {
		for (int i = 0; i < positions.length; i++) {
			if (positions[i] == null) {
				positions[i] = new Coordinate(y,x);
				return;
			}
		}

	}
	
	/**
	 * Checks the x and y positions throughout the arrays to see if the player has stepped on these
	 * positions already. If they have, returns true. If they haven't, returns a false
	 * @param x
	 * 		the x pos checked for
	 * @param y
	 * 		the y pos checked for
	 * @return
	 * 		true if the player has stepped here this turn
	 */
	public boolean checkXYPosition(int x, int y) {
		for (int i = 0; i < positions.length; i++) {
			if (positions[i] == null) return false;
			if (positions[i].getCol() == x && positions[i].getRow() == y) return true;
		}
		return false;
	}
	
	/**
	 * Just gets the coordinates as an array
	 * @return
	 * 		the coordinates array
	 */
	public Coordinate[] getPositions() {
		return positions;
	}

	public void setNumeric(int num) {
		playerNum = num;
	}
	
	//------------------------
	// TO STRING METHODS
	//------------------------

	public String getInitial() {
		switch(player) {
		case SCARLETT:
			return "S";
		case MUSTARD:
			return "M";
		case WHITE:
			return "W";
		case GREEN:
			return "G";
		case PEACOCK:
			return "B";
		default:
			return "P";
		}
	}
	
	public String getNumeric() {
		return playerNum != null ? playerNum.toString() : this.getInitial();
	}

	public String toString(){
		switch(player) {
		case SCARLETT:
			return "MISS SCARLETT";
		case MUSTARD:
			return "COLONEL MUSTARD";
		case WHITE:
			return "MS WHITE";
		case GREEN:
			return "MR GREEN";
		case PEACOCK:
			return "MRS PEACOCK";
		default:
			return "PROFESSOR PLUM";
		}
	}


}