package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import swen225.*;


/**
 * A class for the controller part of model-view-controller, has all of the methods that 
 * require input, or are needed for the input to work.
 * 
 * @author Ethan Munn, Teagan Stewart
 *
 */
public class Controller {
	private Game game;
	private Gui gui;
	ButtonGroup bg1;
	ButtonGroup bg2;
	ButtonGroup bg3;
	GraphicalView images;
	View view;


	public Controller(Game g, Gui gui, GraphicalView images, View v) {
		game = g;
		this.gui = gui;
		this.images = images;
		view = v;
	}

	/**
	 *  Gets the button group that chooses players.
	 *  
	 * @return
	 * 		The needed button group.
	 */
	public ButtonGroup getBG1() {
		return bg1;

	}

	/**
	 *  Gets the button group that chooses weapons.
	 *  
	 * @return
	 * 		The needed button group.
	 */
	public ButtonGroup getBG2() {
		return bg2;
	}

	/**
	 *  Gets the button group that chooses rooms.
	 *  
	 * @return
	 * 		The needed button group.
	 */
	public ButtonGroup getBG3() {
		return bg3;
	}

	/** taken from a link */
	/**
	 * Makes the menu bar, and adds all of the tabs/ drop down menu's and adds their functions.
	 * 
	 */
	void createMenuBar() {

		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");
		JMenu gameMenu = new JMenu("Game");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		gameMenu.setMnemonic(KeyEvent.VK_G);

		JMenuItem startMenuItem = new JMenuItem("Reset  (Alt + F + R)");
		startMenuItem.setMnemonic(KeyEvent.VK_R);
		startMenuItem.setToolTipText("Restarts the Game");
		startMenuItem.addActionListener((event) -> gui.initGameDisp());

		JMenuItem eMenuItem = new JMenuItem("Exit    (Alt + F + E)");
		eMenuItem.setMnemonic(KeyEvent.VK_E);
		eMenuItem.setToolTipText("Exits the Game");
		eMenuItem.addActionListener((event) -> {
			
			int prompt = JOptionPane.showConfirmDialog(null, "Are you sure you would like to exit?", "Quit Game", JOptionPane.YES_NO_OPTION);
	        if (prompt == JOptionPane.YES_OPTION) {
	        	JOptionPane.showMessageDialog(null, "Thanks for playing! :)", "Byee!", JOptionPane.PLAIN_MESSAGE);
	        	System.exit(0);
	        }
			
		});

		JCheckBoxMenuItem randomItem = new JCheckBoxMenuItem("Pick Random Card   (Alt + G + R)", true);
		randomItem.addActionListener((event) -> gui.game.togglePickRandom());
		randomItem.setSelected(gui.game.pickingRandom());
		randomItem.setMnemonic(KeyEvent.VK_R);

		JCheckBoxMenuItem roomLabelItem = new JCheckBoxMenuItem("Toggle Labels          (Alt + G + T)", true);
		roomLabelItem.setMnemonic(KeyEvent.VK_T);
		roomLabelItem.addActionListener((event) -> {

			if (game != null && game.getBoard() != null) {
				game.getBoard().toggleLabels();
				images.drawOnGrid();
				view.window.updateUI();
			} else {
				roomLabelItem.setSelected(true);
			}

		});

		//toggles path finding on and off, set to false by default.
		JCheckBoxMenuItem pathFindingItem = new JCheckBoxMenuItem("Pathfinding Moves  (Alt + G + M)", true);
		pathFindingItem.setMnemonic(KeyEvent.VK_M);
		pathFindingItem.addActionListener((event) -> gui.model.togglePathFinding());
		pathFindingItem.setSelected(gui.model.pathfinding);
		
		fileMenu.add(startMenuItem);
		fileMenu.add(eMenuItem);
		gameMenu.add(randomItem);
		gameMenu.add(roomLabelItem);
		gameMenu.add(pathFindingItem);
		menuBar.add(fileMenu);
		menuBar.add(gameMenu);
		gui.setJMenuBar(menuBar);

	}

