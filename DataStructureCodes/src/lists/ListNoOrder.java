package lists;

import java.lang.IndexOutOfBoundsException;


/*
 * This class is a singly linked list. This list stores data sequentially, not in order.
 * 
 * Author: Jonggi Hong
 * Data: 04/15/2017
 */

public class ListNoOrder<T extends Comparable<T>>
		implements Comparable<ListNoOrder<T>> {

	class Node<T> {
		T data;
		Node<T> next = null;
		
		public Node(T d){
			data = d;
		}
	}
	
	Node<T> head = null;
	Node<T> tail = null;
	int count = 0;
	
	public ListNoOrder() {
		
	}

	public void add(T newElt) {
		if (newElt == null) throw new IllegalArgumentException();
		
		Node<T> newNode = new Node<T>(newElt);
		if (head == null) {
			head = newNode;
			tail = newNode;
		} else {
			tail.next = newNode;
			tail = newNode;
		}
		count++;
	}

	public int length() {
		return count;
	}

	public String toString() {
		if (head == null) return "";

		String result = head.data.toString();
		Node<T> currNode = head.next;
		while(currNode != null){
			result += ", " + currNode.data.toString();
			currNode = currNode.next;
		}
		
		return result;
	}

	public void reset() {
		head = null;
		tail = null;
		count = 0;
	}

	public int numOccurrencesOfElement(T element) {
		if (element == null) throw new IllegalArgumentException();
		
		int numElement = 0;
		Node<T> currNode = head;
		
		while(currNode != null) {
			if (currNode.data.compareTo(element) == 0) numElement++;
			currNode = currNode.next;
		}
		return numElement;
	}

	public int positionOfElement(T element) {
		if (element == null) throw new IllegalArgumentException();
		
		Node<T> currNode = head;
		int index = 0;
		
		while(currNode != null) {
			if (currNode.data.compareTo(element) == 0) return index;
			currNode = currNode.next;
			index++;
		}
		return -1;
	}

	public T elementAtPosition(int position) throws IndexOutOfBoundsException {
		if (position > count - 1 || position < 0) throw new IndexOutOfBoundsException();
		int index = 0;
		Node<T> currNode = head;
		
		while(index < position){
			currNode = currNode.next;
			index++;
		}
		return currNode.data;
	}

	public ListNoOrder<T> elementsAfter(T element) {
		if (element == null) throw new IllegalArgumentException();
		
		ListNoOrder<T> newList = new ListNoOrder<T>();
		Node<T> currNode = head;
		
		while(currNode != null && currNode.data.compareTo(element) != 0){
			currNode = currNode.next;
		}
		
		if (currNode != null) {
			currNode = currNode.next;
			while(currNode != null) {
				newList.add(currNode.data);
				currNode = currNode.next;
			}
		}
		
		return newList;
	}

	public int distanceBetween(T element1, T element2) {
		if (element1 == null || element2 == null) throw new IllegalArgumentException();
		
		Node<T> currNode = head;
		int distance = 0;
		
		while(currNode != null && currNode.data.compareTo(element1) != 0) {
			currNode = currNode.next;
		}
		
		if (currNode == null) return -1;
		while(currNode != null && currNode.data.compareTo(element2) != 0) {
			currNode = currNode.next;
			distance++;
		}
		if (currNode == null) return -1;
		
		return distance;
	}

	public void removeElementAtPosition(int position)
			throws IndexOutOfBoundsException {
		if (position > count - 1 || position < 0) throw new IndexOutOfBoundsException();
		int index = 0;
		Node<T> currNode = head;
		Node<T> prevNode = null;
		
		while(index < position){
			prevNode = currNode;
			currNode = currNode.next;
			index++;
		}
		
		if (position == 0) head = currNode.next;
		if (position == count - 1) tail = prevNode;
		if (prevNode != null) prevNode.next = currNode.next;
		
		count--;
	}

	public int compareTo(ListNoOrder<T> otherList) {
		if (otherList == null) throw new IllegalArgumentException();
		
		Node<T> thisNode = head;
		Node<T> otherNode = otherList.head;
		int compare = 0;
		
		while(thisNode != null && otherNode != null && compare != 0) {
			compare = thisNode.data.compareTo(otherNode.data);
			
			thisNode = thisNode.next;
			otherNode = otherNode.next;
		}
		
		if (compare == 0) {
			if (count < otherList.length()) compare = -1;
			else if (count > otherList.length()) compare = 1;
		}
		
		return compare;
	}

}
