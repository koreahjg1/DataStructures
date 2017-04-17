package lists;

import lists.LinearHashTable.Entity;

/*
 * This class is a hash table with quadratic hashing to resolve collision issue.
 * 
 * Author: Jonggi Hong
 * Last update: 04/16/2017
 */
public class QuadraticHashTable implements HashTable {
	int capacity;
	Entity[] table;
	int size = 0;
	
	class Entity{
		Object key;
		Object data;
		
		public Entity(Object k, Object d) {
			key = k;
			data = d;
		}
	}

	public QuadraticHashTable(int capacity){
		this.capacity = capacity;
		table = new Entity[capacity];
	}
	
	@Override
	public void insert(Object keyIn, Object data) {
		// TODO Auto-generated method stub
		if (size == capacity) return;
		
		int h = keyIn.hashCode();
		Entity e = new Entity(keyIn, data);
		
		int index = h%capacity;
		int reCompute = 0;
		Entity slot = table[index];
		while(slot != null && reCompute < capacity){
			reCompute++;
			index = getHashValue(keyIn, reCompute);
			slot = table[index];
		}
		table[index] = e;
		size++;
	}

	@Override
	public void delete(Object keyIn) {
		// TODO Auto-generated method stub
		int h = keyIn.hashCode();
		
		int index = h%capacity;
		int reCompute = 0;
		Entity slot = table[index];
		boolean found = false;
		
		while(slot != null && reCompute < capacity){
			if (slot.key.equals(keyIn)) {
				table[index] = null;
				size--;
				found = true;
				break;
			}
			
			reCompute++;
			index = getHashValue(keyIn, reCompute);
			slot = table[index];
		}
		
		if (found){
			int nextIndex = getHashValue(keyIn, reCompute+1);
			while(table[nextIndex] != null){
				table[index] = table[nextIndex];
				
				index = nextIndex;
				slot = table[nextIndex];
				
				reCompute++;
				nextIndex = getHashValue(keyIn, reCompute);
			}
			table[index] = table[nextIndex];
		}
	}

	@Override
	public Object getValueForKey(Object keyIn) {
		// TODO Auto-generated method stub
		int h = keyIn.hashCode();
		
		int index = h%capacity;
		int reCompute = 0;
		
		while(table[index] != null && reCompute < capacity){
			if (table[index].key.equals(keyIn)) {
				return table[index].data;
			}
			
			reCompute++;
			index = getHashValue(keyIn, reCompute);
		}
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}
	
	public int getHashValue(Object keyIn, int i){
		return (keyIn.hashCode() + i*i)%capacity;
	}

	public void printTable(){
		for(int i=0; i<capacity; i++){
			if (table[i] == null) System.out.println(i + " : ");
			else System.out.println(i + " : " + table[i].data);
		}
	}
}
