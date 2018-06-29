package com.sauzny.ssq.oak.stream;

import static java.util.stream.Collectors.toList;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.Lists;
import com.sauzny.ssq.ForecastHong;
import com.sauzny.ssq.entity.YuCeHongTemp;

public class Ssq4 {
    
    public static void foo01(){
        
        try {

            // 定义数据文件位置
            String userDir = System.getProperty("user.dir");
            Path historyPath = Paths.get(userDir+"/history_ssq.dat");
            
            // 读取数据
            List<String> lines = Files.readAllLines(historyPath);
            lines.remove(0);
            
            // 变换数据类型， String -> SsqEntity
            Stream<SsqEntity> stream = lines.stream().map(line -> {
                String[] values = line.split("\t");
                return new SsqEntity(values);
            });
            
            List<SsqEntity> list = stream.collect(toList());
            
            List<Integer> lanList = list.stream().mapToInt(SsqEntity::getLan).boxed().collect(Collectors.toList());;

            for(int i=0;i<lanList.size();i++){
                
                System.out.print(lanList.get(i) + "\t");
                
                if(i%3 == 0){
                    System.out.println();
                }
            }
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static void foo02(int lan){
        List<YuCeHongTemp> yuCeHongList = ForecastHong.forecast(lan);

        yuCeHongList.forEach(temp -> System.out.print(temp.getHongNum() + " ") );
        System.out.println(" " + lan);
    }
    
    public static void main(String[] args) {
        Ssq4.foo01();
        
        System.out.println();
        Ssq4.foo02(2);
        
        List<Integer> allHong = Lists.newArrayList();
        for(int i= 1;i<34;i++){
            allHong.add(i);
        }
        
        Collections.shuffle(allHong);
        System.out.println(allHong.subList(0, 6));
    }
}
