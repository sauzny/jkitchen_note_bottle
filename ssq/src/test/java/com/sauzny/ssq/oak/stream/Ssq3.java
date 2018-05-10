package com.sauzny.ssq.oak.stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sauzny.ssq.jsoup.SsqJsoup;
import com.sauzny.ssq.oak.time.Time;


/**
 * *************************************************************************
 * @文件名称: Ssq2.java
 *
 * @包路径  : com.sauzny.jkitchen_note.oak.stream 
 *				 
 * @版权所有: Personal xinxin (C) 2017
 *
 * @类描述:   TODO
 * 
 * @创建人:   ljx 
 *
 * @创建时间: 2017年12月4日 - 上午9:51:02 
 *	
 **************************************************************************
 */
public class Ssq3 {

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
            
            List<SsqEntity> list = stream.collect(Collectors.toList());
            
            // 定义最终结果
            List<Integer> result = Lists.newArrayList();
            
            for(int i=1;i<list.size();i++){
                if(list.get(i).getLan() == list.get(i-1).getLan()){
                    result.add(list.get(i-1).getId());
                    result.add(list.get(i).getId());
                }
            }
            
            System.out.println(result);
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    // 根据最后一期蓝球数字，找到历史上所有 此数字 的下一期的 id
    public static void foo02(){
        
        try {

            // 定义数据文件位置
            String userDir = System.getProperty("user.dir");
            Path historyPath = Paths.get(userDir+"/history_ssq.dat");
            
            // 读取数据
            List<String> lines = Files.readAllLines(historyPath);
            lines.remove(0);

            // 最后一期蓝球数字
            String[] lastValues = Iterators.getLast(lines.iterator()).split("\t");            
            int lastLan = new SsqEntity(lastValues).getLan();

            // 1. 变换数据类型， String -> SsqEntity
            // 2. 过滤数据
            // 3. 分组
            Map<Integer, Long> map = lines.stream()
                    .map(line -> {
                        String[] values = line.split("\t");
                        return new SsqEntity(values);
                    })
                    .filter(ssqEntity -> ssqEntity.getLan() == lastLan)
                    .collect(Collectors.groupingBy(SsqEntity::getLan, Collectors.counting()));
            
            map.forEach((k,v) -> System.out.println("lan:" + k + ", 次数：" + v) );
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        Ssq3.foo02();
    }
}
