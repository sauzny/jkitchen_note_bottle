package com.sauzny.xxleetcode.p001_010;

import java.util.Arrays;

//There are two sorted arrays nums1 and nums2 of size m and n respectively.

//Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).


/*
Example 1:

nums1 = [1, 3]
nums2 = [2]

The median is 2.0
*/


/*
Example 2:

nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5
*/


public class Solution004 {

	public static int[] sort(int[] nums1, int[] nums2){
		
		int[] nums = new int[nums1.length+nums2.length];
		
		for(int i=0; i<nums1.length; ++i){
			nums[i] = nums1[i];
		}
		
		for(int i=nums1.length; i<nums1.length+nums2.length; ++i){
			nums[i] = nums2[i-nums1.length];
		}
		
		// 快速排序 -- 
		Arrays.sort(nums);
		
		return nums;
		
	}
	
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        
    	int[] nums = sort(nums1, nums2);
    	
    	System.out.println(Arrays.toString(nums));
    	
    	if(nums.length%2 == 0){
    		return (double)(nums[nums.length/2]+nums[nums.length/2-1])/2;
    	}else{
    		return nums[(nums.length-1)/2];
    	}
    	
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution004 solution004 = new Solution004();
		double d = solution004.findMedianSortedArrays(new int[]{1,3}, new int[]{2});
		System.out.println(d);
	}

}
