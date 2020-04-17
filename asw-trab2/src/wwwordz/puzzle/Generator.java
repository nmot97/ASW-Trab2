package wwwordz.puzzle;

import java.util.Random;

import wwwordz.puzzle.Trie.Search;
import wwwordz.shared.Puzzle;
import wwwordz.shared.Table;

/**
 * A puzzle generator. Creates a puzzle with many scrambled words contained in a dictionary.
 * @author José Paulo Leal 
 *
 */
public class Generator extends java.lang.Object {
	
	/**
	 * Empty constructor.
	 */
	public Generator() {}
	
	/**
	 * Auxiliary function to obtain a randomly generated char.
	 * @return char
	 */
	public char getRandomLetter() {
		Random r = new Random();
		char c = (char)(r.nextInt(26) + 'a');
		return c;
	}
	
	/**
	 * Generate a high quality puzzle with many words in it.
	 * @return puzzle
	 */
	public Puzzle generate() {
		Puzzle puzzle = new Puzzle();
		puzzle = random();
		return puzzle;
	}
	
	/**
	 * Generates a random puzzle table.
	 * @return puzzle
	 */
	public Puzzle random() {
		Puzzle puzzle = new Puzzle();
		Table table = new Table();
		int size = table.board;
		char letter;
		for ( int i = size ; i > 0 ; i-- ) {
			for ( int j = size ; j > 0 ; j-- ) {
				letter = getRandomLetter();
				table.setLetter(i,j,letter);
			}
		}
		puzzle.setTable(table);
		puzzle.setSolutions(getSolutions(table));
		return puzzle;
	}
	
	/**
	 * Auxiliary function to recursively obtain the solutions of each letter.
	 * @param table - to obtain solutions
	 * @param i - of cell
	 * @param j - of cell
	 * @return list - of solutions for cell
	 */
	public java.util.List<Puzzle.Solution> getTrieSolutions(Table table, int i, int j) {
		java.util.List<Puzzle.Solution> partialSolutions = new java.util.ArrayList<>();
		Dictionary dictionary = Dictionary.getInstance();
		Search search = dictionary.startSearch();
		if ( search.continueWith( table.getLetter(i,j) ) ) {
			//INCOMPLETE
		}
		return partialSolutions;
	}
	
	/**
	 * Return a list of solutions for this table. Solutions have at least 2 letters.
	 *  Different solutions for the same word are discarded.
	 * @param table - containing solutions
	 * @return list - of solutions
	 */
	public java.util.List<Puzzle.Solution> getSolutions(Table table) {
		//INCOMPLETE (getTrieSolutions incomplete)
		java.util.List<Puzzle.Solution> solutions = new java.util.ArrayList<>();
		int size = table.board;
		for ( int i = size ; i > 0 ; i-- ) {
			for ( int j = size ; j > 0 ; j-- ) {
				solutions.addAll(getTrieSolutions(table,i,j));
			}
		}
		return solutions;
	}
}





















