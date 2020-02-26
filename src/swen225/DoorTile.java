package swen225;

public class DoorTile extends Tile {

	private final Room doorTo;
	private final Direction entryDir;
	private final int row, col;
	
	public DoorTile(Room doorTo, Direction entryDir, int row, int col) {
		super(Tile.TileType.DOOR);
		this.doorTo = doorTo;
		this.entryDir = entryDir;
		this.row = row;
		this.col = col;
	}
	
	/**
	 *  A method purely for tiles of type door
	 * @return Returns the room that the door goes to.
	 */
	public Room getDoorTo()
	{
		return doorTo;
	}
	
	public Direction getEntryDir() {
		return entryDir;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}
	
	public String getInitial() {
		return super.getInitial();
	}
	
	public String toString() {
		return "Door to " + doorTo;
	}

}
