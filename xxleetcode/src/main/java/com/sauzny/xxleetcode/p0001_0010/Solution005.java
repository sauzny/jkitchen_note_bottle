package com.sauzny.xxleetcode.p0001_0010;

import java.util.ArrayList;
import java.util.List;

/**

Given a string S, find the longest palindromic substring in S. 
You may assume that the maximum length of S is 1000, 
and there exists one unique longest palindromic substring.

 */

public class Solution005 {

	
    public String longestPalindrome(String s) {
        
        List<int[]> evenIndex = new ArrayList<int[]>();
        List<Integer> oddIndex = new ArrayList<Integer>();
        
        char[] chars = s.toCharArray();
        
        for(int i=0; i<chars.length; i++){
            if(i+1 < chars.length && chars[i] == chars[i+1]){
                evenIndex.add(new int[]{i, i+1});
            }
            
            if(i-1 >= 0 && i+1 < chars.length && chars[i-1] == chars[i+1]){
                oddIndex.add(i);
            }
        }
        
        String result = "";
        
        for(int[] indexs : evenIndex){
            String temp = evenString(indexs[0], indexs[1], s, 1);
            if(temp.length() > result.length()){
                result = temp;
            }
        }
        
        for(int index : oddIndex){
            String temp = oddString(index, s, 1);
            if(temp.length() > result.length()){
                result = temp;
            }
        }
        
        if(result.isEmpty() && !s.isEmpty()){
            result = s.substring(0,1);
        }
        
        return result;
    }

    public static String evenString(int i1, int i2, String s, int offset){
        if(i1-offset >= 0 && i2+offset < s.length() && s.charAt(i1-offset) == s.charAt(i2+offset) ){
            return evenString(i1, i2, s, offset+1);
        }else{
            return s.substring(i1-(offset-1), i2+offset);
        }
    }
    
    public static String oddString(int i, String s, int offset){
        if(i-offset >= 0 && i+offset < s.length()  && s.charAt(i-offset) == s.charAt(i+offset) ){
            return evenString(i, i, s, offset+1);
        }else{
            return s.substring(i-(offset-1), i+offset);
        }
    }
    
	public static void main(String[] args) {
	    Solution005 solution = new Solution005();
	    String result = solution.longestPalindrome("a");
	    System.out.println(result);
	}
}
