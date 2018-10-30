
import org.testng.annotations.*;

import static org.testng.Assert.*;

public class RedBlueOrGreenTilesProblemTest {
	
	@Test(expectedExceptions = InvalidInputException.class)
	public void testRedBlueOrGreenTilesInvalidInput() throws InvalidInputException {
		// setup
		final RedBlueOrGreenTilesProblem redBlueOrGreenTilesProblem = new RedBlueOrGreenTilesProblem(-1);
		
		// execute
		redBlueOrGreenTilesProblem.execute();
	}
	
	@Test(dataProvider = "inputToRedBlueOrGreenTiles")
	public void testRedBlueOrGreenTiles(int numberOfBlackTiles, long expectedTotalNumberOfWays) throws InvalidInputException {
		// setup
		final RedBlueOrGreenTilesProblem redBlueOrGreenTilesProblem = new RedBlueOrGreenTilesProblem(numberOfBlackTiles);
		
		// execute
		redBlueOrGreenTilesProblem.execute();
		
		// verify
		assertEquals(redBlueOrGreenTilesProblem.getOutput(), expectedTotalNumberOfWays);
	}
	
	@DataProvider(name = "inputToRedBlueOrGreenTiles")
	private Object[][] provideInputToRedBlueOrGreenTiles() {
		return new Object[][] {
			new Object[] { /* numberOfBlackTiles  */ 0, /* expectedTotalNumberOfWays */ 0L },
			new Object[] { /* numberOfBlackTiles  */ 1, /* expectedTotalNumberOfWays */ 0L },
			new Object[] { /* numberOfBlackTiles  */ 2, /* expectedTotalNumberOfWays */ 1L },
			new Object[] { /* numberOfBlackTiles  */ 3, /* expectedTotalNumberOfWays */ 3L },
			new Object[] { /* numberOfBlackTiles  */ 4, /* expectedTotalNumberOfWays */ 7L },
			new Object[] { /* numberOfBlackTiles  */ 5, /* expectedTotalNumberOfWays */ 12L },
			new Object[] { /* numberOfBlackTiles  */ 50, /* expectedTotalNumberOfWays */ 20492570929L }
		};
	}
}
