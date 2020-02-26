/*This code was generated using the UMPLE 1.29.1.4581.2aeb7b5a5 modeling language!*/
package swen225;

/**
 * @author Teagan Stewart, Ethan Munn
 */
public class Weapon implements Entity
{

	//------------------------
	// ENUMERATIONS
	//------------------------

	public enum Item { CANDLESTICK, DAGGER, LEADPIPE, REVOLVER, ROPE, SPANNER }

	//------------------------
	// MEMBER VARIABLES
	//------------------------

	//Weapon Attributes
	private final Item name;
	private Room room;

	//------------------------
	// CONSTRUCTOR
	//------------------------

	public Weapon(Item aName, Room aRoom)
	{
		name = aName;
		room = aRoom;
	}

	public Room getRoom() {
		return room;
	}
	
	public void setRoom(Room aRoom) {
		room = aRoom;
	}
	
	//------------------------
	// TO STRING
	//------------------------

	public String getInitial() {
		switch (name) {
		case CANDLESTICK:
			return "CS";

		case DAGGER:
			return "DA";

		case LEADPIPE:
			return "LP";

		case REVOLVER:
			return "RE";

		case ROPE:
			return "RO";

		default:
			return "SP";
		}
	}
	
	public String toString(){
		switch (name) {
		case CANDLESTICK:
			return "CANDLESTICK";

		case DAGGER:
			return "DAGGER";

		case LEADPIPE:
			return "LEADPIPE";

		case REVOLVER:
			return "REVOLVER";

		case ROPE:
			return "ROPE";

		default:
			return "SPANNER";
		}
	}

}