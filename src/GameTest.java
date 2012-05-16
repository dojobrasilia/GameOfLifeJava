import static org.junit.Assert.*;

import org.junit.Test;


public class GameTest {
	
	@Test public void allCellsMustBeDeadBeforeStart(){
		Game game = new Game();
		for(int i = 0; i < 10; i++)
			for(int j = 0 ; j< 10; j++)
				assertFalse("("+i+","+j+") should be False",game.check(i,j));
	}
	
	@Test public void aPopulatedCellMustBeAlive(){
		Game game = new Game();
		game.populate(0,0);
		game.populate(1,2);
		assertTrue("(0,0) should be True",game.check(0,0));
		assertTrue("(1,2) should be True",game.check(1,2));
	}
	
	
	
//	@Test public void aLonelyCellMustDie(){
//		Game game = new Game();
//		game.populate(0,0);
//		game.next();
//		assertFalse(game.check(0,0));
//	}
}
