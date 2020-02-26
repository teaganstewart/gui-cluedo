package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import javax.swing.AbstractButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import swen225.*;

/**
 * A class for the model part of model-view-controller, has all of the methods that 
 * store data, and check data for input and output.
 * 
 * @author Ethan Munn, Teagan Stewart
 *
 */
public class Model {

	private Game game;
	private Gui gui;
	private Controller controller;
	private GraphicalView images;
	
	// pathfinding switcher
	public boolean pathfinding = true;
	
	// Variables for the Timer/Movement to work
	private Timer movement;
	
	public boolean movePiece = false;
	private Player player;
	private Coordinate posOfPlayer;
	private Direction dir;
	private int toGo;
	private Stack<Coordinate> path;
	
	public Model(Game g, Gui gui, Controller controller, GraphicalView images) {
		game = g;
		this.gui = gui;
		this.controller = controller;
		this.images = images;
	}

	/**
	 * Uses the board grid to create the board.
	 * 
	 * @return
	 * 		Returns the finished board.
	 */
	JPanel createBoard() {

		gui.view.board = new JPanel(new GridLayout(25, 24));
		gui.view.board.setBounds(0, 10, gui.getWidth()*11/16, gui.getWidth()*11/16);

		for (int y = 0; y < 25; y++) {
			for (int x1 = 0; x1 < 24; x1++) {

				gui.view.board.add(gui.view.boardGrid[y][x1]);

			}
		}

		gui.view.window.add(gui.view.board);
		return gui.view.board;
	}

	/**
	 * Uses images to create the grid, also creates the mouse listener that allows the tiles
	 * to be clicked on/ selected. Then draws it.
	 */
	void createGrid() {

		Board b = new Board(game.getRooms(),game);
		game.setBoard(b);

		Tile[][] tiles = b.getTiles();
		gui.view.boardGrid = new JLabel[25][24];

		for(int i = 0; i<b.getHeight(); i++) {
			for(int j = 0; j<b.getWidth(); j++) {

				final int ith = i;
				final int jth = j;

				JLabel tile = new JLabel(images.getImageIcon(i,j,b));
				tile.setLayout(new BorderLayout());

				tile.addMouseListener(new MouseAdapter() {

					// for clicking on the board
					@Override
					public void mouseClicked(MouseEvent e) {

						
						gui.setClickedPos(new Coordinate(ith,jth));
						makeMove();
						

					}
					
					// for hovering on the board
					@Override
					public void mouseEntered(MouseEvent e) {

						
						gui.setHoveredPos(new Coordinate(ith,jth));
						highlightTiles();
						

					}


				});

				gui.view.boardGrid[i][j] = tile;

			}
		}

		images.drawOnGrid();
		gui.view.window.updateUI();
	}

	/**
	 * 
	 * Loops through the buttons to find the selected button by the player, this way was easiest
	 * so I didn't have to store all the buttons, or have one giant method. It uses the button 
	 * groups made in createPlayers(), createRooms() and createWeapons().
	 * 
	 * @return
	 * 		Returns a tuple which contains the selected murderer, weapon and room. None of these 
	 * 		should ever be null.
	 */
	public Tuple findButtons() {
		Player murderer = null;
		Weapon murderWeapon = null;
		Room murderRoom = null;

		for (Enumeration<AbstractButton> buttons = controller.getBG1().getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();

			if (button.isSelected()) {
				// retrieves the player associated with the selected button
				murderer = checkPlayer(button.getText());
			}
		}

		for (Enumeration<AbstractButton> buttons = controller.getBG2().getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();

			if (button.isSelected()) {
				// retrieves the weapon associated with the selected button
				murderWeapon = checkWeapon(button.getText());

			}
		}

		for (Enumeration<AbstractButton> buttons = controller.getBG3().getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();

			if (button.isSelected()) {
				// retrieves the room associated with the selected button
				murderRoom = checkRoom(button.getText());
			}
		}

		// returns the players guess as a tuple
		return new Tuple(murderer, murderWeapon, murderRoom);
	}