	/**
	 * Creates the button bar that is on the right side of the window, stores the buttons, a status
	 * text box and a picture of the current player's character.
	 * 
	 * @return
	 * 		Returns a jpanel full off these buttons.
	 */
	JPanel createButtonBar() {

		view.bar = new JPanel(new GridLayout(2,1));
		view.buttonBar = new JPanel(new GridLayout(4,1,0,4));

		JButton suggest = new JButton("Suggest");
		suggest.setBackground(Color.WHITE);
		suggest.setForeground(new Color(97,8,17));
		suggest.setFocusPainted(false);
		suggest.setFont(new Font("Tahoma", Font.PLAIN, 20));
		suggest.addActionListener((event) ->  {
			initSuggestion();
			gui.setFocusable(true);
		});
		JButton accuse = new JButton("Accuse");
		accuse.setBackground(Color.WHITE);
		accuse.setForeground(new Color(97,8,17));
		accuse.setFocusPainted(false);
		accuse.setFont(new Font("Tahoma", Font.PLAIN, 20));
		accuse.addActionListener((event) -> {
			initAccusation(); 
			gui.setFocusable(true);
		});
		JButton nextTurn = new JButton("Next Turn");
		nextTurn.setBackground(Color.WHITE);
		nextTurn.setForeground(new Color(97,8,17));
		nextTurn.setFocusPainted(false);
		nextTurn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nextTurn.addActionListener((event) -> {
			
			if (game.getOrder().peek().getRoom() != null) {
				view.check = true;
				gui.setFocusable(true);
				game.setMovesLeft(0);
				view.nextTurn();
				
			}
			
		});

		JPanel status = new JPanel(new GridLayout(2,1));
		Border grayLine = BorderFactory.createLineBorder(Color.GRAY);
		status.add(new JLabel(gui.model.getGameStatus(true), SwingConstants.CENTER));

		status.add(new JLabel(gui.model.getGameStatus(false), SwingConstants.CENTER));
		status.setBorder(grayLine);


		view.buttonBar.add(status);
		view.buttonBar.add(suggest);
		view.buttonBar.add(accuse);
		view.buttonBar.add(nextTurn);


		view.bar.add(new JLabel(images.getHeadIcon()!=null ? images.getHeadIcon() : images.scarlettHead));
		view.window.updateUI();
		view.bar.add(view.buttonBar);
		view.bar.setBounds(gui.getWidth()*11/16-2, 12, gui.getWidth()*5/17, gui.getWidth()*11/16);
		view.window.add(view.bar);
		return view.buttonBar;

	}


	/**
	 * Lets the user choose how many players they want. Only accepts a number between 3 and 6
	 * otherwise asks again.
	 */
	public void getPlayers() {
		String input = JOptionPane.showInputDialog("Number of players? (3-6): ");

		if(!game.parseNumber(input)) {
			JOptionPane.showMessageDialog(view.board, "Must be a number!"); 
			getPlayers();
		}
		else {
			int numPlayers = Integer.parseInt(input);
			game.setActivePlayers(numPlayers);
			if (numPlayers > 6 || numPlayers < 3) {
				JOptionPane.showMessageDialog(view.board, "Must be between 3 and 6!");
				getPlayers();
			}
			else {
				choosePlayers();
				gui.view.startTurn();
			}
		}
	}

