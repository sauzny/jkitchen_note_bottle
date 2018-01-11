package com.sauzny.ssq.oak.stream;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.base.Joiner;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import com.google.common.collect.Sets;

import lombok.Data;

public class SsqLab {

    // 每年蓝球出现的次数 - SQL
    public static void lanfbByYearSQL(){
        
        List<String> countSqlList = Lists.newArrayList();
        
        for(int i=1;i<17;i++){
            countSqlList.add("count(CASE lan WHEN "+i+" THEN id END) '"+i+"'");
        }
        
        String countSql = Joiner.on(",").join(countSqlList);
        
        String sql = "select year(riqi) riqi_year, "+ countSql +" from ssq GROUP BY riqi_year ORDER BY riqi_year desc;";
        
        System.out.println(sql);
        
    }
    
    // 每年蓝球出现的次数 - Echarts
    public static void lanfbByYearEcharts(){
        try{
            String userDir = System.getProperty("user.dir");
            Path lanfbByYearPath = Paths.get(userDir+"/lanfbByYear.result");
            List<String> lines = Files.readAllLines(lanfbByYearPath);
            Collections.reverse(lines);
            
            
            // lans
            List<String> lansList = Lists.newArrayList();
            
            for(int i=1;i<17;i++){
                lansList.add("'"+i+"'");
            }
            
            String lans = "var lans = ["+Joiner.on(",").join(lansList)+"];";
            
            System.out.println(lans);
            
            
            // years
            List<String> yearsList = Lists.newArrayList();
            for(String line : lines){
                yearsList.add("'"+line.split("\t")[0]+"'");
            }
            String years = "var years = ["+Joiner.on(",").join(yearsList)+"];";
            
            System.out.println(years);
            
            // data
            // [year,lan,times]
            List<String> dataList = Lists.newArrayList();
            for(int year=0; year<lines.size(); year++){
                String[] timess = lines.get(year).split("\t");
                for(int lan=1;lan<timess.length;lan++){
                    dataList.add("["+year+","+(lan-1)+","+timess[lan]+"]");
                }
            }
            String data = "var data = ["+Joiner.on(",").join(dataList)+"];";
            
            System.out.println(data);
            
        }catch(Exception e){
            
        }
    }
    
