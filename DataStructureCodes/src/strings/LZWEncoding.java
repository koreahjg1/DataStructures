package strings;

import java.util.ArrayList;
import java.util.HashMap;
/*
 * This class encodes the normal string into list of integers and decodes the list of integers to a string.
 * 
 * Author: Jonggi Hong
 * Last update: 04/16/2017
 */
public class LZWEncoding {
	HashMap<String, Integer> wordDic = new HashMap<String, Integer>();
	HashMap<Integer, String> indexDic = new HashMap<Integer, String>();
	String[] chars = {" ", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", 
			"o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
	
	public LZWEncoding(){
		for(int i=0; i<chars.length; i++) addNewWord(chars[i]);
	}
	
	public String encode(String s){
		if (s.equals("")) return "";
		
		String part = "";
		ArrayList<Integer> indices = new ArrayList<Integer>();
		
		for(int i=0; i<s.length()-1; i++){
			char curr = s.charAt(i);
			if (wordDic.containsKey(part + curr)) {
				part += curr;
			} else {
				if (part.length() > 0) indices.add(wordDic.get(part));
				addNewWord(part+curr);
				part = curr + "";
			}
		}
		
		part += s.charAt(s.length() - 1);
		if (!wordDic.containsKey(part))
			addNewWord(part);
		indices.add(wordDic.get(part));
		
		String result = "";
		for(int i: indices) result += i+" ";
		return result.trim();
	}
	
	public String decode(String s){
		String[] tokens = s.split(" ");
		int[] indices = new int[tokens.length];
		

		String result = "";
		for(int i=0; i<tokens.length; i++){
			indices[i] = Integer.parseInt(tokens[i]);
			result += indexDic.get(indices[i]);
		}
		return result;
	}
	
	public void addNewWord(String word){
		int index = wordDic.size();
		indexDic.put(index, word);
		wordDic.put(word, index);
	}
}
