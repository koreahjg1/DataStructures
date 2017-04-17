package lists;

/*
 * This is an interface of HashLists implemented in this package. There are three basic methods
 * that should be implemented in classes implementing HashLists.
 * 
 * Author: Jonggi Hong
 * Last update: 04/16/2017
 */
public interface HashTable {
	
	public void insert(Object keyIn, Object data);
	public void delete(Object keyIn);
	public Object getValueForKey(Object keyIn);
	public int size();
}
