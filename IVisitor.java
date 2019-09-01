/**
 * IVisitor generates scores for the most meaningful words across songs and evaluates
 * scores of words within song
 *
 * @author Ruijie Cao, Ningke Hu, Tamara Prabhakar, Sanjana Prakash, Lingyi You
 * @version April 23, 2019
 */
public interface IVisitor {
	/**
     * visit all of the songs in song base and generate scores for meaningful words
     * @param s Song to be visited
     */
	public void visit(Song s); 
}
