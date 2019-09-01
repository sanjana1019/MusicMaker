
/**
 * User interface to take the user's desire genre input
 * and generates a new song and list of references to the used songs 
 *
 * @author Ruijie Cao, Ningke Hu, Tamara Prabhakar, Sanjana Prakash, Lingyi You
 * @version April 23, 2019
 */
public interface IUser {
	
	/**
     * Takes a user's desired genre for a song
     *  and generates a song to match the user's input
     * @param genre user inputs
     * @return a song of new lyrics
     */
    public String generateSong(String genre);
    
	/**
     * Gets the references to the songs used to generate new song
     * @return list of song titles 
     */
    public String getReference();
    
}