	/**
	 * Allows the players to be able to enter their names and choose what token they want to play 
	 * as. After a token is chosen by a player it is grayed out so it cannot be chosen by anyone else.
	 * Forces players to choose a token and enter a valid name (can't be empty, can't cancel). Then
	 * once a player has entered a valid name and chosen a token it sets that player in play.
	 * Repeats for the number of players.
	 */
	public void choosePlayers() {

		int numPlayers = game.getActivePlayers();

		JPanel playersPanel = new JPanel(new GridLayout(2,1));
		ButtonGroup bg1 = new ButtonGroup();
		JPanel playerButtons  = new JPanel();

		// creates buttons for tokens
		JRadioButton scarlett = new JRadioButton("Scarlett");
		JRadioButton mustard = new JRadioButton("Mustard");
		JRadioButton white = new JRadioButton("White");
		JRadioButton green = new JRadioButton("Green");
		JRadioButton peacock = new JRadioButton("Peacock");
		JRadioButton plum = new JRadioButton("Plum");

		// adds them to a button group so only one can be selected.
		bg1.add(scarlett);
		bg1.add(mustard);
		bg1.add(white);
		bg1.add(green);
		bg1.add(peacock);
		bg1.add(plum);

		playersPanel.add(new JLabel("Please choose a token:\n"));
		playerButtons.add(scarlett);
		playerButtons.add(mustard);
		playerButtons.add(white);
		playerButtons.add(green);
		playerButtons.add(peacock);
		playerButtons.add(plum);
		playersPanel.add(playerButtons);

		for(int i=1;i<=numPlayers;i++) {

			String input = JOptionPane.showInputDialog("Player " + i + ", enter your name:" );

			if(input==null || input.equals("")) {
				JOptionPane.showMessageDialog(playersPanel, "Please enter a name");
				i--;
			}
			else {
				JOptionPane.showMessageDialog(null,playersPanel);
				if (scarlett.isSelected() && !(game.getOrder().contains(game.getPlayers().get(0)))) {
					Player curr = game.getPlayers().get(0);
					curr.setInPlay(true);
					curr.setName(input);
					curr.setNumeric(i);
					game.addToOrder(curr);
					scarlett.setEnabled(false);
				}
				else if (mustard.isSelected() && !(game.getOrder().contains(game.getPlayers().get(1)))) {
					Player curr = game.getPlayers().get(1);
					curr.setName(input);
					curr.setNumeric(i);
					curr.setInPlay(true);
					game.addToOrder(curr);
					mustard.setEnabled(false);
				}
				else if (white.isSelected() && !(game.getOrder().contains(game.getPlayers().get(2)))) {
					Player curr = game.getPlayers().get(2);
					curr.setName(input);
					curr.setNumeric(i);
					curr.setInPlay(true);
					game.addToOrder(curr);
					white.setEnabled(false);
				}
				else if (green.isSelected() && !(game.getOrder().contains(game.getPlayers().get(3)))) {
					Player curr = game.getPlayers().get(3);
					curr.setName(input);
					curr.setNumeric(i);
					curr.setInPlay(true);
					game.addToOrder(curr);
					green.setEnabled(false);
				}
				else if (peacock.isSelected() && !(game.getOrder().contains(game.getPlayers().get(4)))) {
					Player curr = game.getPlayers().get(4);
					curr.setName(input);
					curr.setNumeric(i);
					curr.setInPlay(true);
					game.addToOrder(curr);
					peacock.setEnabled(false);
				}
				else if (plum.isSelected() && !(game.getOrder().contains(game.getPlayers().get(5)))) {
					Player curr = game.getPlayers().get(5);
					curr.setInPlay(true);
					curr.setNumeric(i);
					curr.setName(input);
					game.addToOrder(curr);
					plum.setEnabled(false);
				}
				else {
					JOptionPane.showMessageDialog(playersPanel, "Please select a token!");
					i--;
				}
			}
		}


		view.window.remove(view.bar);
		createButtonBar();
		view.window.updateUI();

	}
	
	/**
	 *  Creates the suggestion panel, when the accuse button is clicked. Contains radio buttons so the 
	 *  player can choose the murderer, weapon and room. Forces one to be chosen for each one.
	 *  Automatically skips to next player at the end.
	 */
	public void initSuggestion() {
		// creates the suggestions window
		JPanel suggestionPanel = new JPanel(new GridLayout(6,1));
		suggestionPanel.add(new JLabel("Please choose a murderer:\n"));
		suggestionPanel.add(createPlayersPanel());
		suggestionPanel.add(new JLabel("Please choose a murder weapon:\n"));
		suggestionPanel.add(createWeaponsPanel());
		suggestionPanel.add(new JLabel("Please choose a location:\n"));
		suggestionPanel.add(createRoomsPanel());
		JOptionPane.showMessageDialog(null, suggestionPanel, "Suggestion!", JOptionPane.INFORMATION_MESSAGE);
		
		// checks if the player didn't choose a murderer, weapon or room
		if(bg1.getSelection()==null ||bg2.getSelection()==null || bg3.getSelection()==null) {
			JOptionPane.showMessageDialog(suggestionPanel, "Please choose a murderer, weapon and room!", "Error!", JOptionPane.ERROR_MESSAGE);
			// makes them choose again if they didn't choose one
			initSuggestion();
		}
		else {
			//if they room, murderer and weapon are all valid checks if the suggestion is correct.

			if(checkSuggest(gui.model.findButtons(), suggestionPanel, gui.view.window,this)) {

				game.setMovesLeft(0);
				images.drawOnGrid();
				view.window.updateUI();
				view.check = true;
				view.nextTurn();
				
			}

		}
		
		
	}

