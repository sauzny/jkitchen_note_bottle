package com.sauzny.crosstable;

import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;
import com.sauzny.crosstable.tools.AggTools;

public final class BuildValueTable {

    private BuildValueTable(){}
    
    public static void build(
            Table<String, String, Object> table,
            Table<Integer, Integer, Object> topHeaderTable,
            Table<Integer, Integer, Object> leftHeaderTable,
            Table<Integer, Integer, Object> valueTable
            ){
        leftHeaderTable.rowKeySet().forEach(r-> {
            topHeaderTable.columnKeySet().forEach(c-> {
                String rKey = Joiner.on("-").join(leftHeaderTable.row(r).values());
                String cKey = Joiner.on("-").join(topHeaderTable.column(c).values());
                
                if(rKey.contains("~") && cKey.contains("~")){
                    // 列和行 都 带有 agg 函数 暂时不处理这种
                }else if(rKey.contains("~")){
                    //行中  带有 agg 函数处理
                    String aggName = "";
                    String[] strs = rKey.split("-");
                    List<String> tempRkeyList = Lists.newArrayList();
                    for(String str : strs){
                        if(!str.contains("~")) {
                            tempRkeyList.add(str);
                        }else{
                            aggName = str.split("~")[1];
                        }
                    }
                    if(tempRkeyList.size() == 0){
                        tempRkeyList.add("root");
                    }
                    
                    String tempRkey = Joiner.on("-").join(tempRkeyList);
                    if(aggName.equals("sum") && table.get(tempRkey, cKey) != null){
                        double aggValue = AggTools.sum(Lists.newArrayList(String.valueOf(table.get(tempRkey, cKey)).split(",")));
                        valueTable.put(r, c, aggValue);
                    }
                    if(aggName.equals("avg") && table.get(tempRkey, cKey) != null){
                        double aggValue = AggTools.avg(Lists.newArrayList(String.valueOf(table.get(tempRkey, cKey)).split(",")));
                        valueTable.put(r, c, aggValue);
                    }
                    if(aggName.equals("min") && table.get(tempRkey, cKey) != null){
                        double aggValue = AggTools.min(Lists.newArrayList(String.valueOf(table.get(tempRkey, cKey)).split(",")));
                        valueTable.put(r, c, aggValue);
                    }
                    
                }else if(cKey.contains("~")){
                    //列中  带有 agg 函数处理
                    String aggName = "";
                    String[] strs = cKey.split("-");
                    List<String> tempCkeyList = Lists.newArrayList();
                    for(String str : strs){
                        if(!str.contains("~")) {
                            tempCkeyList.add(str);
                        }else{
                            aggName = str.split("~")[1];
                        }
                    }
                    if(tempCkeyList.size() == 1){
                        tempCkeyList.add(0, "root");
                    }
                    String tempCkey = Joiner.on("-").join(tempCkeyList);
                    if(aggName.equals("sum") && table.get(rKey, tempCkey) != null){
                        double aggValue = AggTools.sum(Lists.newArrayList(String.valueOf(table.get(rKey, tempCkey)).split(",")));
                        valueTable.put(r, c, aggValue);
                    }
                    if(aggName.equals("avg") && table.get(rKey, tempCkey) != null){
                        double aggValue = AggTools.avg(Lists.newArrayList(String.valueOf(table.get(rKey, tempCkey)).split(",")));
                        valueTable.put(r, c, aggValue);
                    }
                    if(aggName.equals("chart") && table.get(rKey, tempCkey) != null){
                        List<Double> aggValue = AggTools.chart(Lists.newArrayList(String.valueOf(table.get(rKey, tempCkey)).split(",")));
                        valueTable.put(r, c, aggValue);
                    }
                }else if(table.get(rKey, cKey) != null){
                    valueTable.put(r, c, table.get(rKey, cKey));
                }
                
                
                
            });
        });
    }
}
