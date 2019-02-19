package com.sauzny.xxleetcode.p011_020;

import java.util.*;

/***************************************************************************
 *
 * @时间: 2019/2/18 - 17:15
 *
 * @描述: TODO
 *
 ***************************************************************************/
public class Solution17 {
    public List<String> letterCombinations(String digits) {
        Map<Character, String> map = new HashMap<Character, String>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");

        List<String> result = new ArrayList<String>();

        List<String> temp = new ArrayList<String>();
        for(char c : digits.toCharArray()){
            temp.add(map.get(c));
        }

        for(String t : temp){
            List<String> list = new ArrayList<String>();

            for(char c1 : t.toCharArray()){
                if(result.size() == 0){
                    list.add(String.valueOf(c1));
                }else{
                    for(String str : result){
                        list.add(str+c1);
                    }
                }
            }

            result.clear();
            result.addAll(list);
        }

        return result;
    }

    public static void main(String[] args) {
        Solution17 solution17 = new Solution17();
        List<String> result = solution17.letterCombinations("234");
        System.out.println(result);
    }
}
