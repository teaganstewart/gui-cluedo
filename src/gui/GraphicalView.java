package gui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import swen225.*;

/**
 * A class for all the ImageIcon's and the method's that choose what one is required in
 * an instance.
 * 
 * @author Ethan Munn, Teagan Stewart
 *
 */
public class GraphicalView {
	private Game game;
	private Gui gui;

	public GraphicalView(Game g, Gui gui) {
		game = g;
		this.gui = gui;
	}

	/**
	 * A method that both resets all of the players and weapons on a grid, then draws
	 * them in their correct position.
	 * 
	 */
	void drawOnGrid() {

		// resets the board grid and adds the players to the board.
		for (int i = 0; i < game.getBoard().getHeight(); i++) {

			for (int j = 0; j < game.getBoard().getWidth(); j++) {
			
				JLabel player = checkForPlayer(j,i);
				if (player != null) {
					gui.view.getBoardGrid(i, j).add(player);
				}
				
				try {
					gui.view.getBoardGrid(i,j).remove(1);
				}
				catch(ArrayIndexOutOfBoundsException e) {

				}

			}

		}

		// loops through all the rooms on the board
		for (Room room : game.getRooms()) {
			// adds the labels for the rooms
			showRoomLabels(room, game.getBoard().showRoomLabels());

			int count = 0;
			int rowsDown = count/4;
			int colsAcross = count%4;

			// displays all the players that are in the room
			for (Player p : room.getPlayers()) {
				// gets the player's icon
				JLabel player = getPlayerIcon(p.getInitial());
				
				//removes player/weapon if they already exist in this location so they don't go on top of each other
				try {
					gui.view.getBoardGrid(room.getRowStart()+rowsDown, room.getColStart()+colsAcross).remove(1);
				}
				catch(ArrayIndexOutOfBoundsException e) {

				}
				
				//adds players to grid
				gui.view.getBoardGrid(room.getRowStart()+rowsDown, room.getColStart()+colsAcross).add(player);

				count++;
				rowsDown = count/4;
				colsAcross = count%4;
			}
			
			// displays all the weapons that are in the room
			for (Weapon w : room.getWeapons()) {
				JLabel weapon = getWeaponIcon(w.getInitial());
				
				//removes player/weapon if they already exist in this location so they don't go on top of each other
				try {
					gui.view.getBoardGrid(room.getRowStart()+rowsDown, room.getColStart()+colsAcross).remove(1);
				}
				catch(ArrayIndexOutOfBoundsException e) {

				}

				// adds weapon to grid
				gui.view.getBoardGrid(room.getRowStart()+rowsDown, room.getColStart()+colsAcross).add(weapon);

				count++;
				rowsDown = count/4;
				colsAcross = count%4;
			}

		}
	}

	/**
	 * 	Allows our other methods to check where the players are/ if a player is on a tile.
	 * 
	 * @param x
	 * 		x-coordinate value of tile we are checking.
	 * @param y
	 * 		y-coordinate value of tile we are checking.
	 * @return
	 * 		Returns the image of the player that should be on the tile at the give coordinates.
	 */
	private JLabel checkForPlayer(int x, int y) {
		for (Player p : game.getPlayers()) {
			if (p.getX() == x && p.getY() == y && p.getRoom() == null) {
				return getPlayerIcon(p.getInitial());
			}
		}
		return null;
	}


