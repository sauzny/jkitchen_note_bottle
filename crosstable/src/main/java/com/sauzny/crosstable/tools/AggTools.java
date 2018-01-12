package com.sauzny.crosstable.tools;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;


public final class AggTools {

    private AggTools(){}
    
    public static double sum(List<String> list){
        double result = list.stream().mapToDouble(Double::parseDouble).sum();
        /*
        for(String str : list){
            result = result + Integer.parseInt(str);
        }
        */
        return result;
    }
    
    public static double avg(List<String> list){
        double result = list.stream().mapToDouble(Double::parseDouble).average().getAsDouble();
        /*
        for(String str : list){
            result = result + Integer.parseInt(str);
        }
        */
        return result;
    }
    
    public static double min(List<String> list){
        double result = list.stream().mapToDouble(Double::parseDouble).min().getAsDouble();
        /*
        for(String str : list){
            if(result > Integer.parseInt(str)){
                result = Integer.parseInt(str);
            }
        }
        */
        return result;
    }
    
    public static List<Double> chart(List<String> list){
        List<Double> result = list.stream().mapToDouble(Double::parseDouble).boxed().collect(Collectors.toList());
        /*
        list.forEach(v -> {
            result.add(Integer.parseInt(v));
        });
        */
        return result;
    }
}
