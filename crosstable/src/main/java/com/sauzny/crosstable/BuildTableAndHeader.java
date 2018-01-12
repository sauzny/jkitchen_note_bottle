package com.sauzny.crosstable;

import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;
import com.google.common.collect.Table.Cell;

public final class BuildTableAndHeader {

    private BuildTableAndHeader(){}
    
    public static void build(
            List<String> value,
            List<Integer> topIndex,
            List<Integer> leftIndex,
            List<Integer> valueIndex,
            Object[][] objss,
            Table<String, String, Object> table,
            ListMultimap<Object, Object> topHeader,
            ListMultimap<Object, Object> leftHeader
            ){
        
        for(Object[] objs : objss){
            
            List<String> cKeyList = Lists.newArrayList();
            for(int i=0;i<topIndex.size();i++){
                
                String name = String.valueOf(objs[topIndex.get(i)]);
                
                // 为 table 组织 ckey
                cKeyList.add(name);
                
                // 组织topheader 第一步 处理普通表头
                if(i==0){
                    if(topHeader.get("root") == null || !topHeader.get("root").contains(name)){
                        topHeader.put("root", name);
                    }
                }else{
                    if(topHeader.get(String.valueOf(objs[topIndex.get(i-1)])) == null || !topHeader.get(String.valueOf(objs[topIndex.get(i-1)])).contains(name)){
                        topHeader.put(String.valueOf(objs[topIndex.get(i-1)]), name);
                    }
                }
            }
            
            List<String> rKeyList = Lists.newArrayList();
            for(int i=0;i<leftIndex.size();i++){
                String name = String.valueOf(objs[leftIndex.get(i)]);
                //rKeyList.add(String.valueOf(objs[i]));
                rKeyList.add(name);
                
                // 组织leftheader 第一步 处理普通表头
                if(i==0){
                    if(leftHeader.get("root") == null || !leftHeader.get("root").contains(name)){
                        leftHeader.put("root", name);
                    }
                }else{
                    if(leftHeader.get(String.valueOf(objs[leftIndex.get(i-1)])) == null || !leftHeader.get(String.valueOf(objs[leftIndex.get(i-1)])).contains(name)){
                        leftHeader.put(String.valueOf(objs[leftIndex.get(i-1)]), name);
                    }
                }
            }
            
            for(int i=0;i<value.size();i++){
                
                String name = value.get(i);
                
                // 为 table 组织 ckey
                int vIndex = valueIndex.get(i);
                List<String> list = Lists.newArrayList(cKeyList);
                list.add(name);
                
                String cKey = Joiner.on("-").join(list);
                String rKey = Joiner.on("-").join(rKeyList);
                
                if(table.get(rKey, cKey) == null){
                    table.put(rKey, cKey, objs[vIndex]);
                }else{
                    Object oldValue = table.get(rKey, cKey);
                    table.put(rKey, cKey, oldValue + "," + objs[vIndex]);
                }
                
                // 为 列agg函数准备
                for(int j=0;j<cKeyList.size();j++){
                    String tempCKey = "root";
                    if(j > 0){
                        tempCKey = cKeyList.get(0);
                        for(int k=1;k<j;k++){
                            tempCKey = tempCKey + "-" + cKeyList.get(k);
                        }
                    }
                    tempCKey = tempCKey + "-" + name;
                    
                    if(table.get(rKey, tempCKey) == null){
                        table.put(rKey, tempCKey, objs[vIndex]);
                    }else{
                        Object oldValue = table.get(rKey, tempCKey);
                        table.put(rKey, tempCKey, oldValue + "," + objs[vIndex]);
                    }
                }
                
                // 为 行 agg函数准备
                for(int j=0;j<rKeyList.size();j++){
                    String tempRKey = "root";
                    if(j > 0){
                        tempRKey = rKeyList.get(0);
                        for(int k=1;k<j;k++){
                            tempRKey = tempRKey + "-" + rKeyList.get(k);
                        }
                        //tempRKey = tempRKey + "-" + name;
                    }
                    
                    if(table.get(tempRKey, cKey) == null){
                        table.put(tempRKey, cKey, objs[vIndex]);
                    }else{
                        Object oldValue = table.get(tempRKey, cKey);
                        table.put(tempRKey, cKey, oldValue + "," + objs[vIndex]);
                    }
                }
            }
            
            
            
        }
        
    }
    
    public static void main(String[] args) {
        String name = "root-xxx";
        System.out.println(name.substring(5));
    }
    
}