	/**
	 * 	Used to create the board grid.
	 * 
	 * @param i
	 * 		x-coordinate value of tile we are checking.
	 * @param j
	 * 		y-coordinate value of tile we are checking.
	 * @param b
	 * 		The current board
	 * @return
	 * 		Returns the correct image icon for this tile.
	 */
	ImageIcon getImageIcon(int i, int j, Board b) {
		Tile[][] tiles = b.getTiles();
		Tile up = b.getTile(i-1, j);
		Tile left = b.getTile(i, j-1);
		Tile down = b.getTile(i+1, j);
		Tile right = b.getTile(i, j+1);

		if(tiles[i][j].getType()==Tile.TileType.DOOR) {

			// can cast here since it is safe
			DoorTile dt = (DoorTile) tiles[i][j];
			if (gui.model.shouldBeDoorWall(left, dt, Direction.RIGHT)) return roomLeft;
			else if (gui.model.shouldBeDoorWall(right, dt, Direction.LEFT)) return roomRight;

			return room;

		} else if (tiles[i][j].getType()==Tile.TileType.ROOM) {
			if (gui.model.shouldBeRoom(up)) {	
				if (gui.model.shouldBeCorner(b, left, i-1, j-1)) return roomUpLeftIn;
				if (gui.model.shouldBeCorner(b, right, i-1, j+1)) return roomUpRightIn;
			}

			if (gui.model.shouldBeRoom(down)) {	
				if (gui.model.shouldBeCorner(b, left, i+1, j-1)) return roomDownLeftIn;
				if (gui.model.shouldBeCorner(b, right, i+1, j+1)) return roomDownRightIn;
			}

			if (gui.model.shouldBeWall(up)) {
				if (gui.model.shouldBeWall(left)) return roomUpLeft;
				else if (gui.model.shouldBeWall(right)) return roomUpRight;
				else return roomUp;
			} else if (gui.model.shouldBeWall(down)) {
				if (gui.model.shouldBeWall(left)) return roomDownLeft;
				else if (gui.model.shouldBeWall(right)) return roomDownRight;
				else return roomDown;
			} 
			else if (gui.model.shouldBeWall(left)) return roomLeft;
			else if (gui.model.shouldBeWall(right)) return roomRight;

			// if none of the above cases pass, should just return the regular room tile
			return room;

		}
		else if(tiles[i][j].getType()==Tile.TileType.NULL) return nullTile; 
		else if(tiles[i][j].getType()==Tile.TileType.HALLWAY) return hallway;
		else return null;

	}

	/**
	 *  A method that displays the room labels.
	 * 
	 * @param room
	 * 		The room that we are displaying the room labels in.
	 * @param show
	 * 		Whether the labels should be shown or not on the board.
	 */	
	void showRoomLabels(Room room, boolean show) {
		switch(room.getInitial()) {
		case "DR":
			if (show) for (int i = 0; i < dining.length; i++) gui.view.boardGrid[15][2+i].add(dining[i]);
			else for (int i = 0; i < dining.length; i++) gui.view.boardGrid[15][2+i].remove(dining[i]);
			return;
		case "KI":
			if (show) for (int i = 0; i < kitchen.length; i++) gui.view.boardGrid[1][2+i].add(kitchen[i]);
			else for (int i = 0; i < kitchen.length; i++) gui.view.boardGrid[1][2+i].remove(kitchen[i]);
			return;
		case "CO":
			if (show) for (int i = 0; i < conserv.length; i++) gui.view.boardGrid[1][19+i].add(conserv[i]);
			else for (int i = 0; i < conserv.length; i++) gui.view.boardGrid[1][19+i].remove(conserv[i]);			
			return;
		case "BA":
			if (show) for (int i = 0; i < ballroom.length; i++) gui.view.boardGrid[1][10+i].add(ballroom[i]);
			else for (int i = 0; i < ballroom.length; i++) gui.view.boardGrid[1][10+i].remove(ballroom[i]);
			return;
		case "HA":
			if (show) for (int i = 0; i < hall.length; i++) gui.view.boardGrid[24][11+i].add(hall[i]);
			else for (int i = 0; i < hall.length; i++) gui.view.boardGrid[24][11+i].remove(hall[i]);
			return;
		case "ST":
			if (show) for (int i = 0; i < study.length; i++) gui.view.boardGrid[24][20+i].add(study[i]);
			else for (int i = 0; i < study.length; i++) gui.view.boardGrid[24][20+i].remove(study[i]);
			return;
		case "LI":
			if (show) for (int i = 0; i < library.length; i++) gui.view.boardGrid[18][20+i].add(library[i]);
			else for (int i = 0; i < library.length; i++) gui.view.boardGrid[18][20+i].remove(library[i]);
			return;
		case "LO":
			if (show) for (int i = 0; i < lounge.length; i++) gui.view.boardGrid[24][2+i].add(lounge[i]);
			else for (int i = 0; i < lounge.length; i++) gui.view.boardGrid[24][2+i].remove(lounge[i]);
			return;
		case "BI":
			if (show) for (int i = 0; i < billiard.length; i++) gui.view.boardGrid[8][19+i].add(billiard[i]);
			else for (int i = 0; i < billiard.length; i++) gui.view.boardGrid[8][19+i].remove(billiard[i]);
			return;
    	}
	}

