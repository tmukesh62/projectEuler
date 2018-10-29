
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class MaximumPathSumOneProblemTest {

	@Test(expectedExceptions = InvalidInputException.class, dataProvider = "invalidInputsToMaximumPathsumOne")
	public void testMaximumPathsumOneInvalidInputs(final long[][] triangle) throws InvalidInputException {
		// setup
		final MaximumPathSumOneProblem maximumPathSumOneProblem = new MaximumPathSumOneProblem(triangle);

		// execute
		maximumPathSumOneProblem.execute();
	}

	@Test(dataProvider = "validInputsToMaximumPathsumOne")
	public void testMaximumPathsumOne(final long[][] triangle, final long expectedMaximumPathsum) throws InvalidInputException {
		// setup
		final MaximumPathSumOneProblem maximumPathSumOneProblem = new MaximumPathSumOneProblem(triangle);

		// execute
		maximumPathSumOneProblem.execute();

		// verify
		assertEquals(maximumPathSumOneProblem.getOutput(), expectedMaximumPathsum);
	}

	@DataProvider(name = "validInputsToMaximumPathsumOne")
	private Object[][] provideValidInputsToMaximumPathsumOne() {
		return new Object[][] {
			new Object[] { /* triangle */ new long[0][0], /* expectedMaximumPathsum */ 0L},
			new Object[] { /* triangle */ new long[][] { { 1 } }, /* expectedMaximumPathsum */ 1L },
			new Object[] { /* triangle */ new long[][] { { 1 }, { 2, 3 } }, /* expectedMaximumPathsum */ 4L },
			new Object[] { /* triangle */ new long[][] { { 1 }, { 2, 3 }, { 4, 5, 6 } }, /* expectedMaximumPathsum */ 10L },
			new Object[] {
				/* triangle */ new long[][] {
					{ 75 },
					{ 95, 64 },
					{ 17, 47, 82 },
					{ 18, 35, 87, 10 },
					{ 20,  4, 82, 47, 65 },
					{ 19,  1, 23, 75,  3, 34 },
					{ 88,  2, 77, 73,  7, 63, 67 },
					{ 99, 65,  4, 28,  6, 16, 70, 92 },
					{ 41, 41, 26, 56, 83, 40, 80, 70, 33 },
					{ 41, 48, 72, 33, 47, 32, 37, 16, 94, 29, },
					{ 53, 71, 44, 65, 25, 43, 91, 52, 97, 51, 14 },
					{ 70, 11, 33, 28, 77, 73, 17, 78, 39, 68, 17, 57 },
					{ 91, 71, 52, 38, 17, 14, 91, 43, 58, 50, 27, 29, 48 },
					{ 63, 66, 04, 68, 89, 53, 67, 30, 73, 16, 69, 87, 40, 31 },
					{ 04, 62, 98, 27, 23,  9, 70, 98, 73, 93, 38, 53, 60,  4, 23 }}, /* expectedMaximumPathsum */ 1074L}
		};
	}

	@DataProvider(name = "invalidInputsToMaximumPathsumOne")
	private Object[][] provideInvalidInputsToMaximumPathsumOne() {
		return new Object[][] { 
			new Object[] { /* triangle */ null }, 
			new Object[] { /* triangle */ new long[][] { { 1 }, { 2, 3 }, { 4, 5 } } } 
		};
	}
}
