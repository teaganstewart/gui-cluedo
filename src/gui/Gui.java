package gui;
import swen225.*;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.*;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;

/**
 * A class that creates the GUI and everything else necessary for the GUI to run/ be shown.
 * @author Ethan Munn, Teagan Stewart
 *
 */
public class Gui extends JFrame implements MouseListener {

	private static final long serialVersionUID = 1L;

	Game game;
	Board gameBoard;
	GraphicalView images;
	Controller control;
	View view;
	public Model model;
	private Coordinate clickedPos;
	private Coordinate hoveredPos;

	public Gui() {

		initUI();
		
		// adds a goodbye message and a warning prompt for the user before they exit the game
		addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent windowEvent) {
		    	int prompt = JOptionPane.showConfirmDialog(null, "Are you sure you wish to exit Cluedo?", "Close Window?", 
			            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		        if (prompt == JOptionPane.YES_OPTION){
		        	JOptionPane.showMessageDialog(null, "Thanks for playing! :)", "Byee!", JOptionPane.PLAIN_MESSAGE);
		            System.exit(0);
		        }
		    }
		});

	}

	/**
	 *  Initialises GUI and creates all the object required for the model-controller-view system we have going
	 *  on. Also allows the gui to have a key listener.
	 */
	private void initUI() {
		game = new Game();

		images = new GraphicalView(game,this);
		view = new View(game,images, this);
		control = new Controller(game,this,images, view);
		model = new Model(game,this,control,images);
		control.createMenuBar();
		initTitle();
		
		// key listener so that you  can move round the board with the arrow keys.
		this.addKeyListener(new KeyListener(){
			@Override
			public void keyTyped(KeyEvent ev) {}

			@Override
			public void keyPressed(KeyEvent ev) {}

			@Override
			public void keyReleased(KeyEvent ev) {
				if (ev.getKeyCode() == KeyEvent.VK_LEFT) {
					model.moveWithKey(Direction.LEFT);
				}
				if (ev.getKeyCode() == KeyEvent.VK_RIGHT) {
					model.moveWithKey(Direction.RIGHT);
				}
				if (ev.getKeyCode() == KeyEvent.VK_UP) {
					model.moveWithKey(Direction.UP);
				}
				if (ev.getKeyCode() == KeyEvent.VK_DOWN) {
					model.moveWithKey(Direction.DOWN);
				}
			}
			
			});

	}

	/**
	 * Creates the jpanels that the title screen needs and places them in the correct
	 * positions.
	 */
	private void initTitle() {

		resetWindow();

		view.window = new JPanel();
		view.window.setLayout(null);
		
		JPanel background = new JPanel();
		background.setBounds(0,0,this.getWidth(),this.getHeight());
		background.add(new JLabel(new ImageIcon(getClass().getResource("../icon/background.png"))));

		JPanel button = new JPanel();
		button.setBackground(Color.DARK_GRAY);

		button.setBounds(0,this.getHeight()*4/8,this.getWidth(),this.getHeight()/8);

		JButton actualButton = new JButton("New Game");

		actualButton.setForeground(Color.white);
		actualButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		actualButton.setFocusPainted(false);
		actualButton.setPreferredSize(new Dimension(160,34));

		// these next two lines do the magic..
		actualButton.setContentAreaFilled(false);
		actualButton.setOpaque(false);
		actualButton.addActionListener((event) -> initGameDisp());

		button.add(actualButton);

		button.setBorder( new EmptyBorder(20, 0, 0, 0) );

		view.window.add(background);
		view.window.add(button);
		add(view.window);
		this.setVisible(true);

	}

	/**
	 * Creates all of the jpanels that the game needs and places them in the correct
	 * positions.
	 */
	void initGameDisp() {

		resetWindow();
		view.window = new JPanel();
		view.window.setLayout(null);

		reset();
		// board

		// textpane
		view.buttonBar = control.createButtonBar();
		model.createGrid();
		view.board = model.createBoard();
		// hud
		view.hud = view.createHUD();

		// adds the window to the frame, which has our layout on it

		control.getPlayers();
		game.dealCards();
		view.window.remove(view.dice);
		view.window.remove(view.hud);
		view.window.remove(view.bar);
		view.createHUD();
		control.createButtonBar();
		add(view.window);

		this.setVisible(true);
		
		clickedPos = new Coordinate(game.getOrder().peek().getY(),game.getOrder().peek().getX());
		
		model.setupTimer();
		
	}
	
	
	/**
	 * 
	 *  Resets everything to do with the game, doesn't touch the GUI. Makes sure the last
	 *  game doesn't run into the next game.
	 */
	public void reset() {

		game.gameWon = false;
		
		game.resetRooms();
		game.resetWeapons();
		game.resetPlayers();
		game.resetCards();
		game.resetSolution();

	}

	/**
	 * Makes the window, and removes the old one (if there is one).
	 */
	private void resetWindow() {
		if (view.window != null) {
			remove(view.window);
		}

		setTitle("Cluedo!");
		setSize(800, 800);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(0);
		
	}

	public static void main(String[] args) {

		Gui ex = new Gui();
		ex.setVisible(true);

	}



	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent ev) {


	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

	/**
	 * Used for movement and path finding.
	 * 
	 * @return
	 * 		Returns the coordinates of the tile that the user has clicked on.
	 */
	public Coordinate getClickedPos() {
		return clickedPos;
	}

	/**
	 * Used for movement and path finding.
	 * 
	 * @param clickedPos
	 * 		 The coordinates of the tile that the user has clicked on.
	 */
	public void setClickedPos(Coordinate clickedPos) {
		this.clickedPos = clickedPos;
	}

	/**
	 * Used so that the user can see where they are going to go if they click.
	 * 
	 * @param hoveredPos
	 * 		 The coordinates of the tile that the user is hovering over.
	 */
	public Coordinate getHoveredPos() {
		return hoveredPos;
	}
	
	/**
	 * Used so that the user can see where they are going to go if they click.
	 * 
	 * @param hoveredPos
	 * 		 The coordinates of the tile that the user the user is hovering over.
	 */
	public void setHoveredPos(Coordinate hoveredPos) {
		this.hoveredPos = hoveredPos;
	}


}
