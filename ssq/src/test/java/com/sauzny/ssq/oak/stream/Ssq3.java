package com.sauzny.ssq.oak.stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.sauzny.ssq.ForecastHong;
import com.sauzny.ssq.entity.YuCeHongTemp;

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
            
            List<SsqEntity> list = stream.collect(toList());
            
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
            List<SsqEntity> ssqEntityList1 = lines.stream()
                    .map(line -> {
                        String[] values = line.split("\t");
                        return new SsqEntity(values);
                    })
                    .collect(toList());
            
            
            List<SsqEntity> ssqEntityList2 = Lists.newArrayList();
            // 2. 过滤数据
            for(int i=1; i<ssqEntityList1.size(); i++){
                if(ssqEntityList1.get(i-1).getLan() == lastLan){
                    ssqEntityList2.add(ssqEntityList1.get(i));
                }
            }

            
            // 3. 分组
            Map<Integer, Long> map = ssqEntityList2.stream().collect(groupingBy(SsqEntity::getLan, counting()));
            
            map.forEach((k,v) -> System.out.println("lan:" + k + ", 次数：" + v) );
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static String lastLine(File file, String charset) {
        if (!file.exists() || file.isDirectory() || !file.canRead()) {
            return null;
        }
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(file, "r");
            long len = raf.length();
            if (len == 0L) {
                return "";
            } else {
                long pos = len - 1;
                while (pos > 0) {
                    pos--;
                    raf.seek(pos);
                    if (raf.readByte() == '\n') {
                        break;
                    }
                }
                if (pos == 0) {
                    raf.seek(0);
                }
                byte[] bytes = new byte[(int) (len - pos)];
                raf.read(bytes);
                if (charset == null) {
                    return new String(bytes);
                } else {
                    return new String(bytes, charset);
                }
            }
        } catch (Exception e) {
        } finally {
            if (raf != null) {
                try {
                    raf.close();
                } catch (Exception e2) {
                }
            }
        }
        return null;
    }
    
    public static SsqEntity lastSsqEntity(){
        
        String userDir = System.getProperty("user.dir");
        
        String line = lastLine(new File(userDir+"/history_ssq.dat"), StandardCharsets.UTF_8.toString());
        
        String[] values = line.split("\t");
        
        return new SsqEntity(values);
    }
    
    public static void main(String[] args) {
        Ssq3.foo02();
        
        int lastLan = lastSsqEntity().getLan();
        
        List<YuCeHongTemp> yuCeHongList = ForecastHong.forecast(lastLan);

        yuCeHongList.forEach(temp -> System.out.print(temp.getHongNum() + " ") );
        System.out.println(" " + lastLan);
    }
}
