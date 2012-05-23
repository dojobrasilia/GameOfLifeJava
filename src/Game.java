public class Game {
	
	private static final int FIELD_SIZE = 10;
	private char[][] cells = new char[FIELD_SIZE][FIELD_SIZE];
	
	public void placeHerbivore(int x, int y) {
		cells[x][y]='H';
	}
	
	public void placeCarnivore(int x, int y) {
		cells[x][y]='C';
	}

	public void next() {
		char[][] nextGen = new char[FIELD_SIZE][FIELD_SIZE];
		for(int x = 0; x < FIELD_SIZE; x++)
			for(int y = 0 ; y< FIELD_SIZE; y++)
				if (isHerbivore(x, y)){
					if (count(x,y) == 3 || count(x,y) == 2 ) nextGen[x][y] = 'H';
					else nextGen[x][y] = ' ';
				}else if( isCarnivore(x, y) ){
					if (isHerbivore(x, y-2)){
						nextGen[x][y-1] = 'C';
						nextGen[x][y] = ' ';
					}else{
						nextGen[x][y] = 'C';
					}
				}else{
					if (count(x,y) == 3) nextGen[x][y] = 'H';
				}
		cells=nextGen;
	}

	private boolean isCarnivore(int x, int y) {
		return check(x, y) == 'C';
	}

	private boolean isHerbivore(int x, int y) {
		return check(x,y) == 'H';
	}

	public char check(int x, int y) {
		if (x < 0 || y < 0 || y > 9 || x > 9) return ' ';
		return cells[x][y] == '\0' ? ' ' : cells[x][y];
	}

	int count(int x, int y) {
		int sum = 0;
		for (int i = -1 ; i < 2; i++)
			for (int j = -1 ; j < 2; j++)
				if (!(i == 0 && j == 0) && check(x+j,y+i) == 'H') sum++;
		
		return sum;
	}

	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		for(int y = 0; y < FIELD_SIZE; y++){
			for(int x = 0; x < FIELD_SIZE; x++)
				if (isHerbivore(x, y)) b.append('X');
				else b.append(' ');
			b.append('\n');
		}
				
		return b.toString();
	}

	public boolean existsHerbivore() {
		for(int y = 0; y < FIELD_SIZE; y++){
			for(int x = 0; x < FIELD_SIZE; x++){
				if(isHerbivore(x, y)) return true;
			}
		}				
		return false;
	}

}

