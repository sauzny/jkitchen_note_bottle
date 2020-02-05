package com.sauzny.xxleetcode.p0001_0010;

public class Solution008 {
    
    public int myAtoi(String str) {
        
        if(str == null || str.length() == 0) return 0;
        
        str = str.trim();
        
        if(str.length() == 0) return 0;
        
        // 47 > 数字  < 58
        // + = 43
        // - = 45
        
        int first = str.charAt(0);
        
        if(first != 43 && first != 45 && !(first > 47 && first < 58)) return 0;
        
        if(first != 43 && first != 45) str = "+"+str;
        
        StringBuilder sb = new StringBuilder(String.valueOf(str.charAt(0)));
        
        for(int i=1; i<str.length(); i++){
            
            int cint = str.charAt(i);
            
            if(cint < 47 || cint > 58) break;
            
            if(sb.length() == 1){
                if(cint > 48 && cint <58) sb.append(str.charAt(i));
            }else{
                if(cint > 47 && cint <58) sb.append(str.charAt(i));
            }
            
        }
        
        System.out.println(str + " -- " + sb.toString());
        
        if(sb.length() < 2) return 0;
        
        // 2147483647
        // -2147483648
        if(sb.charAt(0) == 43 && sb.length() > 11) return Integer.MAX_VALUE;
        if(sb.charAt(0) == 45 && sb.length() > 11) return Integer.MIN_VALUE;
        
        int result = 0;
        
        Long l = Long.parseLong(sb.toString());
        
        if(l < Integer.MIN_VALUE) result = Integer.MIN_VALUE;
        if(l > Integer.MAX_VALUE) result = Integer.MAX_VALUE;
        if(result == 0) result = l.intValue();
        
        return result;
    }
    
    public static void main(String[] args) {
        
        Solution008 solution = new Solution008();
        
        int r1 = solution.myAtoi("42");
        int r2 = solution.myAtoi("   -42");
        int r3 = solution.myAtoi("4193 with words");
        int r4 = solution.myAtoi("words and 987");
        int r5 = solution.myAtoi("-91283472332");
        int r6 = solution.myAtoi("-");
        int r7 = solution.myAtoi("+-2");
        int r8 = solution.myAtoi("20000000000000000000");
        int r9 = solution.myAtoi("  0000000000012345678");
        int r10 = solution.myAtoi("-000000000000001");
        int r11 = solution.myAtoi("    0000000000000   ");
        int r12 = solution.myAtoi("    010   ");
        int r13 = solution.myAtoi("+1");
        
        System.out.println(r1);
        System.out.println(r2);
        System.out.println(r3);
        System.out.println(r4);
        System.out.println(r5);
        System.out.println(r6);
        System.out.println(r7);
        System.out.println(r8);
        System.out.println(r9);
        System.out.println(r10);
        System.out.println(r11);
        System.out.println(r12);
        System.out.println(r13);
    }
}