	/**
	 *  Creates the accusation panel, when the accuse button is clicked. Contains radio buttons so the 
	 *  player can choose the murderer, weapon and room. Forces one to be chosen for each one.
	 *  Removes player from game if they accuse wrong.
	 */
	void initAccusation() {

		// creates the accusation window
		JPanel accusationPanel = new JPanel(new GridLayout(6,1));
		accusationPanel.add(new JLabel("Please choose a murderer:\n"));
		accusationPanel.add(createPlayersPanel());
		accusationPanel.add(new JLabel("Please choose a murder weapon:\n"));
		accusationPanel.add(createWeaponsPanel());
		accusationPanel.add(new JLabel("Please choose a location:\n"));
		accusationPanel.add(createRoomsPanel());
		JOptionPane.showMessageDialog(null, accusationPanel, "Accusation!", JOptionPane.INFORMATION_MESSAGE);

		// checks if the player didn't choose a murderer, weapon or room
		if(bg1.getSelection()==null ||bg2.getSelection()==null || bg3.getSelection()==null) {
			JOptionPane.showMessageDialog(accusationPanel, "Please choose a murderer, weapon and room!", "Error!", JOptionPane.ERROR_MESSAGE);
			// makes them choose again if they didn't choose one
			initAccusation();
		}
		else {
			//if they room, murderer and weapon are all valid checks if the accusation is correct.
			game.checkSuggestion(gui.model.findButtons(), true);
			//if the accusation was incorrect and there are players left
			if(!game.getGameWon() && game.getActivePlayers()>0) {
				JOptionPane.showMessageDialog(accusationPanel, "That accusation was incorrect! Byee!", "Error!", JOptionPane.ERROR_MESSAGE);
				game.setActivePlayers(game.getActivePlayers()-1);
				game.setMovesLeft(0);
				
				// shifts player into random room so they don't block doors
				int roomNum = new Random().nextInt(8);
				game.getOrder().peek().setRoom(game.getRooms().get(roomNum));
				game.getRooms().get(roomNum).addPlayer(game.getOrder().peek());
				
				// updates display
				images.drawOnGrid();
				gui.view.window.remove(gui.view.bar);
				gui.control.createButtonBar();
				gui.view.window.updateUI();
				
				view.check = true;
				view.nextTurn();
			} 
			//if the accusation wasn't correct and there are no players left.
			else if(!game.getGameWon() && game.getActivePlayers()==0) { 
				game.gameOverGUI(false);
			}
			else { //if it was correct
				game.gameOverGUI(true);
			}
		}

	}
	
