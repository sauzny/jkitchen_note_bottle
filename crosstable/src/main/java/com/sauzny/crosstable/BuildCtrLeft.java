package com.sauzny.crosstable;

import java.util.Iterator;
import java.util.List;

import com.google.common.collect.Table;
import com.sauzny.crosstable.cell.HeaderCell;
import com.sauzny.crosstable.cell.LeftCellDesc;
import com.sauzny.crosstable.tools.CtTools;

public final class BuildCtrLeft {

    private BuildCtrLeft(){}
    
    public static void build(
            HeaderCell leftTopCell,
            Table<Integer, Integer, Object> leftHeaderTable,
            CrossTableResult ctr){
        
        List<LeftCellDesc> leftCellDesc = ctr.getMergeCells();
        
        // 行数
        int rowNum = leftHeaderTable.rowKeySet().size();
        //列数
        int columnNum = leftHeaderTable.columnKeySet().size();
        
        // 循环行
        for(int columnKey=0; columnKey<columnNum; columnKey++){
         // 循环列
            for(int rowKey=leftTopCell.getRowspan(); rowKey<rowNum+leftTopCell.getRowspan(); rowKey++){
                // 当前结果中的最后一个元素
                LeftCellDesc lastCell = CtTools.getLast(leftCellDesc);
                
                // 当前元素
                LeftCellDesc currentCell = new LeftCellDesc();
                Object value = leftHeaderTable.get(rowKey, columnKey);
                currentCell.setRow(rowKey-leftTopCell.getRowspan());
                currentCell.setCol(columnKey);
                currentCell.setData(value);
                
                
                // 先计算   表头 colSpan
                boolean isNeedColSpan = false;
                if(CtTools.isAgg(value)){
                    if(columnKey == 0){
                        isNeedColSpan = true;
                    }else if (CtTools.isNotAgg(leftHeaderTable.get(rowKey, columnKey-1))){
                        isNeedColSpan = true;
                    }else{
                        // 此元素不需要再计算，直接跳出循环列
                        break;
                    }
                }
                
                if(isNeedColSpan){
                    for(int k=columnKey+1; k<columnNum; k++){
                        if(CtTools.isAgg(leftHeaderTable.get(rowKey, k))){
                            currentCell.setColspan(currentCell.getColspan()+1);
                        }else{
                            break;
                        }
                    }
                }
                
                // 再计算   表头 rowSpan
                if(leftCellDesc.size() > 0){
                    
                    if(!lastCell.equals(currentCell)){
                        leftCellDesc.add(currentCell);
                    }else{
                        lastCell.setRowspan(lastCell.getRowspan()+1);
                        if(isNeedColSpan){
                            lastCell.setColspan(currentCell.getColspan());
                        }
                    }
                    
                }else{
                    leftCellDesc.add(currentCell);
                }
            }
        }
        
        // 删除  colSpan==1 && rowSpan==1 的元素
        for(Iterator<LeftCellDesc> iterator = leftCellDesc.iterator();iterator.hasNext();){
            LeftCellDesc currentCell = iterator.next();
            if(currentCell.getColspan() == 1 && currentCell.getRowspan() ==1){
                iterator.remove();
            }
        }
    } 
}
