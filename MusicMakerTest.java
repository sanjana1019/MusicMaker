import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Classifier test for test methods in classifier
 *
 * @author Ruijie Cao, Ningke Hu, Tamara Prabhakar, Sanjana Prakash, Lingyi You
 * @version April 23, 2019
 */
public class MusicMakerTest {
	static MusicMaker m;
	
	/**
	 * create a MusicMaker for all the test cases
	 */
	@BeforeClass
	public static void init() {
		m = new MusicMaker("src/newlyrics2.csv");
	}
	
	/**
	 * test for generating a happy song
	 */
	@Test
	public void testHappy() {
		m.generateSong("happy");
		m.getReference();
		m.printSong();
		m.referencelstReset();
	}
	
	/**
	 * test for generating a love song
	 */
	@Test
	public void testLove() {
		m.generateSong("love");
		assertNotNull(m.getReference());
	}
	
	/**
	 * test for generating an angst song
	 */
	@Test
	public void testAngst() {
		m.generateSong("angst");
		assertNotNull(m.getReference());
	}
	
	/**
	 * test for generating a rage song
	 */
	@Test
	public void testRage() {
		m.generateSong("rage");
		assertNotNull(m.getReference());
	}
	
	/**
	 * test for generating a melancholy song
	 */
	@Test
	public void testMelancholy() {
		m.generateSong("melancholy");
		assertNotNull(m.getReference());
	}
	
	/**
	 * test for generating a breakup song
	 */
	@Test
	public void testBreakup() {
		m.generateSong("breakup");
		assertNotNull(m.getReference());
	}
	
	/**
	 * test for generating a pain song
	 */
	@Test
	public void testPain() {
		m.generateSong("pain");
		assertNotNull(m.getReference());
	}
	
	/**
	 * test for generating a yearning song
	 */
	@Test
	public void testYearning() {
		m.generateSong("yearning");
		assertNotNull(m.getReference());
	}

}
