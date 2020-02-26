package gui;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import swen225.Board;
import swen225.Coordinate;
import swen225.Direction;
import swen225.DoorTile;
import swen225.Tile;

/**
 * A class for doing AStar search, felt it was easier given it's own class than fitted into the
 * Model/View/Controller setup, considering it uses a Node Class as well
 * @author Ethan Munn, Teagan Stewart
 *
 */
public class AStar {

	// Just easily stores the board as an array of nodes, as well as holding a reference to it
	Board gameBoard;
	Node[][] nodes;
	
	// for A* specifically, uses a fringe and first/last
	Queue<Node> fringe;
	Node first = null, last = null;
	
	/**
	 * Constructs the A* algorithm by taking in the board, and the start and end coordinates
	 * @param board
	 * 		the cluedo board
	 * @param start
	 * 		where the player is
	 * @param end
	 * 		where the player clicked
	 */
	public AStar(Board board, Coordinate start, Coordinate end) {
		gameBoard = board;
		nodes = new Node[25][24];
		fringe = new PriorityQueue<Node>(1, distComparator);
		setupNodes(board.getTiles(), start, end);
	}
	
	/**
	 * Sets up all the nodes of the board using the same variables from before, then assigns them their neighbours
	 * @param board
	 * 		the board
	 * @param start
	 * 		player pos
	 * @param end
	 * 		end pos
	 */
	private void setupNodes(Tile[][] board, Coordinate start, Coordinate end) {
		for (int i = 0; i < board.length; i++) {
					
			for (int j = 0; j < board[0].length; j++) {
				
				// if there's a player here, just sets the tile here to null
				Node n = new Node((gameBoard.playerOnTile(i, j) ? new Tile(Tile.TileType.NULL) : board[i][j]), new Coordinate(i,j));
				
				n.setfCost(Double.POSITIVE_INFINITY);
				n.setgCost(Double.POSITIVE_INFINITY);
				
				nodes[i][j] = n;
				
			}
					
		}
		
		// setting up neighbours
		for (int i = 0; i < board.length; i++) {
			
			for (int j = 0; j < board[0].length; j++) {
				
				// if neighbours don't exist, sets them to null
				nodes[i][j].setNeighbours(i-1 < 0 ? null : nodes[i-1][j],					// up
										  j+1 >= board[0].length ? null : nodes[i][j+1],	// right
										  i+1 >= board.length ? null : nodes[i+1][j],		// down
										  j-1 < 0 ? null : nodes[i][j-1]);					// left
				
				// ensures first and last hold reference to tiles on the board
				if (i == start.getRow() && j == start.getCol()) { nodes[i][j].setCoord(start); first = nodes[i][j]; }
				else if (i == end.getRow() && j == end.getCol()) { nodes[i][j].setCoord(end); last = nodes[i][j]; }
				
			}
			
		}
	}
	
	/**
	 * The Actual A* algorithm that finds the shortest path between two coordinates
	 * on the board
	 * @return
	 * 		A stack of coordinates containing the path of travel, or null if not
	 * 		(it always should return a Stack for valid moves, there are no disconnected tiles)
	 */
	public Stack<Coordinate> findHeuristicPath() {
		
		// initialises the first tile
		first.setgCost(0.0);
		first.setfCost(getHeuristic(first,last));
		fringe.offer(first);
		
		while (!fringe.isEmpty()) {
			
			// grabs the tile at the front of the queue
			Node curr = fringe.poll();
			curr.setVisited(true);
			
			// if this is the goal, uses LIFO to return the stack pathway
			if (curr == last) {
				return findPath(curr);
			}
			
			// gets all the neighbours of the current tile
			Node[] neighbours = curr.getNeighbours();
			for (int n = 0; n < 4; n++) {
				Node neigh = neighbours[n];
				
				// if the neighbour doesn't exist (i.e out of bounds)
				if (neigh == null) continue;
				// if the neighbour is already visited (this isn't the shortest path)
				else if (neigh.isVisited()) continue;
				
				// gets the tile type to do the final check, ensuring this is either a hall tile or a valid door entry
				Tile.TileType neighType = neigh.getTile().getType();
				if (neighType != Tile.TileType.HALLWAY && !isDoorEntry(neigh.getTile(), n)) continue;
				
				// finds the cost so far, and if this is greater than the cost at the neighbour, this isn't the shortest path
				double tempG = curr.getgCost()+1.0;
				if (tempG >= neigh.getgCost()) continue;
				
				// else, if it gets here, this is the shortest path to this neighbour node so far!
				neigh.setgCost(tempG);
				neigh.setfCost(neigh.getgCost() + getHeuristic(neigh, last));
				neigh.setPrevNode(curr);
				fringe.offer(neigh);
				
			}
			
		}
		
		// ideally never reaches here
		return null;
		
	}

	/**
	 * Confirms whether or not the way into this room is valid
	 * @param t
	 * 		the tile at this position
	 * @param dirAsNum
	 * 		the direction as a number (from the neighbour nodes, they are all listed in URDL order like the Direction enum)
	 * @return
	 */
	private boolean isDoorEntry(Tile t, int dirAsNum) {
		
		// essentially .ordinal() method as a four-times-over embedded ternary
		Direction dir = (dirAsNum == 0 ? Direction.UP
						: dirAsNum == 1 ? Direction.RIGHT
						: dirAsNum == 2 ? Direction.DOWN
						: dirAsNum == 3 ? Direction.LEFT : null);
		
		// if it's not even a door, no point doing this check
		if (t.getType() != Tile.TileType.DOOR || dir == null) return false;
		
		// else, casts it to a DoorTile, and compares the door entry
		return ((DoorTile) t).getEntryDir() == dir;
		
	}

