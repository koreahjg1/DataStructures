package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import strings.*;

public class StringMatchingTests {
	@Test
	public void LZWEncodingDecodingTest(){
		LZWEncoding lzw = new LZWEncoding();
		String encodedString = lzw.encode("lzw encoding test by junit test");
		
		assertEquals("12 26 23 0 5 14 3 15 4 9 14 7 0 20 5 19 20 0 2 25 0 10 21 14 9 43 40 42", encodedString);
		assertEquals("lzw encoding test by junit test", lzw.decode(encodedString));
	}
	

	@Test
	public void KMPTest(){
		KMPMatching kmp = new KMPMatching();

		assertEquals("[0, 12]",kmp.findMatches("AAAABAAAABABAAAABAABAAAAB", "AAAABAA").toString());
	}
}
