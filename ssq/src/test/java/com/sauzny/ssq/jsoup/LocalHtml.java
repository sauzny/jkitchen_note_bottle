package com.sauzny.ssq.jsoup;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

public class LocalHtml {

    public static final String div = "<div class='col-xs-12    col-sm-3' style='margin: 20px;'> <p>期号：2017101 开奖日期：2017-08-27</p> <p>预测：<span style='color:red'>8 2 4 26 14 20</span> <span style='color:blue'>15</span></p> <p>实际：<span style='color:red'>8 2 4 26 14 20</span> <span style='color:blue'>15</span></p> <p>红球中奖数：<span style='color:red'>6</span> 蓝球中奖数：<span style='color:blue'>1</span></p> </div>";
    
    @Test
    public void foo01() throws IOException{
        
        Path dataPath = Paths.get(System.getProperty("user.dir")+File.separator+"ssq.html");
        
        List<String> lines = Files.readAllLines(dataPath, StandardCharsets.UTF_8);
        
        StringBuilder sb = new StringBuilder();
        
        for(String line : lines){
            sb.append(line);
        }
        
        Document doc = Jsoup.parse(sb.toString());
        Element row = doc.getElementById("row");
        row.append(div);
        System.out.println(row.html());
        
        Files.write(dataPath, doc.html().getBytes(StandardCharsets.UTF_8), StandardOpenOption.WRITE);
        
    }
}
