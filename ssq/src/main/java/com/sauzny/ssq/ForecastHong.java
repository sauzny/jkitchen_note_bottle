package com.sauzny.ssq;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import com.google.common.collect.Sets;
import com.sauzny.ssq.entity.SsqEntity;
import com.sauzny.ssq.entity.YuCeHongTemp;

/**
 * *************************************************************************
 * @文件名称: ForecastHong.java
 *
 * @包路径  : com.sauzny.jkitchen_note.demo.ssq 
 *				 
 * @版权所有: Personal xinxin (C) 2017
 *
 * @类描述:   预测红球
 * 
 * @创建人:   ljx 
 *
 * @创建时间: 2018年1月11日 - 上午10:27:40 
 *	
 **************************************************************************
 */
public final class ForecastHong {

    private ForecastHong(){}
    
    /**
     * @描述: 循环找出最优解
     * @param lan
     * @return
     * @返回 List<YuCeHongTemp>
     * @创建人  ljx 创建时间 2018年1月11日 上午10:46:09
     */
    public static List<YuCeHongTemp> forecast(int lan){
        List<YuCeHongTemp> ssqHList = Lists.newArrayList();
        List<List<YuCeHongTemp>> resultTempList = Lists.newArrayList();
        
        for(int i=1 ;i<10; i++){
            List<YuCeHongTemp> temp = forecast(lan, i);
            if(temp.size()>5){
                resultTempList.add(temp);
            }else{
                ssqHList = Iterables.getLast(resultTempList);
                break;
            }
        }

        if(ssqHList.size() == 0){
            List<YuCeHongTemp> temp = Iterables.getLast(resultTempList);
            for(int i=0; i<6; i++){
                ssqHList.add(temp.get(i));
            }
        }
        
        return ssqHList;
    }
    
    public static List<YuCeHongTemp> forecast(int lan, int limit){
        
        List<YuCeHongTemp> ssqHList = Lists.newArrayList();
        
        try {
            
            List<String> lines = DataManager.loadHistory();
            Collections.reverse(lines);

            // 获取当前蓝球的历史记录
            List<SsqEntity> ssqListByLan = lines.stream()
                    .map(line -> new SsqEntity(line))
                    .filter(ssqEntity -> ssqEntity.getLan() - lan == 0)
                    .collect(Collectors.toList());
            
            // 记录每个红球及其出现的次数
            Multiset<Integer> multiset = HashMultiset.create();
            
            ssqListByLan.forEach( ssqEntity -> {
                multiset.add(ssqEntity.getShunxu1());
                multiset.add(ssqEntity.getShunxu2());
                multiset.add(ssqEntity.getShunxu3());
                multiset.add(ssqEntity.getShunxu4());
                multiset.add(ssqEntity.getShunxu5());
                multiset.add(ssqEntity.getShunxu6());
            });
            
            // 记录每个红球及其出现的次数 转换为 list
            ssqHList = multiset.entrySet().stream().map(entry -> new YuCeHongTemp(entry.getElement(), entry.getCount())).collect(Collectors.toList());
            
            //////////////////////////////////////////////////////////////////////////////////////////////////////////
            
            // 最近limit期，中奖红球set
            Set<Integer> set = Sets.newHashSet();
            for(SsqEntity ssqEntity : ssqListByLan.subList(0, limit)){
                set.add(ssqEntity.getShunxu1());
                set.add(ssqEntity.getShunxu2());
                set.add(ssqEntity.getShunxu3());
                set.add(ssqEntity.getShunxu4());
                set.add(ssqEntity.getShunxu5());
                set.add(ssqEntity.getShunxu6());
            }
            //////////////////////////////////////////////////////////////////////////////////////////////////////////
            
            // 记录每个红球及其出现的次数的list中去除最近中奖的红球
            for(Iterator<YuCeHongTemp> iterator = ssqHList.iterator();iterator.hasNext();){
                YuCeHongTemp yuCeHongTemp = iterator.next();
                if(set.contains(yuCeHongTemp.getHongNum())){
                    iterator.remove();
                }
            }

            // 按中奖次数↓排序
            ssqHList.sort((YuCeHongTemp h1, YuCeHongTemp h2) -> h2.getCount() - h1.getCount());
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return ssqHList;
    }
    
}
