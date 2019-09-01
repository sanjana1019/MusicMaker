import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

/**
 * GenreBase test for test methods in GenreBase
 *
 * @author Ruijie Cao, Ningke Hu, Tamara Prabhakar, Sanjana Prakash, Lingyi You
 * @version April 23, 2019
 */
public class GenreBaseTest {

	//Constructor
	GenreBase base = new GenreBase("love"); 
	
	/**
	 * test add method to add a concrete song in genre base
	 */
	@Test
	public void addTest() {
		GenreBase love = new GenreBase("love"); 
		ArrayList<String> lyrics = new ArrayList<String>();
		lyrics.add("Dreams are like angels, They keep bad at bay, Love is the light, Scaring darkness away yeah, I'm so in love with you, Make love your goal, The power of love, A force from above, Cleaning my soul, Flame on burnt desire, Love with tongues of fire, Purge the soul, Make love your goal, This time we go sublime, Lovers entwine-divine divine, Love is danger love is pleasure, Love is pure - the only treasure, I'm so in love with you, Make love your goal, Flame on burnt desire, Love with tongues of fire, Purge the soul, Make love your goal");
		Song loveSong = new Song("The Power of Love", lyrics); 
		assertTrue(love.addSong(loveSong));
	}
	
	/**
	 * test add method to add null in genre base
	 */
	@Test
	public void addTest2() {
		GenreBase love = new GenreBase("love"); 
		Song loveSong = null; 
		assertFalse(love.addSong(loveSong));
	}
	
	/**
	 * test getGenre method
	 */
	@Test
	public void getGenreTest() {
		GenreBase love = new GenreBase("love"); 
		assertEquals("love", love.getGenre());
	}
	
	/**
	 * test setGenre method
	 */
	@Test
	public void setGenreTest() {
		GenreBase love = new GenreBase("love"); 
		love.setGenre("angst");
		assertEquals("angst", love.getGenre());	
	}
	
	/**
	 * test getSongbase method
	 */
	@Test
	public void getSongbaseTest() {
		GenreBase love = new GenreBase("love"); 
		ArrayList<String> lyrics = new ArrayList<String>();
		lyrics.add("Dreams are like angels, They keep bad at bay, Love is the light, Scaring darkness away yeah, I'm so in love with you, Make love your goal, The power of love, A force from above, Cleaning my soul, Flame on burnt desire, Love with tongues of fire, Purge the soul, Make love your goal, This time we go sublime, Lovers entwine-divine divine, Love is danger love is pleasure, Love is pure - the only treasure, I'm so in love with you, Make love your goal, Flame on burnt desire, Love with tongues of fire, Purge the soul, Make love your goal");
		Song loveSong = new Song("The Power of Love", lyrics); 
		love.addSong(loveSong);
		assertEquals(1, love.getSongbase().size());	

	}
	
	/**
	 * test setSongbase method
	 */
	@Test
	public void setSongbaseTest() {
		GenreBase love = new GenreBase("love"); 
		ArrayList<String> lyrics = new ArrayList<String>();
		lyrics.add("Dreams are like angels, They keep bad at bay, Love is the light, Scaring darkness away yeah, I'm so in love with you, Make love your goal, The power of love, A force from above, Cleaning my soul, Flame on burnt desire, Love with tongues of fire, Purge the soul, Make love your goal, This time we go sublime, Lovers entwine-divine divine, Love is danger love is pleasure, Love is pure - the only treasure, I'm so in love with you, Make love your goal, Flame on burnt desire, Love with tongues of fire, Purge the soul, Make love your goal");
		Song loveSong = new Song("The Power of Love", lyrics); 
		love.addSong(loveSong);
		GenreBase angst = new GenreBase("angst"); 
		angst.setSongbase(love.songbase);
		assertEquals(1, angst.getSongbase().size());	
	}

}
