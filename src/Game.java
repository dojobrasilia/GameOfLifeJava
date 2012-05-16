import java.awt.Point;
import java.util.HashSet;
import java.util.Set;


public class Game {

	private Set<Point> alive = new HashSet<Point>();
	
	public void populate(int x, int y) {
		alive.add(new Point(x,y));
	}

	public void next() {
		// TODO Auto-generated method stub
		
	}

	public boolean check(int x, int y) {
		return alive.contains(new Point(x,y));
	}

}
