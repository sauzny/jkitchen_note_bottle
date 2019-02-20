package com.sauzny.xxleetcode.p011_020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/***************************************************************************
 *
 * @时间: 2019/2/19 - 12:49
 *
 * @描述: TODO
 *
 ***************************************************************************/
public class Solution018 {
    public List<List<Integer>> fourSum(int[] nums, int target) {

        if(nums.length < 4) return Collections.emptyList();

        List<List<Integer>> result = new ArrayList<List<Integer>>();

        // 排序
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 3; i++) {

            if(i > 0 && nums[i] == nums[i-1]) continue;

            for (int j = i+1; j < nums.length - 2; j++) {

                if(j > i+1 && nums[j] == nums[j-1]) continue;

                int l = j + 1, r = nums.length - 1;

                while (l < r) {

                    int sum = nums[i] + nums[j] + nums[l] + nums[r];

                    if (sum < target) {
                        l++;
                    } else if (sum > target) {
                        r--;
                    } else {
                        result.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
                        while (l < r && nums[l] == nums[l+1]) l++;
                        while (r > l && nums[r] == nums[r-1]) r--;
                        l++;
                        r--;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution018 solution = new Solution018();
        long a = System.currentTimeMillis();
        List<List<Integer>> result = solution.fourSum(new int[]{-1,2,2,-5,0,-1,4}, 3);
        long b = System.currentTimeMillis();
        System.out.println(result);
        System.out.println("timing : " + (b-a));
    }
}
