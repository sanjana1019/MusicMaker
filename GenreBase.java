import java.util.*;
/**
 * GenreBase stores songs based on its designated genre
 *
 * @author Ruijie Cao, Ningke Hu, Tamara Prabhakar, Sanjana Prakash, Lingyi You
 * @version April 23, 2019
 */
public class GenreBase implements IGenreBase{
	String genre;
	ArrayList<Song> songbase;
	/**
     * constructor
     * @param g genre
     */
	public GenreBase(String g) {
		genre = g;
		songbase = new ArrayList<>();
	}
	/**
     * Add each song to its designated genre base 
     * @param s song to be added to the genre base
     * @return true if song is added
     */
	public boolean addSong(Song s) {
		if(s == null) return false;
		
		songbase.add(s);
		return true;
	}
	/**
     * gets the genre of the base
     * @return the genre of the base
     */
	public String getGenre() {
		return genre;
	}
	/**
     * sets the genre of the base
     * @param genre new genre assigned to the base
     */
	public void setGenre(String genre) {
		this.genre = genre;
	}
	/**
     * gets a list of songs added to the genre base  
     * @return list of songs in the genre base
     */
	public ArrayList<Song> getSongbase() {
		return songbase;
	}
	/**
     * sets a list of songs to designated genre base 
     * @param songbase list of songs to be added to the genre base
     */
	public void setSongbase(ArrayList<Song> songbase) {
		this.songbase = songbase;
	}
	
	
}
