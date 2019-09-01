import java.util.HashMap;
/**
 * IDFVisitor calculates inverse document frequency for each word in every song
 *
 * @author Ruijie Cao, Ningke Hu, Tamara Prabhakar, Sanjana Prakash, Lingyi You
 * @version April 23, 2019
 */
public class IDFVisitor implements IVisitor{
	/**
     * visit all of the songs in song base and generate scores for meaningful words
     * @param s Song to be visited
     */
	public void visit(Song s) {
		HashMap<String, Integer> tf = s.getTermFreq();
		HashMap<String, Integer> idf = Song.getWords();
		for(String w: tf.keySet()) {
			int f = idf.getOrDefault(w, 0);
			idf.put(w, f+1);
		}
	}	
}
