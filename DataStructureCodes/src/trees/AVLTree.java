package trees;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * This class is AVLTree, a type of balanced binary search tree.
 * The height of the tree is balanced by rotation when an element is added or deleted.
 * 
 * Author: Jonggi Hong
 * Last update: 04/15/2017
 */
public class AVLTree<K extends Comparable<K>, V> {
	AVLTree<K, V> left, right;
	K key;
	V value;
	
	public AVLTree(){};
	
	public AVLTree(K key, V value, AVLTree<K, V> left, AVLTree<K, V> right){
		this.key = key;
		this.value = value;
		this.left = left;
		this.right = right;
	}
	
	public void put(K keyIn, V valueIn) {
		int compare = key.compareTo(keyIn);
		
		if (compare < 0) {
			if (right == null) {
				right = new AVLTree<K, V>(keyIn, valueIn, null, null);
			} else {
				right.put(keyIn, valueIn);
			}
			retrace();
		} else if (compare > 0) {
			if (left == null) {
				left = new AVLTree<K, V>(keyIn, valueIn, null, null);
			} else {
				left.put(keyIn, valueIn);
			}
			retrace();
		} else {
			value = valueIn;
		}
	}
	
	public void retrace(){
		int diff = getBalanceFactor();
		
		//when left is higher than right
		if (diff > 1) {
			int leftDiff = left.getBalanceFactor();
			if (leftDiff >= 0) {
				rrRotation();
			} else {
				lrRotation();
			}
		} else if (diff < -1) {
			int rightDiff = right.getBalanceFactor();
			if (rightDiff <= 0) {
				llRotation();
			} else {
				rlRotation();
			}
		}
	}
	
	public int getBalanceFactor(){
		int leftHeight = 0, rightHeight = 0;
		if (left != null) leftHeight = left.height();
		if (right != null) rightHeight = right.height();
		
		return leftHeight - rightHeight;
	}
	
	public void llRotation(){
		AVLTree<K, V> oldRight = right;
		AVLTree<K, V> oldLeft = left;
		AVLTree<K, V> oldRightRight = right.right;
		AVLTree<K, V> oldRightLeft = right.left;
		
		right = oldRightRight;
		left = new AVLTree<K, V>(key, value, oldLeft, oldRightLeft);
		key = oldRight.key;
		value = oldRight.value;
	}
	
	public void rrRotation(){
		AVLTree<K, V> oldRight = right;
		AVLTree<K, V> oldLeft = left;
		AVLTree<K, V> oldLeftLeft = left.left;
		AVLTree<K, V> oldLeftRight = left.right;
		
		left = oldLeftLeft;
		right = new AVLTree<K, V>(key, value, oldLeftRight, oldRight);
		key = oldLeft.key;
		value = oldLeft.value;
	}
	
	public void lrRotation(){
		left.llRotation();
		rrRotation();
	}
	
	public void rlRotation(){
		right.rrRotation();
		llRotation();
	}
	
	public int height(){
		int leftHeight = 0, rightHeight = 0;
		if (left != null) leftHeight = left.height();
		if (right != null) rightHeight = right.height();
		
		return Math.max(leftHeight, rightHeight) + 1;
	}
	
	public void remove(K keyIn){
		
	}
	
	public int size(){
		int size = 1;
		if (left != null) size += left.size();
		if (right != null) size += right.size();
		return size;
	}
	
	public V getValueForKey(K keyIn){
		int compare = key.compareTo(keyIn);
		
		if (compare < 0) {
			if (right == null) return null;
			return right.getValueForKey(keyIn);
		} else if (compare > 0) {
			if (left == null) return null;
			return left.getValueForKey(keyIn);
		} else {
			return value;
		}
	}
	
	public K getMax(){
		if (right != null) return right.getMax();
		else return key;
	}
	
	public K getMin(){
		if (left != null) return left.getMin();
		else return key;
	}
	
	public int leftSpace(){
		if (left == null) return 1; 
		return left.leftSpace() + 1;
	}
	
	public void printTreeHelper(HashMap<Integer, String> levelStrings, int i){
		if (levelStrings.containsKey(i))
			levelStrings.put(i, levelStrings.get(i) + "\t" + key);
		else
			levelStrings.put(i, key.toString());
		
		if (left != null) left.printTreeHelper(levelStrings, i+1);
		if (right != null) right.printTreeHelper(levelStrings, i+1);
	}
	
	public void printTree(){
		HashMap<Integer, String> levelStrings = new HashMap<Integer, String>();
		printTreeHelper(levelStrings, 0);
		
		for(int i=0; i<height(); i++){
			System.out.println(levelStrings.get(i));
		}
	}
}
