package com.yyhome.leetcode.middle;

import java.util.HashMap;
import java.util.Map;

/**
 * 523 连续的子数组和
 * @author: lirl
 * @date: 2021/6/2
 */
public class CheckSubarraySum {

    public static void main(String[] args){
        System.out.println(new CheckSubarraySum().checkSubarraySum(new int[]{1,2,3}, 5));
        System.out.println(new CheckSubarraySum().checkSubarraySum(new int[]{2,4,3}, 6));
        System.out.println(new CheckSubarraySum().checkSubarraySum(new int[]{5,0,0,0}, 3));
        System.out.println(new CheckSubarraySum().checkSubarraySum(new int[]{23,2,4,6,7}, 6));
        System.out.println(new CheckSubarraySum().checkSubarraySum(new int[]{23,2,6,4,7}, 6));
        System.out.println(new CheckSubarraySum().checkSubarraySum(new int[]{23,2,6,4,7}, 13));
    }

    /**
     * 执行耗时:24 ms,击败了21.35% 的Java用户
     * 内存消耗:53.6 MB,击败了50.22% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums.length < 2) {
            return false;
        }
        if (k == 0 || (nums[0] + nums[1]) % k == 0) {
            return true;
        }
        int total = nums[0] + nums[1];
        Map<Integer, Integer> map = new HashMap<>();
        map.put(total % k, 1);
        for (int i = 2; i < nums.length; i++) {
            total+=nums[i];
            int mod = total % k;
            if ((nums[i] + nums[i-1]) % k == 0 || mod == 0 || (map.containsKey(mod) && i - map.get(mod) >= 2)) {
                return true;
            } else {
                map.put(mod, map.getOrDefault(mod, i));
            }
        }
        return false;
    }
}
