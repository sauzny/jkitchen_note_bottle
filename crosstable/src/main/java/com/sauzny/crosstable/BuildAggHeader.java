package com.sauzny.crosstable;

import java.util.List;
import java.util.Set;

import com.google.common.collect.ListMultimap;
import com.google.common.collect.Sets;
import com.sauzny.crosstable.tools.CtTools;

public final class BuildAggHeader {

    private BuildAggHeader(){}
    
    public static void buildTop(
            List<String> topAgg, 
            List<String> top,
            ListMultimap<Object, Object> topHeader
            ){

        topAgg.forEach(agg -> build(agg, top, topHeader) );
    }
    
    public static void buildLeft(
            List<String> leftAgg,
            List<String> left,
            ListMultimap<Object, Object> leftHeader
            ){
        
        leftAgg.forEach(agg -> build(agg, left, leftHeader) );
    }
    
    public static void buildValue(
            List<String> valueAgg,
            ListMultimap<Object, Object> topHeader,
            ListMultimap<Object, Object> leftHeader
            ){
        valueAgg.forEach(agg -> {
            if(agg.contains("top")){
                topHeader.put("root", agg);
            }else if(agg.contains("left")){
                leftHeader.put("root", agg);
            }
        });
    }
    
    private static void build(String agg, List<String> names, ListMultimap<Object, Object> header){
        String[] nameAgg = agg.split("~");
        
        String name = nameAgg[0];
        //String aggStr = nameAgg[1];
        
        int nameIndex = names.indexOf(name);
        
        Set<Object> nameSet = Sets.newHashSet("root");
        Set<Object> tempSet = Sets.newHashSet();
        
        for(int n=0;n<nameIndex+1;n++){
            
            tempSet.clear();
            nameSet.forEach(temp -> {
                tempSet.addAll(Sets.newHashSet(header.get(temp)));
            });

            nameSet.clear();
            nameSet.addAll(tempSet);
            
        }

        nameSet.forEach(temp -> {
            if(CtTools.isNotAgg(temp) && CtTools.isAgg(agg)){
                header.put(temp, agg);
            }
        });
    }
}
