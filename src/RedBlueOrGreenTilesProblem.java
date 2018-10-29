import java.util.*;

/**
 * https://projecteuler.net/problem=116
 */
public class RedBlueOrGreenTilesProblem extends ProjectEulerProblem {
	private static final Set<Tile> TILES_TO_REPLACE = new HashSet<>(Arrays.asList(Tile.RED, Tile.GREEN, Tile.BLUE));
	
	private final int _numberOfBlackTiles;
	private long _totalNumberOfWays;
	
	public RedBlueOrGreenTilesProblem(int numberOfBlackTiles) {
		_numberOfBlackTiles = numberOfBlackTiles;
	}

	@Override
	void validateInput() throws InvalidInputException {
		// nothing to be done here
	}

	@Override
	void solve() {
		_totalNumberOfWays = 0;
		
		for (Tile tile: TILES_TO_REPLACE) {
			_totalNumberOfWays += replaceTiles(tile);
		}
	}

	private long replaceTiles(final Tile tile) {
		long[] ways = new long[_numberOfBlackTiles + 1];
		
		ways[0] = 1;
		
		for (int i = 1; i <= _numberOfBlackTiles; i++) {
			ways[i] += ways[i - 1];
			
			if (i >= tile.getLength()) {
				ways[i] += ways[i - tile.getLength()];
			}
		}
		
		return ways[_numberOfBlackTiles] - 1;
	}
	
	long getOutput() {
		return _totalNumberOfWays;
	}

	@Override
	void printOutput() {
		System.out.println("Total number of ways black tiles can be replaced without mixing colors is " + _totalNumberOfWays);
	}
	
	private static enum Tile {
		BLACK(1),
		RED(2),
		GREEN(3),
		BLUE(4);
		
		private final int _length;
		
		Tile(int length) {
			_length = length;
		}
		
		int getLength() {
			return _length;
		}
	}

}
