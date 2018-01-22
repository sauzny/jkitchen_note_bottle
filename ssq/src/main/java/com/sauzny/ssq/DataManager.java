package com.sauzny.ssq;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.sauzny.ssq.entity.SsqEntity;


/**
 * *************************************************************************
 * @文件名称: DataManager.java
 *
 * @包路径  : com.sauzny.ssq 
 *				 
 * @版权所有: Personal xinxin (C) 2017
 *
 * @类描述:   TODO
 * 
 * @创建人:   ljx 
 *
 * @创建时间: 2018年1月15日 - 上午10:21:16 
 *	
 **************************************************************************
 */
public final class DataManager {

    public static final String rootPath = System.getProperty("user.dir") + File.separator;
    public static final String history = "history_ssq.dat";
    public static final String forecast = "ssq.html";
    public static final Path historyPath = Paths.get(rootPath+history);
    public static final Path forecastPath = Paths.get(rootPath+forecast);
    
    private DataManager(){}
    
    /**
     * @描述: 读取历史数据
     * @return
     * @返回 List<String>
     * @创建人  ljx 创建时间 2017年12月28日 下午3:09:33
     */
    public static List<String> loadHistory(){
        List<String> lines = Lists.newArrayList();
        try {
            lines = Files.readAllLines(historyPath, StandardCharsets.UTF_8);
            lines.remove(0);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return lines;
    }
    
    /**
     * @描述: 增加历史数据
     * @返回 void
     * @创建人  ljx 创建时间 2017年12月28日 下午3:10:09
     */
    public static void addHistory(){
        
        List<String> lines = DataManager.loadHistory();
        Collections.reverse(lines);
        
        // 获取 目标期数 id 和 期号  上次的开奖日期
        String[] lastLine = lines.get(0).split("\t");

        int targetId = Integer.parseInt(lastLine[0]) + 1;
        int targetQihao = Integer.parseInt(lastLine[1]) + 1;
        
        try{

            List<String> list = JsoupManager.ssqData(targetQihao);
            list.add(0, String.valueOf(targetId));
            SsqEntity ssqEntity = new SsqEntity(Joiner.on("\t").join(list));
            
            Files.write(historyPath, ("\n"+ssqEntity.line()).getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
            
        }catch(Exception e){
            System.err.println("无法增加新的历史数据");
        } 
        
    }
    

    // 期号：2017100 开奖日期：2017-08-27
    // 预测red 预测blue
    // 实际red 实际blue
    // 中奖red 中奖blue
    public static final String qihaoTemplate = "${qihao}";

    public static final String firsthangTemplate = "${firsthang}";

    public static final String ycredTemplate = "${ycred}";
    public static final String ycblueTemplate = "${ycblue}";
    
    public static final String divTemplate = "<div id='${qihao}' class='col-xs-12 col-sm-3' style='margin: 20px;'> <p id='${qihao}_1'>${firsthang}</p> <p>预测：<span style='color:red' id='${qihao}_2_1'>${ycred}</span> <span style='color:blue' id='${qihao}_2_2'>${ycblue}</span></p> <p>实际：<span style='color:red' id='${qihao}_3_1'></span> <span style='color:blue' id='${qihao}_3_2'></span></p> <p>红球中奖数：<span style='color:red' id='${qihao}_4_1'></span> 蓝球中奖数：<span style='color:blue' id='${qihao}_4_2'></span></p> </div>";

    public static void addForecast(String ycred, String ycblue){
        
        List<String> lines = DataManager.loadHistory();
        Collections.reverse(lines);
        
        SsqEntity preSsqEntity = new SsqEntity(lines.get(0));
        
        int preQihao = preSsqEntity.getQihao();
        int curQihao = preSsqEntity.getQihao()+1;
        
        String curRiqi = TimeUtils.nextSsqDate(LocalDate.parse(preSsqEntity.getRiqi())).toString();
        
        // 
        try {
            Document doc = JsoupManager.ssqHtml(forecastPath);
            
            // 填入本次预测
            Element curDiv = doc.getElementById(String.valueOf(curQihao));
            if(curDiv == null){
                Element row = doc.getElementById("row");
                String html = divTemplate
                        .replace(qihaoTemplate, String.valueOf(curQihao))
                        .replace(firsthangTemplate, "期号："+curQihao+" 开奖日期："+curRiqi)
                        .replace(ycredTemplate, ycred)
                        .replace(ycblueTemplate, ycblue);
                
                // 方法在被选元素的开头插入内容
                row.prepend(html);
                
                // 不能使用下面的方法
                //System.out.println(row.html());
                //doc.getElementById(curQihao + "_2_1").appendText(ycred);
                //doc.getElementById(curQihao + "_2_2").appendText(ycblue);
            }
            
            // 补充上次预测
            Element preDiv = doc.getElementById(String.valueOf(preQihao));
            if(preDiv != null){

                // 上一期的 预测数据
                String preYcred = doc.getElementById(preQihao + "_2_1").text();
                String preYcblue = doc.getElementById(preQihao + "_2_2").text();
                
                //
                doc.getElementById(preQihao + "_3_1").text(preSsqEntity.getHsorts().replace(",", " "));
                doc.getElementById(preQihao + "_3_2").text(String.valueOf(preSsqEntity.getLan()));
                
                // 计算红球中奖个数
                Set<String> preYcredSet = Sets.newHashSet(preYcred.split(" "));
                Set<String> preSjredSet = Sets.newHashSet(preSsqEntity.getHsorts().split(","));
                
                // 求交集
                int sjred = Sets.intersection(preYcredSet, preSjredSet).size();
                
                doc.getElementById(preQihao + "_4_1").text(String.valueOf(sjred));
                
                // 计算蓝球中奖个数
                String zjblue = "0";
                if(preYcblue.equals(String.valueOf(preSsqEntity.getLan()))){
                    zjblue = "1";
                }
                doc.getElementById(preQihao + "_4_2").text(zjblue);
            }
            
            Files.write(forecastPath, doc.html().getBytes(StandardCharsets.UTF_8), StandardOpenOption.WRITE);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
