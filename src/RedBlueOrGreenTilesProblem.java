import java.util.*;

/**
 * A row of five black square tiles is to have a number of its tiles replaced with colored 
 * oblong tiles chosen from red (length two), green (length three), or blue (length four).
 *
 * If red tiles are chosen there are exactly seven ways this can be done.
 * 		1) RR___  2) _RR__  3) __RR_  4) ___RR  5) RRRR_  6) RR_RR  7) _RRRR
 * 
 * If green tiles are chosen there are three ways.
 * 		1) GGG__  2) _GGG_  3) __GGG
 * 
 * And if blue tiles are chosen there are two ways.
 * 		1) _BBBB  2) BBBB_
 * 
 * Assuming that colors cannot be mixed there are 7 + 3 + 2 = 12 ways of replacing the black 
 * tiles in a row measuring five units in length.
 *
 * How many different ways can the black tiles in a row measuring fifty units in length be replaced 
 * if colors cannot be mixed and at least one colored tile must be used?
 * 
 * For more information, visit https://projecteuler.net/problem=116
 */
public class RedBlueOrGreenTilesProblem extends ProjectEulerProblem {
	private static final Set<Tile> TILES_TO_REPLACE = new HashSet<>(Arrays.asList(Tile.RED, Tile.GREEN, Tile.BLUE));
	
	private final int _numberOfBlackTiles;
	private long _totalNumberOfWays;
	
	public RedBlueOrGreenTilesProblem(int numberOfBlackTiles) {
		_numberOfBlackTiles = numberOfBlackTiles;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	void validateInput() throws InvalidInputException {
		// nothing to be done here
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	void solve() {
		_totalNumberOfWays = 0;
		
		for (Tile tile: TILES_TO_REPLACE) {
			_totalNumberOfWays += replaceTiles(tile);
		}
	}
	
	/**
	 * For a given colored tile, determines the number of ways we can replace the black tiles
	 * 
	 * @param tile to replace the existing black tile with
	 * @return number of ways we can replace the black tiles
	 */
	private long replaceTiles(final Tile tile) {
		long[] ways = new long[_numberOfBlackTiles + 1];
		
		ways[0] = 1;
		
		for (int i = 1; i <= _numberOfBlackTiles; i++) {
			// number of ways we can replace tiles for size n must be greater than or equal to of size n - 1
			ways[i] += ways[i - 1];
			
			// if number of black tiles size(n) is greater than the length of colored tile(COLORED_TILE_LENGTH), 
			// then we can add the number of ways we can replace tile size of n - COLORED_TILE_LENGTH
			if (i >= tile.getLength()) {
				ways[i] += ways[i - tile.getLength()];
			}
		}
		
		// if there are no tiles, then we will return 0
		return ways[_numberOfBlackTiles] - 1;
	}
	
	/**
	 * @return the total number of ways
	 */
	long getOutput() {
		return _totalNumberOfWays;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	void printOutput() {
		System.out.println("Total number of ways black tiles can be replaced without mixing colors is " + getOutput());
	}
	
	/**
	 * class to hold tile information
	 */
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
