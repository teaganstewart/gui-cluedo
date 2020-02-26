/*This code was generated using the UMPLE 1.29.1.4581.2aeb7b5a5 modeling language!*/
package swen225;

import java.util.*;

/**
 * @author Teagan Stewart, Ethan Munn
 */
public class Room implements Entity
{

	//------------------------
	// ENUMERATIONS
	//------------------------

	public enum Area { DININGROOM, BALLROOM, BILLIARDROOM, CONSERVATORY, KITCHEN, LIBRARY, HALL, STUDY, LOUNGE }

	//------------------------
	// MEMBER VARIABLES
	//------------------------

	//Room Attributes
	private Area name;

	//Room Associations
	private List<Player> players;
	private List<Weapon> weapons;
	private List<DoorTile> doors;
	
	// Starting Tiles for placement on the board
	private int rowStart, colStart;
	
	//------------------------
	// CONSTRUCTOR
	//------------------------

	public Room(Area aName)
	{
		name = aName;
		players = new ArrayList<Player>();
		weapons = new ArrayList<Weapon>();
		doors = new ArrayList<DoorTile>();
		
		setStartTiles(aName);
		
	}

	/**
	 * Sets the starting tile for placement on the board, dependent on enum
	 */
	private void setStartTiles(Area name) {
		switch (name) {
		case BALLROOM:
			setRowStart(3);
			setColStart(10);
			return;
		case BILLIARDROOM:
			setRowStart(9);
			setColStart(19);
			return;
		case CONSERVATORY:
			setRowStart(2);
			setColStart(19);
			return;
		case DININGROOM:
			setRowStart(11);
			setColStart(1);
			return;
		case HALL:
			setRowStart(20);
			setColStart(10);
			return;
		case KITCHEN:
			setRowStart(2);
			setColStart(1);
			return;
		case LIBRARY:
			setRowStart(15);
			setColStart(18);
			return;
		case LOUNGE:
			setRowStart(20);
			setColStart(1);
			return;
		case STUDY:
			setRowStart(22);
			setColStart(18);
			return;
		default:
			setRowStart(0);
			setColStart(0);
			return;
		}
	}
	
	//------------------------
	// INTERFACE
	//------------------------

	public List<Player> getPlayers()
	{
		List<Player> newPlayers = Collections.unmodifiableList(players);
		return newPlayers;
	}

	public List<Weapon> getWeapons()
	{
		List<Weapon> newWeapons = Collections.unmodifiableList(weapons);
		return newWeapons;
	}
	
	public List<DoorTile> getDoors()
	{
		List<DoorTile> newDoors = Collections.unmodifiableList(doors);
		return newDoors;
	}

	public int numberOfWeapons()
	{
		int number = weapons.size();
		return number;
	}

	/**
	 * 
	 * @param aWeapon The player to be added to the room.
	 * @return Returns whether the change was successful.
	 */
	public void addPlayer(Player aPlayer)
	{
		players.add(aPlayer);
	}

	/**
	 * 
	 * @param aWeapon The player to be removed from the room.
	 * @return Returns whether the change was successful.
	 */
	public void removePlayer(Player aPlayer)
	{
		players.remove(aPlayer);
	}

	/**
	 * 
	 * @param aWeapon The weapon to be added to the room.
	 * @return Returns whether the change was successful.
	 */
	public void addWeapon(Weapon aWeapon)
	{
		weapons.add(aWeapon);

	}

	/**
	 * 
	 * @param aWeapon The weapon to be removed from the room.
	 * @return Returns whether the change was successful.
	 */
	public void removeWeapon(Weapon aWeapon)
	{
		weapons.remove(aWeapon);
	}

	/**
	 * 
	 * @param aWeapon The door tile to be added to the room.
	 * @return Returns whether the change was successful.
	 */
	public void addDoorTile(DoorTile aDoor)
	{
		doors.add(aDoor);
	}


	/* Code from template association_GetMany */
	public DoorTile getDoorTile(int index)
	{
		DoorTile aDoorTile = doors.get(index);
		return aDoorTile;
	}

	public int numberOfDoors()
	{
		int number = doors.size();
		return number;
	}

	//------------------------
	// TO STRING
	//------------------------

	public String getInitial() {
		switch (name) {
		case DININGROOM:
			return "DR";

		case KITCHEN:
			return "KI";

		case CONSERVATORY:
			return "CO";

		case BALLROOM:
			return "BA";

		case HALL:
			return "HA";

		case STUDY:
			return "ST";

		case LIBRARY:
			return "LI";

		case LOUNGE:
			return "LO";

		default:
			return "BI";
		}
	}

	public String toString(){
		switch (name) {
		case DININGROOM:
			return "DINING ROOM";

		case KITCHEN:
			return "KITCHEN";

		case CONSERVATORY:
			return "CONSERVATORY";

		case BALLROOM:
			return "BALLROOM";

		case HALL:
			return "HALL";

		case STUDY:
			return "STUDY";

		case LIBRARY:
			return "LIBRARY";

		case LOUNGE:
			return "LOUNGE";

		default:
			return "BILLIARD ROOM";
		}
	}

	public int getRowStart() {
		return rowStart;
	}

	private void setRowStart(int rowStart) {
		this.rowStart = rowStart;
	}

	public int getColStart() {
		return colStart;
	}

	private void setColStart(int colStart) {
		this.colStart = colStart;
	}

}