package com.sauzny.xxleetcode.p011_020;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution012 {

    public String intToRoman(int num) {
        
        if(num < 1 || num > 3999) return "";
        
        Map<Integer, String> map1 = new HashMap<Integer, String>();
        Map<Integer, String> map2 = new HashMap<Integer, String>();
        Map<Integer, String> map3 = new HashMap<Integer, String>();
        Map<Integer, String> map4 = new HashMap<Integer, String>();
        
        map1.put(1, "I");
        map1.put(2, "II");
        map1.put(3, "III");
        map1.put(4, "IV");
        map1.put(5, "V");
        map1.put(6, "VI");
        map1.put(7, "VII");
        map1.put(8, "VIII");
        map1.put(9, "IX");
        
        map2.put(1, "X");
        map2.put(2, "XX");
        map2.put(3, "XXX");
        map2.put(4, "XL");
        map2.put(5, "L");
        map2.put(6, "LX");
        map2.put(7, "LXX");
        map2.put(8, "LXXX");
        map2.put(9, "XC");
        
        map3.put(1, "C");
        map3.put(2, "CC");
        map3.put(3, "CCC");
        map3.put(4, "CD");
        map3.put(5, "D");
        map3.put(6, "DC");
        map3.put(7, "DCC");
        map3.put(8, "DCCC");
        map3.put(9, "CM");

        map4.put(1, "M");
        map4.put(2, "MM");
        map4.put(3, "MMM");
        
        StringBuilder sb = new StringBuilder();
        
        if(num/1000%10 > 0) sb.append(map4.get(num/1000%10));
        if(num/100%10 > 0) sb.append(map3.get(num/100%10));
        if(num/10%10 > 0) sb.append(map2.get(num/10%10));
        if(num%10 > 0) sb.append(map1.get(num%10));
        
        return sb.toString();
    }
    
    public static void main(String[] args) {
        
        Solution012 solution = new Solution012();
        
        List<Integer> paramsA = new ArrayList<Integer>();
        List<String> results = new ArrayList<String>();
        
        for(int i=1; i<4000; i++){
            paramsA.add(i);
        }
        
        for(int i=0;i<paramsA.size();i++){
            results.add(solution.intToRoman(paramsA.get(i)));
        }
        
        for(int i=0;i<paramsA.size();i++){
            //System.out.println(paramsA.get(i) + " - " + results.get(i));
            System.out.print("map.put(\""+results.get(i)+"\", "+paramsA.get(i)+");");
        }
    }
}
