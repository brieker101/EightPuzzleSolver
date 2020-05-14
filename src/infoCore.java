import csis4463.*;

public class infoCore {
	
	//All of the solvers
	private PuzzleSolution ucs;
	private PuzzleSolution aStarMisplacedTiles;
	private PuzzleSolution aStarManhattanDistance;
	private PuzzleSolution iterativeDeepening;
	private PuzzleSolution idaMisplacedTiles;
	private PuzzleSolution idaManhattanDistance;
	
	//The generic puzzle object that I use repetedly
	private SlidingTilePuzzle puzzle;
	
	//Output variables
	private String LABEL = "L" + "\t\t" + "UCS" + "\t\t" + "A*1" + "\t\t" + "A*2" + "\t\t" + "ID" + "\t\t" + "IDA*1" + "\t\t" + "IDA*2";
	private String expandedOutput;
	private String generatedOutput;
	private String memoryOutput;
	
	//This is how I hold the values for the table going left to right
	private double[] expandedStorage;
	private double[] generatedStorage;
	private double[] memoryStorage;
	
	//Temp global variables that are used to indicate a row or calculate average
	private double puzzleAmount;
	private int complexity;
	
	public infoCore() {
		//Create the top of each output section
		expandedOutput = "Num States Expanded \n" + LABEL + "\n";
		generatedOutput = "Num States Generated \n" + LABEL + "\n";
		memoryOutput = "Max States in Memory \n" + LABEL + "\n";
		
		//Brand new arrays
		expandedStorage = new double[6];
		generatedStorage = new double[6];
		memoryStorage = new double[6];

	}
	
	public void computePuzzle(int x, int y, int optimalSolutionAmount, int numberOfPuzzlesToSolve) {
		
		//Write down the specifics of this computePuzzle()
		puzzleAmount = (double)numberOfPuzzlesToSolve;
		complexity = optimalSolutionAmount;
		
		//Run numberOfPuzzlesToSolve number of times
		for(int i = 0; i < numberOfPuzzlesToSolve; i++) {
			
			//Make a puzzle
			puzzle = new SlidingTilePuzzle(x, y, optimalSolutionAmount);
			
			//Solve it 6 different ways
			ucs = SlidingTilePuzzleSolver.uniformCostSearch(puzzle);
			aStarMisplacedTiles = SlidingTilePuzzleSolver.AStarSearchMisplacedTiles(puzzle);
			aStarManhattanDistance =  SlidingTilePuzzleSolver.AStarSearchManhattanDistance(puzzle);
			iterativeDeepening = SlidingTilePuzzleSolver.iterativeDeepening(puzzle);
			idaMisplacedTiles = SlidingTilePuzzleSolver.idaStarMisplacedTiles(puzzle);
			idaManhattanDistance = SlidingTilePuzzleSolver.idaStarManhattanDistance(puzzle);
			//Gather the stats about this iteration's puzzle
			statisticsCollector();
		}
		//Append each section of the output with whatever data we have from the for loop
		outputBuilder();
	}
	
	
	
	private void statisticsCollector() {
		//Write to the specific index in the array the current value of each puzzleSolution data point
		expandedStorage[0] = ucs.getNumberOfStatesExpanded();
		generatedStorage[0] = ucs.getNumGenerated();
		memoryStorage[0] = ucs.getNumberOfStatesInMemory();
		
		expandedStorage[1] = aStarMisplacedTiles.getNumberOfStatesExpanded();
		generatedStorage[1] = aStarMisplacedTiles.getNumGenerated();
		memoryStorage[1] = aStarMisplacedTiles.getNumberOfStatesInMemory();
		
		expandedStorage[2] = aStarManhattanDistance.getNumberOfStatesExpanded();
		generatedStorage[2] = aStarManhattanDistance.getNumGenerated();
		memoryStorage[2] = aStarManhattanDistance.getNumberOfStatesInMemory();
		
		expandedStorage[3] = iterativeDeepening.getNumberOfStatesExpanded();
		generatedStorage[3] = iterativeDeepening.getNumGenerated();
		memoryStorage[3] = iterativeDeepening.getNumberOfStatesInMemory();
		
		expandedStorage[4] = idaMisplacedTiles.getNumberOfStatesExpanded();
		generatedStorage[4] = idaMisplacedTiles.getNumGenerated();
		memoryStorage[4] = idaMisplacedTiles.getNumberOfStatesInMemory();
		
		expandedStorage[5] = idaManhattanDistance.getNumberOfStatesExpanded();
		generatedStorage[5] = idaManhattanDistance.getNumGenerated();
		memoryStorage[5] = idaManhattanDistance.getNumberOfStatesInMemory();
	}
	
	private void outputBuilder() { 
		//Each table section is split up so that I can that I can be flexible with how I build the output
		expandedOutput += complexity + "\t\t";
		for(int i = 0;i<expandedStorage.length;i++) {
			expandedOutput += expandedStorage[i]/puzzleAmount + "\t\t";
		}
		expandedOutput += "\n";
		
		generatedOutput += complexity + "\t\t";
		for(int i = 0;i<generatedStorage.length;i++) {
			generatedOutput += generatedStorage[i]/puzzleAmount + "\t\t";
		}
		generatedOutput += "\n";
		
		memoryOutput += complexity + "\t\t";
		for(int i = 0;i<memoryStorage.length;i++) {
			memoryOutput += memoryStorage[i]/puzzleAmount + "\t\t";
		}
		memoryOutput += "\n";
		
	}
	
	public String getOutput() {
		//Return the combined table
		return expandedOutput + "\n" + generatedOutput + "\n" + memoryOutput;
	}
	
	
	
	
	
	
}
