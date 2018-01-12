package com.sauzny.crosstable.tools;

import java.util.List;

public final class CtTools {

    private CtTools(){}
    
    public static <T> T getLast(List<T> list){
        if(list.size() ==0){
            return null;
        }
        return list.get(list.size()-1);
    }
    
    public static boolean isAgg(Object object){
        return String.valueOf(object).contains("~");
    }
    
    public static boolean isNotAgg(Object object){
        return !String.valueOf(object).contains("~");
    }
}
