import java.util.*;

/**
 * Given a triangle, finds maximum total from top to bottom .
 * 
 * For example, in the below triangle:
 *                3
 * 				 7 4
 * 				2 4 6
 * 			   8 5 9 3
 * 
 * The maximum total is 3 + 7 + 4 + 9 = 23.
 * 
 * For more information, visit https://projecteuler.net/problem=18
 */
public class MaximumPathSumOneProblem extends ProjectEulerProblem {
	private final long[][] _triangle;
	private long _maxPathSum;
	
	MaximumPathSumOneProblem(long[][] triangle) {
		_triangle = triangle;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	void validateInput() throws InvalidInputException {
		System.out.println("Validating input triangle : " + Arrays.deepToString(_triangle));
		
		if (Objects.isNull(_triangle) || !isValidTriangle()) {
			System.out.println("Input triangle is invalid.");
			throw new InvalidInputException("Input triangle is invalid.");
		}
		
		System.out.println("Input triangle is valid.");
	}
	
	/**
	 * Validates the input triangle. A given triangle is valid if and only if the number of 
	 * elements at index n is equal to n.
	 * 
	 * For example, consider the following triangles a and b:
	 *				1					1
	 *			   2 3				   2 3
	 *			  4 5 6				   4 5
	 *			   (a)				   (b)
	 *
	 * out of the two, only a is valid.
	 * 
	 * @return true if the triangle is valid and false otherwise
	 */
	private boolean isValidTriangle() {
		for (int i = 1; i <= _triangle.length; i++) {
			if (Objects.isNull(_triangle[i - 1]) || _triangle[i - 1].length != i) {
				return false;
			}
		}
		
		return true;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * Approach: We start from the last index and move up. For any Cell(i, j), the maximum
	 * sum until that point is sum of Cell(i, j) and max(Cell(i + 1, j), Cell(i + 1, j + 1)).
	 * We update the value of the Cell(i, j) which will be used later for the lower index(i - 1)
	 * 
	 * Consider the triangle below:
	 *                3
	 * 				 7 4
	 * 				2 4 6
	 * 			   8 5 9 3
	 * 
	 * After iteration 1:
	 *                3
	 * 				 7 4
	 * 			   10 13 15
	 * 			  8  5  9  3
	 * 
	 * After iteration 2:
	 *                3
	 * 				20 19
	 * 			   10 13 15
	 * 			  8  5  9  3
	 * 
	 * After iteration 3:
	 *               23
	 * 				20 19
	 * 			   10 13 15
	 * 			  8  5  9  3
	 * 
	 * The answer for this case would then be value of Cell(0, 0), which is 23.
	 */
	@Override
	void solve() {
		for (int i = _triangle.length - 2; i >= 0; i--) {
			for (int j = 0; j < _triangle[i].length; j++) {
				_triangle[i][j] += Math.max(_triangle[i + 1][j], _triangle[i + 1][j + 1]);
			}
		}
		
		_maxPathSum = _triangle.length == 0 ? 0 : _triangle[0][0];
	}
	
	/**
	 * @return the maximum path sum
	 */
	long getOutput() {
		return _maxPathSum;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	void printOutput() {
		System.out.println(String.format("The maximum path sum for the given triangle is %d", getOutput()));
	}
}
