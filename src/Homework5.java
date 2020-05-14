import csis4463.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * For this assignment, you will need the puzzle.jar file and its documentation (docs.zip) 
 * from homework 4, and the Examples.java will be useful as well.
 *
 * In Java, implement any search algorithm that we saw in class to solve sliding tile
 * puzzles, such as the 8 puzzle, 15 puzzle, etc.
 *
 * You must not change the name, parameters, or return type of the solver method.
 * You are free to add as many helper methods as you find useful.
 *
 * You are free to use the MinHeapPQ class that is in the puzzle.jar (see its documentation).
 * It supports changing the priority of elements that are in the PQ, whereas the PQ implementation
 * in the Java API does not support that operation.
 *
 * Grading:
 * Your grade will be in the interval [0, 100] if you implement an algorithm that is guaranteed
 * to find the optimal path.  Otherwise, if you implement an algorithm that is not
 * guaranteed to find the optimal path (e.g., DFS), then your grade will be in the interval [0, 85]
 * (i.e., you lose 15 points for the non-optimal algorithms).
 *
 * If your code doesn't compile, then your grade will be in the interval [0, 60] depending upon the severity
 * of the syntax errors.  i.e., make sure your code compiles (you lose at least 40 points if it doesn't.
 *
 * After completing the solver method, write code to demonstrate that it works in the Homework5Main class.
 *
 * @author Your Name Goes here.
 *
 */
public class Homework5 {
	
	/**
	 * Solves sliding tile puzzles with the algorithm of your choice.
	 *
	 * @return A path from the start state to the goal state.
	 */
	public ArrayList<SlidingTilePuzzle> solver(SlidingTilePuzzle start) {
		MinHeapPQ<SlidingTilePuzzle> priority = new MinHeapPQ<SlidingTilePuzzle>();
		ArrayList<SlidingTilePuzzle> path = new ArrayList<SlidingTilePuzzle>();
		SlidingTilePuzzle next;
		
		while(!start.isGoalState()) {
			priority = new MinHeapPQ<SlidingTilePuzzle>();
			for(SlidingTilePuzzle s : start.getSuccessors()) {
				//System.out.println(s);
				int priorityTeller = 0;
				for(int i = 0;i<s.numRows();i++) {
					for(int j = 0;j<s.numColumns();j++) {
						if(s.getTile(i, j)==((j+1)+i*3)) {
							priorityTeller++;
						}
					}
				}
				priority.offer(s, priorityTeller);
			}
			while(priority.size()>1) {
				priority.poll();
			}
			path.add(start);
			System.out.println(priority.peek());
			start = priority.poll();
		}
		
		return path;
	}
	
}