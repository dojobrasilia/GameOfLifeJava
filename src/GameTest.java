import static org.junit.Assert.*;

import org.junit.Test;


public class GameTest {
	
	@Test public void allCellsMustBeDeadBeforeStart(){
		Game game = new Game();
		for(int i = 0; i < 10; i++)
			for(int j = 0 ; j< 10; j++)
				assertEquals("("+i+","+j+") should be Blank",' ',game.check(i,j));
	}
	
	@Test public void aPopulatedCellMustBeAlive(){
		Game game = new Game();
		game.placeHerbivore(0,0);
		game.placeHerbivore(1,2);
		assertEquals("(0,0)",'H',game.check(0,0));
		assertEquals("(1,2)",'H',game.check(1,2));
	}
	
	@Test public void aCellCanBePopulatedByACarnivore(){
		Game game = new Game();
		game.placeCarnivore(0,0);
		game.placeCarnivore(1,2);
		assertEquals("(0,0)",'C',game.check(0,0));
		assertEquals("(1,2)",'C',game.check(0,0));
	}
	
	@Test public void aLonelyCellMustDie(){
		Game game = new Game();
		game.placeHerbivore(0,0);
		game.next();
		assertEquals(' ',game.check(0,0));
	}
	
	@Test public void twoNeighboursCellsMustDie(){
		Game game = new Game();
		game.placeHerbivore(1,2);
		game.placeHerbivore(1,3);
		game.next();
		assertEquals(' ',game.check(1,2));
		assertEquals(' ',game.check(1,3));
	}
	
	@Test public void threeCellsInARowMakesTheNeighourRiseFromTheDead(){
		Game game = new Game();
		game.placeHerbivore(1,2);
		game.placeHerbivore(1,3);
		game.placeHerbivore(1,4);
		game.next();
		assertEquals('H',game.check(2,3));
		assertEquals('H',game.check(0,3));
	}
	
	@Test public void aCellWithTwoNeigboursLives(){
		Game game = new Game();
		game.placeHerbivore(0,0);
		game.placeHerbivore(1,1);
		game.placeHerbivore(0,2);
		game.next();
		assertEquals('H',game.check(1,1));
	}
	
	@Test public void aCellWithThreeNeigboursLives(){
		Game game = new Game();
		game.placeHerbivore(0,0);
		game.placeHerbivore(0,1);
		game.placeHerbivore(1,1);
		game.placeHerbivore(0,2);
		game.next();
		assertEquals('H',game.check(1,1));
	}
	
	@Test public void aCellWithFourNeigboursMustDie(){
		Game game = new Game();
		game.placeHerbivore(0,0);
		game.placeHerbivore(1,0);
		game.placeHerbivore(2,0);
		game.placeHerbivore(0,1);
		game.placeHerbivore(1,1);
		game.next();
		assertEquals(' ',game.check(1,0));
	}
	
	@Test public void aDeadCellWithTwoNeigboursStaysDead(){
		Game game = new Game();
		game.placeHerbivore(0,0);
		game.placeHerbivore(1,1);
		game.next();
		assertEquals(' ',game.check(1,0));
	}
	
	@Test public void countNeighboursForASingleLivingCell(){
		Game game = new Game();
		game.placeHerbivore(2,2);
		assertEquals(1,game.count(1,1));
		assertEquals(1,game.count(2,1));
		assertEquals(1,game.count(3,1));
		assertEquals(1,game.count(1,2));
		assertEquals(0,game.count(2,2));
		assertEquals(1,game.count(3,2));
		assertEquals(1,game.count(1,3));
		assertEquals(1,game.count(2,3));
		assertEquals(1,game.count(3,3));
	}
	
	@Test public void countNeighboursForTwoLivingCell(){
		Game game = new Game();
		game.placeHerbivore(2,2);
		game.placeHerbivore(2,3);
		assertEquals(2,game.count(3,2));
		assertEquals(1,game.count(3,1));
	}
	
	@Test public void countBorders(){
		Game game = new Game();
		game.placeHerbivore(0,1);
		assertEquals(1,game.count(0,0));
		assertEquals(0,game.count(9,9));
	}
	
	@Test public void deadCellsAreBlank(){
		Game game = new Game();
		String r =
				"          \n"
			+	"          \n"
			+	"          \n"
			+	"          \n"
			+	"          \n"
			+	"          \n"
			+	"          \n"
			+	"          \n"
			+	"          \n"
			+	"          \n";
		assertEquals(r, game.toString());
	}
	
	@Test public void liveCellsAreX(){
		Game game = new Game();
		game.placeHerbivore(0, 0);
		game.placeHerbivore(1, 3);
		String r =
				"X         \n"
			+	"          \n"
			+	"          \n"
			+	" X        \n"
			+	"          \n"
			+	"          \n"
			+	"          \n"
			+	"          \n"
			+	"          \n"
			+	"          \n";
		assertEquals(r, game.toString());
	}
	
	@Test public void carnivoreStaysQuietWhenThereIsNoFood(){
		Game game = new Game();
		game.placeCarnivore(2, 2);
		game.next();
		assertEquals('C', game.check(2, 2));
	}
	
	@Test public void carnivoreGoesUpTowardsTheFood(){
		Game game = new Game();
		game.placeHerbivore(2, 0);
		game.placeCarnivore(2, 2);
		game.next();
		assertEquals('C', game.check(2, 1));
		assertEquals(' ', game.check(2, 2));
	}
	
	@Test public void informsThatAnHebivoreExists(){
		Game game = new Game();
		game.placeHerbivore(1,1);
		assertTrue(game.existsHerbivore());
	}
	
	@Test public void informsThatAnHebivoreDoesNotExists(){
		Game game = new Game();		
		assertFalse(game.existsHerbivore());
	}
	
//	@Test public void carnivoreGoesRightTowardsTheFood(){
//		Game game = new Game();
//		game.populate(4, 2);
//		game.placeCarnivore(2, 2);
//		game.next();
//		assertEquals('C', game.check(3, 2));
//		assertEquals(' ', game.check(2, 2));
//	}

}
