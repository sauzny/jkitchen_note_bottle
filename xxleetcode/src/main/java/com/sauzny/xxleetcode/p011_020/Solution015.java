package com.sauzny.xxleetcode.p011_020;

import java.util.ArrayList;
import java.util.List;

public class Solution015 {

    public List<List<Integer>> threeSum(int[] nums) {
        
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        return result;
    }
    
    public static void main(String[] args) {

        Solution015 solution = new Solution015();
        
        List<Integer[]> paramsA = new ArrayList<Integer[]>();
        List<List<List<Integer>>> results = new ArrayList<List<List<Integer>>>();
        
        paramsA.add(new Integer[]{-1, 0, 1, 2, -1, -4});
        
        for(int i=0;i<paramsA.size();i++){
            
            int[] nums = new int[paramsA.get(i).length];
            
            for(int j=0;j<nums.length;j++){
                nums[j] = paramsA.get(i)[j];
            }
            
            results.add(solution.threeSum(nums));
        }
        
        for(int i=0;i<paramsA.size();i++){
            System.out.println(paramsA.get(i) + " - " + results.get(i));
        }
    }
}
