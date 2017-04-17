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

	@Test
	public void kdTreeTest(){
		KDTree kdTree = new KDTree(new int[]{10, 20, 30}, 0);
		kdTree.add(new int[]{11, 21, 31});
		kdTree.add(new int[]{12, 22, 32});
		kdTree.add(new int[]{22, 32, 42});
		assertEquals(4, kdTree.size());
		
		kdTree.delete(new int[]{12, 22, 32});
		assertEquals(3, kdTree.size());
	}
	

	@Test
	public void prQuadtreeTest(){
		PRQuadtree prq = new PRQuadtree(1000, 0, 2000, 0);
		prq.add(new int[]{10, 10});
		prq.add(new int[]{900, 1400});
		prq.add(new int[]{882, 223});
		assertEquals(3, prq.size());
		
		prq.delete(new int[]{10, 10});
		assertEquals(2, prq.size());
	}
}
