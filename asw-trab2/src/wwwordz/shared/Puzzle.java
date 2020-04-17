package wwwordz.shared;

/**
 * A puzzle, containing a table and list of solutions. 
 * A table is a square grid of letters and a solution is a word contained in the grid, where consecutive letters are in neighboring cells on the grid and the letter in each cell is used only once.
 * @author José Paulo Leal
 *
 */
public class Puzzle extends java.lang.Object implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Nested class solution
	 *
	 */
	public static class Solution extends java.lang.Object implements java.io.Serializable {
		private static final long serialVersionUID = 1L;
		java.lang.String word;
		java.util.List<Table.Cell> cells;
		
		/**
		 * Empty constructor.
		 */
		public Solution() {}
		
		/**
		 * Creates a solution with parameteres.
		 * @param word - of solution
		 * @param cells - of solution
		 */
		public Solution(java.lang.String word, java.util.List<Table.Cell> cells) {
			this.word = word;
			this.cells = cells;
		}
		
		/**
		 * Returns the word of the solution.
		 * @return word
		 */
		public java.lang.String getWord() {
			return this.word;
		}
		
		/**
		 * Auxiliary function to calculate points based on word size.
		 * @param size - of word.
		 * @return points worth.
		 */
		public int pointCalc(int size) {
			int result = 0;
			for( int i = 3 ; i <= size ; i++ ) {
				result = (2 * result) + 1;
			}
			return result;
		}
		
		/**
		 * Calculates the points of the solution.
		 * @return points
		 */
		public int getPoints() {
			return pointCalc(word.length());
		}
		
		/**
		 * Returns cells of the solution
		 * @return cells
		 */
		public java.util.List<Table.Cell> getCells() {
			return this.cells;
		}
    }
	
	Table table;
	java.util.List<Puzzle.Solution> solutions;
	
	/**
	 * Empty constructor.
	 */
	public Puzzle() {}
	
	/**
	 * Returns table of instance.
	 * @return table
	 */
	public Table getTable() {
		return this.table;
	}

	/**
	 * Sets table of the instance.
	 * @param table - to be set
	 */
	public void setTable(Table table) {
		this.table = table;
	}
	
	/**
	 * Returns solutions of instance.
	 * @return solutions - of instance
	 */
	public java.util.List<Puzzle.Solution> getSolutions() {
		return this.solutions;
	}
	
	/**
	 * Sets solutions of instance.
	 * @param solutions - to be set
	 */
	public void setSolutions(java.util.List<Puzzle.Solution> solutions) {
		this.solutions = solutions;
	}
}