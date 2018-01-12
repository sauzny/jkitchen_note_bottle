package com.sauzny.crosstable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Range;
import com.google.common.collect.Table;
import com.sauzny.crosstable.cell.HeaderCell;
import com.sauzny.crosstable.tools.StopWatch;

public class CrossTabulationsTest {

    public static ResultEntity buildResultEntity(){
        
        ResultEntity resultEntity = new ResultEntity();
        
        try {
            
            String userDir = System.getProperty("user.dir");
            Path path = Paths.get(userDir+"/ct_data.txt");
            List<String> lines = Files.readAllLines(path);
            
            Object[][] objss = new Object[lines.size()-1][lines.get(0).split("\t").length];
            
            for(int i=1; i<lines.size(); i++){
                String[] values = lines.get(i).split("\t");
                for(int j=0; j<values.length; j++){
                    if(Range.closed(3, 6).contains(j)){
                        objss[i-1][j] = Integer.parseInt(values[j]);
                    }else{
                        objss[i-1][j] = values[j];
                    }
                }
            }
            
            resultEntity.setColumns(Lists.newArrayList(lines.get(0).split("\t")));
            resultEntity.setData(objss);
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return resultEntity;
    }
    
    public static void main(String[] args) {
        
        Lists.newArrayList("JVM级别预热foreach").forEach(System.out::println);

        //boolean isTablePrint = true;
        boolean isTablePrint = false;
        
        boolean isExcel = true;
        //boolean isExcel = false;
        
        //boolean isResult = true;
        boolean isResult = false;
        
        // 这个我把spring里的StopWatch复制过来了……
        StopWatch clock = new StopWatch("交叉表demo");
        
        // 模拟输入参数
        
        List<String> top = Lists.newArrayList("洲", "国家", "城市");
        List<String> left = Lists.newArrayList("年", "月");
        List<String> value = Lists.newArrayList("收入", "支出");
        
        // 模拟输入参数 聚合函数
        List<String> topAgg = Lists.newArrayList("洲~sum", "国家~sum", "国家~avg", "国家~chart");
        List<String> leftAgg = Lists.newArrayList("年~min");
        List<String> valueAgg = Lists.newArrayList("top~sum", "left~sum");
        
        clock.start("组织测试数据");
        ResultEntity resultEntity = CrossTabulationsTest.buildResultEntity();
        clock.stop();
        
        clock.start("确认  top left value 字段位置 ");
        
        // 分别确认 top left value 字段位置
        
        List<Integer> topIndex = Lists.newArrayList();
        List<Integer> leftIndex = Lists.newArrayList();
        List<Integer> valueIndex = Lists.newArrayList();
        
        Map<String, Integer> columnIndexMap = Maps.newHashMap();
        
        for(int i=0; i<resultEntity.getColumns().size(); i++){
            String str = resultEntity.getColumns().get(i);
            columnIndexMap.put(str, i);
        }
        
        top.forEach(name -> topIndex.add(columnIndexMap.get(name)) );
        left.forEach(name -> leftIndex.add(columnIndexMap.get(name)) );
        value.forEach(name -> valueIndex.add(columnIndexMap.get(name)) );

        clock.stop();
        clock.start("组织table，组织header的普通表头");

        // 组织table
        Table<String, String, Object> table = HashBasedTable.create();
        
        // 组织header
        ListMultimap<Object, Object> topHeader = ArrayListMultimap.create();
        ListMultimap<Object, Object> leftHeader = ArrayListMultimap.create();
        
        BuildTableAndHeader.build(value, topIndex, leftIndex, valueIndex, resultEntity.getData(), table, topHeader, leftHeader);
        
        clock.stop();
        
        
        clock.start("agg函数插入表头");
        // agg 插里面去
        
        BuildAggHeader.buildTop(topAgg, top, topHeader);
        BuildAggHeader.buildLeft(leftAgg, left, leftHeader);
        BuildAggHeader.buildValue(valueAgg, topHeader, leftHeader);
        
        clock.stop();
        
        // 打印 table
        if(isTablePrint) table.cellSet().forEach(System.out::println);
        
        // 左上角第一个 cell
        HeaderCell leftTopCell = new HeaderCell();
        leftTopCell.setColspan(left.size());
        leftTopCell.setRowspan(top.size()+1);

        
        clock.start("组织topHeaderTable");
        // 制作 topHeaderTable
        Table<Integer, Integer, Object> topHeaderTable = HashBasedTable.create();

        BuildHeaderTable.buildTop(leftTopCell, topHeaderTable, top, topHeader, value);
        
        // 打印 topHeaderTable
        if(isTablePrint) topHeaderTable.cellSet().forEach(System.out::println);
        
        clock.stop();
        
        clock.start("组织leftHeaderTable");

        // 制作 leftHeaderTable
        Table<Integer, Integer, Object> leftHeaderTable = HashBasedTable.create();
        
        BuildHeaderTable.buildLeft(leftTopCell, leftHeaderTable, left, leftHeader);
        // 打印 leftHeaderTable
        if(isTablePrint) leftHeaderTable.cellSet().forEach(System.out::println);

        clock.stop();
        
        clock.start("组织valueTable");
        
        // 制作 value table
        Table<Integer, Integer, Object> valueTable = HashBasedTable.create();
        
        BuildValueTable.build(table, topHeaderTable, leftHeaderTable, valueTable);
        
        // 打印 leftHeaderTable
        if(isTablePrint) valueTable.cellSet().forEach(System.out::println);
        
        clock.stop();
        System.out.println(clock.prettyPrint());
        
        if(isResult) CrossTableResultBuilder.build(leftTopCell, topHeaderTable, leftHeaderTable, valueTable);
        
        if(isExcel) ExcelTest.buildExcel(topHeaderTable, leftHeaderTable, valueTable);
    }
}
