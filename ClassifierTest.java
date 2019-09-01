import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

/**
 * Classifier test for test methods in classifier
 *
 * @author Ruijie Cao, Ningke Hu, Tamara Prabhakar, Sanjana Prakash, Lingyi You
 * @version April 23, 2019
 */
public class ClassifierTest {
	
	/**
	 * test scan method to read a good file 
	 */
	@Test
	public void testScan() {
		Classifier c = new Classifier();
		c.scan("src/newlyrics.csv");
	}
	
	/**
	 * test scan method to read a bad file
	 */
	@Test
	public void testScan2() {
		Classifier c = new Classifier();
		c.scan("");		
	}
	
	/**
	 * test runTFIDF method to do tfidf algorithm for songs we generate
	 */
	@Test
	public void testRunTFIDF() {
		Classifier c = new Classifier();
		c.scan("src/newlyrics.csv");
		c.runTFIDF();
	}
	
	/**
	 * test getBase method to get base from classifier
	 */
	@Test
	public void testGetBase() {
		Classifier c = new Classifier();
		ArrayList<Song> base = new ArrayList<Song>();
		c.setBase(base);
		assertEquals(base, c.getBase());
	}
	
	/**
	 * test getTrainingSet method to get training set from classifier
	 */
	@Test
	public void testGetTrainingSet() {
		Classifier c = new Classifier();
		c.setTrainingSet(c.loadWords("love-words"));
		assertEquals(c.loadWords("love-words"), c.getTrainingSet());
	}
	
	/**
	 * test getTrainingSet method to get training set from classifier
	 */
	@Test
	public void testLoadWordsWithExcetion() {
		Classifier c = new Classifier();
		c.loadWords("");
	}
	
	/**
	 * test getGenreBases method to get genre bases from classifier
	 */
	@Test
	public void testGetGenreBases() {
		Classifier c = new Classifier();
		HashMap<String, GenreBase> genreBases = null;
		c.setGenreBases(genreBases);
		assertEquals(genreBases, c.getGenreBases());
	}
	
	/**
	 * test generateGenre method to generateGenre for all songs in classifier
	 */
	@Test
	public void testGenerateGenre() {
		Classifier c = new Classifier();
		c.scan("src/newlyrics3.csv");
		c.runTFIDF();
		c.generateGenre();
	}

}
