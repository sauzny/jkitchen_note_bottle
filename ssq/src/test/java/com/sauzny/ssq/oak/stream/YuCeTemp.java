package com.sauzny.ssq.oak.stream;

public class YuCeTemp {
    private int num;
    private int score;
    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    
    @Override
    public String toString() {
        return "[num=" + num + ", score=" + score + "]";
    }

}