	/**
	 * Allows for tiles to be highlighted in four way directions, as long as they are
	 * door tiles or hallway tiles
	 */
	public void highlightTiles() {
		
		Player player = game.getOrder().peek();
		Coordinate posOfPlayer = new Coordinate(player.getY(), player.getX());
		
		Direction dir = determineDirection(gui.getHoveredPos(), posOfPlayer);
		
		// clears tiles from previous turn
		clearTempTiles();
		
		if (dir != null || pathfinding) {
			
			int dist = determineDistance(dir, gui.getHoveredPos(), posOfPlayer);
			
			int rowDisp = dir == Direction.UP ? -1 : dir == Direction.DOWN ? 1 : 0;
			int colDisp = dir == Direction.LEFT ? -1 : dir == Direction.RIGHT ? 1 : 0;
			
			// gets position of mouse if pathfinding on otherwise get's players row/col as can only move in straight lines.
			int currI = pathfinding ? gui.getHoveredPos().getRow() : posOfPlayer.getRow();
			int currJ = pathfinding ? gui.getHoveredPos().getCol() : posOfPlayer.getCol();
			
			// only allows player to move in straight lines.
			if (!pathfinding) {
				for (int i = 0; i < dist; i++) {
					
					currI += rowDisp;
					currJ += colDisp;
					
				}
			}
			
			if (game.getBoard().getTile(currI, currJ).getType() == Tile.TileType.HALLWAY
					|| game.getBoard().getTile(currI, currJ).getType() == Tile.TileType.DOOR) {
			
				gui.view.getBoardGrid(currI, currJ).add(images.ghost);
			}
			
		}
		
		images.drawOnGrid();
		gui.view.window.updateUI();	
		
	}
	
	/**
	 * Makes the timer (if the move is valid) that moves the
	 * player to the destination of clicking
	 */
	public void makeMove() {
		
		Player player = game.getOrder().peek();
		Coordinate posOfPlayer = new Coordinate(player.getY(), player.getX());
		
		// gets the direction needed to move.
		Direction dir = determineDirection(gui.getClickedPos(), posOfPlayer);
		
		if (!pathfinding && dir != null) {
			
			clearTempTiles();
			
			// gets the amount of tiles to the clicked destination
			int dist = determineDistance(dir, gui.getClickedPos(), posOfPlayer);
			path = new Stack<Coordinate>();
			
			//creates the move
			setupMove(player, posOfPlayer, dir, dist);
			movePiece = true;
			movement.start();
			
		} else if (pathfinding && !gui.getClickedPos().equals(posOfPlayer)) {
			
			clearTempTiles();
			
			// gets the tiles required to get to the clicked destination
			Stack<Coordinate> coords = new AStar(game.getBoard(), posOfPlayer, gui.getClickedPos()).findHeuristicPath();
			if (coords == null) return;
			
			//creates the move
			setupMove(player, posOfPlayer, coords);
			movePiece = true;
			movement.start();
			
		}
		
	}

	/**
	 * clears the ghost tile upon moving
	 */
	private void clearTempTiles() {
		
		for (int i = 0; i < game.getBoard().getHeight(); i++)
			for (int j = 0; j < game.getBoard().getWidth(); j++)
				gui.view.getBoardGrid(i, j).remove(images.ghost);
		
	}
	
	/**
	 *  Gets the direction to the destination in relation to the player.
	 *  
	 * @param destination
	 * 		The coordinates of the clicked tile that the player wants to move to.
	 * @param player
	 * 		The coordinates of the player.
	 * @return
	 * 		Returns the direction that the destination is in.
	 */
	private Direction determineDirection(Coordinate destination, Coordinate player) {
		if (destination.getRow() == player.getRow() && destination.getCol() < player.getCol()) return Direction.LEFT;
		else if (destination.getRow() == player.getRow() && destination.getCol() > player.getCol()) return Direction.RIGHT;
		else if (destination.getRow() < player.getRow() && destination.getCol() == player.getCol()) return Direction.UP;
		else if (destination.getRow() > player.getRow() && destination.getCol() == player.getCol()) return Direction.DOWN;
		else return null;
	}
	
	/**
	 *  Gets the distance to the destination in relation to the player in tiles.
	 *  
	 * @param dir
	 * 		Direction of the move.
	 * @param destination
	 * 		The coordinates of the clicked tile that the player wants to move to.
	 * @param player
	 * 		The coordinates of the player.
	 * @return
	 */
	private int determineDistance(Direction dir, Coordinate destination, Coordinate player) {
		if (dir == Direction.LEFT || dir == Direction.RIGHT) return Math.abs(destination.getCol() - player.getCol());
		else if (dir == Direction.UP|| dir == Direction.DOWN) return Math.abs(destination.getRow() - player.getRow());
		else return 0;
	}
	
