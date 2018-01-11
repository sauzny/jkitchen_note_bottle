package com.sauzny.ssq.entity;

import lombok.Data;

@Data
public class YuCeHongTemp {
    private int hongNum;
    private int count;
    
    public YuCeHongTemp(int hongNum, int count) {
        super();
        this.hongNum = hongNum;
        this.count = count;
    }
}
