package strings;

import java.util.ArrayList;
import java.util.List;

/*
 * This class implements the KMP algorithm, which finds matches of a pattern in a text.
 * 
 * Author: Jonggi Hong
 * Last update: 04/16/2017
 */
public class KMPMatching {
	int[] lps;
	
	public List<Integer> findMatches(String text, String pattern){
		preProcessing(pattern);
		
		int pLength = pattern.length(), tLength = text.length();
		ArrayList<Integer> matches = new ArrayList<Integer>();
		int i=0;
		
		while(i < tLength - pLength){
			boolean match = true;
			int skip = pLength - lps[pLength-1];
			
			for(int j=0; j<pattern.length(); j++){
				if (pattern.charAt(j) != text.charAt(i + j)) {
					match = false;
					skip = j + 1 - lps[j];
					break;
				}
			}

			if(match) matches.add(i);
			i += skip;
		}
		
		return matches;
	}
	
	public void preProcessing(String pattern){
		lps = new int[pattern.length()];
		
		for(int i=1; i<=pattern.length(); i++){
			String curr = pattern.substring(0, i);
			
			for(int j=curr.length()-1; j>0; j--){
				String prefix = curr.substring(0, j);
				if (curr.endsWith(prefix)) {
					lps[i-1] = j;
					break;
				}
			}
		}
	}
}
