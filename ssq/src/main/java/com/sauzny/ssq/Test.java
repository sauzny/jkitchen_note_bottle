package com.sauzny.ssq;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.base.Joiner;
import com.sauzny.ssq.entity.SsqEntity;
import com.sauzny.ssq.entity.YuCeHongTemp;
import com.sauzny.ssq.entity.YuCeLanTemp;

public class Test {

    // 旧数据转换新数据
    // history.ssq -> history_ssq.dat
    public static void old2New(){
        try {
            
            List<String> oldLines = Files.readAllLines(Paths.get(DataManager.rootPath+"history.ssq"), StandardCharsets.UTF_8);
            
            List<String> newLines = oldLines.stream().map(line -> new SsqEntity(line).line()).collect(Collectors.toList());
            
            Files.write(Paths.get(DataManager.rootPath+"history_ssq.dat"), newLines, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    
    public static void main(String[] args) {
    }
}
