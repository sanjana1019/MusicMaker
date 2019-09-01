import static org.junit.Assert.*;

import org.junit.Test;

/**
 * TFVisitor test for test methods in TFVisitor class
 *
 * @author Ruijie Cao, Ningke Hu, Tamara Prabhakar, Sanjana Prakash, Lingyi You
 * @version April 23, 2019
 */
public class TFVisitorTest {

	/**
	 * test visit method
	 */
	@Test
	public void testVisit() {
		Classifier c = new Classifier();
		c.scan("src/testTF.csv");
		Song s = c.getBase().get(0);
		s.getLyrics().add(" ");
		IVisitor v = new TFVisitor();
		s.accept(v);
		assertEquals(132, s.getTotalNumOfWords());
	}

}
