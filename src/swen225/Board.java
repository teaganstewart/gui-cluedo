package swen225;

import java.util.ArrayList;
import java.util.List;


/**
 * Stores the physical board that is used and draws it.
 * 
 * @author Teagan Stewart, Ethan Munn
 */
public class Board
{

	//------------------------
	// MEMBER VARIABLES
	//------------------------

	//Board Attributes
	public Tile[][] tiles;
	public Game currentGame;
	boolean viewLabels = true;
	boolean numericPlayers = false;

	//------------------------
	// CONSTRUCTOR
	//------------------------

	public Board(List<Room> rooms, Game game)
	{

		currentGame = game;

		//creates the board
		Tile r = new Tile(Tile.TileType.ROOM);
		Tile n = new Tile(Tile.TileType.NULL);
		Tile h = new Tile(Tile.TileType.HALLWAY);
		List<DoorTile> doors = getDoorTiles(rooms);
		
		
		// 2d board array where r = roomTile, h = hallwayTile, n = nullTIle
		Tile[][] temp = {
				{n, n, n, n, n, n, n, n, n, h, n, n, n,n, h, n, n, n, n, n,n,n,n, n},
				{r, r, r, r, r, r, n, h, h, h, r, r, r, r, h, h, h, n, r, r, r, r, r, r},
				{r, r, r, r, r, r, h, h, r, r, r, r,r, r, r, r, h, h, r, r, r, r, r, r},
				{r, r, r, r, r, r, h, h, r, r, r, r,r, r, r, r, h, h, r, r, r, r, r, r},
				{r, r, r, r, r, r, h, h, r, r, r, r,r, r, r, r, h, h, doors.get(8), r, r, r, r, r},
				{r, r, r, r, r, r, h, h, doors.get(2), r, r, r,r, r, r, doors.get(5), h, h, h, r, r, r, r, n},
				{n, r, r, r, doors.get(9), r, h, h, r, r, r, r, r, r, r, r, h, h, h, h, h,h,h,h},
				{h, h,h,h,h,h,h,h, r, doors.get(3), r,r,r,r, doors.get(4) ,r, h,h,h,h,h,h,h, n},
				{n, h, h,h,h,h,h,h,h,h,h,h,h,h,h,h, h,h,r, r, r, r, r, r},
				{r,r, r, r, r, h, h, h, h, h, h, h, h, h, h, h, h, h, doors.get(6), r, r, r, r, r},
				{r,r, r, r, r, r, r, r, h, h, n, n, n, n, n, h, h,h,r, r, r,r, r, r},
				{r,r, r, r, r, r, r, r, h, h, n, n, n, n, n, h, h,h,r, r, r,r, r, r},
				{r,r, r, r, r, r, r, doors.get(1), h, h, n, n, n, n, n,h, h,h,r, r, r,r, doors.get(7), r},
				{r,r, r, r, r, r, r, r, h, h, n, n, n, n, n,h, h, h, h,h, h,h, h, n},
				{r,r, r, r, r, r, r, r, h, h, n, n, n, n, n,h, h, h, r, r, doors.get(11),r, r, n},
				{r, r, r, r, r, r, doors.get(0), r, h, h, n, n, n, n, n,h, h,r,r, r, r,r, r, r},
				{n, h, h,h,h,h,h,h,h,h, n, n, n, n, n, h, h, doors.get(10), r, r, r, r, r, r},
				{h, h, h, h, h,h,h,h, h, h,h,h,h,h,h,h,h, r, r, r, r, r, r, r },
				{n, h, h, h, h, h, h, h, h, r, r, doors.get(12), doors.get(13), r, r, h, h, h, r, r, r, r, r, n},
				{r, r, r, r, r, r, doors.get(16), h,h, r, r, r, r, r, r, h, h, h, h, h, h,  h,  h, h},
				{r, r, r, r, r, r, r, h,h, r, r, r, r, r, doors.get(14), h, h , h , h , h , h , h , h, n},
				{r, r, r, r, r, r, r, h,h, r, r, r, r, r, r, h, h, doors.get(15), r, r, r, r, r, r},
				{r, r, r, r, r, r, r, h, h,r, r, r, r, r, r, h, h, r, r, r, r, r, r, r},
				{r, r, r, r, r, r, r, h, h, r, r, r, r, r, r, h, h, r, r, r, r, r, r, r}, 
				{r, r, r, r, r, r, n, h, n, r, r, r, r, r, r, n, h, n, r, r, r, r, r, r}	
		};

		setTiles(temp);
		
	}

	//------------------------
	// INTERFACE
	//------------------------

