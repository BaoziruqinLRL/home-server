package com.yyhome.leetcode.middle;

import java.util.HashSet;
import java.util.Set;

/**
 * 3 无重复字符的最长子串
 * @author miluo
 * @date 2020-03-17
 */
public class LengthOfLongestSubstring {

    public static void main(String[] args){
        System.out.println(lengthOfLongestSubstring("abcbbcbb"));
        System.out.println(lengthOfLongestSubstring("bbbbb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
        System.out.println(lengthOfLongestSubstring(" "));
        System.out.println(lengthOfLongestSubstring("dvdf"));
    }

    private static int lengthOfLongestSubstring(String s) {
        int length = 0;
        Set<Integer> charSet = new HashSet<>();
        char[] arr = s.toCharArray();
        int tempLength = 0;
        for (int left = 0, right = 0; left <= right && right < s.length();){
            if (left == right){
                charSet.add((int) arr[left]);
                right++;
                tempLength++;
            }else{
                if (!charSet.contains((int)arr[right])){
                    charSet.add((int)arr[right]);
                    right++;
                    tempLength++;
                }else{
                    // 存在重复，计算长度，左界收拢
                    length = Math.max(length,tempLength);
                    // 左界收拢到第一个等于right的元素的索引位置的后一位
                    while (arr[left] != arr[right]){
                        charSet.remove((int)arr[left]);
                        left++;
                        tempLength--;
                    }
                    left++;
                    right++;
                }
            }
        }
        return Math.max(length,tempLength);
    }
}
