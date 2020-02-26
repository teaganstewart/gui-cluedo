/*This code was generated using the UMPLE 1.29.1.4581.2aeb7b5a5 modeling language!*/
package swen225;

/**
 * The solutions to the game!
 * 
 * @author Teagan Stewart, Ethan Munn
 */
public class Tuple
{

	//------------------------
	// MEMBER VARIABLES
	//------------------------

	//Envelope Attributes
	private final Player murderer;
	private final Weapon weapon;
	private final Room room;

	//------------------------
	// CONSTRUCTOR
	//------------------------

	public Tuple(Player aMurderer, Weapon aWeapon, Room aRoom)
	{
		murderer = aMurderer;
		weapon = aWeapon;
		room = aRoom;
	}

	//------------------------
	// INTERFACE
	//------------------------

	public Player getMurderer()
	{
		return murderer;
	}

	public Weapon getWeapon()
	{
		return weapon;
	}

	public Room getRoom()
	{
		return room;
	}
	
	//------------------------
	// TO STRING
	//------------------------
	
	public String toString(){
		return "The envelope contains " + room + ", " + murderer + ", " + weapon;
	}

}


