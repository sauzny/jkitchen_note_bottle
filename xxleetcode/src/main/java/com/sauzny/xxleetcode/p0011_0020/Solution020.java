package com.sauzny.xxleetcode.p0011_0020;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***************************************************************************
 *
 * @时间: 2019/2/22 - 13:58
 *
 * @描述: TODO
 *
 ***************************************************************************/
public class Solution020 {

    public boolean isValid(String s) {

        if (s.length() == 0) return true;
        if (s.length() % 2 != 0) return false;

        char[] chars = new char[]{'(', ')', '[', ']', '{', '}'};

        Map<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('(', 0);
        map.put(')', 1);
        map.put('[', 2);
        map.put(']', 3);
        map.put('{', 4);
        map.put('}', 5);

        if (s.charAt(0) == ')' || s.charAt(0) == ']' || s.charAt(0) == '}') {
            return false;
        }

        if (s.charAt(s.length() - 1) == '(' || s.charAt(s.length() - 1) == '[' || s.charAt(s.length() - 1) == '{') {
            return false;
        }

        boolean result = false;

        List<Character> leftList = new ArrayList<Character>();

        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) {
                if (map.get(c) % 2 == 0) {
                    leftList.add(c);
                } else {
                    if (leftList.size() != 0 && map.get(leftList.get(leftList.size() - 1)) + 1 == map.get(c)) {
                        leftList.remove(leftList.size() - 1);
                    } else {
                        break;
                    }
                }
            } else {
                break;
            }
        }
        if(leftList.size() == 0) result = true;

        return result;
    }

    public static void main(String[] args) {
        Solution020 solution = new Solution020();
        long a = System.currentTimeMillis();
        boolean result = solution.isValid("{[]}");
        long b = System.currentTimeMillis();
        System.out.println(result);
        System.out.println("timing : " + (b-a));
    }
}
