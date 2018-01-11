package com.sauzny.ssq;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sauzny.ssq.entity.SsqEntity;
import com.sauzny.ssq.entity.YuCeLanTemp;


/**
 * *************************************************************************
 * @文件名称: ForecastLan.java
 *
 * @包路径  : com.sauzny.jkitchen_note.demo.ssq 
 *				 
 * @版权所有: Personal xinxin (C) 2017
 *
 * @类描述:   预测蓝球
 * 
 * @创建人:   ljx 
 *
 * @创建时间: 2018年1月11日 - 上午8:58:08 
 *	
 **************************************************************************
 */
public final class ForecastLan {

    private ForecastLan(){}
    
    public static List<YuCeLanTemp> forecast(){
        
        List<String> lines = DataManager.loadHistory();
        Collections.reverse(lines);
        
        // 获取 目标期数 id 和 期号  上次的开奖日期
        String[] lastLine = lines.get(0).split("\t");
        
        int targetQihao = Integer.parseInt(lastLine[1]) + 1;
    
        // 蓝球数字 - 得分
        Map<Integer, Integer> resultLan = Maps.newHashMap();

        // 变换数据类型， String -> SsqEntity
        Stream<SsqEntity> stream = lines.stream().limit(100).map(line -> new SsqEntity(line) );

        // group by
        Map<Integer, List<SsqEntity>> map = stream.collect(Collectors.groupingBy(SsqEntity::getLan, Collectors.toList()));

        // 计算得分
        map.forEach((k, v) -> {
            Optional<SsqEntity> ssqEntity = v.stream().collect(Collectors.maxBy(Comparator.comparing(SsqEntity::getQihao)));
            // System.out.println("lan : " + k + "； count : " + v.size()+"；上次期号"+ssqEntity.get().getQihao());
            resultLan.put(k, v.size() * (targetQihao - ssqEntity.get().getQihao()));
        });
        
        // 将结果转换为list，再按照得分排序
        List<YuCeLanTemp> resultLanList = Lists.newArrayList();
        
        resultLan.forEach((k,v)-> {
            YuCeLanTemp e = new YuCeLanTemp();
            e.setNum(k);
            e.setScore(v);
            resultLanList.add(e);
        });
        
        Collections.sort(resultLanList, (q1,q2) -> q2.getScore()-q1.getScore());
        /*
        System.out.println("蓝球预测如下：");
        resultLanList.forEach(System.out::println);
        */
        return resultLanList;
    }
}