    // 遍历蓝球，查询每个红球的出现次数
    public static ListMultimap<Integer, SsqH> hongfbBylan(){
        
        ListMultimap<Integer, SsqH> listMultimap = ArrayListMultimap.create();
        
        try{
            // 定义数据文件位置
            String userDir = System.getProperty("user.dir");
            Path historyPath = Paths.get(userDir+"/history.ssq");
            
            // 读取数据
            List<String> lines = Files.readAllLines(historyPath);
            
            Collections.reverse(lines);
            
            // 变换数据类型， String -> SsqEntity
            Stream<SsqEntity> stream = lines.stream().map(line -> {
                String[] values = line.split("\t");
                return new SsqEntity(values);
            });
            
            Map<Integer, List<SsqEntity>> map = stream.collect(Collectors.groupingBy(SsqEntity::getLan, Collectors.toList()));
            
            // 计算得分
            map.forEach((k,v)->{
                
                //System.out.println("lan : " + k + "； count : " + v.size()+"；上次期号"+ssqEntity.get().getQihao());
                
                //System.out.println("蓝球号码\t红球号码\t出现次数\t平均次数\t出现概率");
                
                Multiset<Integer> multiset = HashMultiset.create();
                
                v.forEach( ssqEntity -> {
                    multiset.add(ssqEntity.getShunxu1());
                    multiset.add(ssqEntity.getShunxu2());
                    multiset.add(ssqEntity.getShunxu3());
                    multiset.add(ssqEntity.getShunxu4());
                    multiset.add(ssqEntity.getShunxu5());
                    multiset.add(ssqEntity.getShunxu6());
                });
                
                int hongTotalCount = 0;
                
                for(Multiset.Entry<Integer> entry : multiset.entrySet()){
                    hongTotalCount += entry.getCount();
                }
                
                for(Multiset.Entry<Integer> entry : multiset.entrySet()){
                    
                    BigDecimal a0 = BigDecimal.valueOf(entry.getCount());
                    BigDecimal a1 = BigDecimal.valueOf(hongTotalCount);
                    
                    //System.out.println(k + "\t" + entry.getElement() + "\t" + entry.getCount() + "\t" + a1.divide(BigDecimal.valueOf(33), 2, BigDecimal.ROUND_HALF_DOWN) + "\t" + a0.multiply(BigDecimal.valueOf(100).divide(a1, 2, BigDecimal.ROUND_HALF_DOWN)) + "%");
                
                    SsqH ssqH = new SsqH(entry.getElement(), entry.getCount());
                    
                    listMultimap.put(k, ssqH);
                }
                
            });
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return listMultimap;
    }

    // 根据篮球获取历史记录
    public static List<SsqEntity> getSsqListByLan(Integer lan){
        return getSsqListByLan(lan, 5);
    }
    
    public static List<SsqEntity> getSsqListByLan(Integer lan, Integer limit){
        
        List<SsqEntity> list = Lists.newArrayList();
        
        try{
            // 定义数据文件位置
            String userDir = System.getProperty("user.dir");
            Path historyPath = Paths.get(userDir+"/history.ssq");
            
            // 读取数据
            List<String> lines = Files.readAllLines(historyPath);
            
            Collections.reverse(lines);
            
            // 变换数据类型， String -> SsqEntity
            Stream<SsqEntity> stream = lines.stream().map(line -> {
                String[] values = line.split("\t");
                return new SsqEntity(values);
            });
            
            list = stream.filter(ssqEntity -> ssqEntity.getLan() - lan == 0).limit(limit).collect(Collectors.toList());
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return list;
    }
    
    public static void yuce02(){
        try {
            /*// 获取蓝球
            String userDir = System.getProperty("user.dir");
            Path yucePath = Paths.get(userDir+"/yuce.ssq");
            List<String> list = Files.readAllLines(yucePath);
            String[] values = list.get(list.size()-1).split("\t");
            Integer lan = Integer.parseInt(values[values.length-1]);
            */
            
            Integer lan = 2;
            
            // 获取历史记录
            List<SsqEntity> ssqListByLan = getSsqListByLan(lan, 7);
            
            // 中奖红球set
            Set<Integer> set = Sets.newHashSet();
            for(SsqEntity ssqEntity : ssqListByLan){
                set.add(ssqEntity.getShunxu1());
                set.add(ssqEntity.getShunxu2());
                set.add(ssqEntity.getShunxu3());
                set.add(ssqEntity.getShunxu4());
                set.add(ssqEntity.getShunxu5());
                set.add(ssqEntity.getShunxu6());
            }

            
            // 红球中奖次数统计
            ListMultimap<Integer, SsqH> listMultimap = SsqLab.hongfbBylan();
            List<SsqH> ssqHList = listMultimap.get(lan);

            // 去除最近中奖的红球
            for(Iterator<SsqH> iterator = ssqHList.iterator();iterator.hasNext();){
                SsqH ssqH = iterator.next();
                if(set.contains(ssqH.getHongNum())){
                    iterator.remove();
                }
            }
            
            // 按中奖次数↓排序
            ssqHList.sort((SsqH h1, SsqH h2) -> h2.getCount() - h1.getCount());
            
            //if (ssqHList.size() < 6) System.out.println("预测红球数量不足6个，将208行设置为5以下");
            //if (ssqHList.get(6).getCount() == ssqHList.get(5).getCount()) System.out.println("第六个红球与第七个红球出现次数相等，将208行设置为5以上");
            
            ssqHList.forEach(System.out::println);
            System.out.println("蓝球：" + lan);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws IOException {
        //SsqLab.lanfbByYearEcharts();
        
        //SsqLab.hongfbBylan();
        
        SsqLab.yuce02();
        /*
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
        
        
        // 预测下期数据
        List<String> result = Ssq.yuce(targetQihao, lines, yucePath);
        */
    }
}

@Data
class SsqH {
    private int hongNum;
    private int count;
    
    public SsqH(int hongNum, int count) {
        super();
        this.hongNum = hongNum;
        this.count = count;
    }
    
}