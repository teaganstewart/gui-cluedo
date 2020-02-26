package swen225;

public class Coordinate {

	private final int row;
	private final int col;
	
	public Coordinate(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	@Override
	public boolean equals(Object o) {
		return  o instanceof Coordinate
				&& this.row == ((Coordinate) o).getRow()
				&& this.col == ((Coordinate) o).getCol();
	}
	
	public String toString() {
		return "[" + row + ", " + col + "]";
	}
}
