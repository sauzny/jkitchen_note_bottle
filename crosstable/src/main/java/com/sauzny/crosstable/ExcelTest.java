package com.sauzny.crosstable;

import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;

import com.google.common.collect.Table;

public final class ExcelTest {

    private ExcelTest(){}
    
    public static void buildExcel(
            Table<Integer, Integer, Object> topHeaderTable,
            Table<Integer, Integer, Object> leftHeaderTable,
            Table<Integer, Integer, Object> valueTable
            ){

        // 1.创建Excel工作薄对象
        HSSFWorkbook wb = new HSSFWorkbook();
        // 2.创建Excel工作表对象
        HSSFSheet sheet = wb.createSheet("A");
        // 4.创建单元格样式
        CellStyle cellStyle = wb.createCellStyle();
        // 设置这些样式
        cellStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        
        
        
        for(Table.Cell<Integer, Integer, Object> temp : topHeaderTable.cellSet()){
            
            int rowkey = temp.getRowKey();
            int columnkey = temp.getColumnKey();
            String value = String.valueOf(temp.getValue());

            Row row = sheet.getRow(rowkey);
            if(row == null){
                row = sheet.createRow(rowkey);
            }
            
            row.createCell(columnkey).setCellStyle(cellStyle);
            row.createCell(columnkey).setCellType(CellType.STRING);
            row.createCell(columnkey).setCellValue(value);
            
        }
        
        for (Table.Cell<Integer, Integer, Object> temp : leftHeaderTable.cellSet()) {

            int rowkey = temp.getRowKey();
            int columnkey = temp.getColumnKey();
            String value = String.valueOf(temp.getValue());

            Row row = sheet.getRow(rowkey);
            if (row == null) {
                row = sheet.createRow(rowkey);
            }

            row.createCell(columnkey).setCellStyle(cellStyle);
            row.createCell(columnkey).setCellType(CellType.STRING);
            row.createCell(columnkey).setCellValue(value);

        }

        for (Table.Cell<Integer, Integer, Object> temp : valueTable.cellSet()) {

            int rowkey = temp.getRowKey();
            int columnkey = temp.getColumnKey();
            String value = String.valueOf(temp.getValue());

            Row row = sheet.getRow(rowkey);
            if (row == null) {
                row = sheet.createRow(rowkey);
            }

            row.createCell(columnkey).setCellStyle(cellStyle);
            row.createCell(columnkey).setCellType(CellType.STRING);
            row.createCell(columnkey).setCellValue(value);

        }
        
        
        // 最后一步，将文件存到指定位置
        try {
            FileOutputStream fout = new FileOutputStream("D:/d.xls");
            wb.write(fout);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
