package tests;

import static org.junit.Assert.*;

import org.junit.Test;
import trees.*;

public class TreeTests {

	@Test
	public void avlInsertionTest() {
		AVLTree<Integer, String> avlTree = new AVLTree<Integer, String>(10, "Ten", null, null); 
		assertEquals(1, avlTree.size());
		
		avlTree.put(11, "");
		avlTree.put(12, "");
		avlTree.put(13, "");
		avlTree.put(14, "");
		avlTree.put(15, "");
		avlTree.printTree();
	}

	@Test
	public void avlBalanceTest(){
		AVLTree<Integer, String> avlTree = new AVLTree<Integer, String>(10, "Ten", null, null); 
		avlTree.put(11, "");
		avlTree.put(12, "");
		avlTree.put(13, "");
		avlTree.put(14, "");
		avlTree.put(15, "");
		assertEquals(3, avlTree.height());
	}

}
