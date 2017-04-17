package tests;

import static org.junit.Assert.*;
import lists.*;

import org.junit.Test;

public class HashTests {

	@Test
	public void linearHashTest() {
		LinearHashTable lht = new LinearHashTable(10);
		lht.insert(1, "a");
		lht.insert(11, "b");
		lht.insert(21, "c");
		lht.insert(31, "d");
		lht.insert(41, "e");
		
		lht.delete(21);

		assertEquals(4, lht.size());
		assertEquals("d", lht.getValueForKey(31));
	}
	
	@Test
	public void quadraticHashTest() {
		QuadraticHashTable qht = new QuadraticHashTable(10);
		qht.insert(1, "a");
		qht.insert(11, "b");
		qht.insert(21, "c");
		qht.insert(31, "d");
		qht.insert(41, "e");
		assertEquals(5, qht.size());
		
		qht.delete(21);

		assertEquals(4, qht.size());
		assertEquals("d", qht.getValueForKey(31));
	}
}
