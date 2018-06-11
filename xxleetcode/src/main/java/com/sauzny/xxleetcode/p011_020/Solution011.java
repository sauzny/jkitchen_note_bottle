package com.sauzny.xxleetcode.p011_020;

import java.util.ArrayList;
import java.util.List;

public class Solution011 {

    public int maxArea(int[] height) {
        
        int maxarea = 0, l = 0, r = height.length - 1;
        while (l < r) {
            maxarea = Math.max(maxarea, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r])
                l++;
            else
                r--;
        }
        return maxarea;
    }
    
    public static void main(String[] args) {
        
        Solution011 solution = new Solution011();
        
        List<Integer[]> paramsA = new ArrayList<Integer[]>();
        List<Integer> results = new ArrayList<Integer>();
        
        paramsA.add(new Integer[]{1, 8, 6, 2, 5, 4, 8, 3, 7});
        
        for(int i=0;i<paramsA.size();i++){
            
            int[] height = new int[paramsA.get(i).length];
            
            for(int j=0;j<height.length;j++){
                height[j] = paramsA.get(i)[j];
            }
            
            results.add(solution.maxArea(height));
        }
        
        for(int i=0;i<paramsA.size();i++){
            System.out.println(paramsA.get(i) + " - " + results.get(i));
        }
    }
}
