

public class Game {

	private boolean[][] cells = new boolean[10][10];
	
	public void populate(int x, int y) {
		cells[x][y]=true;
	}

	public void next() {
		boolean[][] nextGen = new boolean[10][10];
		for(int x = 0; x < 10; x++)
			for(int y = 0 ; y< 10; y++)
				if (check(x,y)){
					if (count(x,y) == 3 || count(x,y) == 2 ) nextGen[x][y] = true;
					else nextGen[x][y] = false;
				}else{
					if (count(x,y) == 3) nextGen[x][y] = true;
				}
		cells=nextGen;
	}

	public boolean check(int x, int y) {
		if (x < 0 || y < 0 || y > 9 || x > 9) return false;
		return cells[x][y];
	}

	int count(int x, int y) {
		int sum = 0;
		for (int i = -1 ; i < 2; i++)
			for (int j = -1 ; j < 2; j++)
				if (!(i == 0 && j == 0) && check(x+j,y+i)) sum++;
		
		return sum;
	}

}

