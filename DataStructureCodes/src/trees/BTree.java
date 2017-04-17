package trees;

import java.util.ArrayList;

/*
 * This class is BTree.
 * 
 * Author: Jonggi Hong
 * Last update: 04/15/2017
 */

public class BTree<K extends Comparable<K>> {
	public static int MIN_KEYS = 3;
	
	// A node with list of values and subtrees
	class Node{
		ArrayList<K> values = new ArrayList<K>();
		ArrayList<Node> subTrees = new ArrayList<Node>();
		
		public void add(K data){
			int position = -1;
			for(int i=0; i<values.size(); i++){
				if (values.get(i).compareTo(data) > 0) {
					position = i;
				}
			}
			if (position == -1) position = values.size();
			
			if (subTrees.size() == 0) {
				insertValue(data, position);
			} else {
				Node nodeToAdd = subTrees.get(position);
				nodeToAdd.add(data);
				
				if (nodeToAdd.values.size() > MIN_KEYS * 2) {
					K upValue = nodeToAdd.values.remove(MIN_KEYS);
					insertValue(upValue, position);
					insertNode(nodeToAdd.getSecondHalf(), position);
					insertNode(nodeToAdd.getFirstHalf(), position);
				}
			}
		}
		
		public void remove(K data){
			int vSize = values.size();
			for(int i=0; i<=vSize; i++){
				if (i == vSize) {
					Node subTreeToRemove = subTrees.get(i);
					subTreeToRemove.remove(data);
					
					if(subTreeToRemove.values.size() < MIN_KEYS) {
						Node sibling = subTrees.get(i-1);
						if (sibling.values.size() > MIN_KEYS) {
							subTreeToRemove.values.add(values.remove(i));
							insertValue(sibling.values.remove(sibling.values.size()-1), i);
						} else {
							Node mergedNode = mergeNodes(sibling, values.remove(vSize-1), subTreeToRemove);
							subTrees.remove(vSize-1);
							subTrees.remove(vSize-1);
							subTrees.add(mergedNode);
						}
					}
					break;
				} else if (data.compareTo(values.get(i)) < 0) {
					Node subTreeToRemove = subTrees.get(i);
					subTreeToRemove.remove(data);
					
					if(subTreeToRemove.values.size() < MIN_KEYS) {
						Node sibling = subTrees.get(i+1);
						if (sibling.values.size() > MIN_KEYS) {
							subTreeToRemove.values.add(values.remove(i));
							insertValue(sibling.values.remove(0), i);
						} else {
							Node mergedNode = mergeNodes(subTreeToRemove, values.remove(i), sibling);
							subTrees.remove(i);
							subTrees.remove(i);
							subTrees.add(mergedNode);
						}
					}
					break;
				} else if (data.compareTo(values.get(i))==0) {
					if (subTrees.size() == 0){
						values.remove(i);
					} else {
						if (subTrees.get(i).values.size() > MIN_KEYS){
							ArrayList<K> subTreeValues = subTrees.get(i).values;
							values.set(i, subTreeValues.remove(subTreeValues.size()));
						} else if (subTrees.get(i+1).values.size() > MIN_KEYS){
							ArrayList<K> subTreeValues = subTrees.get(i).values;
							values.set(i, subTreeValues.remove(0));
						} else {
							Node n1 = subTrees.remove(i);
							Node n2 = subTrees.remove(i);
							insertNode(mergeNodes(n1, values.remove(i), n2), i);
						}
					}
					break;
				}
			}
		}
		
		// insert data at position
		public void insertValue(K data, int position){
			ArrayList<K> newValues = new ArrayList<K>();
			for(int i=0; i<position; i++){
				newValues.add(values.get(i));
			}
			newValues.add(data);
			for(int i=position; i<values.size(); i++){
				newValues.add(values.get(i));
			}
			values = newValues;
		}
		
		public void insertNode(Node n, int position){
			ArrayList<Node> newTrees = new ArrayList<Node>();
			for(int i=0; i<position; i++){
				newTrees.add(subTrees.get(i));
			}
			newTrees.add(n);
			for(int i=position; i<values.size(); i++){
				newTrees.add(subTrees.get(i));
			}
			subTrees = newTrees;
		}
		
		public Node getFirstHalf(){
			Node n = new Node();
			ArrayList<K> newValues = new ArrayList<K>();
			ArrayList<Node> newSubTrees = new ArrayList<Node>();
			
			for(int i=0; i<values.size()/2; i++){
				newValues.add(values.get(i));
			}
			for(int i=0; i<subTrees.size()/2; i++){
				newSubTrees.add(subTrees.get(i));
			}
			n.values = newValues;
			n.subTrees = newSubTrees;
			return n;
		}
		
		public Node getSecondHalf(){
			Node n = new Node();
			ArrayList<K> newValues = new ArrayList<K>();
			ArrayList<Node> newSubTrees = new ArrayList<Node>();
			
			for(int i=values.size()/2; i<values.size(); i++){
				newValues.add(values.get(i));
			}
			for(int i=values.size()/2; i<subTrees.size(); i++){
				newSubTrees.add(subTrees.get(i));
			}
			n.values = newValues;
			n.subTrees = newSubTrees;
			return n;
		}
	}
	
	Node root = new Node();
	
	public void add(K data){
		root.add(data);
		
		if (root.values.size() > MIN_KEYS * 2) {
			K upValue = root.values.remove(MIN_KEYS);
			
			Node n = new Node();
			n.values.add(upValue);
			n.subTrees.add(root.getFirstHalf());
			n.subTrees.add(root.getSecondHalf());
			root = n;
		}
	}
	
	public void remove(K data){
		root.remove(data);
		ArrayList<Node> rootSub = root.subTrees;
		if (rootSub.size() == 2 && rootSub.get(0).values.size() == MIN_KEYS
				&& rootSub.get(1).values.size() == MIN_KEYS) {
			root = mergeNodes(rootSub.get(0), root.values.get(0), rootSub.get(1));
		}
	}
	
	public Node mergeNodes(Node n1, K v, Node n2){
		Node newNode = new Node();
		
		for(K value: n1.values) newNode.values.add(value);
		newNode.values.add(v);
		for(K value: n2.values) newNode.values.add(value);
		
		for(Node n: n1.subTrees) newNode.subTrees.add(n);
		for(Node n: n2.subTrees) newNode.subTrees.add(n);
		return newNode;
	}
}
