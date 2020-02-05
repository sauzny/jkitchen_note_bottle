package com.sauzny.xxleetcode.p0011_0020;

import java.util.ArrayList;
import java.util.List;

public class Solution013 {

    public int romanToInt(String s) {
        
        String[] str1 = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        String[] str2 = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] str3 = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] str4 = {"", "M", "MM", "MMM"};
        
        int result = 0;

        for(int i=str4.length-1;i>0;i--){
            String key = str4[i];
            if(s.startsWith(key)){
                result += i*1000;
                s = s.substring(key.length());
                break;
            }
        }

        for(int i=str3.length-1;i>0;i--){
            String key = str3[i];
            if(s.startsWith(key)){
                result += i*100;
                s = s.substring(key.length());
                break;
            }
        }

        for(int i=str2.length-1;i>0;i--){
            String key = str2[i];
            if(s.startsWith(key)){
                result += i*10;
                s = s.substring(key.length());
                break;
            }
        }

        for(int i=str1.length-1;i>0;i--){
            String key = str1[i];
            if(s.startsWith(key)){
                result += i;
                s = s.substring(key.length());
                break;
            }
        }
        
        return result;
    }

    public static void main(String[] args) {

        Solution013 solution = new Solution013();

        List<String> paramsA = new ArrayList<String>();
        List<Integer> results = new ArrayList<Integer>();

        paramsA.add("III");
        paramsA.add("IV");
        paramsA.add("IX");
        paramsA.add("LVIII");
        paramsA.add("MCMXCIV");

        for (int i = 0; i < paramsA.size(); i++) {
            results.add(solution.romanToInt(paramsA.get(i)));
        }

        for (int i = 0; i < paramsA.size(); i++) {
            System.out.println(paramsA.get(i) + " - " + results.get(i));
        }

    }
}
