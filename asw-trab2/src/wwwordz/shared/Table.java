package wwwordz.shared;

import java.util.*;

/**
 * A table composed of a collection of cells indexed by row and column positions.
 * @author José Paulo Leal
 *
 */
public class Table extends java.lang.Object implements java.lang.Iterable<Table.Cell>, java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	public int board = 4;
	
	/**
	 * Auxiliary function to test if a certain cell is within table bounds (excluding null cells).
	 * @param row - of cell
	 * @param column - of cell
	 * @return true if it is within, false otherwise
	 */
	public boolean boundsTest(int row, int column) {
		if (row <= 0 || row >= 5 || column <= 0 || column >= 5 )
			return false;
		else 
			return true;
	}
	
	/**
	 * Nested class Cell.
	 * A cell in the enclosing table
	 *
	 */
	public static class Cell extends java.lang.Object implements java.io.Serializable {
		private static final long serialVersionUID = 1L;
		
		int row;
		int column;
		char letter;
		
		/**
		 * Empty constructor.
		 */
		Cell() {}
		
		/**
		 * Create a cell with letter at given row and column. Letter is set to 7 as to detect it is null, avoiding possible null conflicts.
		 * @param row - of cell
		 * @param column - of cell
		 */
		Cell(int row, int column) {
			this.row = row;
			this.column = column;
			this.letter = 7;
		}
		
		/**
		 * Create an empty cell at the given row, column and letter.
		 * @param row - of cell
		 * @param column - of cell
		 * @param letter - of cell
		 */
		Cell(int row, int column, char letter) {
			this.row = row;
			this.column = column;
			this.letter = letter;
		}
		
		/**
		 * Check if cell is empty.
		 * @return true if empty, false otherwise
		 */
		public boolean isEmpty() {
			if ( letter == 7 )
				return true;
			else
				return false;
		}
		
		/**
		 * Returns a string equivalent to a Cell.
		 * @return string - of cell
		 */
		public java.lang.String toString() {
			char a, b;
			a = (char)this.row;
			b = (char)this.column;
			String duck = "Row: " + String.valueOf(a) + " ; Column: " + String.valueOf(b) + " ; Letter: " + String.valueOf(this.letter) + ".";
			return duck;
		}
		
		/**
		 * Change letter in this cell.
		 * @param letter - to set
		 */
		public void setLetter(char letter) {
			this.letter = letter;
		}
		
		/**
		 * Get letter of this cell.
		 * @return letter in cell
		 */
		public char getLetter() {
			return letter;
		}
		
		/**
		 * Hashes the cell.
		 * @return hashed cell
		 */
		public int hashCode() {
	        final int prime = 31;
	        int result = 1;
	        result = prime * result + this.row + this.column;
	        return result;
		}
		
		/**
		 * Tests if cell is equal to obj.
		 * @return true if same, false otherwise.
		 */
		public boolean equals(java.lang.Object obj) {
			if ( obj == this )
				return true;
			if((obj == null) || (obj.getClass() != this.getClass())) {
		        return false;
			}
			Cell cael = (Cell) obj;
			return (this.row == cael.row) && (this.column == cael.column) && (this.letter == cael.letter);
		}
	}
	
	/**
	 * ------------------------------------------------------------------------------------------------------------ *
	 */
	
	/**
	 * Nested class CellIterator.
	 * An iterator over cells in this table.
	 *
	 */
	class CellIterator extends java.lang.Object implements java.util.Iterator<Table.Cell> {
		int row = -1;
		int column = -1;
		
		/**
		 * Constructor that sets the values of the iterator.
		 */
		CellIterator() {
			row = 1;
			column = 0;
		}
		
		/**
		 * Tests if current cell has a next one.
		 * @return true if it has, false otherwise.
		 */
		public boolean hasNext() {
			if ( row == board && column == board )
				return false;
			else if ( row == 1 && column == 0 )
				return true;
			else
				return boundsTest(row,column);
		}
		
		/**
		 * Sets the iterator to next cell.
		 * @return next cell
		 */
		public Table.Cell next() {
			if ( column == board ) {
				row = row + 1;
				column = 1;
				return getCell(row,column);
			}
			else {
				column = column + 1;
				return getCell(row,column);
			}
		}
	} 
	
	/**
	 * ------------------------------------------------------------------------------------------------------------ *
	 */
	
	Table.Cell[][] table = new Table.Cell[board+2][board+2];
	
	/**
	 * Creates a table with empty cells.
	 */
	public Table() {
		for ( int i = board ; i > 0 ; i-- ) {
			for ( int j = board ; j > 0 ; j-- ) {
				table[i][j] = new Cell(i,j);
			}	
		}
	}
	
	/**
	 * Creates a table given data.
	 * @param data - array of strings to initialize table.
	 */
	public Table(java.lang.String[] data) {
		Cell temp;
		for ( int i = board ; i > 0 ; i-- ) {
			for ( int j = board ; j > 0 ; j-- ) {
				temp = new Cell(i,j,data[i-1].charAt(j-1));
				table[i][j] = temp;
			}
		}
	}
	
	/**
	 * Returns a new instance of CellIterator.
	 */
	public java.util.Iterator<Table.Cell> iterator() {
		return new CellIterator();
	}
	
	/**
	 * Get a letter at a given row and column.
	 * @param row - of cell
	 * @param column - of cell
	 * @return letter in given position
	 */
	public char getLetter(int row, int column) {
		return table[row][column].getLetter();
	}
	
	/**
	 * Set a letter at a given row and column.
	 * @param row - to set
	 * @param column - to set
	 * @param letter - to set
	 */
	public void setLetter(int row, int column, char letter) {
		table[row][column].setLetter(letter);
	}
	
	/**
	 * Return a list with the empty cells in this table.
	 * @return list of cells
	 */
	public java.util.List<Table.Cell> getEmptyCells() {
		Table.Cell temp;
		List<Table.Cell> empties = new ArrayList<Table.Cell>();
		Iterator<Cell> iterator = this.iterator();
		if ( getCell(1, 1).isEmpty() )
			empties.add(getCell(1,1));
		while ( iterator.hasNext() ) {
			temp = iterator.next();
			if ( temp.isEmpty() )
				empties.add(temp);
		}
		return empties;
	}
	
	/**
	 * The 8 neighboring cells of the given cell.
	 * @param cell - defining the neighborhood 
	 * @return list of Table.Cell
	 */
	public java.util.List<Table.Cell> getNeighbors(Table.Cell cell) {
		List<Table.Cell> neighbours = new ArrayList<Table.Cell>();
		int row, column, flag = 3;
		row = cell.row - 1;
		column = cell.column - 2;
		while ( flag > 0) {
			column++;
			if ( boundsTest (row,column) ) {
				neighbours.add(getCell(row,column));
			}
			flag--;
		}
		row++; 
		if ( boundsTest (row,column) )
			neighbours.add(getCell(row,column));
		column = column - 2;
		if ( boundsTest (row,column) )
			neighbours.add(getCell(row,column));
		flag = 3;
		row++;
		while ( flag > 0) {
			if ( boundsTest (row,column) ) {
				neighbours.add(getCell(row,column));
			}
			column++;
			flag--;
		}
		return neighbours;
	}
	
	/**
	 * Get cell of given row and column.
	 * @param row - to set
	 * @param column - to set
	 * @return cell in given position
	 */
	public Table.Cell getCell(int row, int column) {
		return table[row][column];
	}
	
	/**
	 * Returns a string equivalent of a table.
	 * @return string - of table
	 */
	public java.lang.String toString() {
		String duck = "";
		for ( int i = board ; i > 0 ; i-- ) {
			for ( int j = board ; j > 0 ; j-- ) {
				duck = duck + table[i][j].toString() + "\n";
			}
		}
		return duck;
	}
	
	/**
	 * Hashes the table.
	 * @return hashed table
	 */
	public int hashCode() {
        final int prime = 13;
        int result = 1;
		for ( int i = board ; i > 0 ; i-- ) {
			for ( int j = board ; j > 0 ; j-- ) {
				result = (prime * result) + table[i][j].hashCode();
			}
		}
        return result;
	}
	
	/**
	 * Tests of a certain table equals to an obj.
	 * @return true if same, false otherwise
	 */
	public boolean equals(java.lang.Object obj) {
		if ( obj == this )
			return true;
		if((obj == null) || (obj.getClass() != this.getClass())) {
	        return false;
		}
		Table taeble = (Table) obj;
		if ( this.board == taeble.board ) {
			for ( int i = board ; i > 0 ; i -- ) {
				for ( int j = board ; j > 0 ; j-- ) {
					if ( !table[i][j].equals(taeble.table[i][j]) )
						return false;
				}
			}
			return true;
		}
		else 
			return false;
	}
}