	/**
	 *  A method that is used whenever a player is in a room, they can make a guess as to what is in the Tuple to question 
	 *  other players or they can make an accusation to check what they have guessed against the cards in the Tuple
	 *  
	 * @param suggested
	 * 		The tuple containing player, weapon and room.
	 * @param player 
	 * 		The player that the current player has accused/ guessed..
	 * @param weapon 
	 * 		The weapon they chose.
	 * @param room 
	 * 		The room they chose as the murder room.
	 * @param check 
	 * 		Whether or not it was a suggestion or an accusation.
	 * @param suggestedPanel, board
	 * 		JPanels to display messages on.
	 * @param 
	 * 		Controller to display the suggestion menu.
	 */
	public boolean checkSuggest(Tuple suggested, JPanel suggestionsPanel, JPanel board, Controller controller) {

		Player player = suggested.getMurderer();
		Weapon weapon = suggested.getWeapon();
		Room room = suggested.getRoom();


		Queue<Player> order = game.getOrder();

		int playerCount = game.getCurrentPlayer();
		boolean pickRandom = game.pickingRandom();


		if(!game.validSuggestion(room, false)) {
			if(order.peek().getRoom()==null) {
				JOptionPane.showMessageDialog(suggestionsPanel, "Must in a room!"); 
				return false;
			}
			JOptionPane.showMessageDialog(suggestionsPanel, "That was not a valid suggestion! Try again.");
			controller.initSuggestion();

			
		} 
		else {

			weapon.getRoom().removeWeapon(weapon);
			weapon.setRoom(room);
			room.addWeapon(weapon);
			room.addPlayer(player);
			if(player.getRoom()!=null) player.getRoom().removePlayer(player);
			player.setRoom(room);
			player.setX(order.peek().getX());
			player.setY(order.peek().getY());
			// makes sure only one card is shown.
			boolean found = false;
			Random rand = new Random();

			// loops through all the players that were chosen at the start whether or not they are in play or out.
			for(Player p : order) {

				List<Card> multipleInHand = new ArrayList<Card>();
				if(!found) {
					if(p.equals(order.peek())) {playerCount = game.getCurrentPlayer(); continue;}

					// Needed in order to say what player had the card.
					playerCount = playerCount+1;
					if(playerCount>game.getActivePlayers()) playerCount=1;

					// All the cards in the players hand.
					List<Card> hand = p.getHand();

					//this is used to randomise the order to we don't always get the same type of cards returned first.
					List<String> typesLeft = new ArrayList<String>();
					List<String> types = new ArrayList<String>();
					typesLeft.add("player");					
					typesLeft.add("weapon");
					typesLeft.add("room");

					for(int i=3; i>0; i--) {
						int cardType = (rand.nextInt(i));
						types.add(typesLeft.remove(cardType));	
					}

					// looks for the first card it finds that matches prints that then breaks. Acts like a player kind of.
					for(String type : types) {
						if(type.equals("player")) {
							for(Card c : hand) {
								if(c.getType().toString().equalsIgnoreCase(player.toString())) {
									if(!found) {
										if(pickRandom) {
											JOptionPane.showMessageDialog(suggestionsPanel, "Player " + playerCount + " has the " + player.toString() + " card."); 

											game.setPlayerHasCard(playerCount);
											game.setCardPlayerHas(c);
											found = true;
											break;
										}else multipleInHand.add(c);
									}


								}
							}
						}
						else if(type.equals("weapon")) {
							for(Card c : hand) {
								if(c.getType().toString().equalsIgnoreCase(weapon.toString())) {
									if(!found) {
										if(pickRandom) {
											JOptionPane.showMessageDialog(suggestionsPanel, "Player " + playerCount + " has the " + weapon.toString() + " card."); 
											game.setPlayerHasCard(playerCount);
											game.setCardPlayerHas(c);
											found = true;
											break;
										} else multipleInHand.add(c);
									}


								}
							}

						}
						else if(type.equals("room")) {
							for(Card c : hand) {
								if(c.getType().toString().equalsIgnoreCase(room.toString())) {
									if(!found) {
										if(pickRandom) {
											JOptionPane.showMessageDialog(suggestionsPanel, "Player " + playerCount + " has the " + room.toString() + " card."); 
											game.setPlayerHasCard(playerCount);
											game.setCardPlayerHas(c);
											found = true;
											break;
										} else multipleInHand.add(c);
									}


								}
							}

						}


					}

				}
				// allows player with cards to decided what card they want to show.
				if(multipleInHand.size()>1 && !pickRandom) {
					JOptionPane.showMessageDialog(suggestionsPanel, "Showing cards, only Player " + playerCount + " is allowed to see!"); 


					int index = -1;
					index = numberInHand(index, multipleInHand, suggestionsPanel, board);
			
					found = true;
					JOptionPane.showMessageDialog(board, "PLAYER " + playerCount + " has the " +multipleInHand.get(index).toString() + " card.");
					
					
				}
				else if(multipleInHand.size()==1 && !pickRandom) {
					JOptionPane.showMessageDialog(board, "Player " + playerCount + " has the " +multipleInHand.get(0).toString() + " card.\n");
					found = true;
				}
				multipleInHand.clear();
			}

			if(!found) {
				JOptionPane.showMessageDialog(suggestionsPanel, "No card found!");
				return true;
			}



		} 
		return true;
	}
	
	/**
	 *	Gets the card that the player want's to show the player (if they have more than one
	 *	of the suggested ones)
	 * 
	 * @param index
	 * 		Index of card that player has chosen to show. Default is -1.
	 * @param multipleInHand
	 * 		The suggest cards in the player's hand.
	 * @param suggestionsPanel, window 
	 * 		Allows messages to be displayed.
	 * @return
	 * 		Index of the chosen card.
	 */
	public int numberInHand(int index, List<Card> multipleInHand, JPanel suggestionsPanel, JPanel window) {
		String input = JOptionPane.showInputDialog("Cards can show: " + multipleInHand + ". \nPick the index of the one you want to show to PLAYER " + game.getCurrentPlayer());

		if(!game.parseNumber(input)) {
			JOptionPane.showMessageDialog(window, "Must be a number!"); 
			return numberInHand(index, multipleInHand, suggestionsPanel, window);
		}
		else {
			
			index = Integer.parseInt(input);
			if(index<0 || index>= multipleInHand.size()) {
				JOptionPane.showMessageDialog(window, "Must be between 0 and " + (multipleInHand.size()-1) + "."); 
				return numberInHand(index,multipleInHand, suggestionsPanel, window);
			}
			else {
				return index;
			}
		}
	}
	
