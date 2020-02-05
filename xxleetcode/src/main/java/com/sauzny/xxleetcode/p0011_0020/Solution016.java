package com.sauzny.xxleetcode.p0011_0020;

import java.util.Arrays;

/***************************************************************************
 *
 * @时间: 2019/2/18 - 15:54
 *
 * @描述: TODO
 *
 ***************************************************************************/
public class Solution016 {

    public int threeSumClosest(int[] nums, int target) {
        // 排序
        Arrays.sort(nums);
        int closestNum = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                //System.out.println(nums[l]);
                int threeSum = nums[l] + nums[r] + nums[i];
                if (Math.abs(threeSum - target) < Math.abs(closestNum - target)) {
                    closestNum = threeSum;
                }
                if (threeSum > target) {
                    while (r > l && nums[r] == nums[r-1]) r--;
                    r--;
                } else if (threeSum < target) {
                    while (l < r && nums[l] == nums[l+1]) l++;
                    l++;
                } else {
                    // 如果已经等于target的话, 肯定是最接近的
                    return target;
                }
            }
        }
        return closestNum;
    }

    public static void main(String[] args) {
        Solution016 solution016 = new Solution016();
        int result = solution016.threeSumClosest(new int[]{-1, 0, 1, 1, 55}, 3);
        System.out.println(result);
    }
}
