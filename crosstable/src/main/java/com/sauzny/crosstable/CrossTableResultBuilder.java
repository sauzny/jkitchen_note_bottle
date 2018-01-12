package com.sauzny.crosstable;

import com.google.common.collect.Table;
import com.sauzny.crosstable.cell.HeaderCell;
import com.sauzny.crosstable.tools.JacksonTools;

public final class CrossTableResultBuilder {

    private CrossTableResultBuilder(){}
    
    public static CrossTableResult build(
            HeaderCell leftTopCell,
            Table<Integer, Integer, Object> topHeaderTable,
            Table<Integer, Integer, Object> leftHeaderTable,
            Table<Integer, Integer, Object> valueTable){
        
        CrossTableResult ctr = new CrossTableResult();
        
        BuildCtrHead.build(leftTopCell, topHeaderTable, ctr);
        BuildCtrBody.build(leftTopCell, leftHeaderTable, valueTable, ctr);
        BuildCtrLeft.build(leftTopCell, leftHeaderTable, ctr);
        
        // 打印结果
        //JsonMapper j = new JsonMapper(Include.NON_NULL);
        JacksonTools j = JacksonTools.nonNull();
        
        //String json = JsonMapper.nonEmptyMapper().toJson(ctr);
        //String json = JsonMapper.nonDefaultMapper().toJson(ctr);
        String json = j.toJson(ctr);
        System.out.println(json);
        
        return ctr;
    }
}
