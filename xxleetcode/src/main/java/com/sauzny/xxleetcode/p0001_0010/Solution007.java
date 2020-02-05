package com.sauzny.xxleetcode.p0001_0010;

public class Solution007 {
    
    public int reverse(int x) {
        
        int result = 0;
        
        if(x <= Integer.MIN_VALUE) return result;
        
        boolean isNegative = x<0;
        
        String s = String.valueOf(x);
        char[] cs = s.toCharArray();
        
        StringBuilder sb = new StringBuilder();
        
        if(isNegative) sb.append("-");
        
        for(int i=cs.length-1;i>=(isNegative?1:0);i--) sb.append(cs[i]);
        
        Long l = Long.parseLong(sb.toString());
        
        if(l < Integer.MIN_VALUE || l > Integer.MAX_VALUE){
            result = 0;
        }else{
            result = l.intValue();
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        
        Solution007 solution = new Solution007();

        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        
        int r1 = solution.reverse(123);
        System.out.println(r1);
        int r2 = solution.reverse(-123);
        System.out.println(r2);
        int r3 = solution.reverse(120);
        System.out.println(r3);
        int r4 = solution.reverse(1534236469);
        System.out.println(r4);
    }
}
