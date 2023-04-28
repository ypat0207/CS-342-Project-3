import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;

class LogicTest {
	static Logic game;
	static ArrayList<Card> straightFlush, three, straight, flush, pair;

	@BeforeAll
	static void setup() {
		game = new Logic();
		straightFlush = new ArrayList();
		three = new ArrayList();
		straight = new ArrayList();
		flush = new ArrayList();
		pair = new ArrayList();
		
		straightFlush.add(new Card("club", "10"));
		straightFlush.add(new Card("club", "9"));
		straightFlush.add(new Card("club", "8"));
		
		three.add(new Card("club","Q"));
		three.add(new Card("heart","Q"));
		three.add(new Card("spade","Q"));
		
		straight.add(new Card("diamond","8"));
		straight.add(new Card("club","7"));
		straight.add(new Card("diamond","6"));
		
		flush.add(new Card("diamond","K"));
		flush.add(new Card("diamond","9"));
		flush.add(new Card("diamond","7"));
		
		pair.add(new Card("heart","K"));
		pair.add(new Card("club","K"));
		pair.add(new Card("diamond","9"));
	}
	
	@Test
	void testEvalHand() {
		assertEquals(game.evalHand(straightFlush), "Straight Flush");
		assertEquals(game.evalHand(three), "Three of a Kind");
		assertEquals(game.evalHand(straight), "Straight");
		assertEquals(game.evalHand(flush), "Flush");
		assertEquals(game.evalHand(pair), "Pair");
	}

}
