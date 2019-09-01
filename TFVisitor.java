import java.util.*;
/**
 * TFVisitor calculates term frequency for each word in every song
 *
 * @author Ruijie Cao, Ningke Hu, Tamara Prabhakar, Sanjana Prakash, Lingyi You
 * @version April 23, 2019
 */
public class TFVisitor implements IVisitor{
	/**
     * visit all of the songs in song base and generate scores for meaningful words
     * @param s Song to be visited
     */
	public void visit(Song s) {
		ArrayList<String> lyrics = s.getLyrics();
		HashMap<String, Integer> tf = s.getTermFreq();
		int count = 0;
		
		for(String line: lyrics) {
			String tmp;
			tmp = line.replace("?","");
			tmp = tmp.replace(":","");
			tmp = tmp.replace(",","");
			tmp = tmp.replace(";","");
			tmp = tmp.replace("!","");
			tmp = tmp.replace("(","");
			tmp = tmp.replace(")","");
			tmp = tmp.replace("[","");
			tmp = tmp.replace("*","");
			tmp = tmp.replace("]","");
			tmp = tmp.replace("&","");
			tmp = tmp.replaceAll("\\d","");
			tmp = tmp.toLowerCase();
			String[] ws = tmp.split(" ");
			
			for(String w: ws) {
				
				int f = tf.getOrDefault(w, 0);
				tf.put(w, f+1);
				//System.out.println(w);
				count++;
			}
		}
		
		s.setTotalNumOfWords(count);
	}	
}
