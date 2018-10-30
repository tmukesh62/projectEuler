
/**
 * Abstract class for solving Project Euler problems
 */
abstract class ProjectEulerProblem {
	private long _startTime;
	private long _endTime;
	
	/**
	 * Solves the problem and logs the result and elapsed time.
	 * 
	 * @throws InvalidInputException if the input passed on to the problem is invalid
	 */
	void execute() throws InvalidInputException {
		validateInput();
		
		startTimer();
		
		solve();
		
		endTimer();
		
		printOutput();
	}
	
	/**
	 * Validates the input.
	 * 
	 * @throws InvalidInputException if the input is invalid
	 */
	abstract void validateInput() throws InvalidInputException;
	
	/**
	 * Solves the problem and updates the result.
	 */
	abstract void solve();
	
	/**
	 * Logs the output to the console.
	 */
	abstract void printOutput();
	
	/**
	 * Starts the timer. Must be called before the solve method.
	 */
	private void startTimer() {
		_startTime = System.currentTimeMillis();
	}
	
	/**
	 * Ends the timer and logs the elapsed time. Must be called after the solve method.
	 */
	private void endTimer() {
		_endTime = System.currentTimeMillis();
		
		System.out.println("Elapsed time in milli seconds: " + (_endTime - _startTime));
	}
}
