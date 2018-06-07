package com.sauzny.xxleetcode.p001_010;

import java.util.ArrayList;
import java.util.List;

public class Solution009 {
    
    public boolean isPalindrome(int x) {
        
        if(x < 0) return false;
        if(x < 10) return true;
        
        int ox = x;
        int y = 0;
        
        while(x!=0){
            y = y*10 + x%10;
            x = x/10;
        }
        
        return ox == y;
    }
    
    public static void main(String[] args) {
        
        Solution009 solution = new Solution009();
        
        List<Integer> params = new ArrayList<Integer>();
        List<Boolean> results = new ArrayList<Boolean>();
        
        params.add(121);
        params.add(-121);
        params.add(10);
        
        for(Integer param : params){
            results.add(solution.isPalindrome(param));
        }
        
        for(int i=0;i<params.size();i++){
            System.out.println(params.get(i) + " - " + results.get(i));
        }
    }
}
