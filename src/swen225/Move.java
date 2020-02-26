package swen225;

public class Move {

	private final int moves;
	private final Direction dir;
	
	public Move(int moves, Direction dir) {
		this.moves = moves;
		this.dir = dir;
	}

	public int getMoves() {
		return moves;
	}

	public Direction getDir() {
		return dir;
	}
	
	public String toString() {
		return moves + " step" + (moves==1?"":"s") + " in direction " + dir;
	}
	
}
