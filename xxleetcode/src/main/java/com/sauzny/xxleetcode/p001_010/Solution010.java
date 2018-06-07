package com.sauzny.xxleetcode.p001_010;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Solution010 {
    
    public boolean isMatch(String s, String p) {
        
        if(s.equals(p)) return true;
        if(p.length() == 0) return false;
        
        if(p.equals("*")) p = ".*";
        
        return Pattern.matches(p, s);
    }
    
    public static void main(String[] args) {
        
        Solution010 solution = new Solution010();
        
        List<String> paramsA = new ArrayList<String>();
        List<String> paramsB = new ArrayList<String>();
        List<Boolean> results = new ArrayList<Boolean>();
        
        paramsA.add("aa"); paramsB.add("a");
        paramsA.add("aa"); paramsB.add("a*");
        paramsA.add("ab"); paramsB.add(".*");
        paramsA.add("aab"); paramsB.add("c*a*b");
        paramsA.add("mississippi"); paramsB.add("mis*is*p*.");
        
        for(int i=0;i<paramsA.size();i++){
            results.add(solution.isMatch(paramsA.get(i), paramsB.get(i)));
        }
        
        for(int i=0;i<paramsA.size();i++){
            System.out.println(paramsA.get(i) + " - " +paramsB.get(i) + " - " + results.get(i));
        }
    }
}
