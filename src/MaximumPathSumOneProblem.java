import java.util.*;

/**
 * https://projecteuler.net/problem=18
 */
public class MaximumPathSumOneProblem extends ProjectEulerProblem {
	private final long[][] _triangle;
	private long _maxPathSum;
	
	MaximumPathSumOneProblem(long[][] triangle) {
		_triangle = triangle;
	}

	@Override
	void validateInput() throws InvalidInputException {
		System.out.println("Validating input triangle : " + Arrays.deepToString(_triangle));
		
		if (Objects.isNull(_triangle) || !isValidTriangle()) {
			System.out.println("Input triangle is invalid.");
			throw new InvalidInputException("Input triangle is invalid.");
		}
		
		System.out.println("Input triangle is valid.");
	}
	
	private boolean isValidTriangle() {
		for (int i = 1; i <= _triangle.length; i++) {
			if (Objects.isNull(_triangle[i - 1]) || _triangle[i - 1].length != i) {
				return false;
			}
		}
		
		return true;
	}

	@Override
	void solve() {
		for (int i = _triangle.length - 2; i >= 0; i--) {
			for (int j = 0; j < _triangle[i].length; j++) {
				_triangle[i][j] += Math.max(_triangle[i + 1][j], _triangle[i + 1][j + 1]);
			}
		}
		
		_maxPathSum = _triangle.length == 0 ? 0 : _triangle[0][0];
	}
	
	long getOutput() {
		return _maxPathSum;
	}

	@Override
	void printOutput() {
		System.out.println(String.format("The maximum path sum for the given triangle is %d", _maxPathSum));
	}
}