	/**
	 * Allows player to move in a given direction, for the distance of one tile.
	 * @param dir
	 * 		The direction to move in.
	 */
	public void moveWithKey(Direction dir) {
		Player player = game.getOrder().peek();
		Coordinate posOfPlayer = new Coordinate(player.getY(), player.getX());
		game.setMovesLeft(game.getMovesLeft() - game.performMove(new Move(1,dir), game.getMovesLeft(), player));
		
		if (game.getMovesLeft() > 0) {
			
			JLabel icon = images.getPlayerIcon(game.getOrder().peek().getInitial());
			gui.view.getBoardGrid(posOfPlayer.getRow(),posOfPlayer.getCol()).remove(icon);
			posOfPlayer = new Coordinate(player.getY(), player.getX());
			gui.view.getBoardGrid(posOfPlayer.getRow(),posOfPlayer.getCol()).add(icon);
			if(player.getRoom()!=null) {
				game.setMovesLeft(0);
			}
		}
		
		// checks whether there is any where for the player to move to.
		boolean trapped = game.playerTrapped(player, game.getMovesLeft());
		boolean turnEnd = (game.getMovesLeft() <= 0 || trapped) && player.getRoom() == null;
		if (turnEnd) {
			
			if (trapped && game.getMovesLeft() > 0) {
				JOptionPane.showMessageDialog(gui.view.window, "You have trapped yourself! Your turn has been skipped.", "Oh no!", JOptionPane.ERROR_MESSAGE);
			}
			
			game.setMovesLeft(0);
			images.drawOnGrid();
			gui.view.window.updateUI();
			gui.view.nextTurn();
		}
		
		images.drawOnGrid();
		gui.view.window.remove(gui.view.bar);
		gui.control.createButtonBar();
		gui.view.window.updateUI();
	}
	
	/**
	 * 
	 * @param 
	 * 		The string from the selected button, contains a player name.
	 * @return
	 * 		Returns the player object associated with the given player name.
	 */
	public Player checkPlayer(String player) {
		player = player.toLowerCase();
		if(player.equals("scarlett")) {
			return game.getPlayers().get(0);
		}
		if(player.equals("mustard")) {
			return game.getPlayers().get(1);
		}
		if(player.equals("white")) {
			return game.getPlayers().get(2);
		}
		if(player.equals("green")) {
			return game.getPlayers().get(3);
		}
		if(player.equals("peacock")) {
			return game.getPlayers().get(4);
		}
		if(player.equals("plum")) {
			return game.getPlayers().get(5);
		}	

		return null;
	}

	/**
	 * 
	 * @param 
	 * 		The string from the selected button, contains a weapon name.
	 * @return
	 * 		Returns the weapon object associated with the given weapon name.
	 */
	public Weapon checkWeapon(String weapon) {
		weapon = weapon.toLowerCase();
		if(weapon.equals("candlestick")) {
			return game.getWeapons().get(0);
		}
		if(weapon.equals("dagger")) {
			return game.getWeapons().get(1);

		}
		if(weapon.equals("lead pipe")) {
			return game.getWeapons().get(2);
		}
		if(weapon.equals("revolver")) {
			return game.getWeapons().get(3);
		}
		if(weapon.equals("rope")) {
			return game.getWeapons().get(4);
		}
		if(weapon.equals("spanner")) {
			return game.getWeapons().get(5);
		}	

		return null;
	}

	/**
	 * 
	 * @param 
	 * 		The string from the selected button, contains a room name.
	 * @return
	 * 		Returns the room object associated with the given room name.
	 */
	public Room checkRoom(String room) {

		room = room.toLowerCase();
		if(room.equals("dining room")) {
			return game.getRooms().get(0);
		}
		if(room.equals("ball room")) {
			return game.getRooms().get(1);
		}
		if(room.equals("billiard room")) {
			return game.getRooms().get(2);
		}
		if(room.equals("conservatory")) {
			return game.getRooms().get(3);
		}
		if(room.equals("kitchen")) {
			return game.getRooms().get(4);
		}
		if(room.equals("library")) {
			return game.getRooms().get(5);
		}	
		if(room.equals("hall")) {
			return game.getRooms().get(6);
		}
		if(room.equals("study")) {
			return game.getRooms().get(7);
		}	
		if(room.equals("lounge")) {
			return game.getRooms().get(8);
		}	


		return null;
	}


	/**
	 * 
	 * Method used to get what is supposed to be in the status/ text box in the button bar/ under the
	 * picture of the current player.
	 * 
	 * @param name
	 * 		Used to check whether want the line about players name, or their amount of moves left.
	 * @return
	 * 		Returns a the status string based on whether name is true or false.
	 */
	String getGameStatus(boolean name) {
		if(name) {
			return game.getOrder().isEmpty() ? "" :"It is " + game.getOrder().peek().getName() + "'s turn.";
		}
		else {	
			if(!(game.getOrder().isEmpty()) && game.getOrder().peek().getRoom()!=null) {
				return "You are in a room, choose a button!:";
			}
			return game.getOrder().isEmpty() ? "" : "You have " + game.getMovesLeft() + " move" + (game.getMovesLeft() == 1 ? "" : "s") + " left!";
		}
	}

