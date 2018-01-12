package com.sauzny.crosstable;

import java.util.List;

import com.google.common.collect.Lists;
import com.google.common.collect.Table;
import com.sauzny.crosstable.cell.HeaderCell;
import com.sauzny.crosstable.tools.CtTools;

public final class BuildCtrHead {

    private BuildCtrHead(){}

    public static void build(
            HeaderCell leftTopCell,
            Table<Integer, Integer, Object> topHeaderTable,
            CrossTableResult ctr){

        topHeaderTable.rowKeySet().forEach(rowKey -> {
            ctr.getColHearders().add(Lists.newArrayList());
        });
        
        // 行数
        int rowNum = topHeaderTable.rowKeySet().size();
        //列数
        int columnNum = topHeaderTable.columnKeySet().size();
        
        // 循环行
        for(int rowKey=0; rowKey<rowNum; rowKey++){

            List<HeaderCell> currentRow = ctr.getColHearders().get(rowKey);
            
            // 循环列
            for(int columnKey=leftTopCell.getColspan(); columnKey<columnNum+leftTopCell.getColspan(); columnKey++){
                
                // 当前结果行中的最后一个元素
                HeaderCell lastCell = CtTools.getLast(currentRow);
                
                // 当前元素
                HeaderCell currentCell = new HeaderCell();
                Object value = topHeaderTable.get(rowKey, columnKey);
                currentCell.setData(value);
                
                // 先计算   表头 rowSpan
                /*
                boolean isNeedRowSpan = false;
                if(CtTools.isAgg(value)){
                    if(rowKey == 0){
                        isNeedRowSpan = true;
                    }else if (CtTools.isNotAgg(topHeaderTable.get(rowKey-1, columnKey))){
                        isNeedRowSpan = true;
                    }else{
                        // 此元素不需要再计算，直接跳出循环列
                        break;
                    }
                }
                
                if(isNeedRowSpan){
                    for(int k=rowKey+1; k<rowNum; k++){
                        if(CtTools.isAgg(topHeaderTable.get(k, columnKey))){
                            currentCell.setRowspan(currentCell.getRowspan()+1);
                        }else{
                            break;
                        }
                    }
                }
                */
                // 再计算   表头 colSpan
                if(currentRow.size() > 0){
                    
                    if(!lastCell.equals(currentCell)){
                        currentRow.add(currentCell);
                    }else{
                        lastCell.setColspan(lastCell.getColspan()+1);
                        /*
                        if(isNeedRowSpan){
                            lastCell.setRowspan(currentCell.getRowspan());
                        }
                        */
                    }
                    
                }else{
                    currentRow.add(currentCell);
                }
            }

        }
        // 左上角补充
        ctr.getColHearders().forEach(row -> {
            HeaderCell cell = new HeaderCell();
            cell.setData("");
            cell.setColspan(leftTopCell.getColspan());
            row.add(0,cell);
        });
    }
}
