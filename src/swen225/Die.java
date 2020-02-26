/*This code was generated using the UMPLE 1.29.1.4581.2aeb7b5a5 modeling language!*/
package swen225;

import java.util.Random;

/**
 * @author Teagan Stewart, Ethan Munn
 */
public class Die
{

	//------------------------
	// MEMBER VARIABLES
	//------------------------

	//Die Attributes
	private int randomNumber;

	//------------------------
	// CONSTRUCTOR
	//------------------------

	public Die()
	{
		Random rand = new Random(); 
		int value = rand.nextInt(6) +1; 
		randomNumber = value;
	}

	//------------------------
	// INTERFACE
	//------------------------

	/**
	 *  
	 * @return Returns the dice roll
	 */
	public Integer getRoll(){
		return randomNumber;
	}


}


