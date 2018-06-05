package com.sauzny.xxleetcode.p01_10;

/*

    ↓  ↓  ↓
            ↓ ↗↓ ↗↓
    ↓  ↓

    ↓    ↓    ↓
            ↓     ↗  ↓     ↗  ↓
            ↓ ↗      ↓ ↗
    ↓    ↓
 */
public class Solution006 {
    public String convert(String s, int numRows) {
        
        if(s == null || s.length() == 0) return "";
        if(s.length() <= numRows || numRows == 1) return s;
        
        if(numRows == 2){
            
            StringBuilder result = new StringBuilder();
            
            for(int i=0;i<s.length();i+=2) result.append(s.charAt(i));
            
            for(int i=1;i<s.length();i+=2) result.append(s.charAt(i));
            
            return result.toString();
        }
        
        StringBuilder result = new StringBuilder();
        
        // |/ 认为是一组字符，确认一组有多少个字符
        int perLength = 2*(numRows-1);
        
        // 二维数组x轴长度 第一部分
        int x1 = s.length()/perLength;
        
        // 二维数组x轴长度 第二部分
        int x2 = s.length()%perLength;
        if(x2 == 0){
            
        }else if(x2 <= numRows){
            x2 = 1;
        }else{
            x2 = 1 + x2 - numRows;
        }

        // 二维数组x轴y轴长度
        int x = x1*(numRows-1)+x2;
        int y = numRows;
        
        // 定义二维数组
        char [][] zarray = new char[y][x];
        
        // 每一组的字符串
        String[] perString = new String[x1 + (x2 == 0 ? 0 : 1)];
        
        for(int i=0;i<perString.length;i++){

            if((i+1)*perLength > s.length()){
                perString[i] = s.substring(i*perLength, s.length());
            }else{
                perString[i] = s.substring(i*perLength, (i+1)*perLength);
            }
            //System.out.println(perString[i]);
        }
        
        // 填充二维数组
        for (int i = 0; i < perString.length; i++) {
            char[] c = perString[i].toCharArray();
            for (int j = 0; j < c.length; j++) {
                if (j < numRows) {
                    // 一组数据的第一部分
                    zarray[j][i * (numRows-1)] = c[j];
                } else {
                    // 一组数据的第二部分
                    zarray[2*numRows-j-2][i * (numRows-1) + (j-numRows+1)] = c[j];
                }
            }
        }

        for(int i=0;i<y;i++){
            for(int j=0;j<x;j++){
                if(zarray[i][j] != 0){
                    //System.out.print(zarray[i][j] + " ");
                    result.append(zarray[i][j]);
                }else{
                    //System.out.print("  ");
                }
            }
            //System.out.println();
        }
        
        return result.toString();
    }
    
    public static void main(String[] args) {
        Solution006 solution = new Solution006();
        String result1 = solution.convert("PAYPALISHIRING", 3);
        System.out.println(result1);
        String result2 = solution.convert("PAYPALISHIRING", 4);
        System.out.println(result2);
        String result3 = solution.convert("PA", 4);
        System.out.println(result3);
        String result4 = solution.convert("", 4);
        System.out.println(result4);
        String result5 = solution.convert("", 1);
        System.out.println(result5);
        String result6 = solution.convert("A", 1);
        System.out.println(result6);
        String result7 = solution.convert("AB", 1);
        System.out.println(result7);
        String result8 = solution.convert("ABC", 2);
        System.out.println(result8);
        String result9 = solution.convert("ABCDE", 4);
        System.out.println(result9);
        String result10 = solution.convert("PAYPALISHIRING", 5);
        System.out.println(result10);
        String result11 = solution.convert("PAYPALISHIRING", 7);
        System.out.println(result11);
    }
}
