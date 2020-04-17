package wwwordz.puzzle;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Locale;

import wwwordz.puzzle.Trie.Search;

import java.io.BufferedReader;
import java.io.*; 

/**
 * An organized collection of words, optimized for searching them.
 * This class is a singleton, meaning that there is, at most, a single instance of this class per application.
 * This dictionary uses a collection of Portuguese words loaded as a resource from a file in this package.
 * It is backed by a Trie to index words and speedup searches.  
 * @author José Paulo Leal
 *
 */
public class Dictionary extends java.lang.Object {
	private static Dictionary dictionary = null;
	Trie dicTrie = new Trie();

	/**
	 * Auxiliary function to read from the original file to the trie.
	 */
	private Dictionary() { 	
		try {	
			final String DIC_FILE = "wwwordz/puzzle/dirty.dic";
			InputStream input = ClassLoader.getSystemResourceAsStream(DIC_FILE);
			BufferedReader reader = new BufferedReader(new InputStreamReader(input,"UTF-8"));
						
			String line;
			String newline;
			
			reader.readLine(); 
			while((line = reader.readLine()) != null) {
				String[] temp = line.split("/|\\s");
				newline = temp[0];
				newline = Normalizer.normalize(newline.toUpperCase(Locale.ENGLISH),Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
				
				dicTrie.put(newline);
			}
			reader.close();
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Obtain the sole instance of this class. Multiple invocations will receive the exact same instance.
	 * @return singleton instance
	 */
	public static Dictionary getInstance() {
		if (dictionary == null)
			return dictionary = new Dictionary();
		else
			return dictionary;
	}

	/**
	 * Start a dictionary search.
	 * @return search
	 */
	public Trie.Search startSearch() {
		Search search = dicTrie.startSearch();
		return search;
	}
	
	/**
	 * Return a large random word from the trie.
	 * @return large word
	 */
	public java.lang.String getRandomLargeWord() {
		//INCOMPLETE
		String string = dicTrie.getRandomLargeWord();
		return string;
	} 
}
