
/**
 * GenreBase stores songs based on its designated genre
 *
 * @author Ruijie Cao, Ningke Hu, Tamara Prabhakar, Sanjana Prakash, Lingyi You
 * @version April 23, 2019
 */
public interface IGenreBase {
	/**
     * Add each song to its designated genre base 
     * @param s song to be added to the genre base
     * @return true if song is added
     * @throws IllegalStateException if repeat song is added
     */
	public boolean addSong(Song s);
}
