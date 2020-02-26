package tests;
import swen225.*;
import java.util.*;

//This program is copyright VUW.
//You are granted permission to use it to construct your answer to a SWEN221 assignment.
//You may not distribute it in any other way without permission.

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CluedoTests {


	//-----------------------------//
	//----- ACCUSATION TESTS ------//
	//-----------------------------//

	@Test
	void accuseTest1() { 
		// test accusation is right, (game over)
		Game g = new Game();
		g.setBoard(defaultBoard(g));	
		g.setOrder(g.getPlayers());

		g.checkSuggestion(new Tuple(g.getPlayers().get(0),g.getWeapons().get(0),g.getRooms().get(0)), true);
		assertEquals(g.getBoard().toString(),
				"|X|X|X|X|X|X|X|X|X|W|X|X|X|X|G|X|X|X|X|X|X|X|X|X|\n" +
						"|#|#|#|#|#|#|X|_|_|_|#|#|#|#|_|_|_|X|#|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|#|_|_|#|#|#|#|#|#|\n" +
						"|#|K|C|H|N|#|_|_|#|#|B|A|L|L|#|#|_|_|#|C|O|N|S|#|\n" +
						"|#|#|#|#|#|#|_|_|#|#|R|O|O|M|#|#|_|_|@|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|_|_|@|#|#|#|#|#|#|@|_|_|_|#|#|#|#|X|\n" +
						"|X|#|#|#|@|#|_|_|#|#|#|#|#|#|#|#|_|_|_|_|_|_|_|B|\n" +
						"|_|_|_|_|_|_|_|_|#|@|#|#|#|#|@|#|_|_|_|_|_|_|_|X|\n" +
						"|X|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|#|#|#|#|#|#|\n" +
						"|#|#|#|#|#|_|_|_|_|_|_|_|_|_|_|_|_|_|@|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|B|L|R|D|#|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|#|#|#|#|#|\n" +
						"|#|D|I|N|I|N|G|@|_|_|X|X|X|X|X|_|_|_|#|#|#|#|@|#|\n" +
						"|#|#|R|O|O|M|#|#|_|_|X|X|X|X|X|_|_|_|_|_|_|_|_|X|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|#|@|#|#|X|\n" +
						"|#|#|#|#|#|#|@|#|_|_|X|X|X|X|X|_|_|#|#|#|#|#|#|#|\n" +
						"|X|_|_|_|_|_|_|_|_|_|X|X|X|X|X|_|_|@|#|L|B|R|Y|#|\n" +
						"|M|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|#|#|#|#|#|#|#|\n" +
						"|X|_|_|_|_|_|_|_|_|#|#|@|@|#|#|_|_|_|#|#|#|#|#|X|\n" +
						"|#|#|#|#|#|#|@|_|_|#|#|#|#|#|#|_|_|_|_|_|_|_|_|P|\n" +
						"|#|#|#|#|#|#|#|_|_|#|#|#|#|#|@|_|_|_|_|_|_|_|_|X|\n" +
						"|#|#|L|O|U|#|#|_|_|#|#|#|#|#|#|_|_|@|#|#|#|#|#|#|\n" +
						"|#|#|N|G|E|#|#|_|_|#|H|A|L|L|#|_|_|#|S|T|U|D|Y|#|\n" +
						"|#|#|#|#|#|#|#|_|_|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|\n" +
				"|#|#|#|#|#|#|X|S|X|#|#|#|#|#|#|X|_|X|#|#|#|#|#|#|\n");

		assertEquals(g.getActivePlayers(),0);
		assertEquals(g.getGameWon(), true);

	}

	@Test
	void accuseTest2() {
		// test accusation is wrong, (player loses)
		Game g = new Game();
		g.setBoard(defaultBoard(g));	
		g.setOrder(g.getPlayers());

		g.checkSuggestion(new Tuple(g.getPlayers().get(1),g.getWeapons().get(0),g.getRooms().get(0)), true);

		assertEquals(g.getBoard().toString(),
				"|X|X|X|X|X|X|X|X|X|W|X|X|X|X|G|X|X|X|X|X|X|X|X|X|\n" +
				"|#|#|#|#|#|#|X|_|_|_|#|#|#|#|_|_|_|X|#|#|#|#|#|#|\n" +
				"|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|#|_|_|#|#|#|#|#|#|\n" +
				"|#|K|C|H|N|#|_|_|#|#|B|A|L|L|#|#|_|_|#|C|O|N|S|#|\n" +
				"|#|#|#|#|#|#|_|_|#|#|R|O|O|M|#|#|_|_|@|#|#|#|#|#|\n" +
				"|#|#|#|#|#|#|_|_|@|#|#|#|#|#|#|@|_|_|_|#|#|#|#|X|\n" +
				"|X|#|#|#|@|#|_|_|#|#|#|#|#|#|#|#|_|_|_|_|_|_|_|B|\n" +
				"|_|_|_|_|_|_|_|_|#|@|#|#|#|#|@|#|_|_|_|_|_|_|_|X|\n" +
				"|X|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|#|#|#|#|#|#|\n" +
				"|#|#|#|#|#|_|_|_|_|_|_|_|_|_|_|_|_|_|@|#|#|#|#|#|\n" +
				"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|B|L|R|D|#|\n" +
				"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|#|#|#|#|#|\n" +
				"|#|D|I|N|I|N|G|@|_|_|X|X|X|X|X|_|_|_|#|#|#|#|@|#|\n" +
				"|#|#|R|O|O|M|#|#|_|_|X|X|X|X|X|_|_|_|_|_|_|_|_|X|\n" +
				"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|#|@|#|#|X|\n" +
				"|#|#|#|#|#|#|@|#|_|_|X|X|X|X|X|_|_|#|#|#|#|#|#|#|\n" +
				"|X|_|_|_|_|_|_|_|_|_|X|X|X|X|X|_|_|@|#|L|B|R|Y|#|\n" +
				"|M|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|#|#|#|#|#|#|#|\n" +
				"|X|_|_|_|_|_|_|_|_|#|#|@|@|#|#|_|_|_|#|#|#|#|#|X|\n" +
				"|#|#|#|#|#|#|@|_|_|#|#|#|#|#|#|_|_|_|_|_|_|_|_|P|\n" +
				"|#|#|#|#|#|#|#|_|_|#|#|#|#|#|@|_|_|_|_|_|_|_|_|X|\n" +
				"|#|#|L|O|U|#|#|_|_|#|#|#|#|#|#|_|_|@|#|#|#|#|#|#|\n" +
				"|#|#|N|G|E|#|#|_|_|#|H|A|L|L|#|_|_|#|S|T|U|D|Y|#|\n" +
				"|#|#|#|#|#|#|#|_|_|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|\n" +
				"|#|#|#|#|#|#|X|_|X|#|#|#|#|#|#|X|_|X|#|#|#|#|#|#|\n");


		assertEquals(g.getActivePlayers(),5);

	}

	@Test
	void accuseTest3() { 
		// test accusation is right, (game over)
		Game g = new Game();
		g.setBoard(defaultBoard(g));	
		g.setOrder(g.getPlayers());
		defaultHand(g);
		// move scarlett up 6, left 1, down 1
		int movesLeft = 7;
		Player p = g.getOrder().peek();
		p.resetXYPositions(movesLeft+1);
		p.setXYPosition(p.getX(), p.getY());	
		movesLeft -= g.performMove(new Move(6, Direction.UP), movesLeft, p);
		movesLeft -= g.performMove(new Move(1, Direction.LEFT), movesLeft, p);
		movesLeft -= g.performMove(new Move(1, Direction.DOWN), movesLeft, p);

		g.checkSuggestion(new Tuple(g.getPlayers().get(0),g.getWeapons().get(0),g.getRooms().get(0)), true);
		assertEquals(g.getBoard().toString(),
				"|X|X|X|X|X|X|X|X|X|W|X|X|X|X|G|X|X|X|X|X|X|X|X|X|\n" +
						"|#|#|#|#|#|#|X|_|_|_|#|#|#|#|_|_|_|X|#|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|#|_|_|#|#|#|#|#|#|\n" +
						"|#|K|C|H|N|#|_|_|#|#|B|A|L|L|#|#|_|_|#|C|O|N|S|#|\n" +
						"|#|#|#|#|#|#|_|_|#|#|R|O|O|M|#|#|_|_|@|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|_|_|@|#|#|#|#|#|#|@|_|_|_|#|#|#|#|X|\n" +
						"|X|#|#|#|@|#|_|_|#|#|#|#|#|#|#|#|_|_|_|_|_|_|_|B|\n" +
						"|_|_|_|_|_|_|_|_|#|@|#|#|#|#|@|#|_|_|_|_|_|_|_|X|\n" +
						"|X|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|#|#|#|#|#|#|\n" +
						"|#|#|#|#|#|_|_|_|_|_|_|_|_|_|_|_|_|_|@|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|B|L|R|D|#|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|#|#|#|#|#|\n" +
						"|#|D|I|N|I|N|G|@|_|_|X|X|X|X|X|_|_|_|#|#|#|#|@|#|\n" +
						"|#|#|R|O|O|M|#|#|_|_|X|X|X|X|X|_|_|_|_|_|_|_|_|X|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|#|@|#|#|X|\n" +
						"|#|#|#|#|#|#|@|#|_|_|X|X|X|X|X|_|_|#|#|#|#|#|#|#|\n" +
						"|X|_|_|_|_|_|_|_|_|_|X|X|X|X|X|_|_|@|#|L|B|R|Y|#|\n" +
						"|M|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|#|#|#|#|#|#|#|\n" +
						"|X|_|_|_|_|_|_|_|_|#|#|@|@|#|#|_|_|_|#|#|#|#|#|X|\n" +
						"|#|#|#|#|#|#|@|_|_|#|#|#|#|#|#|_|_|_|_|_|_|_|_|P|\n" +
						"|#|#|#|#|#|#|#|_|_|#|#|#|#|#|@|_|_|_|_|_|_|_|_|X|\n" +
						"|#|#|L|O|U|#|#|_|_|#|#|#|#|#|#|_|_|@|#|#|#|#|#|#|\n" +
						"|#|#|N|G|E|#|#|_|_|#|H|A|L|L|#|_|_|#|S|T|U|D|Y|#|\n" +
						"|#|#|#|#|#|#|#|_|_|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|\n" +
				"|#|#|#|#|#|#|X|_|X|#|#|#|#|#|#|X|_|X|#|#|#|#|#|#|\n");

		assertEquals(g.getActivePlayers(),0);
		assertEquals(g.getGameWon(), true);

	}

	@Test
	void accuseTest4() {
		// test accusation is wrong, (player loses)
		Game g = new Game();
		g.setBoard(defaultBoard(g));	
		g.setOrder(g.getPlayers());
		g.dealCards();

		// move scarlett up 6, left 1, down 1
		int movesLeft = 7;
		Player p = g.getOrder().peek();
		p.resetXYPositions(movesLeft+1);
		p.setXYPosition(p.getX(), p.getY());	
		movesLeft -= g.performMove(new Move(6, Direction.UP), movesLeft, p);
		movesLeft -= g.performMove(new Move(1, Direction.LEFT), movesLeft, p);
		movesLeft -= g.performMove(new Move(1, Direction.DOWN), movesLeft, p);
		g.checkSuggestion(new Tuple(g.getPlayers().get(1),g.getWeapons().get(0),g.getRooms().get(0)), true);


		assertEquals(g.getBoard().toString(),
				"|X|X|X|X|X|X|X|X|X|W|X|X|X|X|G|X|X|X|X|X|X|X|X|X|\n" +
						"|#|#|#|#|#|#|X|_|_|_|#|#|#|#|_|_|_|X|#|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|#|_|_|#|#|#|#|#|#|\n" +
						"|#|K|C|H|N|#|_|_|#|#|B|A|L|L|#|#|_|_|#|C|O|N|S|#|\n" +
						"|#|#|#|#|#|#|_|_|#|#|R|O|O|M|#|#|_|_|@|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|_|_|@|#|#|#|#|#|#|@|_|_|_|#|#|#|#|X|\n" +
						"|X|#|#|#|@|#|_|_|#|#|#|#|#|#|#|#|_|_|_|_|_|_|_|B|\n" +
						"|_|_|_|_|_|_|_|_|#|@|#|#|#|#|@|#|_|_|_|_|_|_|_|X|\n" +
						"|X|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|#|#|#|#|#|#|\n" +
						"|#|#|#|#|#|_|_|_|_|_|_|_|_|_|_|_|_|_|@|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|B|L|R|D|#|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|#|#|#|#|#|\n" +
						"|#|D|I|N|I|N|G|@|_|_|X|X|X|X|X|_|_|_|#|#|#|#|@|#|\n" +
						"|#|#|R|O|O|M|#|#|_|_|X|X|X|X|X|_|_|_|_|_|_|_|_|X|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|#|@|#|#|X|\n" +
						"|#|#|#|#|#|#|@|#|_|_|X|X|X|X|X|_|_|#|#|#|#|#|#|#|\n" +
						"|X|_|_|_|_|_|_|_|_|_|X|X|X|X|X|_|_|@|#|L|B|R|Y|#|\n" +
						"|M|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|#|#|#|#|#|#|#|\n" +
						"|X|_|_|_|_|_|_|_|_|#|#|@|@|#|#|_|_|_|#|#|#|#|#|X|\n" +
						"|#|#|#|#|#|#|@|_|_|#|#|#|#|#|#|_|_|_|_|_|_|_|_|P|\n" +
						"|#|#|#|#|#|#|#|_|_|#|#|#|#|#|@|_|_|_|_|_|_|_|_|X|\n" +
						"|#|#|L|O|U|#|#|_|_|#|#|#|#|#|#|_|_|@|#|#|#|#|#|#|\n" +
						"|#|#|N|G|E|#|#|_|_|#|H|A|L|L|#|_|_|#|S|T|U|D|Y|#|\n" +
						"|#|#|#|#|#|#|#|_|_|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|\n" +
				"|#|#|#|#|#|#|X|_|X|#|#|#|#|#|#|X|_|X|#|#|#|#|#|#|\n");


		assertEquals(g.getActivePlayers(),5);

	}

	@Test
	void gameLose() {
		Game g = new Game();
		g.setBoard(defaultBoard(g));	
		g.setOrder(g.getPlayers());
		g.checkSuggestion(new Tuple(g.getPlayers().get(1),g.getWeapons().get(0),g.getRooms().get(0)), true);
		for(int i=0; i<5; i++) {

			g.nextPlayer();
			g.checkSuggestion(new Tuple(g.getPlayers().get(1),g.getWeapons().get(0),g.getRooms().get(0)), true);
		}

		assertEquals(g.getBoard().toString(),
				"|X|X|X|X|X|X|X|X|X|_|X|X|X|X|_|X|X|X|X|X|X|X|X|X|\n" +
				"|#|#|#|#|#|#|X|_|_|_|#|#|#|#|_|_|_|X|#|#|#|#|#|#|\n" +
				"|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|#|_|_|#|#|#|#|#|#|\n" +
				"|#|K|C|H|N|#|_|_|#|#|B|A|L|L|#|#|_|_|#|C|O|N|S|#|\n" +
				"|#|#|#|#|#|#|_|_|#|#|R|O|O|M|#|#|_|_|@|#|#|#|#|#|\n" +
				"|#|#|#|#|#|#|_|_|@|#|#|#|#|#|#|@|_|_|_|#|#|#|#|X|\n" +
				"|X|#|#|#|@|#|_|_|#|#|#|#|#|#|#|#|_|_|_|_|_|_|_|_|\n" +
				"|_|_|_|_|_|_|_|_|#|@|#|#|#|#|@|#|_|_|_|_|_|_|_|X|\n" +
				"|X|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|#|#|#|#|#|#|\n" +
				"|#|#|#|#|#|_|_|_|_|_|_|_|_|_|_|_|_|_|@|#|#|#|#|#|\n" +
				"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|B|L|R|D|#|\n" +
				"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|#|#|#|#|#|\n" +
				"|#|D|I|N|I|N|G|@|_|_|X|X|X|X|X|_|_|_|#|#|#|#|@|#|\n" +
				"|#|#|R|O|O|M|#|#|_|_|X|X|X|X|X|_|_|_|_|_|_|_|_|X|\n" +
				"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|#|@|#|#|X|\n" +
				"|#|#|#|#|#|#|@|#|_|_|X|X|X|X|X|_|_|#|#|#|#|#|#|#|\n" +
				"|X|_|_|_|_|_|_|_|_|_|X|X|X|X|X|_|_|@|#|L|B|R|Y|#|\n" +
				"|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|#|#|#|#|#|#|#|\n" +
				"|X|_|_|_|_|_|_|_|_|#|#|@|@|#|#|_|_|_|#|#|#|#|#|X|\n" +
				"|#|#|#|#|#|#|@|_|_|#|#|#|#|#|#|_|_|_|_|_|_|_|_|_|\n" +
				"|#|#|#|#|#|#|#|_|_|#|#|#|#|#|@|_|_|_|_|_|_|_|_|X|\n" +
				"|#|#|L|O|U|#|#|_|_|#|#|#|#|#|#|_|_|@|#|#|#|#|#|#|\n" +
				"|#|#|N|G|E|#|#|_|_|#|H|A|L|L|#|_|_|#|S|T|U|D|Y|#|\n" +
				"|#|#|#|#|#|#|#|_|_|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|\n" +
				"|#|#|#|#|#|#|X|_|X|#|#|#|#|#|#|X|_|X|#|#|#|#|#|#|\n");

		assertEquals(g.getActivePlayers(),0);
		assertEquals(g.getGameWon(),false);

		for (Player p : g.getPlayers()) {
			assertEquals(p.isInPlay(), false);
		}

	}

	@Test
	void suggestTest1() {

		/**
		 * Creates default hands so we can test 
		 * 
		 * MISS SCARLETT [Room: BALLROOM, Room: STUDY, Player: PROFESSOR PLUM]
		 * COLONEL MUSTARD [Room: BILLIARD ROOM, Room: LOUNGE, Weapon: DAGGER]
		 * MS WHITE [Room: CONSERVATORY, Player: COLONEL MUSTARD, Weapon: LEADPIPE]
		 * MR GREEN [Room: KITCHEN, Player: MS WHITE, Weapon: REVOLVER]
		 * MRS PEACOCK [Room: LIBRARY, Player: MR GREEN, Weapon: ROPE]
		 * PROFESSOR PLUM [Room: HALL, Player: MRS PEACOCK, Weapon: SPANNER]
		 */
		Game g = new Game();
		g.setBoard(defaultBoard(g));	
		g.setOrder(g.getPlayers());
		defaultHand(g);

		// gets scarlett to the lounge room so that she can suggest something
		int movesLeft = 7;
		Player p = g.getOrder().peek();
		p.resetXYPositions(movesLeft+1);
		p.setXYPosition(p.getX(), p.getY());	
		movesLeft -= g.performMove(new Move(6, Direction.UP), movesLeft, p);
		movesLeft -= g.performMove(new Move(1, Direction.LEFT), movesLeft, p);
		movesLeft -= g.performMove(new Move(1, Direction.DOWN), movesLeft, p);

		g.checkSuggestion(new Tuple(g.getPlayers().get(2),g.getWeapons().get(0),g.getRooms().get(8)), false);

		// checks that the lounge card was found and that player 2 had it
		assertEquals(g.getCardPlayerHas().toString(),g.getPlayers().get(1).getHand().get(1).toString());
		assertEquals(g.getPlayerHasCard(),2);
	}

	@Test
	void suggestTest2() {

		for(int i=0; i<7;i++) { //makes sure it checks both cases
			Game g = new Game();
			g.setBoard(defaultBoard(g));	
			g.setOrder(g.getPlayers());
			defaultHand(g);

			// gets scarlett to the dining room so that she can suggest something
			int movesLeft = 7;
			Player p = g.getOrder().peek();
			p.resetXYPositions(movesLeft+1);
			p.setXYPosition(p.getX(), p.getY());	
			movesLeft -= g.performMove(new Move(6, Direction.UP), movesLeft, p);
			movesLeft -= g.performMove(new Move(1, Direction.LEFT), movesLeft, p);
			movesLeft -= g.performMove(new Move(1, Direction.DOWN), movesLeft, p);

			g.checkSuggestion(new Tuple(g.getPlayers().get(5),g.getWeapons().get(1),g.getRooms().get(8)), false);

			// checks that the lounge or dagger card was found and that player 2 had it (as player 2 has 2/3 of the suggested cards)

			assertTrue(g.getCardPlayerHas().toString().equals(g.getPlayers().get(1).getHand().get(1).toString())
					|| g.getCardPlayerHas().toString().equals(g.getPlayers().get(1).getHand().get(2).toString()));
			assertEquals(g.getPlayerHasCard(),2);
		}
	}

	@Test
	void suggestTest3() {

		/**
		 * Creates default hands so we can test 
		 * 
		 * MISS SCARLETT [Room: BALLROOM, Room: STUDY, Player: PROFESSOR PLUM]
		 * COLONEL MUSTARD [Room: BILLIARD ROOM, Room: LOUNGE, Weapon: DAGGER]
		 * MS WHITE [Room: CONSERVATORY, Player: COLONEL MUSTARD, Weapon: LEADPIPE]
		 * MR GREEN [Room: KITCHEN, Player: MS WHITE, Weapon: REVOLVER]
		 * MRS PEACOCK [Room: LIBRARY, Player: MR GREEN, Weapon: ROPE]
		 * PROFESSOR PLUM [Room: HALL, Player: MRS PEACOCK, Weapon: SPANNER]
		 */
		Game g = new Game();
		g.setBoard(defaultBoard(g));	
		g.setOrder(g.getPlayers());
		defaultHand(g);

		// gets scarlett to the lounge room so that she can suggest something
		int movesLeft = 12;
		Player p = g.getOrder().peek();
		p.resetXYPositions(movesLeft+1);
		p.setXYPosition(p.getX(), p.getY());	
		movesLeft -= g.performMove(new Move(7, Direction.UP), movesLeft, p);
		movesLeft -= g.performMove(new Move(4, Direction.RIGHT), movesLeft, p);
		movesLeft -= g.performMove(new Move(1, Direction.DOWN), movesLeft, p);

		System.out.println(g.getRooms());
		g.checkSuggestion(new Tuple(g.getPlayers().get(5),g.getWeapons().get(1),g.getRooms().get(6)), false);

		// checks that the lounge or dagger card was found and that player 2 had it (as player 2 has 2/3 of the suggested cards)

		assertEquals( g.getCardPlayerHas().toString(),(g.getPlayers().get(1).getHand().get(2).toString()));
		assertEquals(g.getPlayerHasCard(),2);
	}

	@Test
	void suggestTest4() {

		/**
		 * Creates default hands so we can test 
		 * 
		 * MISS SCARLETT [Room: BALLROOM, Room: STUDY, Player: PROFESSOR PLUM]
		 * COLONEL MUSTARD [Room: BILLIARD ROOM, Room: LOUNGE, Weapon: DAGGER]
		 * MS WHITE [Room: CONSERVATORY, Player: COLONEL MUSTARD, Weapon: LEADPIPE]
		 * MR GREEN [Room: KITCHEN, Player: MS WHITE, Weapon: REVOLVER]
		 * MRS PEACOCK [Room: LIBRARY, Player: MR GREEN, Weapon: ROPE]
		 * PROFESSOR PLUM [Room: HALL, Player: MRS PEACOCK, Weapon: SPANNER]
		 */
		Game g = new Game();
		g.setBoard(defaultBoard(g));	
		g.setOrder(g.getPlayers());
		defaultHand(g);

		// gets scarlett to the dining room so that she can suggest something
		int movesLeft = 12;
		Player p = g.getOrder().peek();
		p.resetXYPositions(movesLeft+1);
		p.setXYPosition(p.getX(), p.getY());	
		movesLeft -= g.performMove(new Move(7, Direction.UP), movesLeft, p);
		movesLeft -= g.performMove(new Move(4, Direction.RIGHT), movesLeft, p);
		movesLeft -= g.performMove(new Move(1, Direction.DOWN), movesLeft, p);

		g.checkSuggestion(new Tuple(g.getPlayers().get(1),g.getWeapons().get(4),g.getRooms().get(6)), false);

		// checks that the colonel mustard card was found and that player 3 had it 

		assertEquals( g.getCardPlayerHas().toString(),(g.getPlayers().get(2).getHand().get(1).toString()));
		assertEquals(g.getPlayerHasCard(),3);
	}

	@Test
	void suggestTest5() {

		/**
		 * Creates default hands so we can test 
		 * 
		 * MISS SCARLETT [Room: BALLROOM, Room: STUDY, Player: PROFESSOR PLUM]
		 * COLONEL MUSTARD [Room: BILLIARD ROOM, Room: LOUNGE, Weapon: DAGGER]
		 * MS WHITE [Room: CONSERVATORY, Player: COLONEL MUSTARD, Weapon: LEADPIPE]
		 * MR GREEN [Room: KITCHEN, Player: MS WHITE, Weapon: REVOLVER]
		 * MRS PEACOCK [Room: LIBRARY, Player: MR GREEN, Weapon: ROPE]
		 * PROFESSOR PLUM [Room: HALL, Player: MRS PEACOCK, Weapon: SPANNER]
		 */
		Game g = new Game();
		g.setBoard(defaultBoard(g));	
		g.setOrder(g.getPlayers());
		defaultHand(g);

		for(int i=0; i<4; i++) {
			g.nextPlayer();
		}

		// gets scarlett to the dining room so that she can suggest something
		int movesLeft = 17;
		Player p = g.getOrder().peek();
		p.resetXYPositions(movesLeft+1);
		p.setXYPosition(p.getX(), p.getY());	
		movesLeft -= g.performMove(new Move(7, Direction.LEFT), movesLeft, p);
		movesLeft -= g.performMove(new Move(10, Direction.DOWN), movesLeft, p);
		movesLeft -= g.performMove(new Move(1, Direction.RIGHT), movesLeft, p);

		System.out.println(g.getPlayers());

		g.checkSuggestion(new Tuple(g.getPlayers().get(3),g.getWeapons().get(4),g.getRooms().get(5)), false);

		// checks that the colonel mustard card was found and that player 3 had it 

		assertEquals( g.getCardPlayerHas(),null);
		assertEquals(g.getPlayerHasCard(),0);
	}

	@Test
	void suggestTest6() {
		Game g = new Game();
		g.setBoard(defaultBoard(g));	
		g.setOrder(g.getPlayers());
		defaultHand(g);

		// move scarlett up 6, left 1, up 3
		int movesLeft = 10;
		Player p = g.getOrder().peek();
		p.resetXYPositions(movesLeft+1);
		p.setXYPosition(p.getX(), p.getY());	
		movesLeft -= g.performMove(new Move(6, Direction.UP), movesLeft, p);
		movesLeft -= g.performMove(new Move(1, Direction.LEFT), movesLeft, p);
		movesLeft -= g.performMove(new Move(3, Direction.UP), movesLeft, p);

		g.checkSuggestion(new Tuple(g.getPlayers().get(2),g.getWeapons().get(0),g.getRooms().get(0)), false);
		g.nextPlayer();

		// moves mustard right 6, down 2
		movesLeft = 8;
		p = g.getOrder().peek();
		p.resetXYPositions(movesLeft+1);
		p.setXYPosition(p.getX(), p.getY());	
		movesLeft -= g.performMove(new Move(6, Direction.RIGHT), movesLeft, p);
		movesLeft -= g.performMove(new Move(2, Direction.DOWN), movesLeft, p);

		g.checkSuggestion(new Tuple(g.getPlayers().get(2),g.getWeapons().get(0),g.getRooms().get(8)), false);
		assertEquals(g.getCardPlayerHas().toString(),g.getPlayers().get(3).getHand().get(1).toString());

	}

	//-----------------------------//
	// DEFAULT METHODS FOR TESTING //
	//-----------------------------//


	/**
	 * Tests to see if the default board and default solutions are correct
	 */
	@Test
	void testGame() {
		Game g = new Game();
		g.setBoard(defaultBoard(g));

		assertEquals(g.getSolution().toString(),
				"The envelope contains DINING ROOM, MISS SCARLETT, CANDLESTICK"
				);
		assertEquals(g.getBoard().toString(),
				"|X|X|X|X|X|X|X|X|X|W|X|X|X|X|G|X|X|X|X|X|X|X|X|X|\n" +
						"|#|#|#|#|#|#|X|_|_|_|#|#|#|#|_|_|_|X|#|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|#|_|_|#|#|#|#|#|#|\n" +
						"|#|K|C|H|N|#|_|_|#|#|B|A|L|L|#|#|_|_|#|C|O|N|S|#|\n" +
						"|#|#|#|#|#|#|_|_|#|#|R|O|O|M|#|#|_|_|@|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|_|_|@|#|#|#|#|#|#|@|_|_|_|#|#|#|#|X|\n" +
						"|X|#|#|#|@|#|_|_|#|#|#|#|#|#|#|#|_|_|_|_|_|_|_|B|\n" +
						"|_|_|_|_|_|_|_|_|#|@|#|#|#|#|@|#|_|_|_|_|_|_|_|X|\n" +
						"|X|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|#|#|#|#|#|#|\n" +
						"|#|#|#|#|#|_|_|_|_|_|_|_|_|_|_|_|_|_|@|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|B|L|R|D|#|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|#|#|#|#|#|\n" +
						"|#|D|I|N|I|N|G|@|_|_|X|X|X|X|X|_|_|_|#|#|#|#|@|#|\n" +
						"|#|#|R|O|O|M|#|#|_|_|X|X|X|X|X|_|_|_|_|_|_|_|_|X|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|#|@|#|#|X|\n" +
						"|#|#|#|#|#|#|@|#|_|_|X|X|X|X|X|_|_|#|#|#|#|#|#|#|\n" +
						"|X|_|_|_|_|_|_|_|_|_|X|X|X|X|X|_|_|@|#|L|B|R|Y|#|\n" +
						"|M|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|#|#|#|#|#|#|#|\n" +
						"|X|_|_|_|_|_|_|_|_|#|#|@|@|#|#|_|_|_|#|#|#|#|#|X|\n" +
						"|#|#|#|#|#|#|@|_|_|#|#|#|#|#|#|_|_|_|_|_|_|_|_|P|\n" +
						"|#|#|#|#|#|#|#|_|_|#|#|#|#|#|@|_|_|_|_|_|_|_|_|X|\n" +
						"|#|#|L|O|U|#|#|_|_|#|#|#|#|#|#|_|_|@|#|#|#|#|#|#|\n" +
						"|#|#|N|G|E|#|#|_|_|#|H|A|L|L|#|_|_|#|S|T|U|D|Y|#|\n" +
						"|#|#|#|#|#|#|#|_|_|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|\n" +
				"|#|#|#|#|#|#|X|S|X|#|#|#|#|#|#|X|_|X|#|#|#|#|#|#|\n");

		g.setOrder(g.getPlayers());
		for (Player p : g.getPlayers()) {
			assertEquals(p.isInPlay(),true);
		}
	}

	@Test
	void testMoveScarlett() {
		Game g = new Game();
		g.setBoard(defaultBoard(g));	
		g.setOrder(g.getPlayers());

		int movesLeft = 5;
		Player p = g.getOrder().peek();
		p.resetXYPositions(movesLeft+1);
		p.setXYPosition(p.getX(), p.getY());

		movesLeft -= g.performMove(new Move(5, Direction.UP), movesLeft, p);

		assertEquals(g.getBoard().toString(),
				"|X|X|X|X|X|X|X|X|X|W|X|X|X|X|G|X|X|X|X|X|X|X|X|X|\n" +
						"|#|#|#|#|#|#|X|_|_|_|#|#|#|#|_|_|_|X|#|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|#|_|_|#|#|#|#|#|#|\n" +
						"|#|K|C|H|N|#|_|_|#|#|B|A|L|L|#|#|_|_|#|C|O|N|S|#|\n" +
						"|#|#|#|#|#|#|_|_|#|#|R|O|O|M|#|#|_|_|@|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|_|_|@|#|#|#|#|#|#|@|_|_|_|#|#|#|#|X|\n" +
						"|X|#|#|#|@|#|_|_|#|#|#|#|#|#|#|#|_|_|_|_|_|_|_|B|\n" +
						"|_|_|_|_|_|_|_|_|#|@|#|#|#|#|@|#|_|_|_|_|_|_|_|X|\n" +
						"|X|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|#|#|#|#|#|#|\n" +
						"|#|#|#|#|#|_|_|_|_|_|_|_|_|_|_|_|_|_|@|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|B|L|R|D|#|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|#|#|#|#|#|\n" +
						"|#|D|I|N|I|N|G|@|_|_|X|X|X|X|X|_|_|_|#|#|#|#|@|#|\n" +
						"|#|#|R|O|O|M|#|#|_|_|X|X|X|X|X|_|_|_|_|_|_|_|_|X|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|#|@|#|#|X|\n" +
						"|#|#|#|#|#|#|@|#|_|_|X|X|X|X|X|_|_|#|#|#|#|#|#|#|\n" +
						"|X|_|_|_|_|_|_|_|_|_|X|X|X|X|X|_|_|@|#|L|B|R|Y|#|\n" +
						"|M|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|#|#|#|#|#|#|#|\n" +
						"|X|_|_|_|_|_|_|_|_|#|#|@|@|#|#|_|_|_|#|#|#|#|#|X|\n" +
						"|#|#|#|#|#|#|@|S|_|#|#|#|#|#|#|_|_|_|_|_|_|_|_|P|\n" +
						"|#|#|#|#|#|#|#|_|_|#|#|#|#|#|@|_|_|_|_|_|_|_|_|X|\n" +
						"|#|#|L|O|U|#|#|_|_|#|#|#|#|#|#|_|_|@|#|#|#|#|#|#|\n" +
						"|#|#|N|G|E|#|#|_|_|#|H|A|L|L|#|_|_|#|S|T|U|D|Y|#|\n" +
						"|#|#|#|#|#|#|#|_|_|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|\n" +
				"|#|#|#|#|#|#|X|_|X|#|#|#|#|#|#|X|_|X|#|#|#|#|#|#|\n");

		assertEquals(movesLeft,0);

	}

	@Test
	void testMoveMustard() {
		Game g = new Game();
		g.setBoard(defaultBoard(g));	
		g.setOrder(g.getPlayers());
		g.nextPlayer();

		int movesLeft = 8;
		Player p = g.getOrder().peek();
		p.resetXYPositions(movesLeft+1);
		p.setXYPosition(p.getX(), p.getY());

		movesLeft -= g.performMove(new Move(3, Direction.RIGHT), movesLeft, p);
		movesLeft -= g.performMove(new Move(1, Direction.DOWN), movesLeft, p);
		movesLeft -= g.performMove(new Move(4, Direction.RIGHT), movesLeft, p);

		assertEquals(g.getBoard().toString(),
				"|X|X|X|X|X|X|X|X|X|W|X|X|X|X|G|X|X|X|X|X|X|X|X|X|\n" +
						"|#|#|#|#|#|#|X|_|_|_|#|#|#|#|_|_|_|X|#|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|#|_|_|#|#|#|#|#|#|\n" +
						"|#|K|C|H|N|#|_|_|#|#|B|A|L|L|#|#|_|_|#|C|O|N|S|#|\n" +
						"|#|#|#|#|#|#|_|_|#|#|R|O|O|M|#|#|_|_|@|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|_|_|@|#|#|#|#|#|#|@|_|_|_|#|#|#|#|X|\n" +
						"|X|#|#|#|@|#|_|_|#|#|#|#|#|#|#|#|_|_|_|_|_|_|_|B|\n" +
						"|_|_|_|_|_|_|_|_|#|@|#|#|#|#|@|#|_|_|_|_|_|_|_|X|\n" +
						"|X|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|#|#|#|#|#|#|\n" +
						"|#|#|#|#|#|_|_|_|_|_|_|_|_|_|_|_|_|_|@|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|B|L|R|D|#|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|#|#|#|#|#|\n" +
						"|#|D|I|N|I|N|G|@|_|_|X|X|X|X|X|_|_|_|#|#|#|#|@|#|\n" +
						"|#|#|R|O|O|M|#|#|_|_|X|X|X|X|X|_|_|_|_|_|_|_|_|X|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|#|@|#|#|X|\n" +
						"|#|#|#|#|#|#|@|#|_|_|X|X|X|X|X|_|_|#|#|#|#|#|#|#|\n" +
						"|X|_|_|_|_|_|_|_|_|_|X|X|X|X|X|_|_|@|#|L|B|R|Y|#|\n" +
						"|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|#|#|#|#|#|#|#|\n" +
						"|X|_|_|_|_|_|_|M|_|#|#|@|@|#|#|_|_|_|#|#|#|#|#|X|\n" +
						"|#|#|#|#|#|#|@|_|_|#|#|#|#|#|#|_|_|_|_|_|_|_|_|P|\n" +
						"|#|#|#|#|#|#|#|_|_|#|#|#|#|#|@|_|_|_|_|_|_|_|_|X|\n" +
						"|#|#|L|O|U|#|#|_|_|#|#|#|#|#|#|_|_|@|#|#|#|#|#|#|\n" +
						"|#|#|N|G|E|#|#|_|_|#|H|A|L|L|#|_|_|#|S|T|U|D|Y|#|\n" +
						"|#|#|#|#|#|#|#|_|_|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|\n" +
				"|#|#|#|#|#|#|X|S|X|#|#|#|#|#|#|X|_|X|#|#|#|#|#|#|\n");

		assertEquals(movesLeft,0);

	}

	@Test
	void testMoveWhite() {
		Game g = new Game();
		g.setBoard(defaultBoard(g));	
		g.setOrder(g.getPlayers());

		// skips to ms white
		for (int i = 0; i < 2; i++)
			g.nextPlayer();

		int movesLeft = 12;
		Player p = g.getOrder().peek();
		p.resetXYPositions(movesLeft+1);
		p.setXYPosition(p.getX(), p.getY());

		movesLeft -= g.performMove(new Move(1, Direction.DOWN), movesLeft, p);
		movesLeft -= g.performMove(new Move(2, Direction.LEFT), movesLeft, p);
		movesLeft -= g.performMove(new Move(8, Direction.DOWN), movesLeft, p);
		movesLeft -= g.performMove(new Move(1, Direction.RIGHT), movesLeft, p);

		assertEquals(g.getBoard().toString(),
				"|X|X|X|X|X|X|X|X|X|_|X|X|X|X|G|X|X|X|X|X|X|X|X|X|\n" +
						"|#|#|#|#|#|#|X|_|_|_|#|#|#|#|_|_|_|X|#|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|#|_|_|#|#|#|#|#|#|\n" +
						"|#|K|C|H|N|#|_|_|#|#|B|A|L|L|#|#|_|_|#|C|O|N|S|#|\n" +
						"|#|#|#|#|#|#|_|_|#|#|R|O|O|M|#|#|_|_|@|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|_|_|@|#|#|#|#|#|#|@|_|_|_|#|#|#|#|X|\n" +
						"|X|#|#|#|@|#|_|_|#|#|#|#|#|#|#|#|_|_|_|_|_|_|_|B|\n" +
						"|_|_|_|_|_|_|_|_|#|@|#|#|#|#|@|#|_|_|_|_|_|_|_|X|\n" +
						"|X|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|#|#|#|#|#|#|\n" +
						"|#|#|#|#|#|_|_|_|W|_|_|_|_|_|_|_|_|_|@|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|B|L|R|D|#|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|#|#|#|#|#|\n" +
						"|#|D|I|N|I|N|G|@|_|_|X|X|X|X|X|_|_|_|#|#|#|#|@|#|\n" +
						"|#|#|R|O|O|M|#|#|_|_|X|X|X|X|X|_|_|_|_|_|_|_|_|X|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|#|@|#|#|X|\n" +
						"|#|#|#|#|#|#|@|#|_|_|X|X|X|X|X|_|_|#|#|#|#|#|#|#|\n" +
						"|X|_|_|_|_|_|_|_|_|_|X|X|X|X|X|_|_|@|#|L|B|R|Y|#|\n" +
						"|M|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|#|#|#|#|#|#|#|\n" +
						"|X|_|_|_|_|_|_|_|_|#|#|@|@|#|#|_|_|_|#|#|#|#|#|X|\n" +
						"|#|#|#|#|#|#|@|_|_|#|#|#|#|#|#|_|_|_|_|_|_|_|_|P|\n" +
						"|#|#|#|#|#|#|#|_|_|#|#|#|#|#|@|_|_|_|_|_|_|_|_|X|\n" +
						"|#|#|L|O|U|#|#|_|_|#|#|#|#|#|#|_|_|@|#|#|#|#|#|#|\n" +
						"|#|#|N|G|E|#|#|_|_|#|H|A|L|L|#|_|_|#|S|T|U|D|Y|#|\n" +
						"|#|#|#|#|#|#|#|_|_|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|\n" +
				"|#|#|#|#|#|#|X|S|X|#|#|#|#|#|#|X|_|X|#|#|#|#|#|#|\n");

		assertEquals(movesLeft,0);

	}

	@Test
	void testMoveGreen() {
		Game g = new Game();
		g.setBoard(defaultBoard(g));	
		g.setOrder(g.getPlayers());

		// skips to mr green
		for (int i = 0; i < 3; i++)
			g.nextPlayer();

		int movesLeft = 6;
		Player p = g.getOrder().peek();
		p.resetXYPositions(movesLeft+1);
		p.setXYPosition(p.getX(), p.getY());

		movesLeft -= g.performMove(new Move(1, Direction.DOWN), movesLeft, p);
		movesLeft -= g.performMove(new Move(2, Direction.RIGHT), movesLeft, p);
		movesLeft -= g.performMove(new Move(1, Direction.DOWN), movesLeft, p);
		movesLeft -= g.performMove(new Move(1, Direction.RIGHT), movesLeft, p);
		movesLeft -= g.performMove(new Move(1, Direction.DOWN), movesLeft, p);

		assertEquals(g.getBoard().toString(),
				"|X|X|X|X|X|X|X|X|X|W|X|X|X|X|_|X|X|X|X|X|X|X|X|X|\n" +
						"|#|#|#|#|#|#|X|_|_|_|#|#|#|#|_|_|_|X|#|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|#|_|_|#|#|#|#|#|#|\n" +
						"|#|K|C|H|N|#|_|_|#|#|B|A|L|L|#|#|_|G|#|C|O|N|S|#|\n" +
						"|#|#|#|#|#|#|_|_|#|#|R|O|O|M|#|#|_|_|@|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|_|_|@|#|#|#|#|#|#|@|_|_|_|#|#|#|#|X|\n" +
						"|X|#|#|#|@|#|_|_|#|#|#|#|#|#|#|#|_|_|_|_|_|_|_|B|\n" +
						"|_|_|_|_|_|_|_|_|#|@|#|#|#|#|@|#|_|_|_|_|_|_|_|X|\n" +
						"|X|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|#|#|#|#|#|#|\n" +
						"|#|#|#|#|#|_|_|_|_|_|_|_|_|_|_|_|_|_|@|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|B|L|R|D|#|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|#|#|#|#|#|\n" +
						"|#|D|I|N|I|N|G|@|_|_|X|X|X|X|X|_|_|_|#|#|#|#|@|#|\n" +
						"|#|#|R|O|O|M|#|#|_|_|X|X|X|X|X|_|_|_|_|_|_|_|_|X|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|#|@|#|#|X|\n" +
						"|#|#|#|#|#|#|@|#|_|_|X|X|X|X|X|_|_|#|#|#|#|#|#|#|\n" +
						"|X|_|_|_|_|_|_|_|_|_|X|X|X|X|X|_|_|@|#|L|B|R|Y|#|\n" +
						"|M|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|#|#|#|#|#|#|#|\n" +
						"|X|_|_|_|_|_|_|_|_|#|#|@|@|#|#|_|_|_|#|#|#|#|#|X|\n" +
						"|#|#|#|#|#|#|@|_|_|#|#|#|#|#|#|_|_|_|_|_|_|_|_|P|\n" +
						"|#|#|#|#|#|#|#|_|_|#|#|#|#|#|@|_|_|_|_|_|_|_|_|X|\n" +
						"|#|#|L|O|U|#|#|_|_|#|#|#|#|#|#|_|_|@|#|#|#|#|#|#|\n" +
						"|#|#|N|G|E|#|#|_|_|#|H|A|L|L|#|_|_|#|S|T|U|D|Y|#|\n" +
						"|#|#|#|#|#|#|#|_|_|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|\n" +
				"|#|#|#|#|#|#|X|S|X|#|#|#|#|#|#|X|_|X|#|#|#|#|#|#|\n");

		assertEquals(movesLeft,0);

	}

	@Test
	void testMovePeacock() {
		Game g = new Game();
		g.setBoard(defaultBoard(g));	
		g.setOrder(g.getPlayers());

		// skips to mrs peacock
		for (int i = 0; i < 4; i++)
			g.nextPlayer();

		int movesLeft = 5;
		Player p = g.getOrder().peek();
		p.resetXYPositions(movesLeft+1);
		p.setXYPosition(p.getX(), p.getY());

		movesLeft -= g.performMove(new Move(4, Direction.LEFT), movesLeft, p);
		movesLeft -= g.performMove(new Move(1, Direction.DOWN), movesLeft, p);

		assertEquals(g.getBoard().toString(),
				"|X|X|X|X|X|X|X|X|X|W|X|X|X|X|G|X|X|X|X|X|X|X|X|X|\n" +
						"|#|#|#|#|#|#|X|_|_|_|#|#|#|#|_|_|_|X|#|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|#|_|_|#|#|#|#|#|#|\n" +
						"|#|K|C|H|N|#|_|_|#|#|B|A|L|L|#|#|_|_|#|C|O|N|S|#|\n" +
						"|#|#|#|#|#|#|_|_|#|#|R|O|O|M|#|#|_|_|@|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|_|_|@|#|#|#|#|#|#|@|_|_|_|#|#|#|#|X|\n" +
						"|X|#|#|#|@|#|_|_|#|#|#|#|#|#|#|#|_|_|_|_|_|_|_|_|\n" +
						"|_|_|_|_|_|_|_|_|#|@|#|#|#|#|@|#|_|_|_|B|_|_|_|X|\n" +
						"|X|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|#|#|#|#|#|#|\n" +
						"|#|#|#|#|#|_|_|_|_|_|_|_|_|_|_|_|_|_|@|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|B|L|R|D|#|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|#|#|#|#|#|\n" +
						"|#|D|I|N|I|N|G|@|_|_|X|X|X|X|X|_|_|_|#|#|#|#|@|#|\n" +
						"|#|#|R|O|O|M|#|#|_|_|X|X|X|X|X|_|_|_|_|_|_|_|_|X|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|#|@|#|#|X|\n" +
						"|#|#|#|#|#|#|@|#|_|_|X|X|X|X|X|_|_|#|#|#|#|#|#|#|\n" +
						"|X|_|_|_|_|_|_|_|_|_|X|X|X|X|X|_|_|@|#|L|B|R|Y|#|\n" +
						"|M|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|#|#|#|#|#|#|#|\n" +
						"|X|_|_|_|_|_|_|_|_|#|#|@|@|#|#|_|_|_|#|#|#|#|#|X|\n" +
						"|#|#|#|#|#|#|@|_|_|#|#|#|#|#|#|_|_|_|_|_|_|_|_|P|\n" +
						"|#|#|#|#|#|#|#|_|_|#|#|#|#|#|@|_|_|_|_|_|_|_|_|X|\n" +
						"|#|#|L|O|U|#|#|_|_|#|#|#|#|#|#|_|_|@|#|#|#|#|#|#|\n" +
						"|#|#|N|G|E|#|#|_|_|#|H|A|L|L|#|_|_|#|S|T|U|D|Y|#|\n" +
						"|#|#|#|#|#|#|#|_|_|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|\n" +
				"|#|#|#|#|#|#|X|S|X|#|#|#|#|#|#|X|_|X|#|#|#|#|#|#|\n");

		assertEquals(movesLeft,0);

	}

	@Test
	void testMovePlum() {
		Game g = new Game();
		g.setBoard(defaultBoard(g));	
		g.setOrder(g.getPlayers());

		// skips to mrs peacock
		for (int i = 0; i < 5; i++)
			g.nextPlayer();

		int movesLeft = 7;
		Player p = g.getOrder().peek();
		p.resetXYPositions(movesLeft+1);
		p.setXYPosition(p.getX(), p.getY());

		movesLeft -= g.performMove(new Move(6, Direction.LEFT), movesLeft, p);
		movesLeft -= g.performMove(new Move(1, Direction.UP), movesLeft, p);

		assertEquals(g.getBoard().toString(),
				"|X|X|X|X|X|X|X|X|X|W|X|X|X|X|G|X|X|X|X|X|X|X|X|X|\n" +
						"|#|#|#|#|#|#|X|_|_|_|#|#|#|#|_|_|_|X|#|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|#|_|_|#|#|#|#|#|#|\n" +
						"|#|K|C|H|N|#|_|_|#|#|B|A|L|L|#|#|_|_|#|C|O|N|S|#|\n" +
						"|#|#|#|#|#|#|_|_|#|#|R|O|O|M|#|#|_|_|@|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|_|_|@|#|#|#|#|#|#|@|_|_|_|#|#|#|#|X|\n" +
						"|X|#|#|#|@|#|_|_|#|#|#|#|#|#|#|#|_|_|_|_|_|_|_|B|\n" +
						"|_|_|_|_|_|_|_|_|#|@|#|#|#|#|@|#|_|_|_|_|_|_|_|X|\n" +
						"|X|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|#|#|#|#|#|#|\n" +
						"|#|#|#|#|#|_|_|_|_|_|_|_|_|_|_|_|_|_|@|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|B|L|R|D|#|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|#|#|#|#|#|\n" +
						"|#|D|I|N|I|N|G|@|_|_|X|X|X|X|X|_|_|_|#|#|#|#|@|#|\n" +
						"|#|#|R|O|O|M|#|#|_|_|X|X|X|X|X|_|_|_|_|_|_|_|_|X|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|#|@|#|#|X|\n" +
						"|#|#|#|#|#|#|@|#|_|_|X|X|X|X|X|_|_|#|#|#|#|#|#|#|\n" +
						"|X|_|_|_|_|_|_|_|_|_|X|X|X|X|X|_|_|@|#|L|B|R|Y|#|\n" +
						"|M|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|#|#|#|#|#|#|#|\n" +
						"|X|_|_|_|_|_|_|_|_|#|#|@|@|#|#|_|_|P|#|#|#|#|#|X|\n" +
						"|#|#|#|#|#|#|@|_|_|#|#|#|#|#|#|_|_|_|_|_|_|_|_|_|\n" +
						"|#|#|#|#|#|#|#|_|_|#|#|#|#|#|@|_|_|_|_|_|_|_|_|X|\n" +
						"|#|#|L|O|U|#|#|_|_|#|#|#|#|#|#|_|_|@|#|#|#|#|#|#|\n" +
						"|#|#|N|G|E|#|#|_|_|#|H|A|L|L|#|_|_|#|S|T|U|D|Y|#|\n" +
						"|#|#|#|#|#|#|#|_|_|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|\n" +
				"|#|#|#|#|#|#|X|S|X|#|#|#|#|#|#|X|_|X|#|#|#|#|#|#|\n");

		assertEquals(movesLeft,0);

	}

	@Test
	void testMoveMoreThanTwelve() {
		Game g = new Game();
		g.setBoard(defaultBoard(g));	
		g.setOrder(g.getPlayers());
		g.nextPlayer();

		int movesLeft = 12;
		Player p = g.getOrder().peek();
		p.resetXYPositions(movesLeft+1);
		p.setXYPosition(p.getX(), p.getY());

		movesLeft -= g.performMove(new Move(500, Direction.RIGHT), movesLeft, p);
		p.setXYPosition(p.getX(), p.getY());

		assertEquals(g.getBoard().toString(),
				"|X|X|X|X|X|X|X|X|X|W|X|X|X|X|G|X|X|X|X|X|X|X|X|X|\n" +
						"|#|#|#|#|#|#|X|_|_|_|#|#|#|#|_|_|_|X|#|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|#|_|_|#|#|#|#|#|#|\n" +
						"|#|K|C|H|N|#|_|_|#|#|B|A|L|L|#|#|_|_|#|C|O|N|S|#|\n" +
						"|#|#|#|#|#|#|_|_|#|#|R|O|O|M|#|#|_|_|@|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|_|_|@|#|#|#|#|#|#|@|_|_|_|#|#|#|#|X|\n" +
						"|X|#|#|#|@|#|_|_|#|#|#|#|#|#|#|#|_|_|_|_|_|_|_|B|\n" +
						"|_|_|_|_|_|_|_|_|#|@|#|#|#|#|@|#|_|_|_|_|_|_|_|X|\n" +
						"|X|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|#|#|#|#|#|#|\n" +
						"|#|#|#|#|#|_|_|_|_|_|_|_|_|_|_|_|_|_|@|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|B|L|R|D|#|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|#|#|#|#|#|\n" +
						"|#|D|I|N|I|N|G|@|_|_|X|X|X|X|X|_|_|_|#|#|#|#|@|#|\n" +
						"|#|#|R|O|O|M|#|#|_|_|X|X|X|X|X|_|_|_|_|_|_|_|_|X|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|#|@|#|#|X|\n" +
						"|#|#|#|#|#|#|@|#|_|_|X|X|X|X|X|_|_|#|#|#|#|#|#|#|\n" +
						"|X|_|_|_|_|_|_|_|_|_|X|X|X|X|X|_|_|@|#|L|B|R|Y|#|\n" +
						"|_|_|_|_|_|_|_|_|_|_|_|_|M|_|_|_|_|#|#|#|#|#|#|#|\n" +
						"|X|_|_|_|_|_|_|_|_|#|#|@|@|#|#|_|_|_|#|#|#|#|#|X|\n" +
						"|#|#|#|#|#|#|@|_|_|#|#|#|#|#|#|_|_|_|_|_|_|_|_|P|\n" +
						"|#|#|#|#|#|#|#|_|_|#|#|#|#|#|@|_|_|_|_|_|_|_|_|X|\n" +
						"|#|#|L|O|U|#|#|_|_|#|#|#|#|#|#|_|_|@|#|#|#|#|#|#|\n" +
						"|#|#|N|G|E|#|#|_|_|#|H|A|L|L|#|_|_|#|S|T|U|D|Y|#|\n" +
						"|#|#|#|#|#|#|#|_|_|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|\n" +
				"|#|#|#|#|#|#|X|S|X|#|#|#|#|#|#|X|_|X|#|#|#|#|#|#|\n");

		assertEquals(movesLeft,0);

	}

	@Test
	void testInvalidBasicMoves() {
		Game g = new Game();
		g.setBoard(defaultBoard(g));	
		g.setOrder(g.getPlayers());

		int movesLeft = 5;
		Player p = g.getOrder().peek();
		p.resetXYPositions(movesLeft+1);
		p.setXYPosition(p.getX(), p.getY());

		// invalid move backward out of bounds
		movesLeft -= g.performMove(new Move(1, Direction.DOWN), movesLeft, p);
		// invalid move backward onto null tile
		movesLeft -= g.performMove(new Move(1, Direction.LEFT), movesLeft, p);
		// valid move forward
		movesLeft -= g.performMove(new Move(4, Direction.UP), movesLeft, p);
		// invalid move into room tile
		movesLeft -= g.performMove(new Move(1, Direction.LEFT), movesLeft, p);
		// invalid move backward onto self
		movesLeft -= g.performMove(new Move(1, Direction.DOWN), movesLeft, p);

		assertEquals(g.getBoard().toString(),
				"|X|X|X|X|X|X|X|X|X|W|X|X|X|X|G|X|X|X|X|X|X|X|X|X|\n" +
						"|#|#|#|#|#|#|X|_|_|_|#|#|#|#|_|_|_|X|#|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|#|_|_|#|#|#|#|#|#|\n" +
						"|#|K|C|H|N|#|_|_|#|#|B|A|L|L|#|#|_|_|#|C|O|N|S|#|\n" +
						"|#|#|#|#|#|#|_|_|#|#|R|O|O|M|#|#|_|_|@|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|_|_|@|#|#|#|#|#|#|@|_|_|_|#|#|#|#|X|\n" +
						"|X|#|#|#|@|#|_|_|#|#|#|#|#|#|#|#|_|_|_|_|_|_|_|B|\n" +
						"|_|_|_|_|_|_|_|_|#|@|#|#|#|#|@|#|_|_|_|_|_|_|_|X|\n" +
						"|X|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|#|#|#|#|#|#|\n" +
						"|#|#|#|#|#|_|_|_|_|_|_|_|_|_|_|_|_|_|@|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|B|L|R|D|#|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|#|#|#|#|#|\n" +
						"|#|D|I|N|I|N|G|@|_|_|X|X|X|X|X|_|_|_|#|#|#|#|@|#|\n" +
						"|#|#|R|O|O|M|#|#|_|_|X|X|X|X|X|_|_|_|_|_|_|_|_|X|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|#|@|#|#|X|\n" +
						"|#|#|#|#|#|#|@|#|_|_|X|X|X|X|X|_|_|#|#|#|#|#|#|#|\n" +
						"|X|_|_|_|_|_|_|_|_|_|X|X|X|X|X|_|_|@|#|L|B|R|Y|#|\n" +
						"|M|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|#|#|#|#|#|#|#|\n" +
						"|X|_|_|_|_|_|_|_|_|#|#|@|@|#|#|_|_|_|#|#|#|#|#|X|\n" +
						"|#|#|#|#|#|#|@|_|_|#|#|#|#|#|#|_|_|_|_|_|_|_|_|P|\n" +
						"|#|#|#|#|#|#|#|S|_|#|#|#|#|#|@|_|_|_|_|_|_|_|_|X|\n" +
						"|#|#|L|O|U|#|#|_|_|#|#|#|#|#|#|_|_|@|#|#|#|#|#|#|\n" +
						"|#|#|N|G|E|#|#|_|_|#|H|A|L|L|#|_|_|#|S|T|U|D|Y|#|\n" +
						"|#|#|#|#|#|#|#|_|_|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|\n" +
				"|#|#|#|#|#|#|X|_|X|#|#|#|#|#|#|X|_|X|#|#|#|#|#|#|\n");

		// asserts Miss Scarlett is still in turn, since she will have 1 move left
		assertEquals(g.getPlayers().get(0),g.getOrder().peek());
		assertEquals(movesLeft,1);

	}

	@Test
	void testInvalidMoveOnPlayer() {
		Game g = new Game();
		g.setBoard(defaultBoard(g));	
		g.setOrder(g.getPlayers());

		// move scarlett up 7
		int movesLeft = 7;
		Player p = g.getOrder().peek();
		p.resetXYPositions(movesLeft+1);
		p.setXYPosition(p.getX(), p.getY());	
		movesLeft -= g.performMove(new Move(7, Direction.UP), movesLeft, p);
		g.nextPlayer();

		// move mustard right 6
		movesLeft = 8;
		p = g.getOrder().peek();
		p.resetXYPositions(movesLeft+1);
		p.setXYPosition(p.getX(), p.getY());	
		movesLeft -= g.performMove(new Move(8, Direction.RIGHT), movesLeft, p);

		assertEquals(g.getBoard().toString(),
				"|X|X|X|X|X|X|X|X|X|W|X|X|X|X|G|X|X|X|X|X|X|X|X|X|\n" +
				"|#|#|#|#|#|#|X|_|_|_|#|#|#|#|_|_|_|X|#|#|#|#|#|#|\n" +
				"|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|#|_|_|#|#|#|#|#|#|\n" +
				"|#|K|C|H|N|#|_|_|#|#|B|A|L|L|#|#|_|_|#|C|O|N|S|#|\n" +
				"|#|#|#|#|#|#|_|_|#|#|R|O|O|M|#|#|_|_|@|#|#|#|#|#|\n" +
				"|#|#|#|#|#|#|_|_|@|#|#|#|#|#|#|@|_|_|_|#|#|#|#|X|\n" +
				"|X|#|#|#|@|#|_|_|#|#|#|#|#|#|#|#|_|_|_|_|_|_|_|B|\n" +
				"|_|_|_|_|_|_|_|_|#|@|#|#|#|#|@|#|_|_|_|_|_|_|_|X|\n" +
				"|X|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|#|#|#|#|#|#|\n" +
				"|#|#|#|#|#|_|_|_|_|_|_|_|_|_|_|_|_|_|@|#|#|#|#|#|\n" +
				"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|B|L|R|D|#|\n" +
				"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|#|#|#|#|#|\n" +
				"|#|D|I|N|I|N|G|@|_|_|X|X|X|X|X|_|_|_|#|#|#|#|@|#|\n" +
				"|#|#|R|O|O|M|#|#|_|_|X|X|X|X|X|_|_|_|_|_|_|_|_|X|\n" +
				"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|#|@|#|#|X|\n" +
				"|#|#|#|#|#|#|@|#|_|_|X|X|X|X|X|_|_|#|#|#|#|#|#|#|\n" +
				"|X|_|_|_|_|_|_|_|_|_|X|X|X|X|X|_|_|@|#|L|B|R|Y|#|\n" +
				"|_|_|_|_|_|_|M|S|_|_|_|_|_|_|_|_|_|#|#|#|#|#|#|#|\n" +
				"|X|_|_|_|_|_|_|_|_|#|#|@|@|#|#|_|_|_|#|#|#|#|#|X|\n" +
				"|#|#|#|#|#|#|@|_|_|#|#|#|#|#|#|_|_|_|_|_|_|_|_|P|\n" +
				"|#|#|#|#|#|#|#|_|_|#|#|#|#|#|@|_|_|_|_|_|_|_|_|X|\n" +
				"|#|#|L|O|U|#|#|_|_|#|#|#|#|#|#|_|_|@|#|#|#|#|#|#|\n" +
				"|#|#|N|G|E|#|#|_|_|#|H|A|L|L|#|_|_|#|S|T|U|D|Y|#|\n" +
				"|#|#|#|#|#|#|#|_|_|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|\n" +
				"|#|#|#|#|#|#|X|_|X|#|#|#|#|#|#|X|_|X|#|#|#|#|#|#|\n");

		// asserts Colonel Mustard is still in turn, since he will have 2 moves left
		assertEquals(g.getPlayers().get(1),g.getOrder().peek());
		assertEquals(movesLeft,2);

	}
	
	@Test
	void testInvalidEndTurnOnPlayer() {
		Game g = new Game();
		g.setBoard(defaultBoard(g));	
		g.setOrder(g.getPlayers());

		// move scarlett up 6
		int movesLeft = 6;
		Player p = g.getOrder().peek();
		p.resetXYPositions(movesLeft+1);
		p.setXYPosition(p.getX(), p.getY());	
		movesLeft -= g.performMove(new Move(6, Direction.UP), movesLeft, p);
		g.nextPlayer();

		// move mustard right 7
		movesLeft = 8;
		p = g.getOrder().peek();
		p.resetXYPositions(movesLeft+1);
		p.setXYPosition(p.getX(), p.getY());	
		movesLeft -= g.performMove(new Move(7, Direction.RIGHT), movesLeft, p);
		movesLeft -= g.performMove(new Move(1, Direction.DOWN), movesLeft, p);

		assertEquals(g.getBoard().toString(),
				"|X|X|X|X|X|X|X|X|X|W|X|X|X|X|G|X|X|X|X|X|X|X|X|X|\n" +
						"|#|#|#|#|#|#|X|_|_|_|#|#|#|#|_|_|_|X|#|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|#|_|_|#|#|#|#|#|#|\n" +
						"|#|K|C|H|N|#|_|_|#|#|B|A|L|L|#|#|_|_|#|C|O|N|S|#|\n" +
						"|#|#|#|#|#|#|_|_|#|#|R|O|O|M|#|#|_|_|@|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|_|_|@|#|#|#|#|#|#|@|_|_|_|#|#|#|#|X|\n" +
						"|X|#|#|#|@|#|_|_|#|#|#|#|#|#|#|#|_|_|_|_|_|_|_|B|\n" +
						"|_|_|_|_|_|_|_|_|#|@|#|#|#|#|@|#|_|_|_|_|_|_|_|X|\n" +
						"|X|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|#|#|#|#|#|#|\n" +
						"|#|#|#|#|#|_|_|_|_|_|_|_|_|_|_|_|_|_|@|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|B|L|R|D|#|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|#|#|#|#|#|\n" +
						"|#|D|I|N|I|N|G|@|_|_|X|X|X|X|X|_|_|_|#|#|#|#|@|#|\n" +
						"|#|#|R|O|O|M|#|#|_|_|X|X|X|X|X|_|_|_|_|_|_|_|_|X|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|#|@|#|#|X|\n" +
						"|#|#|#|#|#|#|@|#|_|_|X|X|X|X|X|_|_|#|#|#|#|#|#|#|\n" +
						"|X|_|_|_|_|_|_|_|_|_|X|X|X|X|X|_|_|@|#|L|B|R|Y|#|\n" +
						"|_|_|_|_|_|_|_|M|_|_|_|_|_|_|_|_|_|#|#|#|#|#|#|#|\n" +
						"|X|_|_|_|_|_|_|S|_|#|#|@|@|#|#|_|_|_|#|#|#|#|#|X|\n" +
						"|#|#|#|#|#|#|@|_|_|#|#|#|#|#|#|_|_|_|_|_|_|_|_|P|\n" +
						"|#|#|#|#|#|#|#|_|_|#|#|#|#|#|@|_|_|_|_|_|_|_|_|X|\n" +
						"|#|#|L|O|U|#|#|_|_|#|#|#|#|#|#|_|_|@|#|#|#|#|#|#|\n" +
						"|#|#|N|G|E|#|#|_|_|#|H|A|L|L|#|_|_|#|S|T|U|D|Y|#|\n" +
						"|#|#|#|#|#|#|#|_|_|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|\n" +
				"|#|#|#|#|#|#|X|_|X|#|#|#|#|#|#|X|_|X|#|#|#|#|#|#|\n");

		// asserts Colonel Mustard is still in turn, since he will have 1 move left
		assertEquals(g.getPlayers().get(1),g.getOrder().peek());
		assertEquals(movesLeft,1);

	}

	@Test
	void testInvalidPlayerOnEdge() {
		Game g = new Game();
		g.setBoard(defaultBoard(g));	
		g.setOrder(g.getPlayers());

		// move scarlett up 7, left 5
		int movesLeft = 12;
		Player p = g.getOrder().peek();
		p.resetXYPositions(movesLeft+1);
		p.setXYPosition(p.getX(), p.getY());	
		movesLeft -= g.performMove(new Move(7, Direction.UP), movesLeft, p);
		movesLeft -= g.performMove(new Move(5, Direction.LEFT), movesLeft, p);

		// skips back to miss scarlett
		for (int i = 0; i < 6; i++)
			g.nextPlayer();

		// move scarlett right 1, as she cannot move right 2
		movesLeft = 2;
		p = g.getOrder().peek();
		p.resetXYPositions(movesLeft+1);
		p.setXYPosition(p.getX(), p.getY());	
		movesLeft -= g.performMove(new Move(2, Direction.LEFT), movesLeft, p);

		assertEquals(g.getBoard().toString(),
				"|X|X|X|X|X|X|X|X|X|W|X|X|X|X|G|X|X|X|X|X|X|X|X|X|\n" +
						"|#|#|#|#|#|#|X|_|_|_|#|#|#|#|_|_|_|X|#|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|#|_|_|#|#|#|#|#|#|\n" +
						"|#|K|C|H|N|#|_|_|#|#|B|A|L|L|#|#|_|_|#|C|O|N|S|#|\n" +
						"|#|#|#|#|#|#|_|_|#|#|R|O|O|M|#|#|_|_|@|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|_|_|@|#|#|#|#|#|#|@|_|_|_|#|#|#|#|X|\n" +
						"|X|#|#|#|@|#|_|_|#|#|#|#|#|#|#|#|_|_|_|_|_|_|_|B|\n" +
						"|_|_|_|_|_|_|_|_|#|@|#|#|#|#|@|#|_|_|_|_|_|_|_|X|\n" +
						"|X|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|#|#|#|#|#|#|\n" +
						"|#|#|#|#|#|_|_|_|_|_|_|_|_|_|_|_|_|_|@|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|B|L|R|D|#|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|#|#|#|#|#|\n" +
						"|#|D|I|N|I|N|G|@|_|_|X|X|X|X|X|_|_|_|#|#|#|#|@|#|\n" +
						"|#|#|R|O|O|M|#|#|_|_|X|X|X|X|X|_|_|_|_|_|_|_|_|X|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|#|@|#|#|X|\n" +
						"|#|#|#|#|#|#|@|#|_|_|X|X|X|X|X|_|_|#|#|#|#|#|#|#|\n" +
						"|X|_|_|_|_|_|_|_|_|_|X|X|X|X|X|_|_|@|#|L|B|R|Y|#|\n" +
						"|M|S|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|#|#|#|#|#|#|#|\n" +
						"|X|_|_|_|_|_|_|_|_|#|#|@|@|#|#|_|_|_|#|#|#|#|#|X|\n" +
						"|#|#|#|#|#|#|@|_|_|#|#|#|#|#|#|_|_|_|_|_|_|_|_|P|\n" +
						"|#|#|#|#|#|#|#|_|_|#|#|#|#|#|@|_|_|_|_|_|_|_|_|X|\n" +
						"|#|#|L|O|U|#|#|_|_|#|#|#|#|#|#|_|_|@|#|#|#|#|#|#|\n" +
						"|#|#|N|G|E|#|#|_|_|#|H|A|L|L|#|_|_|#|S|T|U|D|Y|#|\n" +
						"|#|#|#|#|#|#|#|_|_|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|\n" +
				"|#|#|#|#|#|#|X|_|X|#|#|#|#|#|#|X|_|X|#|#|#|#|#|#|\n");

		// asserts Miss Scarlett is still in turn, since she will have 1 move left
		assertEquals(g.getPlayers().get(0),g.getOrder().peek());
		assertEquals(movesLeft,1);

	}

	@Test
	void testInvalidDoorEntry() {
		Game g = new Game();
		g.setBoard(defaultBoard(g));	
		g.setOrder(g.getPlayers());

		// move scarlett up 5
		int movesLeft = 6;
		Player p = g.getOrder().peek();
		p.resetXYPositions(movesLeft+1);
		p.setXYPosition(p.getX(), p.getY());	
		movesLeft -= g.performMove(new Move(5, Direction.UP), movesLeft, p);
		movesLeft -= g.performMove(new Move(1, Direction.LEFT), movesLeft, p);

		assertEquals(g.getBoard().toString(),
				"|X|X|X|X|X|X|X|X|X|W|X|X|X|X|G|X|X|X|X|X|X|X|X|X|\n" +
						"|#|#|#|#|#|#|X|_|_|_|#|#|#|#|_|_|_|X|#|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|#|_|_|#|#|#|#|#|#|\n" +
						"|#|K|C|H|N|#|_|_|#|#|B|A|L|L|#|#|_|_|#|C|O|N|S|#|\n" +
						"|#|#|#|#|#|#|_|_|#|#|R|O|O|M|#|#|_|_|@|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|_|_|@|#|#|#|#|#|#|@|_|_|_|#|#|#|#|X|\n" +
						"|X|#|#|#|@|#|_|_|#|#|#|#|#|#|#|#|_|_|_|_|_|_|_|B|\n" +
						"|_|_|_|_|_|_|_|_|#|@|#|#|#|#|@|#|_|_|_|_|_|_|_|X|\n" +
						"|X|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|#|#|#|#|#|#|\n" +
						"|#|#|#|#|#|_|_|_|_|_|_|_|_|_|_|_|_|_|@|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|B|L|R|D|#|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|#|#|#|#|#|\n" +
						"|#|D|I|N|I|N|G|@|_|_|X|X|X|X|X|_|_|_|#|#|#|#|@|#|\n" +
						"|#|#|R|O|O|M|#|#|_|_|X|X|X|X|X|_|_|_|_|_|_|_|_|X|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|#|@|#|#|X|\n" +
						"|#|#|#|#|#|#|@|#|_|_|X|X|X|X|X|_|_|#|#|#|#|#|#|#|\n" +
						"|X|_|_|_|_|_|_|_|_|_|X|X|X|X|X|_|_|@|#|L|B|R|Y|#|\n" +
						"|M|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|#|#|#|#|#|#|#|\n" +
						"|X|_|_|_|_|_|_|_|_|#|#|@|@|#|#|_|_|_|#|#|#|#|#|X|\n" +
						"|#|#|#|#|#|#|@|S|_|#|#|#|#|#|#|_|_|_|_|_|_|_|_|P|\n" +
						"|#|#|#|#|#|#|#|_|_|#|#|#|#|#|@|_|_|_|_|_|_|_|_|X|\n" +
						"|#|#|L|O|U|#|#|_|_|#|#|#|#|#|#|_|_|@|#|#|#|#|#|#|\n" +
						"|#|#|N|G|E|#|#|_|_|#|H|A|L|L|#|_|_|#|S|T|U|D|Y|#|\n" +
						"|#|#|#|#|#|#|#|_|_|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|\n" +
				"|#|#|#|#|#|#|X|_|X|#|#|#|#|#|#|X|_|X|#|#|#|#|#|#|\n");

		// asserts Miss Scarlett is still in turn, since she will have 1 move left
		assertEquals(g.getPlayers().get(0),g.getOrder().peek());

	}

	@Test
	void testValidDoorEntry() {
		Game g = new Game();
		g.setBoard(defaultBoard(g));	
		g.setOrder(g.getPlayers());

		// move scarlett up 6, left 1, down 1
		int movesLeft = 8;
		Player p = g.getOrder().peek();
		p.resetXYPositions(movesLeft+1);
		p.setXYPosition(p.getX(), p.getY());	
		movesLeft -= g.performMove(new Move(6, Direction.UP), movesLeft, p);
		movesLeft -= g.performMove(new Move(1, Direction.LEFT), movesLeft, p);
		movesLeft -= g.performMove(new Move(1, Direction.DOWN), movesLeft, p);

		// scarlett should not appear on the map
		assertEquals(g.getBoard().toString(),
				"|X|X|X|X|X|X|X|X|X|W|X|X|X|X|G|X|X|X|X|X|X|X|X|X|\n" +
				"|#|#|#|#|#|#|X|_|_|_|#|#|#|#|_|_|_|X|#|#|#|#|#|#|\n" +
				"|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|#|_|_|#|#|#|#|#|#|\n" +
				"|#|K|C|H|N|#|_|_|#|#|B|A|L|L|#|#|_|_|#|C|O|N|S|#|\n" +
				"|#|#|#|#|#|#|_|_|#|#|R|O|O|M|#|#|_|_|@|#|#|#|#|#|\n" +
				"|#|#|#|#|#|#|_|_|@|#|#|#|#|#|#|@|_|_|_|#|#|#|#|X|\n" +
				"|X|#|#|#|@|#|_|_|#|#|#|#|#|#|#|#|_|_|_|_|_|_|_|B|\n" +
				"|_|_|_|_|_|_|_|_|#|@|#|#|#|#|@|#|_|_|_|_|_|_|_|X|\n" +
				"|X|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|#|#|#|#|#|#|\n" +
				"|#|#|#|#|#|_|_|_|_|_|_|_|_|_|_|_|_|_|@|#|#|#|#|#|\n" +
				"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|B|L|R|D|#|\n" +
				"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|#|#|#|#|#|\n" +
				"|#|D|I|N|I|N|G|@|_|_|X|X|X|X|X|_|_|_|#|#|#|#|@|#|\n" +
				"|#|#|R|O|O|M|#|#|_|_|X|X|X|X|X|_|_|_|_|_|_|_|_|X|\n" +
				"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|#|@|#|#|X|\n" +
				"|#|#|#|#|#|#|@|#|_|_|X|X|X|X|X|_|_|#|#|#|#|#|#|#|\n" +
				"|X|_|_|_|_|_|_|_|_|_|X|X|X|X|X|_|_|@|#|L|B|R|Y|#|\n" +
				"|M|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|#|#|#|#|#|#|#|\n" +
				"|X|_|_|_|_|_|_|_|_|#|#|@|@|#|#|_|_|_|#|#|#|#|#|X|\n" +
				"|#|#|#|#|#|#|@|_|_|#|#|#|#|#|#|_|_|_|_|_|_|_|_|P|\n" +
				"|#|#|#|#|#|#|#|_|_|#|#|#|#|#|@|_|_|_|_|_|_|_|_|X|\n" +
				"|#|#|L|O|U|#|#|_|_|#|#|#|#|#|#|_|_|@|#|#|#|#|#|#|\n" +
				"|#|#|N|G|E|#|#|_|_|#|H|A|L|L|#|_|_|#|S|T|U|D|Y|#|\n" +
				"|#|#|#|#|#|#|#|_|_|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|\n" +
				"|#|#|#|#|#|#|X|_|X|#|#|#|#|#|#|X|_|X|#|#|#|#|#|#|\n");

		// asserts Miss Scarlett is in the Lounge
		assertEquals(g.getOrder().peek().getRoom(),g.getRooms().get(8));

	}

	@Test
	void testRoomExit() {
		Game g = new Game();
		g.setBoard(defaultBoard(g));	
		g.setOrder(g.getPlayers());

		// move scarlett up 6, left 1, down 1
		int movesLeft = 8;
		Player p = g.getOrder().peek();
		p.resetXYPositions(movesLeft+1);
		p.setXYPosition(p.getX(), p.getY());	
		movesLeft -= g.performMove(new Move(6, Direction.UP), movesLeft, p);
		movesLeft -= g.performMove(new Move(1, Direction.LEFT), movesLeft, p);
		movesLeft -= g.performMove(new Move(1, Direction.DOWN), movesLeft, p);

		g.exitRoom(p);	

		assertEquals(g.getBoard().toString(),
				"|X|X|X|X|X|X|X|X|X|W|X|X|X|X|G|X|X|X|X|X|X|X|X|X|\n" +
				"|#|#|#|#|#|#|X|_|_|_|#|#|#|#|_|_|_|X|#|#|#|#|#|#|\n" +
				"|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|#|_|_|#|#|#|#|#|#|\n" +
				"|#|K|C|H|N|#|_|_|#|#|B|A|L|L|#|#|_|_|#|C|O|N|S|#|\n" +
				"|#|#|#|#|#|#|_|_|#|#|R|O|O|M|#|#|_|_|@|#|#|#|#|#|\n" +
				"|#|#|#|#|#|#|_|_|@|#|#|#|#|#|#|@|_|_|_|#|#|#|#|X|\n" +
				"|X|#|#|#|@|#|_|_|#|#|#|#|#|#|#|#|_|_|_|_|_|_|_|B|\n" +
				"|_|_|_|_|_|_|_|_|#|@|#|#|#|#|@|#|_|_|_|_|_|_|_|X|\n" +
				"|X|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|#|#|#|#|#|#|\n" +
				"|#|#|#|#|#|_|_|_|_|_|_|_|_|_|_|_|_|_|@|#|#|#|#|#|\n" +
				"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|B|L|R|D|#|\n" +
				"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|#|#|#|#|#|\n" +
				"|#|D|I|N|I|N|G|@|_|_|X|X|X|X|X|_|_|_|#|#|#|#|@|#|\n" +
				"|#|#|R|O|O|M|#|#|_|_|X|X|X|X|X|_|_|_|_|_|_|_|_|X|\n" +
				"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|#|@|#|#|X|\n" +
				"|#|#|#|#|#|#|@|#|_|_|X|X|X|X|X|_|_|#|#|#|#|#|#|#|\n" +
				"|X|_|_|_|_|_|_|_|_|_|X|X|X|X|X|_|_|@|#|L|B|R|Y|#|\n" +
				"|M|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|#|#|#|#|#|#|#|\n" +
				"|X|_|_|_|_|_|_|_|_|#|#|@|@|#|#|_|_|_|#|#|#|#|#|X|\n" +
				"|#|#|#|#|#|#|S|_|_|#|#|#|#|#|#|_|_|_|_|_|_|_|_|P|\n" +
				"|#|#|#|#|#|#|#|_|_|#|#|#|#|#|@|_|_|_|_|_|_|_|_|X|\n" +
				"|#|#|L|O|U|#|#|_|_|#|#|#|#|#|#|_|_|@|#|#|#|#|#|#|\n" +
				"|#|#|N|G|E|#|#|_|_|#|H|A|L|L|#|_|_|#|S|T|U|D|Y|#|\n" +
				"|#|#|#|#|#|#|#|_|_|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|\n" +
				"|#|#|#|#|#|#|X|_|X|#|#|#|#|#|#|X|_|X|#|#|#|#|#|#|\n");


	}
	
	@Test
	void testRoomCantExit() {
		Game g = new Game();
		g.setBoard(defaultBoard(g));	
		g.setOrder(g.getPlayers());

		// move scarlett up 6, left 1, down 1
		int movesLeft = 8;
		Player p = g.getOrder().peek();
		p.resetXYPositions(movesLeft+1);
		p.setXYPosition(p.getX(), p.getY());	
		movesLeft -= g.performMove(new Move(6, Direction.UP), movesLeft, p);
		movesLeft -= g.performMove(new Move(1, Direction.LEFT), movesLeft, p);
		movesLeft -= g.performMove(new Move(1, Direction.DOWN), movesLeft, p);
		g.nextPlayer();
		
		// moves mustard in front of lounge door
		movesLeft = 7;
		p = g.getOrder().peek();
		p.resetXYPositions(movesLeft+1);
		p.setXYPosition(p.getX(), p.getY());	
		movesLeft -= g.performMove(new Move(6, Direction.RIGHT), movesLeft, p);
		movesLeft -= g.performMove(new Move(1, Direction.DOWN), movesLeft, p);

		for (int i = 0; i < 5; i++)
			g.nextPlayer();
		
		// scarlett shouldnt be able to leave
		p = g.getOrder().peek();
		assertEquals(g.exitRoom(p), false);	
		assertEquals(g.getBoard().toString(),
				"|X|X|X|X|X|X|X|X|X|W|X|X|X|X|G|X|X|X|X|X|X|X|X|X|\n" +
				"|#|#|#|#|#|#|X|_|_|_|#|#|#|#|_|_|_|X|#|#|#|#|#|#|\n" +
				"|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|#|_|_|#|#|#|#|#|#|\n" +
				"|#|K|C|H|N|#|_|_|#|#|B|A|L|L|#|#|_|_|#|C|O|N|S|#|\n" +
				"|#|#|#|#|#|#|_|_|#|#|R|O|O|M|#|#|_|_|@|#|#|#|#|#|\n" +
				"|#|#|#|#|#|#|_|_|@|#|#|#|#|#|#|@|_|_|_|#|#|#|#|X|\n" +
				"|X|#|#|#|@|#|_|_|#|#|#|#|#|#|#|#|_|_|_|_|_|_|_|B|\n" +
				"|_|_|_|_|_|_|_|_|#|@|#|#|#|#|@|#|_|_|_|_|_|_|_|X|\n" +
				"|X|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|#|#|#|#|#|#|\n" +
				"|#|#|#|#|#|_|_|_|_|_|_|_|_|_|_|_|_|_|@|#|#|#|#|#|\n" +
				"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|B|L|R|D|#|\n" +
				"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|#|#|#|#|#|\n" +
				"|#|D|I|N|I|N|G|@|_|_|X|X|X|X|X|_|_|_|#|#|#|#|@|#|\n" +
				"|#|#|R|O|O|M|#|#|_|_|X|X|X|X|X|_|_|_|_|_|_|_|_|X|\n" +
				"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|#|@|#|#|X|\n" +
				"|#|#|#|#|#|#|@|#|_|_|X|X|X|X|X|_|_|#|#|#|#|#|#|#|\n" +
				"|X|_|_|_|_|_|_|_|_|_|X|X|X|X|X|_|_|@|#|L|B|R|Y|#|\n" +
				"|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|#|#|#|#|#|#|#|\n" +
				"|X|_|_|_|_|_|M|_|_|#|#|@|@|#|#|_|_|_|#|#|#|#|#|X|\n" +
				"|#|#|#|#|#|#|@|_|_|#|#|#|#|#|#|_|_|_|_|_|_|_|_|P|\n" +
				"|#|#|#|#|#|#|#|_|_|#|#|#|#|#|@|_|_|_|_|_|_|_|_|X|\n" +
				"|#|#|L|O|U|#|#|_|_|#|#|#|#|#|#|_|_|@|#|#|#|#|#|#|\n" +
				"|#|#|N|G|E|#|#|_|_|#|H|A|L|L|#|_|_|#|S|T|U|D|Y|#|\n" +
				"|#|#|#|#|#|#|#|_|_|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|\n" +
				"|#|#|#|#|#|#|X|_|X|#|#|#|#|#|#|X|_|X|#|#|#|#|#|#|\n");


	}


	@Test
	void testPlayerTrapped() {
		Game g = new Game();
		g.setBoard(defaultBoard(g));	
		g.setOrder(g.getPlayers());

		// move scarlett up 2, right 1, down 1
		int movesLeft = 5;
		Player p = g.getOrder().peek();
		p.resetXYPositions(movesLeft+1);
		p.setXYPosition(p.getX(), p.getY());	
		movesLeft -= g.performMove(new Move(2, Direction.UP), movesLeft, p);
		movesLeft -= g.performMove(new Move(1, Direction.RIGHT), movesLeft, p);
		movesLeft -= g.performMove(new Move(1, Direction.DOWN), movesLeft, p);

		assertEquals(g.getBoard().toString(),
				"|X|X|X|X|X|X|X|X|X|W|X|X|X|X|G|X|X|X|X|X|X|X|X|X|\n" +
						"|#|#|#|#|#|#|X|_|_|_|#|#|#|#|_|_|_|X|#|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|#|_|_|#|#|#|#|#|#|\n" +
						"|#|K|C|H|N|#|_|_|#|#|B|A|L|L|#|#|_|_|#|C|O|N|S|#|\n" +
						"|#|#|#|#|#|#|_|_|#|#|R|O|O|M|#|#|_|_|@|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|_|_|@|#|#|#|#|#|#|@|_|_|_|#|#|#|#|X|\n" +
						"|X|#|#|#|@|#|_|_|#|#|#|#|#|#|#|#|_|_|_|_|_|_|_|B|\n" +
						"|_|_|_|_|_|_|_|_|#|@|#|#|#|#|@|#|_|_|_|_|_|_|_|X|\n" +
						"|X|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|#|#|#|#|#|#|\n" +
						"|#|#|#|#|#|_|_|_|_|_|_|_|_|_|_|_|_|_|@|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|B|L|R|D|#|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|#|#|#|#|#|\n" +
						"|#|D|I|N|I|N|G|@|_|_|X|X|X|X|X|_|_|_|#|#|#|#|@|#|\n" +
						"|#|#|R|O|O|M|#|#|_|_|X|X|X|X|X|_|_|_|_|_|_|_|_|X|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|#|@|#|#|X|\n" +
						"|#|#|#|#|#|#|@|#|_|_|X|X|X|X|X|_|_|#|#|#|#|#|#|#|\n" +
						"|X|_|_|_|_|_|_|_|_|_|X|X|X|X|X|_|_|@|#|L|B|R|Y|#|\n" +
						"|M|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|#|#|#|#|#|#|#|\n" +
						"|X|_|_|_|_|_|_|_|_|#|#|@|@|#|#|_|_|_|#|#|#|#|#|X|\n" +
						"|#|#|#|#|#|#|@|_|_|#|#|#|#|#|#|_|_|_|_|_|_|_|_|P|\n" +
						"|#|#|#|#|#|#|#|_|_|#|#|#|#|#|@|_|_|_|_|_|_|_|_|X|\n" +
						"|#|#|L|O|U|#|#|_|_|#|#|#|#|#|#|_|_|@|#|#|#|#|#|#|\n" +
						"|#|#|N|G|E|#|#|_|_|#|H|A|L|L|#|_|_|#|S|T|U|D|Y|#|\n" +
						"|#|#|#|#|#|#|#|_|S|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|\n" +
				"|#|#|#|#|#|#|X|_|X|#|#|#|#|#|#|X|_|X|#|#|#|#|#|#|\n");

		// asserts Miss Scarlett is trapped
		assertEquals(g.playerTrapped(p, movesLeft), true);

	}

	@Test
	void testPlayersArentTrapped() {
		Game g = new Game();
		g.setBoard(defaultBoard(g));	
		g.setOrder(g.getPlayers());

		for (int i = 0; i < 6; i++) {
			int movesLeft = 6;
			Player p = g.getOrder().peek();
			p.resetXYPositions(movesLeft+1);
			p.setXYPosition(p.getX(), p.getY());	

			assertFalse(g.playerTrapped(p, movesLeft));
			g.nextPlayer();
		}

	}

	@Test
	void testInvalidEnterSameRoom() {
		Game g = new Game();
		g.setBoard(defaultBoard(g));	
		g.setOrder(g.getPlayers());

		// move scarlett up 6, left 1, up 3
		int movesLeft = 10;
		Player p = g.getOrder().peek();
		p.resetXYPositions(movesLeft+1);
		p.setXYPosition(p.getX(), p.getY());	
		movesLeft -= g.performMove(new Move(6, Direction.UP), movesLeft, p);
		movesLeft -= g.performMove(new Move(1, Direction.LEFT), movesLeft, p);
		movesLeft -= g.performMove(new Move(3, Direction.UP), movesLeft, p);

		// skips back to miss scarlett
		for (int i = 0; i < 6; i++)
			g.nextPlayer();

		// moves miss scarlett out of the room via door 2
		Room roomIn = p.getRoom();
		DoorTile door = roomIn.getDoorTile(1);
		Direction inverse = door.getEntryDir().inverse();
		p.setX(door.getCol());
		p.setY(door.getRow());
		p.setRoom(null);
		p.setRoomLastIn(roomIn);
		g.performMove(new Move(1,inverse),1,p);

		// doesn't move scarlett, as she cannot move left 2
		movesLeft = 2;
		p = g.getOrder().peek();
		p.resetXYPositions(movesLeft+1);
		p.setXYPosition(p.getX(), p.getY());	
		movesLeft -= g.performMove(new Move(2, Direction.LEFT), movesLeft, p);

		assertEquals(g.getBoard().toString(),
				"|X|X|X|X|X|X|X|X|X|W|X|X|X|X|G|X|X|X|X|X|X|X|X|X|\n" +
						"|#|#|#|#|#|#|X|_|_|_|#|#|#|#|_|_|_|X|#|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|#|_|_|#|#|#|#|#|#|\n" +
						"|#|K|C|H|N|#|_|_|#|#|B|A|L|L|#|#|_|_|#|C|O|N|S|#|\n" +
						"|#|#|#|#|#|#|_|_|#|#|R|O|O|M|#|#|_|_|@|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|_|_|@|#|#|#|#|#|#|@|_|_|_|#|#|#|#|X|\n" +
						"|X|#|#|#|@|#|_|_|#|#|#|#|#|#|#|#|_|_|_|_|_|_|_|B|\n" +
						"|_|_|_|_|_|_|_|_|#|@|#|#|#|#|@|#|_|_|_|_|_|_|_|X|\n" +
						"|X|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|#|#|#|#|#|#|\n" +
						"|#|#|#|#|#|_|_|_|_|_|_|_|_|_|_|_|_|_|@|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|B|L|R|D|#|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|#|#|#|#|#|\n" +
						"|#|D|I|N|I|N|G|@|S|_|X|X|X|X|X|_|_|_|#|#|#|#|@|#|\n" +
						"|#|#|R|O|O|M|#|#|_|_|X|X|X|X|X|_|_|_|_|_|_|_|_|X|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|#|@|#|#|X|\n" +
						"|#|#|#|#|#|#|@|#|_|_|X|X|X|X|X|_|_|#|#|#|#|#|#|#|\n" +
						"|X|_|_|_|_|_|_|_|_|_|X|X|X|X|X|_|_|@|#|L|B|R|Y|#|\n" +
						"|M|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|#|#|#|#|#|#|#|\n" +
						"|X|_|_|_|_|_|_|_|_|#|#|@|@|#|#|_|_|_|#|#|#|#|#|X|\n" +
						"|#|#|#|#|#|#|@|_|_|#|#|#|#|#|#|_|_|_|_|_|_|_|_|P|\n" +
						"|#|#|#|#|#|#|#|_|_|#|#|#|#|#|@|_|_|_|_|_|_|_|_|X|\n" +
						"|#|#|L|O|U|#|#|_|_|#|#|#|#|#|#|_|_|@|#|#|#|#|#|#|\n" +
						"|#|#|N|G|E|#|#|_|_|#|H|A|L|L|#|_|_|#|S|T|U|D|Y|#|\n" +
						"|#|#|#|#|#|#|#|_|_|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|\n" +
				"|#|#|#|#|#|#|X|_|X|#|#|#|#|#|#|X|_|X|#|#|#|#|#|#|\n");

		assertEquals(movesLeft,2);

	}

	@Test
	void testDie() {

		Die die = new Die();
		assertTrue(die.getRoll() >= 1 && die.getRoll() <= 6);
		Game g = new Game();
		g.setBoard(defaultBoard(g));	
		g.setOrder(g.getPlayers());
		assertTrue(g.newTurn() >= 2 && g.newTurn() <= 12);

	}


	@Test
	void testToString() {

		Game g = new Game();
		g.setBoard(defaultBoard(g));	
		g.setOrder(g.getPlayers());

		// tests the entities in the solution
		assertEquals(g.getSolution().getMurderer().toString(), "MISS SCARLETT");
		assertEquals(g.getSolution().getMurderer().getInitial(), "S");

		assertEquals(g.getSolution().getWeapon().toString(), "CANDLESTICK");
		assertEquals(g.getSolution().getWeapon().getInitial(), "CS");

		assertEquals(g.getSolution().getRoom().toString(), "DINING ROOM");
		assertEquals(g.getSolution().getRoom().getInitial(), "DR");

		// tests every other display, using card string + player switch cases
		assertEquals(g.getDeck().get(0).toString(), "Room: BALLROOM");
		assertEquals(g.getDeck().get(0).getType().getInitial(), "BA");

		assertEquals(g.getDeck().get(1).toString(), "Room: BILLIARD ROOM");
		assertEquals(g.getDeck().get(1).getType().getInitial(), "BI");

		assertEquals(g.getDeck().get(2).toString(), "Room: CONSERVATORY");
		assertEquals(g.getDeck().get(2).getType().getInitial(), "CO");

		assertEquals(g.getDeck().get(3).toString(), "Room: KITCHEN");
		assertEquals(g.getDeck().get(3).getType().getInitial(), "KI");

		assertEquals(g.getDeck().get(4).toString(), "Room: LIBRARY");
		assertEquals(g.getDeck().get(4).getType().getInitial(), "LI");

		assertEquals(g.getDeck().get(5).toString(), "Room: HALL");
		assertEquals(g.getDeck().get(5).getType().getInitial(), "HA");

		assertEquals(g.getDeck().get(6).toString(), "Room: STUDY");
		assertEquals(g.getDeck().get(6).getType().getInitial(), "ST");

		assertEquals(g.getDeck().get(7).toString(), "Room: LOUNGE");
		assertEquals(g.getDeck().get(7).getType().getInitial(), "LO");

		assertEquals(g.getDeck().get(8).toString(), "Player: COLONEL MUSTARD");
		assertEquals(g.getDeck().get(8).getType().getInitial(), "M");

		assertEquals(g.getDeck().get(9).toString(), "Player: MS WHITE");
		assertEquals(g.getDeck().get(9).getType().getInitial(), "W");

		assertEquals(g.getDeck().get(10).toString(), "Player: MR GREEN");
		assertEquals(g.getDeck().get(10).getType().getInitial(), "G");

		assertEquals(g.getDeck().get(11).toString(), "Player: MRS PEACOCK");
		assertEquals(g.getDeck().get(11).getType().getInitial(), "B");

		assertEquals(g.getDeck().get(12).toString(), "Player: PROFESSOR PLUM");
		assertEquals(g.getDeck().get(12).getType().getInitial(), "P");

		assertEquals(g.getDeck().get(13).toString(), "Weapon: DAGGER");
		assertEquals(g.getDeck().get(13).getType().getInitial(), "DA");

		assertEquals(g.getDeck().get(14).toString(), "Weapon: LEADPIPE");
		assertEquals(g.getDeck().get(14).getType().getInitial(), "LP");

		assertEquals(g.getDeck().get(15).toString(), "Weapon: REVOLVER");
		assertEquals(g.getDeck().get(15).getType().getInitial(), "RE");

		assertEquals(g.getDeck().get(16).toString(), "Weapon: ROPE");
		assertEquals(g.getDeck().get(16).getType().getInitial(), "RO");

		assertEquals(g.getDeck().get(17).toString(), "Weapon: SPANNER");
		assertEquals(g.getDeck().get(17).getType().getInitial(), "SP");

		// test tiles
		assertEquals(new Tile(Tile.TileType.ROOM).toString(), "ROOM");
		assertEquals(new Tile(Tile.TileType.NULL).toString(), "NULL");
		assertEquals(new Tile(Tile.TileType.HALLWAY).toString(), "HALLWAY");
		assertEquals(new DoorTile(g.getRooms().get(0),Direction.DOWN, 19, 6).toString(), "Door to DINING ROOM");

		// test move
		assertEquals(new Move(1,Direction.DOWN).toString(), "1 step in direction DOWN");
		assertEquals(new Move(2,Direction.UP).toString(), "2 steps in direction UP");

		// test numerics of players
		for (Integer i = 1; i <= 6; i++) {
			assertEquals(g.getOrder().peek().getNumeric(),i.toString());
			g.nextPlayer();
		}

	}

	@Test
	void testInverses() {
		assertEquals(Direction.UP.inverse(), Direction.DOWN);
		assertEquals(Direction.RIGHT.inverse(), Direction.LEFT);
		assertEquals(Direction.DOWN.inverse(), Direction.UP);
		assertEquals(Direction.LEFT.inverse(), Direction.RIGHT);
	}

	@Test
	void testResetSolution() {
		Game g = new Game();
		Board b = basicBoard(g);

		Game g2 = new Game();
		Board b2 = basicBoard(g2);

		g.resetSolution();
		g2.resetSolution();
		assertFalse((g.getSolution().toString().equals(g2.getSolution().toString())));
	}

	@Test
	void testDoorCounts() {
		Game g = new Game();
		g.setBoard(defaultBoard(g));

		// DINING ROOM
		assertEquals(g.getRooms().get(0).numberOfDoors(),2);
		// BALLROOM
		assertEquals(g.getRooms().get(1).numberOfDoors(),4);
		// BILLIARD ROOM
		assertEquals(g.getRooms().get(2).numberOfDoors(),2);
		// CONSERVATORY
		assertEquals(g.getRooms().get(3).numberOfDoors(),1);
		// KITCHEN
		assertEquals(g.getRooms().get(4).numberOfDoors(),1);
		// LIBRARY
		assertEquals(g.getRooms().get(5).numberOfDoors(),2);
		// HALL
		assertEquals(g.getRooms().get(6).numberOfDoors(),3);
		// STUDY
		assertEquals(g.getRooms().get(7).numberOfDoors(),1);
		// LOUNGE
		assertEquals(g.getRooms().get(8).numberOfDoors(),1);

	}

	@Test
	void testNonNullLists() {
		Game g = new Game();
		g.setBoard(defaultBoard(g));	

		Room r = g.getRooms().get(0);
		assertTrue(r.getPlayers() != null);
		assertTrue(r.getWeapons() != null);

	}

	@Test 
	void testSuggestParser() {

		Game g = new Game();
		g.setBoard(defaultBoard(g));	
		g.setOrder(g.getPlayers());

		String command = "suggest ms white dagger lounge";
		Tuple t = g.parseSuggestion(command, false);
		command = "accuse ms white dagger lounge";
		Tuple t2 = g.parseSuggestion(command, true);

		Tuple answer = new Tuple(g.getPlayers().get(2), g.getWeapons().get(1), g.getRooms().get(8));
		assertEquals(t.toString(), answer.toString());
		assertEquals(t2.toString(), answer.toString());
	}

	@Test 
	void testSuggestParser2() {

		Game g = new Game();
		g.setBoard(defaultBoard(g));	
		g.setOrder(g.getPlayers());

		String command = "suggest mswhite dagger lounge";
		Tuple t = g.parseSuggestion(command, false);
		command = "accuse mswhite dagger lounge";
		Tuple t2 = g.parseSuggestion(command, true);

		assertEquals(t, null);
		assertEquals(t2, null);
	}

	@Test 
	void testSuggestParser3() {

		Game g = new Game();
		g.setBoard(defaultBoard(g));	
		g.setOrder(g.getPlayers());

		String command = "suggest W DA LO";
		Tuple t = g.parseSuggestion(command, false);
		command = "accuse W DA LO";
		Tuple t2 = g.parseSuggestion(command, true);
		Tuple answer = new Tuple(g.getPlayers().get(2), g.getWeapons().get(1), g.getRooms().get(8));
		assertEquals(t.toString(), answer.toString());
		assertEquals(t2.toString(), answer.toString());
	}

	@Test
	void testNumberParser() {
		Game g = new Game();
		assertTrue(g.parseNumber("5"));
		assertFalse(g.parseNumber("five"));
	}

	@Test
	void testCodesParser() {
		Game g = new Game();
		Board b = defaultBoard(g);
		String command = "codes weapon";
		g.parseCodes(command);
		//assertEquals(g.parseCodes(command), true);
		command = "codes player";
		assertEquals(g.parseCodes(command), true);
		command = "codes room";
		assertEquals(g.parseCodes(command), true);
		command = "codes asdfghjkl";
		assertEquals(g.parseCodes(command), false);
	}

	@Test
	void testMovesParser() {
		Game g = new Game();
		Board b = defaultBoard(g);

		String command = "up 3";
		Move ourMove = g.parseMove(command);
		Move actualMove = new Move(3,Direction.UP);
		assertEquals(ourMove.toString(), actualMove.toString());

		command = "left 3";
		ourMove = g.parseMove(command);
		actualMove = new Move(3,Direction.LEFT);
		assertEquals(ourMove.toString(), actualMove.toString());

		command = "right 3";
		ourMove = g.parseMove(command);
		actualMove = new Move(3,Direction.RIGHT);
		assertEquals(ourMove.toString(), actualMove.toString());

		command = "down 3";
		ourMove = g.parseMove(command);
		actualMove = new Move(3,Direction.DOWN);
		assertEquals(ourMove.toString(), actualMove.toString());

		command = "upre 3";
		ourMove = g.parseMove(command);
		assertEquals(ourMove, null);
	}

	@Test
	void testDefaultPlayerNumeric() {
		Game g = new Game();
		g.resetPlayers();

		for (Player p : g.getPlayers()) {
			assertEquals(p.getNumeric(), p.getInitial());
		}

	}

	@Test
	void testPrintPlayerStatus() {
		Game g = new Game();
		g.setBoard(defaultBoard(g));	
		g.setOrder(g.getPlayers());

		int movesLeft = 7;
		Player p = g.getOrder().peek();
		p.resetXYPositions(movesLeft+1);
		p.setXYPosition(p.getX(), p.getY());
		g.printPlayerStatus(p, movesLeft, movesLeft);
		assertEquals(g.getValidDirections(p, p.getX(), p.getY(), movesLeft), "UP, ");

		movesLeft -= g.performMove(new Move(7, Direction.UP), movesLeft, p);

		for (int i = 0; i < 6; i++)
			g.nextPlayer();

		movesLeft = 11;
		p.resetXYPositions(movesLeft+1);
		p.setXYPosition(p.getX(), p.getY());
		g.printPlayerStatus(p, movesLeft, movesLeft);
		assertEquals(g.getValidDirections(p, p.getX(), p.getY(), movesLeft), "UP, RIGHT, DOWN, LEFT, ");

	}

	@Test
	void testLabels() {
		Game g = new Game();
		g.setBoard(defaultBoard(g));	
		g.setOrder(g.getPlayers());

		// toggles the labels on the board
		g.getBoard().toggleLabels();
		g.getBoard().togglePlayers();

		assertEquals(g.getBoard().toString(),
				"|X|X|X|X|X|X|X|X|X|3|X|X|X|X|4|X|X|X|X|X|X|X|X|X|\n" +
						"|#|#|#|#|#|#|X|_|_|_|#|#|#|#|_|_|_|X|#|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|#|_|_|#|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|#|_|_|#|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|#|_|_|@|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|_|_|@|#|#|#|#|#|#|@|_|_|_|#|#|#|#|X|\n" +
						"|X|#|#|#|@|#|_|_|#|#|#|#|#|#|#|#|_|_|_|_|_|_|_|5|\n" +
						"|_|_|_|_|_|_|_|_|#|@|#|#|#|#|@|#|_|_|_|_|_|_|_|X|\n" +
						"|X|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|#|#|#|#|#|#|\n" +
						"|#|#|#|#|#|_|_|_|_|_|_|_|_|_|_|_|_|_|@|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|#|@|_|_|X|X|X|X|X|_|_|_|#|#|#|#|@|#|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|_|_|_|_|_|X|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|#|@|#|#|X|\n" +
						"|#|#|#|#|#|#|@|#|_|_|X|X|X|X|X|_|_|#|#|#|#|#|#|#|\n" +
						"|X|_|_|_|_|_|_|_|_|_|X|X|X|X|X|_|_|@|#|#|#|#|#|#|\n" +
						"|2|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|#|#|#|#|#|#|#|\n" +
						"|X|_|_|_|_|_|_|_|_|#|#|@|@|#|#|_|_|_|#|#|#|#|#|X|\n" +
						"|#|#|#|#|#|#|@|_|_|#|#|#|#|#|#|_|_|_|_|_|_|_|_|6|\n" +
						"|#|#|#|#|#|#|#|_|_|#|#|#|#|#|@|_|_|_|_|_|_|_|_|X|\n" +
						"|#|#|#|#|#|#|#|_|_|#|#|#|#|#|#|_|_|@|#|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|#|_|_|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|#|_|_|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|\n" +
				"|#|#|#|#|#|#|X|1|X|#|#|#|#|#|#|X|_|X|#|#|#|#|#|#|\n");

		// toggles them back
		g.getBoard().toggleLabels();
		g.getBoard().togglePlayers();

		assertEquals(g.getBoard().toString(),
				"|X|X|X|X|X|X|X|X|X|W|X|X|X|X|G|X|X|X|X|X|X|X|X|X|\n" +
						"|#|#|#|#|#|#|X|_|_|_|#|#|#|#|_|_|_|X|#|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|#|_|_|#|#|#|#|#|#|\n" +
						"|#|K|C|H|N|#|_|_|#|#|B|A|L|L|#|#|_|_|#|C|O|N|S|#|\n" +
						"|#|#|#|#|#|#|_|_|#|#|R|O|O|M|#|#|_|_|@|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|_|_|@|#|#|#|#|#|#|@|_|_|_|#|#|#|#|X|\n" +
						"|X|#|#|#|@|#|_|_|#|#|#|#|#|#|#|#|_|_|_|_|_|_|_|B|\n" +
						"|_|_|_|_|_|_|_|_|#|@|#|#|#|#|@|#|_|_|_|_|_|_|_|X|\n" +
						"|X|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|#|#|#|#|#|#|\n" +
						"|#|#|#|#|#|_|_|_|_|_|_|_|_|_|_|_|_|_|@|#|#|#|#|#|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|B|L|R|D|#|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|#|#|#|#|#|\n" +
						"|#|D|I|N|I|N|G|@|_|_|X|X|X|X|X|_|_|_|#|#|#|#|@|#|\n" +
						"|#|#|R|O|O|M|#|#|_|_|X|X|X|X|X|_|_|_|_|_|_|_|_|X|\n" +
						"|#|#|#|#|#|#|#|#|_|_|X|X|X|X|X|_|_|_|#|#|@|#|#|X|\n" +
						"|#|#|#|#|#|#|@|#|_|_|X|X|X|X|X|_|_|#|#|#|#|#|#|#|\n" +
						"|X|_|_|_|_|_|_|_|_|_|X|X|X|X|X|_|_|@|#|L|B|R|Y|#|\n" +
						"|M|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|#|#|#|#|#|#|#|\n" +
						"|X|_|_|_|_|_|_|_|_|#|#|@|@|#|#|_|_|_|#|#|#|#|#|X|\n" +
						"|#|#|#|#|#|#|@|_|_|#|#|#|#|#|#|_|_|_|_|_|_|_|_|P|\n" +
						"|#|#|#|#|#|#|#|_|_|#|#|#|#|#|@|_|_|_|_|_|_|_|_|X|\n" +
						"|#|#|L|O|U|#|#|_|_|#|#|#|#|#|#|_|_|@|#|#|#|#|#|#|\n" +
						"|#|#|N|G|E|#|#|_|_|#|H|A|L|L|#|_|_|#|S|T|U|D|Y|#|\n" +
						"|#|#|#|#|#|#|#|_|_|#|#|#|#|#|#|_|_|#|#|#|#|#|#|#|\n" +
				"|#|#|#|#|#|#|X|S|X|#|#|#|#|#|#|X|_|X|#|#|#|#|#|#|\n");

	}


	//-----------------------------//
	// DEFAULT METHODS FOR TESTING //
	//-----------------------------//


	/**
	 * Creates a default board with a default solution
	 * @param g
	 * 		the default game
	 * @return
	 * 		the default board
	 */
	public static Board defaultBoard(Game g) {
		// Resets all entities at the start of the game, creating new objects for these
		g.resetRooms();
		g.resetWeapons();
		g.resetPlayers();
		g.setActivePlayers(g.numberOfPlayers());
		// Resets the reset of the objects dependent on these prior objects being created
		Board board = new Board(g.getRooms(), g);
		g.resetCards();
		g.setSolution(defaultSolution(g.getDeck()));
		return board;
	}

	/**
	 * Creates a default board with a changeable solution
	 * @param g
	 * 		the default game
	 * @return
	 * 		the default board
	 */
	public static Board basicBoard(Game g) {
		// Resets all entities at the start of the game, creating new objects for these
		g.resetRooms();
		g.resetWeapons();
		g.resetPlayers();
		g.setActivePlayers(g.numberOfPlayers());
		// Resets the reset of the objects dependent on these prior objects being created
		Board board = new Board(g.getRooms(), g);
		g.resetCards();

		return board;
	}


	/**
	 * Creates default hands so we can test 
	 * 
	 * MISS SCARLETT [Room: BALLROOM, Room: STUDY, Player: PROFESSOR PLUM]
	 * COLONEL MUSTARD [Room: BILLIARD ROOM, Room: LOUNGE, Weapon: DAGGER]
	 * MS WHITE [Room: CONSERVATORY, Player: COLONEL MUSTARD, Weapon: LEADPIPE]
	 * MR GREEN [Room: KITCHEN, Player: MS WHITE, Weapon: REVOLVER]
	 * MRS PEACOCK [Room: LIBRARY, Player: MR GREEN, Weapon: ROPE]
	 * PROFESSOR PLUM [Room: HALL, Player: MRS PEACOCK, Weapon: SPANNER]
	 */
	public static void defaultHand(Game g) {

		Queue<Player> tempOrder = g.getOrder();

		while(g.getDeck().size() > 0) {
			Player curr = tempOrder.poll();
			curr.addToHand(g.getDeck().remove(0));
			tempOrder.offer(curr);
		}

		for(Player p : tempOrder) {
			System.out.println(p.toString() + " " + p.getHand());
		}
	}

	/**
	 * Generates a solution with Dining Room, Scarlett, Candlestick (as these are the first in the enum)
	 * @param deck
	 * 		the default deck
	 * @return
	 * 		the default solution
	 */
	public static Tuple defaultSolution(List<Card> deck) {
		Card weapon = deck.get(15);
		deck.remove(15);

		Card player = deck.get(9);
		deck.remove(9);

		Card room = deck.get(0);
		deck.remove(0);

		Tuple solution = new Tuple((Player) player.getType(), (Weapon) weapon.getType(), (Room) room.getType());
		return solution;
	}
}