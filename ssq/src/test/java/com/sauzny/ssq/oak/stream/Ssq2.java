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
public class Ssq2 {

    /**
     * @描述: 添加开奖历史记录
     * @param targetId
     * @param targetQihao
     * @param dataPath
     * @返回 void
     * @创建人  ljx 创建时间 2017年10月10日 上午9:27:33
     */
    public static void addHistory(int targetId, int targetQihao, Path dataPath){
        try {
            // 获取指定期号的数据 from web
            List<String> list = SsqJsoup.last(targetQihao);
            list.add(0, String.valueOf(targetId));
            SsqEntity ssqEntity = new SsqEntity(list);
            
            insertDataToFile(ssqEntity, dataPath);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("暂时不能增加 "+targetQihao+" 期数据");
        }
    }
    
    /**
     * @描述: 添加预测数据记录
     * @param targetId
     * @param targetQihao
     * @param lastSsqDate
     * @param yucePath
     * @param result
     * @返回 void
     * @创建人  ljx 创建时间 2017年10月10日 上午9:27:45
     */
    public static void addYuce(int targetId, int targetQihao, LocalDate lastSsqDate, Path yucePath, List<String> result){
        result.add(0, String.valueOf(targetId));
        result.add(1, String.valueOf(targetQihao));
        result.add(2, Time.nextSsqDate(lastSsqDate).toString());
        SsqEntity ssqEntity = new SsqEntity(result);
        
        try {
            List<String> lines = Files.readAllLines(yucePath);
            String preId = lines.get(lines.size()-1).split("\t")[0];
            if(ssqEntity.getId() > Integer.parseInt(preId)){
                insertDataToFile(ssqEntity, yucePath);
            }else{
                System.out.println("预测数据已经存在");
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /**
     * @描述: 将记录写入文件
     * @param ssqEntity
     * @param dataPath
     * @返回 void
     * @创建人  ljx 创建时间 2017年10月10日 上午9:28:01
     */
    public static void insertDataToFile(SsqEntity ssqEntity, Path dataPath){
        try {
            Files.write(dataPath, ("\n"+ssqEntity.allValues()).getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /**
     * @描述: 预测下期开奖结果
     * @param targetQihao
     * @param lines
     * @param yucePath
     * @return
     * @返回 List<String>
     * @创建人  ljx 创建时间 2017年10月10日 上午9:28:25
     */
    public static List<YuCeTemp> yuceLan(int targetQihao, List<String> lines, Path yucePath){
        
        // 预测蓝球
        Map<Integer, Integer> resultLan = yuceCore(targetQihao, lines.stream().limit(100), SsqEntity::getLan);

        List<YuCeTemp> resultLanList = Lists.newArrayList();
        
        resultLan.forEach((k,v)-> {
            YuCeTemp e = new YuCeTemp();
            e.setNum(k);
            e.setScore(v);
            resultLanList.add(e);
        });
        
        Collections.sort(resultLanList, (q1,q2) -> q2.getScore()-q1.getScore());

        System.out.println("蓝球预测如下：");
        resultLanList.forEach(System.out::println);
        
        return resultLanList;
    }
    
    /**
     * @描述: 预测核心
     * @param targetQihao
     * @param lines
     * @param classifier
     * @return
     * @返回 Map<Integer,Integer>
     * @创建人  ljx 创建时间 2017年10月10日 上午9:29:26
     */
    public static Map<Integer, Integer> yuceCore(int targetQihao, Stream<String> lines, Function<? super SsqEntity, ? extends Integer> classifier){
        
        // 与定义结果
        Map<Integer, Integer> result = Maps.newHashMap();
        
        // 变换数据类型， String -> SsqEntity
        Stream<SsqEntity> stream = lines.map(line -> {
            String[] values = line.split("\t");
            return new SsqEntity(values);
        });
        
        // group by
        Map<Integer, List<SsqEntity>> map = stream.collect(Collectors.groupingBy(classifier, Collectors.toList()));
        
        // 计算得分
        map.forEach((k,v)->{
            Optional<SsqEntity> ssqEntity= v.stream().collect(Collectors.maxBy(Comparator.comparing(SsqEntity::getQihao)));
            //System.out.println("lan : " + k + "； count : " + v.size()+"；上次期号"+ssqEntity.get().getQihao());
            result.put(k, v.size()*(targetQihao-ssqEntity.get().getQihao()));
        });
        
        return result;
    }
    
    public static void run(){
        
        try {
            
            // 定义数据文件位置
            String userDir = System.getProperty("user.dir");
            Path historyPath = Paths.get(userDir+"/history.ssq");
            Path yucePath = Paths.get(userDir+"/yuce.ssq");
            
            // 读取数据
            List<String> lines = Files.readAllLines(historyPath);
            
            Collections.reverse(lines);

            // 获取 目标期数 id 和 期号  上次的开奖日期
            String[] lastLine = lines.get(0).split("\t");
            
            int targetId = Integer.parseInt(lastLine[0]) + 1;
            int targetQihao = Integer.parseInt(lastLine[1]) + 1;
            LocalDate lastSsqDate = LocalDate.parse(lastLine[2]);
            
            // 增加开奖历史数据
            //Ssq2.addHistory(targetId, targetQihao, historyPath);
            
            // 预测下期数据
            List<YuCeTemp> result = Ssq2.yuceLan(targetQihao, lines, yucePath);
            
            // 将结果写入预测文件
            //Ssq2.addYuce(targetId, targetQihao, lastSsqDate, yucePath, result);
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        Ssq2.run();
    }
}
