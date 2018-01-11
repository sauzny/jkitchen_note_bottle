package com.sauzny.ssq.entity;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import lombok.Data;

@Data
public class SsqEntity {

    // id   qihao   riqi    shunxu1 shunxu2 shunxu3 shunxu4 shunxu5 shunxu6 lan     hsorts  hmd5    amd5
    
    private int id;
    private int qihao;
    private String riqi;
    private int shunxu1;
    private int shunxu2;
    private int shunxu3;
    private int shunxu4;
    private int shunxu5;
    private int shunxu6;
    private int lan;
    // 红球自然顺序 0 -> max
    private String hsorts;
    // hsorts的 md5值
    private String hmd5;
    // hmd5+lan的md5值
    private String amd5;
    
    public SsqEntity(){}
    
    public SsqEntity(String line){

        String[] values = line.split("\t");
        
        this.id = Integer.parseInt(values[0]);
        this.qihao = Integer.parseInt(values[1]);
        this.riqi = values[2];
        this.shunxu1 = Integer.parseInt(values[3]);
        this.shunxu2 = Integer.parseInt(values[4]);
        this.shunxu3 = Integer.parseInt(values[5]);
        this.shunxu4 = Integer.parseInt(values[6]);
        this.shunxu5 = Integer.parseInt(values[7]);
        this.shunxu6 = Integer.parseInt(values[8]);
        this.lan = Integer.parseInt(values[9]);
        
        List<Integer> temp = Lists.newArrayList(shunxu1, shunxu2, shunxu3, shunxu4, shunxu5, shunxu6);
        temp.sort( (n1,n2) -> n1.compareTo(n2));
        String hsorts = Joiner.on(",").join(temp);
        this.hsorts = hsorts;
        this.hmd5 = DigestUtils.md5Hex(hsorts);
        this.amd5 = DigestUtils.md5Hex(hmd5 + values[9]);
    }
    
    public String line(){
        return Joiner.on("\t").join(id, qihao, riqi, shunxu1, shunxu2, shunxu3, shunxu4, shunxu5, shunxu6, lan, hsorts, hmd5, amd5);
    }
    
}
