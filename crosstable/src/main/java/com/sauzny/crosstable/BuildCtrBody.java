package com.sauzny.crosstable;

import java.util.List;

import com.google.common.collect.Lists;
import com.google.common.collect.Table;
import com.sauzny.crosstable.cell.Cell;
import com.sauzny.crosstable.cell.HeaderCell;

public final class BuildCtrBody {

    private BuildCtrBody(){}
    
    public static void build(
            HeaderCell leftTopCell,
            Table<Integer, Integer, Object> leftHeaderTable,
            Table<Integer, Integer, Object> valueTable,
            CrossTableResult ctr){
        leftHeaderTable.rowKeySet().forEach(rowKey -> {
            ctr.getData().add(Lists.newArrayList());
        });
        
        // 行数
        int rowNum = leftHeaderTable.rowKeySet().size();
        
        //列数
        int leftHeaderColumnNum = leftHeaderTable.columnKeySet().size();
        int valueColumnNum = valueTable.columnKeySet().size();
        
        // 
        for(int rowKey=leftTopCell.getRowspan(); rowKey<rowNum+leftTopCell.getRowspan(); rowKey++){

            List<Cell> currentRow = ctr.getData().get(rowKey-leftTopCell.getRowspan());

            // leftHeaderTable 元素计算 
            for(int columnKey=0;columnKey<leftHeaderColumnNum;columnKey++){
                Cell cell = new Cell();
                cell.setData(leftHeaderTable.get(rowKey, columnKey));
                currentRow.add(cell);
            }
            
            // valueTable 元素计算 
            for(int columnKey=leftTopCell.getColspan();columnKey<valueColumnNum+leftTopCell.getColspan();columnKey++){
                Cell cell = new Cell();
                if(valueTable.get(rowKey, columnKey) == null){
                    cell.setData("");
                }else{
                    cell.setData(valueTable.get(rowKey, columnKey));
                    /*
                    if(!NumberUtils.isCreatable(String.valueOf(valueTable.get(rowKey, columnKey)))){
                        cell.setChartType("column");
                    }
                    */
                }
                currentRow.add(cell);
            }
        }
    }
}
