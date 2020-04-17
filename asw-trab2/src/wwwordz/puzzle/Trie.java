package wwwordz.puzzle;

import java.util.HashMap;
import java.lang.Character;

/**
 * A trie is data structure to store words efficiently using a tree. 
 * Each tree node represents prefix that may be a a complete word. The descendants of a node are indexed by a letter, representing a possible continuation of that prefix followed by that letter.
 * @author José Paulo Leal
 *
 */
public class Trie extends java.lang.Object implements java.lang.Iterable<java.lang.String> {
	Node root = new Node();

	/**
	 * Empty constructor.
	 */
	public Trie() {}
	
	/**
	 * Nested Class Node
	 * Represents a single node of the trie
	 *
	 */
	public class Node {
		HashMap<Character,Node> nodeMap;
		boolean complete;
		
		/**
		 * Constructor to initialize node fields.
		 */
		Node() {
			complete = false;
			nodeMap = new HashMap<Character,Node>();
		}
	}
	

	/**
	 * Nested Class 1 - Iterator
	 * Iterator over strings stored in the internal node structure It traverses the node tree depth first, using coroutine with threads, and collects all possible words in no particular order. 
	 * An instance of this class is returned by iterator().
	 *
	 */
	public class NodeIterator extends java.lang.Object implements java.util.Iterator<java.lang.String>, java.lang.Runnable {
		java.lang.Thread thread;
		boolean terminated;
		java.lang.String nextWord;
		
		NodeIterator() {};
		
		/**
		 * Runnable.
		 */
		public void run() {
			//INCOMPLETE
		}
		
		/**
		 * Checks if there is a node following the current one. 
		 * @return true if there is, false if not
		 */
		public boolean hasNext() {
			//INCOMPLETE
			return false;
		}
		
		/**
		 *
		 */
		public java.lang.String next() {
			//INCOMPLETE
			String temp = new String();
			return temp;
		}
	}
	/**
	 * 
	 */
	
	/**
	 * Nested class Search.
	 * A position in the node structure when looking for a word. A word can be searched giving successive words.
	 *
	 */
	public static class Search extends java.lang.Object {
		wwwordz.puzzle.Trie.Node node;
		
		/**
		 * Create a search starting in given node.
		 * @param node - prefix already searched
		 */
		public Search(wwwordz.puzzle.Trie.Node node) {
			this.node = node;
		}
		
		/**
		 * Create a clone of the given search, with the same fields.
		 * @param search - to be cloned
		 */
		public Search(Trie.Search search) {
			this.node = search.node;
		}
		
		/**
		 * Check if the search can continue with the given letter. Internal node is updated if the search is valid.
		 * @param letter - to continue search
		 * @return true if letter found, false otherwise
		 */
		boolean continueWith(char letter) {
			if ( node.nodeMap == null || node.nodeMap.isEmpty() )
				return false;
			else if ( node.nodeMap.containsKey(letter) ){
				node = node.nodeMap.get(letter);
				return true;
			}
			else
				return false;
		}
		
		/**
		 * Check if characters searched so far correspond to a word.
		 * @return true if node is a complete word, false otherwise
		 */
		boolean isWord() {
			return node.complete;
		}
	}
	
	/**
	 * 
	 */
	
	/**
	 * Insert a word in the structure, starting from the root. 
	 * @param word - to be inserted
	 */
	public void put(java.lang.String word) {
		Node temp = root;
		Character ch;
		while ( true ) {
			if ( word != null && word.length() !=0 ) {
				ch = word.charAt(0);
				word = word.substring(1);
				if ( temp.nodeMap == null || temp.nodeMap.isEmpty() || !temp.nodeMap.containsKey(ch) ){
					Node newNode = new Node();
					temp.nodeMap.put(ch,newNode);
					temp = newNode;
				}				
				else
					temp = temp.nodeMap.get(ch);	
			}
			else {
				temp.complete = true;
				break;
			}
		}
	}
	
	/**
	 * Start a word search from the root.
	 * @return search instance
	 */
	public Trie.Search startSearch() {
		Search search = new Search(this.root);
		return search;
	}
	
	/**
	 * Performs a random walk in the data structure, randomly selecting a path in each node, until reaching a leaf (a node with no descendants).
	 * @return word - as a String
	 */
	public java.lang.String getRandomLargeWord() {
		//INCOMPLETE
		String temp = new String();
		return temp;
	}
	
	/**
	 * Returns an iterator over the strings stored in the trie.
	 * @return iterator instance
	 */
	public java.util.Iterator<java.lang.String> iterator() {
		return new NodeIterator();
	}
}