	/**
	 * Gets the player's icon.
	 * 
	 * @param in
	 * 		The initial of the player.
	 * @return
	 * 		The image of the player.
	 */
	JLabel getPlayerIcon(String in) {
		switch(in) {
		case "S":
			return scarlett;
		case "M":
			return mustard;
		case "W":
			return white;
		case "G":
			return green;
		case "B":
			return peacock;
		case "P":
			return plum;
		default:
			return null;
		}
	}

	/**
	 * Gets the weapon's icon.
	 * 
	 * @param in
	 * 		The initial of the weapon
	 * @return
	 * 		The image of the weapon.
	 */
	private JLabel getWeaponIcon(String in) {
		switch(in) {
		case "CS":
			return candlestick;
		case "DA":
			return dagger;
		case "LP":
			return leadpipe;
		case "RE":
			return revolver;
		case "RO":
			return rope;
		case "SP":
			return spanner;
		default:
			return null;
		}
	}

	/**
	 *  Gets the player's card.
	 *  
	 * @param player
	 * 		The player who's card we want
	 * @return
	 * 		Returns the ImageIcon of the given player's card.
	 */
	public ImageIcon getPlayerCard(String player) {
		if(player.equals(game.getPlayers().get(0).toString())) {
			return scarlettCard;
		}
		if(player.equals(game.getPlayers().get(1).toString())) {
			return mustardCard;
		}
		if(player.equals(game.getPlayers().get(2).toString())) {
			return whiteCard;
		}
		if(player.equals(game.getPlayers().get(3).toString())) {
			return greenCard;
		}
		if(player.equals(game.getPlayers().get(4).toString())) {
			return peacockCard;
		}
		if(player.equals(game.getPlayers().get(5).toString())) {
			return plumCard;
		}
		return null;

	}
	
	/**
	 *  Gets the weapon's card.
	 *  
	 * @param weapon
	 * 		The weapon who's card we want
	 * @return
	 * 		Returns the ImageIcon of the given weaons's card.
	 */
	public ImageIcon getWeaponCard(String weapon) {
		if(weapon.equals(game.getWeapons().get(0).toString())) {
			return candlestickCard;
		}
		if(weapon.equals(game.getWeapons().get(1).toString())) {
			return daggerCard;
		}
		if(weapon.equals(game.getWeapons().get(2).toString())) {
			return leadpipeCard;
		}
		if(weapon.equals(game.getWeapons().get(3).toString())) {
			return revolverCard;
		}
		if(weapon.equals(game.getWeapons().get(4).toString())) {
			return ropeCard;
		}
		if(weapon.equals(game.getWeapons().get(5).toString())) {
			return spannerCard;
		}
		return null;
	}

	/**
	 *  Gets the rooms's card.
	 *  
	 * @param room
	 * 		The room who's card we want
	 * @return
	 * 		Returns the ImageIcon of the given room's card.
	 */
	public ImageIcon getRoomCard(String room) {
		if(room.equals(game.getRooms().get(0).toString())) {
			return diningCard;
		}
		if(room.equals(game.getRooms().get(1).toString())) {
			return billiardCard;
		}
		if(room.equals(game.getRooms().get(2).toString())) {
			return ballroomCard;
		}
		if(room.equals(game.getRooms().get(3).toString())) {
			return conservatoryCard;
		}
		if(room.equals(game.getRooms().get(4).toString())) {
			return kitchenCard;
		}
		if(room.equals(game.getRooms().get(5).toString())) {
			return libraryCard;
		}
		if(room.equals(game.getRooms().get(6).toString())) {
			return hallCard;
		}
		if(room.equals(game.getRooms().get(7).toString())) {
			return studyCard;
		}
		if(room.equals(game.getRooms().get(8).toString())) {
			return loungeCard;
		}
		return null;
	}

