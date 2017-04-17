package lists;

/*
 * This class is a hash table with linear probing method to resolve collision issue.
 * 
 * Author: Jonggi Hong
 * Last update: 04/15/2017
 */
public class LinearHashTable implements HashTable {
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
	
	public LinearHashTable(int capacity){
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
		Entity slot = table[index];
		while(slot != null){
			index = (index+1)%capacity;
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
		Entity slot = table[index];
		boolean found = false;
		
		while(slot != null){
			if (slot.key.equals(keyIn)) {
				table[index] = null;
				size--;
				found = true;
				break;
			}
			index = (index+1)%capacity;
			slot = table[index];
		}
		
		if (found){
			slot = table[index+1];
			while(slot != null){
				table[index] = table[index+1];
				
				index = (index+1)%capacity;
				slot = table[index+1];
			}
			table[index] = table[(index+1)%capacity];
		}
	}

	@Override
	public Object getValueForKey(Object keyIn) {
		// TODO Auto-generated method stub
		int h = keyIn.hashCode();
		
		int index = h%capacity;
		Entity slot = table[index];
		
		while(slot != null){
			if (slot.key.equals(keyIn)) {
				return slot.data;
			}
			index = (index+1)%capacity;
			slot = table[index];
		}
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}
	
	public void printTable(){
		for(int i=0; i<capacity; i++){
			if (table[i] == null) System.out.println(i + " : ");
			else System.out.println(i + " : " + table[i].data);
		}
	}
}
