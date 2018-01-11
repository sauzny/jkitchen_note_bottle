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
        
        // 增加历史数据
        DataManager.addHistory();
        
        System.out.println("增加历史数据 - 完成");
        
        // 预测蓝球
        List<YuCeLanTemp> yuCeLanList = ForecastLan.forecast();
        int lan = yuCeLanList.get(0).getNum();
        
        System.out.println("预测蓝球 - 完成");
        
        // 预测红球
        List<YuCeHongTemp> yuCeHongList = ForecastHong.forecast(lan);
        //System.out.println(yuCeLanList.get(0).getNum());
        //yuCeHongList.forEach(System.out::println);
        
        System.out.println("预测红球 - 完成");
        
        // 增加预测数据
        List<Integer> hongNumList = yuCeHongList.stream().mapToInt(YuCeHongTemp::getHongNum).boxed().collect(Collectors.toList());
        hongNumList.sort( (n1,n2) -> n1.compareTo(n2));
        String ycred = Joiner.on(" ").join(hongNumList);
        DataManager.addForecast(ycred, String.valueOf(lan));
        
        System.out.println("增加预测数据- 完成");
    }
}
