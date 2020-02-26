/*This code was generated using the UMPLE 1.29.1.4581.2aeb7b5a5 modeling language!*/
package swen225;

/**
 *  Stored in the board class are used to determine where the player can move to and where the player is at.
 *  
 * @author Teagan Stewart, Ethan Munn
 */
public class Tile
{

	//------------------------
	// ENUMERATIONS
	//------------------------

	public enum TileType { ROOM, HALLWAY, NULL, DOOR}

	//------------------------
	// MEMBER VARIABLES
	//------------------------

	//Tile Attributes
	private TileType type;

	//------------------------
	// CONSTRUCTORS
	//------------------------

	public Tile(TileType aType)
	{
		type = aType;

	}

	//------------------------
	// INTERFACE
	//------------------------


	/**
	 * 
	 * @return Returns the tile type eg door, hallway, null or room.
	 */
	public TileType getType()
	{
		return type;
	}

	
	//------------------------
	// TO STRING METHODS
	//------------------------
	
	public String getInitial() {
		switch(type) {
		case ROOM:
			return "#";
		case DOOR:
			return "@";
		case NULL:
			return "X";
		default:
			return "_";
		}
	}

	public String toString(){
		switch(type) {
		case ROOM:
			return "ROOM";
		case NULL:
			return "NULL";
		default:
			return "HALLWAY";
		}
	}

}


