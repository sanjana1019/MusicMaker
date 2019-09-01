import static org.junit.Assert.*;

import org.junit.Test;

/**
 * IDFVisitor test for test methods in IDFVisitor
 *
 * @author Ruijie Cao, Ningke Hu, Tamara Prabhakar, Sanjana Prakash, Lingyi You
 * @version April 23, 2019
 */
public class IDFVisitorTest {
	
	/**
	 * test visit method for one song
	 */
	@Test
	public void testVisit() {
		Classifier c = new Classifier();
		c.scan("src/testIDF.csv");
		IVisitor v1 = new TFVisitor();
		IVisitor v2 = new IDFVisitor();
		
		for (Song s : c.getBase()) {
			s.accept(v1);
			s.accept(v2);
		}

		assertEquals((Integer)1, Song.words.get("yesterday"));
	}

}