	/**
	 * Gets the dice icon.
	 * 
	 * @param diceRoll
	 * 		The number of the dice roll, made in createHUD()
	 * @return
	 * 		Returns the image of the dice corresponding to the given dice roll.
	 */
	ImageIcon getDiceIcon(int diceRoll) {
		// can change to a switch case if we want to.
		if(diceRoll == 1) {
			return dice1;
		}
		if(diceRoll == 2) {
			return dice2;
		}
		if(diceRoll == 3) {
			return dice3;
		}
		if(diceRoll == 4) {
			return dice4;
		}
		if(diceRoll == 5) {
			return dice5;
		}
		else {
			return dice6;
		}

	}

	/**
	 * Works out what players turn it is, then returns the image of their head to use in the 
	 * button bar on the side.
	 * 
	 * @return
	 * 		Returns image of current players character's head.
	 */
	ImageIcon getHeadIcon() {
		if(!game.getOrder().isEmpty()) {
			switch(game.getOrder().peek().getPlayer()) {
			case SCARLETT:
				return scarlettHead;
			case MUSTARD:
				return mustardHead;
			case WHITE:
				return whiteHead;
			case GREEN:
				return greenHead;
			case PEACOCK:
				return peacockHead;
			case PLUM:
				return plumHead;
			default:
				return null;
			}
		}
		return null;

	}
	
	//------------------------------------//
	// ALL OF THE IMAGES USED IN THE GAME //
	//------------------------------------//
	
	private final ImageIcon nullTile = new ImageIcon(getClass().getResource("../icon/red.png"));
	private final ImageIcon hallway = new ImageIcon(getClass().getResource("../icon/sand.png"));

	private final String roomDirectory = "../icon/roomtiles/";
	private final ImageIcon room = new ImageIcon(getClass().getResource(roomDirectory + "normal.png"));
	private final ImageIcon roomUp = new ImageIcon(getClass().getResource(roomDirectory + "up.png"));
	private final ImageIcon roomLeft = new ImageIcon(getClass().getResource(roomDirectory + "left.png"));
	private final ImageIcon roomDown = new ImageIcon(getClass().getResource(roomDirectory + "down.png"));
	private final ImageIcon roomRight = new ImageIcon(getClass().getResource(roomDirectory + "right.png"));
	private final ImageIcon roomUpLeft = new ImageIcon(getClass().getResource(roomDirectory + "up-left.png"));
	private final ImageIcon roomUpRight = new ImageIcon(getClass().getResource(roomDirectory + "up-right.png"));
	private final ImageIcon roomDownLeft = new ImageIcon(getClass().getResource(roomDirectory + "down-left.png"));
	private final ImageIcon roomDownRight = new ImageIcon(getClass().getResource(roomDirectory + "down-right.png"));
	private final ImageIcon roomUpLeftIn = new ImageIcon(getClass().getResource(roomDirectory + "up-left-in.png"));
	private final ImageIcon roomUpRightIn = new ImageIcon(getClass().getResource(roomDirectory + "up-right-in.png"));
	private final ImageIcon roomDownLeftIn = new ImageIcon(getClass().getResource(roomDirectory + "down-left-in.png"));
	private final ImageIcon roomDownRightIn = new ImageIcon(getClass().getResource(roomDirectory + "down-right-in.png"));

	private final String charDirectory = "../icon/chars/";
	private final JLabel scarlett = new JLabel(new ImageIcon(getClass().getResource(charDirectory + "scarlett.png")));
	private final JLabel mustard = new JLabel(new ImageIcon(getClass().getResource(charDirectory + "mustard.png")));
	private final JLabel white = new JLabel(new ImageIcon(getClass().getResource(charDirectory + "white.png")));
	private final JLabel green = new JLabel(new ImageIcon(getClass().getResource(charDirectory + "green.png")));
	private final JLabel peacock = new JLabel(new ImageIcon(getClass().getResource(charDirectory + "peacock.png")));
	private final JLabel plum = new JLabel(new ImageIcon(getClass().getResource(charDirectory + "plum.png")));
	public final JLabel ghost = new JLabel(new ImageIcon(getClass().getResource(charDirectory + "ghost.png")));
	public final JLabel trap = new JLabel(new ImageIcon(getClass().getResource(charDirectory + "trap.png")));
	