	/**
	 * 
	 * @param aTiles
	 * 		The new board.
	 * @return 
	 * 		Whether the change was successful.
	 */
	public boolean setTiles(Tile[][] aTiles) {
		boolean wasSet = false;
		tiles = aTiles;
		wasSet = true;
		return wasSet;
	}
	
	/**
	 * Assigns all the door tiles to the rooms and returns these as a list of DoorTiles.
	 * This method is reliant on the fact that the board is set to the regular Cluedo board layout
	 * @param rooms
	 */
	private List<DoorTile> getDoorTiles(List<Room> rooms) {
		
		List<DoorTile> doors = new ArrayList<>();
		
		// DINING ROOM
		doors.add(new DoorTile(rooms.get(0), Direction.UP, 15, 6));
		doors.add(new DoorTile(rooms.get(0), Direction.LEFT, 12, 7));
		rooms.get(0).addDoorTile(doors.get(0));
		rooms.get(0).addDoorTile(doors.get(1));
		
		// BALL ROOM
		doors.add(new DoorTile(rooms.get(1), Direction.RIGHT, 5, 8));
		doors.add(new DoorTile(rooms.get(1), Direction.UP, 7, 9));
		doors.add(new DoorTile(rooms.get(1), Direction.UP, 7, 14));
		doors.add(new DoorTile(rooms.get(1), Direction.LEFT, 5, 15));
		rooms.get(1).addDoorTile(doors.get(2));
		rooms.get(1).addDoorTile(doors.get(3));
		rooms.get(1).addDoorTile(doors.get(4));
		rooms.get(1).addDoorTile(doors.get(5));
		
		// BILLIARD ROOM
		doors.add(new DoorTile(rooms.get(2),Direction.RIGHT, 9, 18));
		doors.add(new DoorTile(rooms.get(2),Direction.UP, 12, 22));
		rooms.get(2).addDoorTile(doors.get(6));
		rooms.get(2).addDoorTile(doors.get(7));

		// CONSERVATORY
		doors.add(new DoorTile(rooms.get(3), Direction.UP, 4, 18));
		rooms.get(3).addDoorTile(doors.get(8));
		
		// KITCHEN
		doors.add(new DoorTile(rooms.get(4), Direction.UP, 6, 4));
		rooms.get(4).addDoorTile(doors.get(9));
		
		// LIBRARY
		doors.add(new DoorTile(rooms.get(5), Direction.RIGHT, 16, 17));
		doors.add(new DoorTile(rooms.get(5), Direction.DOWN, 14, 20));
		rooms.get(5).addDoorTile(doors.get(10));
		rooms.get(5).addDoorTile(doors.get(11));
		
		// HALL
		doors.add(new DoorTile(rooms.get(6), Direction.DOWN, 18, 11));
		doors.add(new DoorTile(rooms.get(6), Direction.DOWN, 18, 12));
		doors.add(new DoorTile(rooms.get(6), Direction.LEFT, 20, 14));
		rooms.get(6).addDoorTile(doors.get(12));
		rooms.get(6).addDoorTile(doors.get(13));
		rooms.get(6).addDoorTile(doors.get(14));
		
		// STUDY
		doors.add(new DoorTile(rooms.get(7), Direction.DOWN, 21, 17));
		rooms.get(7).addDoorTile(doors.get(15));
	
		// LOUNGE
		doors.add(new DoorTile(rooms.get(8), Direction.DOWN, 19, 6));
		rooms.get(8).addDoorTile(doors.get(16));
		
		return doors;
		
	}

	
	public Tile[][] getTiles() {
		return tiles;
	}
	/**
	 * Returns a tile at a specific point
	 * @param r
	 * 		row/yPos
	 * @param c
	 * 		col/xPos
	 * @return
	 * 		Tile object at this position on the board
	 */
	public Tile getTile(int r, int c) {
		
		if (r < 0 || r > 24 || c < 0 || c > 23) return null;
		Tile tile = tiles[r][c];
		return tile;
		
	}
	

