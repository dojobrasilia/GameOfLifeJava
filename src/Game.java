import java.awt.Point;

public class Game {

	private static final int FIELD_SIZE = 10;
	private Animal[][] cells = new Animal[FIELD_SIZE][FIELD_SIZE];

	public void placeHerbivore(int x, int y) {
		cells[x][y] = new Animal('H');
	}

	public void placeCarnivore(int x, int y) {
		cells[x][y] = new Animal('C');
	}

	public void next() {
		Animal[][] nextGen = new Animal[FIELD_SIZE][FIELD_SIZE];
		for (int x = 0; x < FIELD_SIZE; x++)
			for (int y = 0; y < FIELD_SIZE; y++)
				if (isHerbivore(x, y)) {
					nextHerbivore(nextGen, x, y);
				} else if (isCarnivore(x, y)) {
					nextCarnivore(nextGen, x, y);
				} else {
					checkForBreeding(nextGen, x, y);
				}
		cells = nextGen;
	}

	private void checkForBreeding(Animal[][] nextGen, int x, int y) {
		if (count(x, y) == 3) nextGen[x][y] = new Animal('H');
	}

	private void nextCarnivore(Animal[][] nextGen, int x, int y) {
		Animal animal = this.cells[x][y];
		animal.incTurn();
		if (animal.hunger > 4){
			nextGen[x][y] = null;
			return ;
		}
		Point food = findNearestHerbivore(x, y);
		if (food != null) {
			if (distance(x, y, food.x, food.y) == 1) {
				animal.hunger =0;
				nextGen[food.x][food.y] = animal;
			} else {
				Point direction = new Point();
				
				
				if (food.y < y)
					direction.y = -1;
				else if (food.y > y)
					direction.y = +1;
				
				if (direction.y == 0
						|| nextGen[x][y+direction.y] != null) {
					direction.y = 0;
					if (food.x < x)
						direction.x = -1;
					else
						direction.x = +1;
				}
				
				nextGen[x + direction.x][y + direction.y] = animal;
			}

			nextGen[x][y] = null;
		} else {
			nextGen[x][y] = animal;
		}
	}

	private void nextHerbivore(Animal[][] nextGen, int x, int y) {
		if(!isProcessed(nextGen, x, y)){
			if (count(x, y) == 3 || count(x, y) == 2)
				nextGen[x][y] = new Animal('H');
			else
				nextGen[x][y] = null;
		}
	}

	private boolean isProcessed(Animal[][] nextGen, int x, int y) {
		return nextGen[x][y] != null;
	}

	private boolean isCarnivore(int x, int y) {
		return check(x, y) == 'C';
	}

	private boolean isHerbivore(int x, int y) {
		return check(x, y) == 'H';
	}

	public char check(int x, int y) {
		if (x < 0 || y < 0 || y > 9 || x > 9)
			return ' ';
		return cells[x][y] == null ? ' ' : cells[x][y].type;
	}

	int count(int x, int y) {
		int sum = 0;
		for (int i = -1; i < 2; i++)
			for (int j = -1; j < 2; j++)
				if (!(i == 0 && j == 0) && isHerbivore(x + j, y + i))
					sum++;

		return sum;
	}

	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		for (int y = 0; y < FIELD_SIZE; y++) {
			for (int x = 0; x < FIELD_SIZE; x++)
					b.append(check(x,y));
				b.append('\n');
		}
		return b.toString();
	}

	public boolean existsHerbivore() {
		for (int y = 0; y < FIELD_SIZE; y++) {
			for (int x = 0; x < FIELD_SIZE; x++) {
				if (isHerbivore(x, y))
					return true;
			}
		}
		return false;
	}

	public int distance(int refX, int refY, int nearestX, int nearestY) {

		return Math.abs(nearestX - refX) + Math.abs(nearestY - refY);
	}

	public Point findNearestHerbivore(int refX, int refY) {
		Point nearest = null;
		int nearestDistance = Integer.MAX_VALUE;

		for (int y = 0; y < FIELD_SIZE; y++)

			for (int x = 0; x < FIELD_SIZE; x++)
				if (isHerbivore(x, y)) {
					int distance = distance(refX, refY, x, y);
					if (nearestDistance > distance) {
						nearest = new Point(x, y);
						nearestDistance = distance;
					}
				}

		return nearest;
	}

}

class Animal
{
	Character type;
	int hunger;
	
	public Animal(Character type){
		this.type = type;
	}

	public void incTurn() {	hunger++;}
	
}