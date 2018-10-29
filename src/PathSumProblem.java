import java.util.*;

/**
 * https://projecteuler.net/problem=81
 */
public class PathSumProblem extends ProjectEulerProblem {
	private final int[][] _matrix;
	private long _minimalPathSUm;
	
	public PathSumProblem(int[][] matrix) {
		_matrix = matrix;
	}

	@Override
	void validateInput() throws InvalidInputException {
		System.out.println("Validating input matrix : " + Arrays.deepToString(_matrix));
		
		if (_matrix == null) {
			System.out.println("Invalid matrix found: " + _matrix);
			
			throw new InvalidInputException("Invalid matrix found.");
		}
		
		System.out.println("Input matrix is valid.");
	}

	@Override
	void solve() {
		final int numberOfRows = _matrix.length;
		
		if (numberOfRows == 0) {
			_minimalPathSUm = 0;
			
			return;
		}
		
		final int numberOfColumns = _matrix[0].length;
		
		if (numberOfColumns == 0) {
			_minimalPathSUm = 0;
			
			return;
		}
		
		final long[][] dp = new long[numberOfRows][numberOfColumns];
		
		dp[0][0] = _matrix[0][0];
		for (int i = 1; i < numberOfColumns; i++) dp[0][i] = _matrix[0][i] + dp[0][i - 1];
		for (int i = 1; i < numberOfRows; i++) dp[i][0] = _matrix[i][0] + dp[i - 1][0];
		
		
		for (int i = 1; i < numberOfRows; i++) {
			for (int j = 1; j < numberOfColumns; j++) {
				dp[i][j] = _matrix[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
			}
		}
		
		_minimalPathSUm = dp[numberOfRows - 1][numberOfColumns - 1];
	}
	
	long getOutput() {
		return _minimalPathSUm;
	}

	@Override
	void printOutput() {
		System.out.println("The minimal path sum for the given matrix is " + _minimalPathSUm);
	}

}