	/**
	 * Returns whether or not a character was inserted to this position on the board
	 * as a label for the room, instead of a regular room tiles. Determines this if the 
	 * StringBuffer res passed over as a parameter has increased by the end of the method.
	 * 
	 * @param i
	 * 		the row/y coord
	 * @param j
	 * 		the col/x coord
	 * @param res
	 * 		the current board display contained within the string buffer
	 * @return
	 * 		whether or not a room was drawn on the board
	 */
	private boolean roomLabel(int i, int j, StringBuffer res) {
		int length = res.length();

		if (i == 3) {
			if (j == 1) res.append("K");
			else if (j == 2 || j == 19) res.append("C");
			else if (j == 3) res.append("H");
			else if (j == 4 || j == 21) res.append("N");		
			else if (j == 10) res.append("B");
			else if (j == 11) res.append("A");
			else if (j == 12 || j == 13) res.append("L");
			else if (j == 20) res.append("O");
			else if (j == 22) res.append("S");
		}
		else if (i == 4) {
			if (j == 10) res.append("R");
			else if (j == 11 || j == 12) res.append("O");
			else if (j == 13) res.append("M");
		}
		else if (i == 10) {
			if (j == 19) res.append("B");
			else if (j == 20) res.append("L");
			else if (j == 21) res.append("R");
			else if (j == 22) res.append("D");
		}
		else if (i == 12) {
			if (j == 1) res.append("D");
			else if (j == 2 || j == 4) res.append("I");
			else if (j == 3 || j == 5) res.append("N");
			else if (j == 6) res.append("G");
		}
		else if (i == 13) {
			if (j == 2) res.append("R");
			else if (j == 3 || j == 4) res.append("O");
			else if (j == 5) res.append("M");
		}
		else if (i == 16) {
			if (j == 19) res.append("L");
			else if (j == 20) res.append("B");
			else if (j == 21) res.append("R");
			else if (j == 22) res.append("Y");
		}
		else if (i == 21) {
			if (j == 2) res.append("L");
			else if (j == 3) res.append("O");
			else if (j == 4) res.append("U");
		}
		else if (i == 22) {
			if (j == 2) res.append("N");
			else if (j == 3) res.append("G");
			else if (j == 4) res.append("E");
			else if (j == 10) res.append("H");
			else if (j == 11) res.append("A");
			else if (j == 12 || j == 13) res.append("L");
			else if (j == 18) res.append("S");
			else if (j == 19) res.append("T");
			else if (j == 20) res.append("U");
			else if (j == 21) res.append("D");
			else if (j == 22) res.append("Y");

		}

		return res.length() > length;
	}

	/**
	 * Returns whether or not a player has been drawn on the board at this specific i-jth tile,
	 * and if so appends them to the StringBuffer res 
	 * @param i
	 * 		the row/y coord 
	 * @param j
	 * 		the col/x coord
	 * @param res
	 * 		the current board display contained within the string buffer
	 * @return
	 * 		whether or not a player was drawn on the board
	 */
	private boolean playerLabel(int i, int j, StringBuffer res) {		

		for (Player p : currentGame.getPlayers()) {
			if (p.getRoom() == null) {
				if (p.getY() == i && p.getX() == j) {
					res.append(numericPlayers ? p.getNumeric() : p.getInitial());
					return true;
				}
			}
		}

		return false;
	}
	

	public void toggleLabels() {
		viewLabels = !viewLabels;
	}
	
	public void togglePlayers() {
		numericPlayers = !numericPlayers;
	}
	
	public boolean showRoomLabels() {
		return viewLabels;
	}

	
	/**
	 * Gets the width of the board
	 * @return
	 * 		The board width
	 */
	public int getWidth() {
		return tiles[0].length;
	}
	
	
	/**
	 * Gets the height of the board
	 * @return
	 * 		The board height
	 */
	public int getHeight() {
		return tiles.length;
	}
	
	/**
	 * Returns true if a player exists on this tile
	 * @param row
	 * 		the row	
	 * @param col
	 * 		the col
	 * @return
	 * 		whether or not a player is here
	 */
	public boolean playerOnTile(int row, int col) {
		for (Player p : currentGame.getPlayers()) {
			if (p.getRoom() == null) {
				if (p.getY() == row && p.getX() == col) {
					return true;
				}
			}
		}
		return false;
	}
	
	//------------------------
	// TO STRING
	//------------------------
	
	
	/**
	 * Writes the contents of the board to a StringBuffer, then returns this for output
	 * to the console. Determines whether or not to draw labels for the players and other characters
	 * by using the label methods below
	 * 
	 * @return
	 * 		the completed board as a string
	 */
	public String toString(){
		StringBuffer res = new StringBuffer();
		
		for (int i = 0; i < tiles.length; i++) {
			
			// every row starts with a | symbol
			res.append("|");
			for (int j = 0; j < tiles[0].length; j++) {
				
				// if viewLabels is true, then it should check to see if a room initial should be placed here
				// otherwise, it should also check that a player initial shouldn't be drawn here
				// if both of these cases are false, draws the regular tile determined by the TileType
				if (((viewLabels && !roomLabel(i,j,res)) || !viewLabels) && !playerLabel(i,j,res)) {
					res.append(tiles[i][j].getInitial());
				}
				// appends to the end of every tile to format the board into a grid
				res.append("|");
			}
			res.append("\n");
		}
		return res.toString();
	}
	
}