	public final ImageIcon scarlettHead = new ImageIcon(getClass().getResource(charDirectory + "Scarlett-Headshot.png"));
	private final ImageIcon mustardHead = new ImageIcon(getClass().getResource(charDirectory + "Mustard-Headshot.png"));
	private final ImageIcon whiteHead = new ImageIcon(getClass().getResource(charDirectory + "White-Headshot.png"));
	private final ImageIcon greenHead = new ImageIcon(getClass().getResource(charDirectory + "Green-Headshot.png"));
	private final ImageIcon peacockHead = new ImageIcon(getClass().getResource(charDirectory + "Peacock-Headshot.png"));
	private final ImageIcon plumHead = new ImageIcon(getClass().getResource(charDirectory + "Plum-Headshot.png"));

	private final String diceDirectory = "../icon/dice/";
	private final ImageIcon dice1 = new ImageIcon(getClass().getResource(diceDirectory + "dice1.png"));
	private final ImageIcon dice2 = new ImageIcon(getClass().getResource(diceDirectory + "dice2.png"));
	private final ImageIcon dice3 = new ImageIcon(getClass().getResource(diceDirectory + "dice3.png"));
	private final ImageIcon dice4 = new ImageIcon(getClass().getResource(diceDirectory + "dice4.png"));
	private final ImageIcon dice5 = new ImageIcon(getClass().getResource(diceDirectory + "dice5.png"));
	private final ImageIcon dice6 = new ImageIcon(getClass().getResource(diceDirectory + "dice6.png"));

	private final String weapDirectory = "../icon/weapons/";
	private final JLabel candlestick = new JLabel(new ImageIcon(getClass().getResource(weapDirectory + "candlestick.png")));
	private final JLabel dagger = new JLabel(new ImageIcon(getClass().getResource(weapDirectory + "dagger.png")));
	private final JLabel leadpipe = new JLabel(new ImageIcon(getClass().getResource(weapDirectory + "leadpipe.png")));
	private final JLabel revolver = new JLabel(new ImageIcon(getClass().getResource(weapDirectory + "revolver.png")));
	private final JLabel rope = new JLabel(new ImageIcon(getClass().getResource(weapDirectory + "rope.png")));
	private final JLabel spanner =  new JLabel(new ImageIcon(getClass().getResource(weapDirectory + "spanner.png")));

	private final String cardsDirectory = "../icon/cards/";
	private final ImageIcon scarlettCard= new ImageIcon(getClass().getResource(cardsDirectory + "scarlett-card.png"));
	private final ImageIcon mustardCard= new ImageIcon(getClass().getResource(cardsDirectory + "mustard-card.png"));
	private final ImageIcon whiteCard= new ImageIcon(getClass().getResource(cardsDirectory + "white-card.png"));
	private final ImageIcon greenCard = new ImageIcon(getClass().getResource(cardsDirectory + "green-card.png"));
	private final ImageIcon peacockCard= new ImageIcon(getClass().getResource(cardsDirectory + "peacock-card.png"));
	private final ImageIcon plumCard= new ImageIcon(getClass().getResource(cardsDirectory + "plum-card.png"));

	private final ImageIcon candlestickCard= new ImageIcon(getClass().getResource(cardsDirectory + "candlestick-card.png"));
	private final ImageIcon daggerCard= new ImageIcon(getClass().getResource(cardsDirectory + "dagger-card.png"));
	private final ImageIcon leadpipeCard= new ImageIcon(getClass().getResource(cardsDirectory + "leadpipe-card.png"));
	private final ImageIcon revolverCard= new ImageIcon(getClass().getResource(cardsDirectory + "revolver-card.png"));
	private final ImageIcon ropeCard= new ImageIcon(getClass().getResource(cardsDirectory + "rope-card.png"));
	private final ImageIcon spannerCard= new ImageIcon(getClass().getResource(cardsDirectory + "spanner-card.png"));

