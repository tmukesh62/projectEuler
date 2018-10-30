import java.util.*;

/**
 * Given a matrix, determines the minimal path sum by only moving to the right and down.
 * 
 * For example, in the matrix below:
 * 				131 673 234 103  18
 * 				201  96 342 965 150
 * 				630 803 746 422 111
 * 				537 699 497 121 956
 * 				805 732 524  37 331
 * 
 * The minimal path is 131 -> 201 -> 96 -> 342 -> 746 -> 422 -> 121 -> 37 -> 331 and the sum is 2427.
 * 
 * For more information, visit https://projecteuler.net/problem=81
 */
public class PathSumProblem extends ProjectEulerProblem {
	private final int[][] _matrix;
	private long _minimalPathSum;
	
	public PathSumProblem(int[][] matrix) {
		_matrix = matrix;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	void validateInput() throws InvalidInputException {
		System.out.println("Validating input matrix : " + Arrays.deepToString(_matrix));
		
		if (_matrix == null) {
			System.out.println("Invalid matrix found: " + _matrix);
			
			throw new InvalidInputException("Invalid matrix found.");
		}
		
		System.out.println("Input matrix is valid.");
	}

	/**
	 * {@inheritDoc}
	 * 
	 * Approach: We solve this problem using dynamic programming approach. We first create
	 * a two dimensional matrix(dp) of exactly same size as the original matrix, then for
	 * any dp[i][j] is equal to sum of matrix[i][j] and min(dp[i - 1][j], dp[i][j - 1]) because
	 * we can only move either right or down.
	 * 
	 * For example, lets say the given matrix is:
	 * 						1	2	3	4
	 * 						5	6	7	8
	 * 						9  10  11  12
	 * 
	 * 
	 * Initially, dp will be the same as above but after processing it will become following:
	 * 						1	3	6  10
	 * 						6   9  13  18	
	 * 					   15  19  24 (30)
	 * 
	 * The output will be 30 as the minimal path is 1 -> 2 -> 3 -> 4 -> 8 -> 12.
	 */
	@Override
	void solve() {
		// make sure there are non-zero rows and columns
		final int numberOfRows = _matrix.length;
		
		if (numberOfRows == 0) {
			_minimalPathSum = 0;
			
			return;
		}
		
		final int numberOfColumns = _matrix[0].length;
		
		if (numberOfColumns == 0) {
			_minimalPathSum = 0;
			
			return;
		}
		
		// initialize the dynamic programming matrix
		final long[][] dp = new long[numberOfRows][numberOfColumns];
		
		// first element will be the same
		dp[0][0] = _matrix[0][0];
		
		// initialize the first row and column because there is only one direction
		for (int i = 1; i < numberOfColumns; i++) dp[0][i] = _matrix[0][i] + dp[0][i - 1];
		for (int i = 1; i < numberOfRows; i++) dp[i][0] = _matrix[i][0] + dp[i - 1][0];
		
		// populate the rest of dp
		for (int i = 1; i < numberOfRows; i++) {
			for (int j = 1; j < numberOfColumns; j++) {
				dp[i][j] = _matrix[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
			}
		}
		
		// minimal path sum is the last element of the dp
		_minimalPathSum = dp[numberOfRows - 1][numberOfColumns - 1];
	}
	
	/**
	 * @return the minimal path sum
	 */
	long getOutput() {
		return _minimalPathSum;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	void printOutput() {
		System.out.println("The minimal path sum for the given matrix is " + getOutput());
	}

}