	/**
	 * A method that forces this player to exit the room they are currently in
	 * This method is only ever called if the player is inside a room to begin with
	 * 
	 * If it were required, this would also be the method that would handle the 
	 * secret passageway shortcuts between rooms
	 * 
	 * @param player
	 * 		The player that is leaving the room.
	 * @param window
	 * 		Allows method to display messages.
	 */
	public void exitRoom(Player player, JPanel window) {

		// index of the room the player is exiting
		int index = 1;
		Room roomIn = player.getRoom();

		// A status message to alert the player that they're not in the hallway
		JOptionPane.showMessageDialog(window, "\nPlayer " + game.getCurrentPlayer() + " (" + player.toString() + ") " + " is in room " + roomIn + "...", "In room!", JOptionPane.PLAIN_MESSAGE);
		
		// checks if doors are free
		int freeDoors = 0;
		for (DoorTile door : roomIn.getDoors()) {
			freeDoors += game.doorIsFree(door);
		}
		// if all doors are blocked by players.
		if (freeDoors == 0) {
			JOptionPane.showMessageDialog(window, "There are no free doors! Your turn has been skipped!", "Trapped!", JOptionPane.WARNING_MESSAGE);
			game.setMovesLeft(0);
			images.drawOnGrid();
			view.window.updateUI();
			view.check = true;
			view.nextTurn();
			return;
		}

		// if there is more than one door, the player needs to choose the door they wish to leave
		if (roomIn.numberOfDoors() > 1) {
			
			int chosen = 0;

			chosen = getDoorNumber(window, chosen, roomIn);
			index = chosen;

		}

		// grabs the door and the inverse direction the player needs to move to exit from that door
		DoorTile door = roomIn.getDoorTile(index-1);
		Direction inverse = door.getEntryDir().inverse();

		// sets the players location to where the door is located
		player.setX(door.getCol());
		player.setY(door.getRow());
		player.setRoom(null);
		player.setRoomLastIn(roomIn);
		roomIn.removePlayer(player);
		
		// just uses the perform move method to shift the player
		game.performMove(new Move(1,inverse),1,player);

		JOptionPane.showMessageDialog(window, "Player " + game.getCurrentPlayer() + " (" + player.toString() + ") " + " has exited via Door " + index + "!", "Out of room!", JOptionPane.PLAIN_MESSAGE);

	}
	
	/**
	 * Allows the player to choose what door their character exits through.
	 * 
	 * @param window
	 * 		Allows method to display messages.
	 * @param chosen
	 * 		Index of door that player wants to leave, defaults to 0.
	 * @param roomIn
	 * 		The room that the player is in.
	 * @return
	 * 		Returns the index of the chosen door.
	 */
	public int getDoorNumber(JPanel window, int chosen, Room roomIn) {
		String input = JOptionPane.showInputDialog("From left to right, please select the door in room " + roomIn +
				" that you would like to exit, Player " + game.getCurrentPlayer() +
				" (between 1 - " + roomIn.numberOfDoors() + ")");

		if(!game.parseNumber(input)) {
			JOptionPane.showMessageDialog(window, "Must be a number!"); 
			return getDoorNumber(window, chosen,roomIn);
		}
		else {
			chosen = Integer.parseInt(input);

			if(chosen < 1 || chosen > roomIn.numberOfDoors()) {
				JOptionPane.showMessageDialog(window, "Must be between 1 and " + roomIn.numberOfDoors() + "!");
				return getDoorNumber(window, chosen,roomIn);
				
			}
			
			// determines whether this door is actually free
			if (game.doorIsFree(roomIn.getDoorTile(chosen-1)) == 0) {
				JOptionPane.showMessageDialog(window,"That door is blocked by another player! Please select a different door");
				return getDoorNumber(window,chosen,roomIn);
			}
		}
		
		return chosen;
	}
	
	
	