	private final ImageIcon diningCard= new ImageIcon(getClass().getResource(cardsDirectory + "dining-card.png"));
	private final ImageIcon billiardCard= new ImageIcon(getClass().getResource(cardsDirectory + "billiard-card.png"));
	private final ImageIcon ballroomCard= new ImageIcon(getClass().getResource(cardsDirectory + "ballroom-card.png"));
	private final ImageIcon conservatoryCard= new ImageIcon(getClass().getResource(cardsDirectory + "conserv-card.png"));
	private final ImageIcon kitchenCard= new ImageIcon(getClass().getResource(cardsDirectory + "kitchen-card.png"));
	private final ImageIcon libraryCard= new ImageIcon(getClass().getResource(cardsDirectory + "library-card.png"));
	private final ImageIcon hallCard= new ImageIcon(getClass().getResource(cardsDirectory + "hall-card.png"));
	private final ImageIcon studyCard= new ImageIcon(getClass().getResource(cardsDirectory + "study-card.png"));
	private final ImageIcon loungeCard= new ImageIcon(getClass().getResource(cardsDirectory + "lounge-card.png"));

	private final String rlDirectory = "../icon/roomlabels/";
	private final JLabel[] ballroom = { new JLabel(new ImageIcon(getClass().getResource(rlDirectory + "ballroom0.png"))),
										new JLabel(new ImageIcon(getClass().getResource(rlDirectory + "ballroom1.png"))),
										new JLabel(new ImageIcon(getClass().getResource(rlDirectory + "ballroom2.png"))),
										new JLabel(new ImageIcon(getClass().getResource(rlDirectory + "ballroom3.png")))};
	private final JLabel[] billiard = { new JLabel(new ImageIcon(getClass().getResource(rlDirectory + "billiard0.png"))),
								 		new JLabel(new ImageIcon(getClass().getResource(rlDirectory + "billiard1.png"))),
								 		new JLabel(new ImageIcon(getClass().getResource(rlDirectory + "billiard2.png"))),
								 		new JLabel(new ImageIcon(getClass().getResource(rlDirectory + "billiard3.png")))};
	private final JLabel[] dining = { 	new JLabel(new ImageIcon(getClass().getResource(rlDirectory + "dining0.png"))),
								 		new JLabel(new ImageIcon(getClass().getResource(rlDirectory + "dining1.png"))),
								 		new JLabel(new ImageIcon(getClass().getResource(rlDirectory + "dining2.png")))};
	private final JLabel[] kitchen = {	new JLabel(new ImageIcon(getClass().getResource(rlDirectory + "kitchen0.png"))),
										new JLabel(new ImageIcon(getClass().getResource(rlDirectory + "kitchen1.png")))};
	private final JLabel[] conserv = {	new JLabel(new ImageIcon(getClass().getResource(rlDirectory + "conserv0.png"))),
								 		new JLabel(new ImageIcon(getClass().getResource(rlDirectory + "conserv1.png"))),
								 		new JLabel(new ImageIcon(getClass().getResource(rlDirectory + "conserv2.png"))),
								 		new JLabel(new ImageIcon(getClass().getResource(rlDirectory + "conserv3.png")))};
	private final JLabel[] hall = {		new JLabel(new ImageIcon(getClass().getResource(rlDirectory + "hall0.png"))),
								 		new JLabel(new ImageIcon(getClass().getResource(rlDirectory + "hall1.png")))};
	private final JLabel[] study = {	new JLabel(new ImageIcon(getClass().getResource(rlDirectory + "study0.png"))),
								 		new JLabel(new ImageIcon(getClass().getResource(rlDirectory + "study1.png")))};
	private final JLabel[] lounge = {	new JLabel(new ImageIcon(getClass().getResource(rlDirectory + "lounge0.png"))),
								 		new JLabel(new ImageIcon(getClass().getResource(rlDirectory + "lounge1.png")))};
	private final JLabel[] library = {	new JLabel(new ImageIcon(getClass().getResource(rlDirectory + "library0.png"))),
										new JLabel(new ImageIcon(getClass().getResource(rlDirectory + "library1.png")))};
	
}
