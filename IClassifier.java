
/**
 * Classifier organizes songs by genre and categorizes most important lyrics
 *
 * @author Ruijie Cao, Ningke Hu, Tamara Prabhakar, Sanjana Prakash, Lingyi You
 * @version April 23, 2019
 */
public interface IClassifier {
	/**
     * runs TFIDF on all song objects and generates scores for
     * most meaningful words
     */
	public void runTFIDF() ;
	/**
     * Read in the file of songs and store song objects with lyrics
     * @param filename name of the file to be scanned
     */
	public void scan(String filename) ; 
	/**
     * determines genre of the song and adds song to correct GenreBase
     */
	public void generateGenre();
}
