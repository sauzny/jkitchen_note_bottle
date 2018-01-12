package com.sauzny.crosstable;

import java.util.List;

import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;
import com.sauzny.crosstable.cell.HeaderCell;

public final class BuildHeaderTable {

    private BuildHeaderTable(){}
    
    public static void buildTop(
            HeaderCell leftTopCell,
            Table<Integer, Integer, Object> topHeaderTable,
            List<String> top,
            ListMultimap<Object, Object> topHeader,
            List<String> value
            ){
        
        List<String> topNameList = Lists.newArrayList("root");
        
        for(int t=0;t<top.size();t++){

            List<String> tempList = Lists.newArrayList();
            topNameList.forEach(name1 -> {
                
                String[] strs = name1.split("-");
                String cName = strs[strs.length-1];
                
                if(cName.contains("~")){
                    tempList.add(name1 + "-" + cName);
                }else{
                    topHeader.get(cName).forEach(name2 -> {
                        tempList.add(name1 + "-" + name2);
                    });
                }
                
            });
            topNameList.clear();
            topNameList.addAll(tempList);
        }
        
        
        List<String> columnKeyList = Lists.newArrayList();
        
        topNameList.forEach(name -> {
            value.forEach(vName ->{
                columnKeyList.add(name.substring(5)+"-"+ vName);
            });
        });
        
        for(int c=0; c<columnKeyList.size(); c++){
            
            String[] names = columnKeyList.get(c).split("-");
            for(int i=0;i<names.length;i++){
                topHeaderTable.put(i, leftTopCell.getColspan()+c, names[i]);
            }
        }
    }
    
    public static void buildLeft(
            HeaderCell leftTopCell,
            Table<Integer, Integer, Object> leftHeaderTable,
            List<String> left,
            ListMultimap<Object, Object> leftHeader
            ){
        // --------------------------------
        
        List<String> leftNameList = Lists.newArrayList("root");
        
        for(int t=0;t<left.size();t++){

            List<String> tempList = Lists.newArrayList();
            leftNameList.forEach(name1 -> {
                
                String[] strs = name1.split("-");
                String cName = strs[strs.length-1];
                
                if(cName.contains("~")){
                    tempList.add(name1 + "-" + cName);
                }else{
                    leftHeader.get(cName).forEach(name2 -> {
                        tempList.add(name1 + "-" + name2);
                    });
                }
                
            });
            leftNameList.clear();
            leftNameList.addAll(tempList);
        }
        
        // --------------------------------
        
        List<String> rowKeyList = Lists.newArrayList();
        
        leftNameList.forEach(name -> {
            rowKeyList.add(name.substring(5));
        });
        //List<String> rowKeyList = Lists.newArrayList(table.rowKeySet());
        
        for(int r=0; r<rowKeyList.size(); r++){
            
            String[] names = rowKeyList.get(r).split("-");
            for(int i=0;i<names.length;i++){
                leftHeaderTable.put(leftTopCell.getRowspan()+r, i, names[i]);
            }
        }
    }
}