	boolean shouldBeWall(Tile t) {
		return t == null || t.getType() == Tile.TileType.HALLWAY || t.getType() == Tile.TileType.NULL;
	}

	boolean shouldBeRoom(Tile t) {
		return t != null && (t.getType() == Tile.TileType.ROOM || t.getType() == Tile.TileType.DOOR);
	}

	boolean shouldBeDoorWall(Tile t, DoorTile dt, Direction dir) {
		return t != null && t.getType() == Tile.TileType.HALLWAY && dt.getEntryDir() != dir;
	}

	boolean shouldBeCorner(Board b, Tile t, int i, int j) {
		if (shouldBeRoom(t)) {
			Tile corner = b.getTile(i, j);
			return corner != null && shouldBeWall(corner);
		}
		return false;
	}

	
	/**
	 * Set up for a new timer
	 * @param p
	 * 		player
	 * @param c
	 * 		coordinate of player
	 * @param d
	 * 		direction of movement
	 * @param i
	 * 		movesleft/steps to go
	 */
	public void setupMove(Player p, Coordinate c, Direction d, int i) {
		player = p;
		posOfPlayer = c;
		dir = d;
		toGo = i;
	}
	
	/**
	 * Set up for a new timer (for A*)
	 * @param player
	 * 		the player
	 * @param posOfPlayer
	 * 		their initial position
	 * @param coords
	 * 		the coordinate pathway as a stack
	 */
	private void setupMove(Player p, Coordinate c, Stack<Coordinate> coords) {
		player = p;
		posOfPlayer = c;
		path = coords;
		toGo = coords.size();
	}
	
	/**
	 * Sets up the timer for animations upon moving.
	 */
	public void setupTimer() {
		
		movement = new Timer(100, new ActionListener(){
			
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	
		    	if (movePiece) {
		    		
		    		boolean pfEnd = pathfinding && path.isEmpty();
		    		
		    		if (!pfEnd) {
		    			if (pathfinding) {
			    			Coordinate newPos = path.pop();
			    			dir = determineDirection(newPos, posOfPlayer);
			    		}
			    		
			    		game.setMovesLeft(game.getMovesLeft() - game.performMove(new Move(1,dir), game.getMovesLeft(), player));
						toGo--;
						
						if (game.getMovesLeft() > 0 && toGo > 0) {
							
							// moves the player one square 
							JLabel icon = images.getPlayerIcon(player.getInitial());
							gui.view.getBoardGrid(posOfPlayer.getRow(),posOfPlayer.getCol()).remove(icon);	
							posOfPlayer = new Coordinate(player.getY(), player.getX());
							gui.view.getBoardGrid(posOfPlayer.getRow(),posOfPlayer.getCol()).add(icon);
							
						} else {
							movePiece = false;
						}
		    		} else movePiece = false;
		    		
				
		    		// updates the display.
					images.drawOnGrid();
					gui.view.window.remove(gui.view.bar);
					gui.control.createButtonBar();
					gui.view.window.updateUI();
					
					// checks if player cannot move anywhere.
					boolean trapped = game.playerTrapped(player, game.getMovesLeft());
					if (game.getMovesLeft() <= 0 || trapped || !player.isInPlay()) {
						
						if (trapped && game.getMovesLeft() > 0 && player.getRoom() == null) {
							JOptionPane.showMessageDialog(gui.view.window, "You have trapped yourself! Your turn has been skipped.", "Oh no!", JOptionPane.ERROR_MESSAGE);
						}
						
						game.setMovesLeft(0);
						gui.view.nextTurn();
					}
					
					
		    	} else { //happens upon movesLeft equaling 0 or destination being reached.
		    		movement.stop();
		    		images.drawOnGrid();
					gui.view.window.remove(gui.view.bar);
					gui.control.createButtonBar();
					gui.view.window.updateUI();
		    	}

		    }
		    
		});
		movement.setInitialDelay(0);
		movement.setRepeats(true);
		
	}
	
	/**
	 *  Turns path finding on/ off, when off player's can only move in straight lines.
	 */
	public void togglePathFinding() {
		pathfinding = !pathfinding;
	}

}