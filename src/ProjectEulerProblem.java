
abstract class ProjectEulerProblem {
	long _startTime;
	long _endTime;
	
	void execute() throws InvalidInputException {
		validateInput();
		
		startTimer();
		
		solve();
		
		endTimer();
		
		printOutput();
	}
	
	abstract void validateInput() throws InvalidInputException;
	
	abstract void solve();
	
	abstract void printOutput();
	
	private void startTimer() {
		_startTime = System.currentTimeMillis();
	}
	
	private void endTimer() {
		_endTime = System.currentTimeMillis();
		
		System.out.println("Elapsed time in milli seconds: " + (_endTime - _startTime));
	}
}
