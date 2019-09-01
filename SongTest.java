import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Test;

/**
 * Song test for test methods in Song class
 *
 * @author Ruijie Cao, Ningke Hu, Tamara Prabhakar, Sanjana Prakash, Lingyi You
 * @version April 23, 2019
 */
public class SongTest {
	
	/**
	 * test isLove method for a song which belongs to love
	 */
	@Test
	public void testisLove() {
		Song song = new Song("random title");
		song.setTitle("tester");
		String[] words = {"also", "first", "fondly", "sensually", "doth", "always", "romantically", "amorously", "love", "gladly", "dreamer", "sometimes", "close", "each", "lovingly"}; 
		song.getEmotionScore();
		song.setEmotionScore(1.1);
		song.getTermFreq1();
		ArrayList<String> w = new ArrayList<>();
		for(String word: words) {
			w.add(word);
		}
		//set top words
		//convert array to array list 
		song.setTopWords(w);
		assertEquals(w, song.getTopwords());
		//determine if its a love song or not 
		song.setLove();
		//expected to be a love song 
		assertTrue(song.isLove()); 
	}
	
	/**
	 * test isLove method for a song which belongs to not love
	 */
	@Test
	public void testisNotLove() {
		Song song = new Song("random title");
		String[] words = {"rainy", "future", "painted", "evening", "last", "give", "sad", "runner", "sometimes", "little", "fool", "stars"}; 
		//set top words. convert array to arrayList 
		ArrayList<String> w = new ArrayList<>();
		for(String word: words) {
			w.add(word);
		}
		song.setLyrics(w);
		song.setTopWords(w);
		song.setLove();
		//not expected to be a love song
		assertFalse(song.isLove()); 
	}
	
	/**
	 * test determineGenre method for a song which belongs genre love
	 */
	@Test
	public void testDetermineGenreLove() {  //do for all the genres 
		Song song = new Song("random title"); 
		String[] words = {"also", "first", "fondly", "sensually", "doth", "always", "romantically", "amorously", "love", "gladly", "dreamer", "sometimes", "close", "each", "lovingly"};
		ArrayList<String> w = new ArrayList<>();
		for(String word: words) {
			w.add(word);
		}
		//set top words
		//convert array to array list 
		song.setTopWords(w);
		//determine and set the genre
		song.determineGenre();
		//get the genre 
		assertEquals("love", song.getGenre()); 		
	}
	
	/**
	 * test determineGenre method for a song which is not love
	 */
	@Test
	public void testDetermineGenre() {  //do for all the genres 
		Classifier c = new Classifier();
		c.scan("src/newlyrics.csv");
		c.runTFIDF();
		Song s = c.getBase().get(3);
		
		System.out.println(s.getTitle()); 
		ArrayList<String> w = s.getTopwords();
		w.toString();
		s.getLyrics().toString();
		//set top words
		//convert array to array list 
		s.setTopWords(w);
		s.determineGenre();
		assertFalse(s.isLove());
		//System.out.println(s.getGenre());
		//assertEquals("love", s.getGenre()); 		
	}
	
	/**
	 * test determineGenre method for a song which belongs genre angst
	 */
	@Test
	public void testDetermineGenre2() {  //do for all the genres 
		Classifier c = new Classifier();
		c.scan("src/newlyrics.csv");
		c.runTFIDF();
		Song s = c.getBase().get(400);
		System.out.println(s.getTitle()); 
		ArrayList<String> w = s.getTopwords();
		w.toString();
		s.getLyrics().toString();
		//set top words
		//convert array to array list 
		s.setTopWords(w);
		s.determineGenre();
		assertFalse(s.isLove());
		System.out.println(s.getGenre());
		assertEquals("angst", s.getGenre()); 		
	}
	
	/**
	 * test determineGenre method for a song which belongs to genre happy
	 */
	@Test
	public void testDetermineGenre3() {  //do for all the genres 
		Classifier c = new Classifier();
		c.scan("src/newlyrics.csv");
		c.runTFIDF();
		Song s = c.getBase().get(401);
		//System.out.println(s.getTitle()); 
		ArrayList<String> w = s.getTopwords();
		w.toString();
		s.getLyrics().toString();
		//set top words
		//convert array to array list 
		s.setTopWords(w);
		s.determineGenre();
		assertFalse(s.isLove());
		//System.out.println(s.getGenre());
		assertEquals("happy", s.getGenre()); 
	}
	
}
	
	
	
	
