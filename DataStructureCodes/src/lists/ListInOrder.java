package lists;

import lists.ListNoOrder.Node;

/*
 * This class is a ordered linked list. This list stores data in order.
 * 
 * Author: Jonggi Hong
 * Data: 04/15/2017
 */

public class ListInOrder<T extends Comparable<T>> extends ListNoOrder<T> {

  /*
   * Implement (i.e., override) whatever methods from the superclass that
   * you find are necessary to have this list be sorted, *as well as* any
   * methods that would be more efficient if overridden in this subclass.
   */
	public void add(T newElt) {
		if (newElt == null) throw new IllegalArgumentException();
		
		Node<T> newNode = new Node<T>(newElt);
		Node<T> currNode = head;
		Node<T> prevNode = null;
		while(currNode != null && currNode.data.compareTo(newElt) <= 0) {
			prevNode = currNode;
			currNode = currNode.next;
		}
		
		if (prevNode != null) prevNode.next = newNode;
		else head = newNode;
		if (currNode == null) tail = newNode;
		
		newNode.next = currNode;
		count++;
	}
	
	public int numOccurrencesOfElement(T element) {
		if (element == null) throw new IllegalArgumentException();
		
		int numElement = 0;
		Node<T> currNode = head;
		
		while(currNode != null && currNode.data.compareTo(element) <= 0) {
			if (currNode.data.compareTo(element) == 0) numElement++;
			currNode = currNode.next;
		}
		return numElement;
	}
	
	public int distanceBetween(T element1, T element2) {
		if (element1 == null || element2 == null) throw new IllegalArgumentException();
		
		if (element1.compareTo(element2) > 0) return -1;
		return super.distanceBetween(element1, element2);
	}
	
	// This method returns a part of list from start (inclusive) to end (exclusive)
	public ListInOrder<T> subList(int start, int end){
		ListInOrder<T> newList = new ListInOrder<T>();
		if (length() == 0) return newList;
		
		int startIndex = start;
		int endIndex = end;
		if (startIndex < 0) startIndex = 0;
		if (endIndex > length()) endIndex = length();
		
		if(startIndex > endIndex) return null; 
		
		int index = 0;
		Node<T> curr = head; 
		while(index < startIndex) {
			curr = curr.next;
			index++;
		}
		
		while(index < endIndex){
			newList.add(curr.data);
			curr = curr.next;
			index++;
		}
		
		return newList;
	}
}
