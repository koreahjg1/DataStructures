package trees;

/*
 * This class is KDTree. This tree stores multidimensional data. In this class,
 * the data is from 3 dimensional space.
 * 
 * Author: Jonggi Hong
 * Last update: 04/16/2017
 */
public class KDTree {
	int[] value;
	int dim;
	KDTree left, right;
	
	public KDTree(int[] valueIn, int d){
		value = new int[3];
		value[0] = valueIn[0];
		value[1] = valueIn[1];
		value[2] = valueIn[2];
		dim = d;
	}
	
	public KDTree add(int[] valueIn){
		if (equalValues(valueIn)) return this;
			
		if (valueIn[dim] < value[dim]) {
			if (left == null) left = new KDTree(valueIn, (dim+1)%3);
			else left = left.add(valueIn);
		} else {
			if (right == null) right = new KDTree(valueIn, (dim+1)%3);
			else right = right.add(valueIn);
		}
		return this;
	}
	
	public int[] findMin(int d){
		if (d == dim){
			if (left != null) return left.findMin(d);
			else return value; 
		} else {
			int[] min = value;
			if (left != null) {
				int[] leftMin = left.findMin(d);
				if (leftMin[d] < min[d]) min = leftMin;
			}
			if (right != null) {
				int[] rightMin = right.findMin(d);
				if (rightMin[d] < min[d]) min = rightMin;
			}
			return min;
		}
	}
	
	public KDTree delete(int[] valueIn){
		if (equalValues(valueIn)) {
			if (right != null) {
				value = right.findMin(dim);
				right = right.delete(value);
			} else {
				return left;
			}
		} else if (valueIn[dim] < value[dim]){
			if (left != null) left.delete(valueIn);
		} else {
			if (right != null) right.delete(valueIn);
		}
		return this;
	}
	
	public int size(){
		int size = 1;
		if (left != null) size += left.size();
		if (right != null) size += right.size();
		return size;
	}
	
	public boolean equalValues(int[] valueIn){
		return value[0] == valueIn[0] && value[1] == valueIn[1] && value[2] == valueIn[2];
	}
}
