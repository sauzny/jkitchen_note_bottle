package com.sauzny.xxleetcode.p011_020;

import java.util.ArrayList;
import java.util.List;

public class Solution014 {
    
    public String longestCommonPrefix(String[] strs) {
        
        if(strs == null || strs.length == 0 || strs[0].length() == 0) return "";
        
        String first = strs[0];
        
        StringBuilder sb = new StringBuilder();
        
        for(int i=0; i<first.length(); i++){
            int ci = first.charAt(i);
            boolean isSame = true;
            for(int j=1; j<strs.length; j++){
                if(i>=strs[j].length()){
                    isSame = false;
                    break;
                }
                int ji = strs[j].charAt(i);
                if(ci != ji){
                    isSame = false;
                    break;
                }
            }
            if(isSame){
                sb.append(String.valueOf(first.charAt(i)));
            }else{
                break;
            }
        }
        
        return sb.toString();
    }
    
    public static void main(String[] args) {

        Solution014 solution = new Solution014();
        
        List<String[]> paramsA = new ArrayList<String[]>();
        List<String> results = new ArrayList<String>();
        
        paramsA.add(new String[]{"flower","flow","flight"});
        paramsA.add(new String[]{"flower","flowight","flow"});
        paramsA.add(new String[]{"dog","racecar","car"});
        paramsA.add(new String[]{"aca","cba"});
        
        for(int i=0;i<paramsA.size();i++){
            
            String[] strs = new String[paramsA.get(i).length];
            
            for(int j=0;j<strs.length;j++){
                strs[j] = paramsA.get(i)[j];
            }
            
            results.add(solution.longestCommonPrefix(strs));
        }
        
        for(int i=0;i<paramsA.size();i++){
            System.out.println(paramsA.get(i) + " - " + results.get(i));
        }
    }
}
