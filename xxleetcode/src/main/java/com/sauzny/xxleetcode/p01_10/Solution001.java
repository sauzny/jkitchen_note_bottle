package com.sauzny.xxleetcode.p01_10;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
 */

public class Solution001 {

	/*
	 *  使用 map
	 */
	
	public int[] twoSum(int[] nums, int target) {
		
	    Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	    for (int i = 0; i < nums.length; i++) {
	        int complement = target - nums[i];
	        if (map.containsKey(complement)) {
	            return new int[] { map.get(complement), i };
	        }
	        map.put(nums[i], i);
	    }
	    throw new IllegalArgumentException("No two sum solution");
	}

	public static void main(String[] args) {
		// TODO https://leetcode.com/problems/two-sum/
		
		Solution001 solution001 = new Solution001();
		
    	long a = System.nanoTime();
    	
    	int[] result = solution001.twoSum(new int[]{2,3,4}, 6);
    	
    	System.out.println(Arrays.toString(result));
    	
    	System.out.println( System.nanoTime() - a );
    	
	}

}
