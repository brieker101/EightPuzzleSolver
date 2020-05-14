import csis4463.*;

/**
 * See comments in Homework5.java first.
 * 
 * @author Brian Hauck
 */
public class Homework5Main {
	
	public static void main(String[] args) {
		// write code here to demonstrate that your 8 puzzle solver works.
		Homework5 test = new Homework5();
		test.solver(new SlidingTilePuzzle(3, 3, 5));
	}
}