/*This code was generated using the UMPLE 1.29.1.4581.2aeb7b5a5 modeling language!*/
package swen225;

/**
 * A class that is used to store all the cards in cluedo, including their type and whether they are part of the 
 * solution. 
 * 
 * @author Teagan Stewart, Ethan Munn
 */
public class Card
{

	//------------------------
	// MEMBER VARIABLES
	//------------------------

	//Card Attributes
	private Entity type;

	//------------------------
	// CONSTRUCTOR
	//------------------------

	public Card(Entity aType)
	{
		type = aType;
	}

	//------------------------
	// INTERFACE
	//------------------------

	public Entity getType()
	{
		return type;
	}

	//------------------------
	// TO STRING
	//------------------------
	
	public String toString(){
		return type.getClass().getSimpleName() + ": " + type.toString();
	}

}