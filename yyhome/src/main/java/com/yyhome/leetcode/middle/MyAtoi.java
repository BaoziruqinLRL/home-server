package com.yyhome.leetcode.middle;

import java.lang.reflect.InvocationTargetException;

/**
 * 8. 字符串转换整数 (atoi)
 * @author miluo
 * @date 2020-04-03
 */
public class MyAtoi {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        System.out.println(myAtoi("42"));
        System.out.println(myAtoi("    -42"));
        System.out.println(myAtoi("4193 with words"));
        System.out.println(myAtoi("words and 987"));
        System.out.println(myAtoi("-91283472332"));
    }

    private static int myAtoi(String str) throws InvocationTargetException, IllegalAccessException {
        char[] strArr = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        boolean start = false;
        for (char ch : strArr){
            if (ch >= '0' && ch <= '9'){
                start = true;
                sb.append(ch);
            }else if (ch == '-'){
                start = true;
                sb.append(ch);
            }else{
                if (start){
                    break;
                }
            }
        }
        System.out.println(sb.toString());
        return Integer.MAX_VALUE;
    }
}
