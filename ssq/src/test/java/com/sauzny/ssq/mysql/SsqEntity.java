package com.sauzny.ssq.mysql;

import com.google.common.base.Joiner;

public class SsqEntity {

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
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getQihao() {
        return qihao;
    }
    public void setQihao(int qihao) {
        this.qihao = qihao;
    }
    public int getShunxu1() {
        return shunxu1;
    }
    public void setShunxu1(int shunxu1) {
        this.shunxu1 = shunxu1;
    }
    public int getShunxu2() {
        return shunxu2;
    }
    public void setShunxu2(int shunxu2) {
        this.shunxu2 = shunxu2;
    }
    public int getShunxu3() {
        return shunxu3;
    }
    public void setShunxu3(int shunxu3) {
        this.shunxu3 = shunxu3;
    }
    public int getShunxu4() {
        return shunxu4;
    }
    public void setShunxu4(int shunxu4) {
        this.shunxu4 = shunxu4;
    }
    public int getShunxu5() {
        return shunxu5;
    }
    public void setShunxu5(int shunxu5) {
        this.shunxu5 = shunxu5;
    }
    public int getShunxu6() {
        return shunxu6;
    }
    public void setShunxu6(int shunxu6) {
        this.shunxu6 = shunxu6;
    }
    public int getLan() {
        return lan;
    }
    public void setLan(int lan) {
        this.lan = lan;
    }
    public String getRiqi() {
        return riqi;
    }
    public void setRiqi(String riqi) {
        this.riqi = riqi;
    }
    
    public SsqEntity(int id, int qihao, String riqi, int shunxu1, int shunxu2, int shunxu3, int shunxu4, int shunxu5, int shunxu6, int lan) {
        super();
        this.id = id;
        this.qihao = qihao;
        this.riqi = riqi;
        this.shunxu1 = shunxu1;
        this.shunxu2 = shunxu2;
        this.shunxu3 = shunxu3;
        this.shunxu4 = shunxu4;
        this.shunxu5 = shunxu5;
        this.shunxu6 = shunxu6;
        this.lan = lan;
    }
    
    public SsqEntity(int qihao, String riqi, int shunxu1, int shunxu2, int shunxu3, int shunxu4, int shunxu5, int shunxu6, int lan) {
        
        this.qihao = qihao;
        this.riqi = riqi;
        this.shunxu1 = shunxu1;
        this.shunxu2 = shunxu2;
        this.shunxu3 = shunxu3;
        this.shunxu4 = shunxu4;
        this.shunxu5 = shunxu5;
        this.shunxu6 = shunxu6;
        this.lan = lan;
    }
    
    public String allValues(){
        return Joiner.on(", ").join(id, qihao, riqi, shunxu1, shunxu2, shunxu3, shunxu4, shunxu5, shunxu6, lan);
    }
}
