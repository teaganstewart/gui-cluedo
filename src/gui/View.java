package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import swen225.*;

/**
 * A class for the view part of model-view-controller, has all of the methods that 
 * have output/ are required for output.
 * 
 * @author Ethan Munn, Teagan Stewart
 *
 */
public class View {
	
	JPanel window;
	JPanel board;
	JPanel buttonBar;
	JPanel hud;
	JPanel dice;
	JPanel bar;
	GraphicalView images;
	Game game;
	Gui gui;
	boolean check = false;
	
	public JLabel[][] boardGrid;
	
	public View(Game g, GraphicalView images, Gui gui) {
		this.images= images;
		game = g;
		this.gui = gui;
	}
	
	/**
	 * Gets the board grid at a specific i/j
	 * @param i
	 * 		row
	 * @param j
	 * 		col
	 * @return
	 * 		JLabel from the board grid
	 */
	public JLabel getBoardGrid(int i, int j) {
		return boardGrid[i][j];
	}
	
	/**
	 * Is called every new turn to make the players skip, reinitialises
	 * panels on screen to swap player icons, cards etc.
	 */
	public void nextTurn() {
		// only should be able to go to next turn if they have no moves left.
		if(game.getMovesLeft()==0) {
			window.remove(bar);
			gui.control.createButtonBar();
			window.updateUI();
			
			// allows players to stay in rooms if they enter on their last turn.
			if(game.getOrder().peek().getRoom()!=null && !check) {
				return;
			}
			check = false;
		
			game.newTurn();
		
			// checks if current player is in a room before their first move so it can remove them.
			if(game.getOrder().peek().getRoom()!=null) {
				gui.control.exitRoom(game.getOrder().peek(), window);
			}
			
			// updates the main parts of the GUI.
			window.remove(dice);
			window.remove(hud);
			window.remove(bar);
				
			createHUD();

			gui.control.createButtonBar();
			images.drawOnGrid();
			window.updateUI();
		}
	}
	
	/**
	 * Only for the very first turn in the game
	 */
	public void startTurn() {
		game.getOrder().peek().resetXYPositions(game.getDieRoll()+1);
		game.getOrder().peek().setXYPosition(game.getOrder().peek().getX(), game.getOrder().peek().getY());
	}
	
	/**
	 * This method creates the hud and dice jpanels displayed at the bottom of the window, creates dice to randomise 
	 * the numbers on the dice, then sets the roll to the sum of these. Also holds the players' cards
	 * 
	 * @return 
	 * 		Returns the hud that's at the bottom of the window, which holds the cards and the dice.
	 */
	JPanel createHUD() {


		hud = new JPanel(new GridLayout(1,6));
		dice = new JPanel();
		//dice.setBounds(26, this.getWidth()*11/16+GAP+8, this.getWidth()*2/13, this.getWidth()*4/16-GAP);
		//hud.setBounds(this.getWidth()*3/15+3, this.getWidth()*11/16+6, this.getWidth()*11/14, this.getWidth()*4/16-GAP);

		// positions the jpanels in the correct place.
		dice.setBounds(-14, gui.getWidth()*11/16+13, gui.getWidth()*2/13, gui.getWidth()*4/16-5);
		hud.setBounds(gui.getWidth()*3/15+3, gui.getWidth()*11/16+4, gui.getWidth()*11/14, gui.getWidth()*4/16-5);

		// creates new dice and uses them for moves, and to show the dice on the display.
		int roll1 = game.getDie1().getRoll();
		int roll2 = game.getDie2().getRoll();

		ImageIcon die1 = images.getDiceIcon(roll1);
		ImageIcon die2 = images.getDiceIcon(roll2);
		game.setDieRoll(roll1+roll2);
		game.setMovesLeft(roll1+roll2);

		dice.add(new JLabel(die1));
		dice.add(new JLabel(die2));

		//adds all of the cards, based on what player's turn it is and how many cards/ what cards they have in their hand.
		if(!game.getOrder().isEmpty()) {
			for(int i=0; i<6-game.getOrder().peek().getHand().size(); i++) {
				hud.add(new JPanel());
			}
			for(int i=0;i<game.getOrder().peek().getHand().size(); i++) {
				String type = game.getOrder().peek().getHand().get(i).getType().getClass().getSimpleName();
				if(type.equals("Room")) {
					ImageIcon p = images.getRoomCard(game.getOrder().peek().getHand().get(i).getType().toString());
					hud.add(new JLabel(p));

				}
				else if(type.equals("Player")) {
					ImageIcon p = images.getPlayerCard(game.getOrder().peek().getHand().get(i).getType().toString());
					hud.add(new JLabel(p));
				}
				else if(type.equals("Weapon")) {
					ImageIcon p = images.getWeaponCard(game.getOrder().peek().getHand().get(i).getType().toString());
					hud.add(new JLabel(p));
				}
			}

		}
		else {
			for(int i=0; i<6;i++) {
				hud.add(new JPanel());
			}
		}

		window.add(dice);
		window.add(hud);
		return hud;

	}

	
	

}
