package trees;

/*
 * This class is PR Quadtree. This tree stores multidimensional data. 
 * In PR Quadtree, data are points which are from 2D space.
 * 
 * Author: Jonggi Hong
 * Last update: 04/16/2017
 */
public class PRQuadtree {
	int[] value;
	PRQuadtree hh, hl, ll, lh;
	int xMax, xMin, yMax, yMin;
	boolean leaf = true;
	
	public PRQuadtree(int xMax, int xMin, int yMax, int yMin){
		this.xMax = xMax;
		this.xMin = xMin;
		this.yMax = yMax;
		this.yMin = yMin;
	}
	
	public void add(int[] valueIn){
		if (leaf) {
			if (value == null) {
				value = valueIn;
			} else {
				split();
				matchSubtree(value).add(value);
				matchSubtree(valueIn).add(valueIn);
				value = null;
				leaf = false;
			}
		} else {
			matchSubtree(valueIn).add(valueIn);
		}
	}
	
	public PRQuadtree matchSubtree(int[] valueIn){
		if (valueIn[0] < (xMax + xMin)/2) {
			if (valueIn[1] < (yMax + yMin)/2) {
				return ll;
			} else {
				return lh;
			}
		} else {
			if (valueIn[1] < (yMax + yMin)/2) {
				return hl;
			} else {
				return hh;
			}
		}
	}
	
	public void split(){
		int xMid = (xMax + xMin)/2;
		int yMid = (yMax + yMin)/2;
		hh = new PRQuadtree(xMax, xMid, yMax, yMid);
		hl = new PRQuadtree(xMax, xMid, yMid, yMin);
		lh = new PRQuadtree(xMid, xMin, yMax, yMid);
		ll = new PRQuadtree(xMid, xMin, yMid, yMin);
	}
	
	public PRQuadtree delete(int[] valueIn){
		if (leaf){
			if(equalValues(valueIn)) {
				value = null;
			}
		} else {
			matchSubtree(valueIn).delete(valueIn);
			if (size() == 1){
				value = merge();
			}
		}
		return this;
	}
	
	public int[] merge(){
		if (leaf) return value;
		
		if (hh.size() == 1) value = hh.merge();
		if (lh.size() == 1) value = lh.merge();
		if (hl.size() == 1) value = hl.merge();
		if (ll.size() == 1) value = ll.merge();
		leaf = true;
		return value;
	}
	
	public int size(){
		if (leaf) {
			if (value != null)
				return 1;
			else return 0;
		}
		else {
			return hh.size() + hl.size() + ll.size() + lh.size();
		}
	}
	
	public boolean equalValues(int[] valueIn){
		return value[0] == valueIn[0] && value[1] == valueIn[1];
	}
}