	/**
	 * Creates all the radio buttons for the players section of the suggestion/ accusation panels.
	 * Adds these buttons to a group that are stored to be used when checking what button was selected.
	 * 
	 * @return
	 * 		Returns a jpanel with all the radio buttons.
	 */
	private JPanel createPlayersPanel() {

		JPanel playersPanel = new JPanel();
		bg1 = new ButtonGroup();
		JPanel playerButtons  = new JPanel();

		// creates the player buttons
		JRadioButton scarlett = new JRadioButton("Scarlett");
		JRadioButton mustard = new JRadioButton("Mustard");
		JRadioButton white = new JRadioButton("White");
		JRadioButton green = new JRadioButton("Green");
		JRadioButton peacock = new JRadioButton("Peacock");
		JRadioButton plum = new JRadioButton("Plum");

		// adds them to a group so only one can be selected
		bg1.add(scarlett);
		bg1.add(mustard);
		bg1.add(white);
		bg1.add(green);
		bg1.add(peacock);
		bg1.add(plum);

		// adds them to panel
		playerButtons.add(scarlett);
		playerButtons.add(mustard);
		playerButtons.add(white);
		playerButtons.add(green);
		playerButtons.add(peacock);
		playerButtons.add(plum);
		playersPanel.add(playerButtons);
		return playersPanel;

	}

	/**
	 * Creates all the radio buttons for the weapons section of the suggestion/ accusation panels.
	 * Adds these buttons to a group that are stored to be used when checking what button was selected.
	 * 
	 * @return
	 * 		Returns a jpanel with all the radio buttons.
	 */
	private JPanel createWeaponsPanel() {

		JPanel weaponsPanel = new JPanel();
		bg2 = new ButtonGroup();
		JPanel weaponButtons  = new JPanel();

		// creates the weapon buttons
		JRadioButton candleStick = new JRadioButton("Candlestick");
		JRadioButton dagger = new JRadioButton("Dagger");
		JRadioButton leadPipe = new JRadioButton("Lead Pipe");
		JRadioButton revolver = new JRadioButton("Revolver");
		JRadioButton rope = new JRadioButton("Rope");
		JRadioButton spanner = new JRadioButton("Spanner");

		// adds them to a group so only one can be selected
		bg2.add(candleStick);
		bg2.add(dagger);
		bg2.add(leadPipe);
		bg2.add(revolver);
		bg2.add(rope);
		bg2.add(spanner);

		// adds them to panel
		weaponButtons.add(candleStick);
		weaponButtons.add(dagger);
		weaponButtons.add(leadPipe);
		weaponButtons.add(revolver);
		weaponButtons.add(rope);
		weaponButtons.add(spanner);
		weaponsPanel.add(weaponButtons);
		return weaponsPanel;

	}

	/**
	 * Creates all the radio buttons for the rooms section of the suggestion/ accusation panels.
	 * Adds these buttons to a group that are stored to be used when checking what button was selected.
	 * 
	 * @return
	 * 		Returns a jpanel with all the radio buttons.
	 */
	public JPanel createRoomsPanel() {

		JPanel roomsPanel = new JPanel();
		bg3 = new ButtonGroup();
		JPanel roomButtons  = new JPanel();

		// creates the room buttons
		JRadioButton diningRoom = new JRadioButton("Dining Room");
		JRadioButton ballRoom = new JRadioButton("Ball Room");
		JRadioButton billiardRoom= new JRadioButton("Billiard Room");
		JRadioButton conservatory = new JRadioButton("Conservatory");
		JRadioButton kitchen = new JRadioButton("Kitchen");
		JRadioButton library = new JRadioButton("Library");
		JRadioButton hall = new JRadioButton("Hall");
		JRadioButton study = new JRadioButton("Study");
		JRadioButton lounge = new JRadioButton("Lounge");

		// adds them to a group so only one can be selected
		bg3.add(diningRoom);
		bg3.add(ballRoom);
		bg3.add(billiardRoom);
		bg3.add(conservatory);
		bg3.add(kitchen);
		bg3.add(library);
		bg3.add(hall);
		bg3.add(study);
		bg3.add(lounge);

		// adds them to panel
		roomButtons.add(diningRoom);
		roomButtons.add(ballRoom);
		roomButtons.add(billiardRoom);
		roomButtons.add(conservatory);
		roomButtons.add(kitchen);
		roomButtons.add(library);
		roomButtons.add(hall);
		roomButtons.add(study);
		roomButtons.add(lounge);
		roomsPanel.add(roomButtons);
		return roomsPanel;

	}


}