	/**
	 * Gets the Euclidean distance between the two nodes
	 * @param first
	 * 		the first node
	 * @param secnd
	 * 		the second node
	 * @return
	 * 		a double for this distance
	 */
	private double getHeuristic(Node first, Node secnd) {
		return Math.hypot(first.getCoord().getCol() - secnd.getCoord().getCol(), first.getCoord().getRow() - secnd.getCoord().getRow());
	}
	
	/**
	 * Backtracks through the prevNodes until a path is formed
	 * @param curr
	 * 		the starting node, which is overwritten in the while loop
	 * @return
	 * 		a stack containing all the coordinates
	 */
	private Stack<Coordinate> findPath(Node curr) {
		
		Stack<Coordinate> path = new Stack<>();
		
		while (curr.getPrevNode() != null) {
			
			path.add(curr.getCoord());
			curr = curr.getPrevNode();
			
		}
		
		return path;
	}
	
	/**
	 * Defines the comparator of the Priority Queue for the fringe
	 */
	Comparator<Node> distComparator = (n1, n2) -> {

        double heuristicOfN1 = n1.getfCost();
        double heuristicOfN2 = n2.getfCost();

        if (heuristicOfN1 > heuristicOfN2) return 1;
        else if (heuristicOfN1 < heuristicOfN2) return -1;
        return 0;

    };
    
    
    /**
     * An inner Node class used for the A* search algorithm, in click pathfinding.
     * @author Ethan Munn, Teagan Stewart
     *
     */
    class Node {
    	
    	private final Tile tile;
    	private Coordinate coord;
    	private boolean visited;
    	private Node prevNode;
    	private Node[] neighbours; // 0 is up, 1 is right, 2 is down, 3 is left
    	
    	private double fCost;
    	private double gCost;
    	
    	/**
    	 * Creates a new node
    	 * @param t
    	 * 		the tile associated with the node
    	 * @param c
    	 * 		the coordinate of the tile (preferred to store it in this object type)
    	 */
    	public Node(Tile t, Coordinate c) {
    		tile = t;
    		setCoord(c);
    		setVisited(false);
    		setPrevNode(null); 
    		neighbours = new Node[4];
    	}

    	/**
    	 * Gets the fCost
    	 * @return
    	 * 		fCost
    	 */
    	public double getfCost() {
    		return fCost;
    	}

    	/**
    	 * Sets the fCost
    	 * @param fCost
    	 * 		the cost so far plus the heuristic
    	 */
    	public void setfCost(double fCost) {
    		this.fCost = fCost;
    	}

    	/**
    	 * Gets the gCost
    	 * @return
    	 * 		gCost
    	 */
    	public double getgCost() {
    		return gCost;
    	}

    	/**
    	 * Sets the gCost
    	 * @param gCost
    	 * 		the cost so far
    	 */
    	public void setgCost(double gCost) {
    		this.gCost = gCost;
    	}

    	/**
    	 * Gets the coord
    	 * @return
    	 * 		coordinate 
    	 */
    	public Coordinate getCoord() {
    		return coord;
    	}

    	/**
    	 * Sets the coord
    	 * @param coord
    	 * 		the coordinate here
    	 */
    	public void setCoord(Coordinate coord) {
    		this.coord = coord;
    	}

    	/**
    	 * Returns whether or not this node has been visited in the A* search yet
    	 * @return
    	 * 		visited status 
    	 */
    	public boolean isVisited() {
    		return visited;
    	}

    	/**
    	 * Essentially is only ever used the set a node to visited from unvisited
    	 * @param visited
    	 * 		most often is true
    	 */
    	public void setVisited(boolean visited) {
    		this.visited = visited;
    	}

    	/**
    	 * Gets the previous node for backtracking
    	 * @return
    	 * 		the neighbour this node originated from in A*
    	 */
    	public Node getPrevNode() {
    		return prevNode;
    	}

    	/**
    	 * Sets this to the neighbour this node originated from in A*
    	 * @param prevNode
    	 * 		the previous node
    	 */
    	public void setPrevNode(Node prevNode) {
    		this.prevNode = prevNode;
    	}

    	/**
    	 * Gets the Tile at this position
    	 * @return
    	 * 		the tile
    	 */
    	public Tile getTile() {
    		return tile;
    	}
    	
    	/**
    	 * Sets up all the neighbours
    	 * @param up
    	 * 		northern neighbour
    	 * @param right
    	 * 		eastern neighbour
    	 * @param down
    	 * 		southern neighbour
    	 * @param left
    	 * 		western neighbour
    	 */
    	public void setNeighbours(Node up, Node right, Node down, Node left) {
    		if (up != null) neighbours[0] = up;
    		if (right != null) neighbours[1] = right;
    		if (down != null) neighbours[2] = down;
    		if (left != null) neighbours[3] = left;
    	}
    	
    	/**
    	 * Gets all the neighbours
    	 * @return
    	 * 		the neighbours in an array
    	 */
    	public Node[] getNeighbours() {
    		
    		return neighbours;
    		
    	}

    }


	
}