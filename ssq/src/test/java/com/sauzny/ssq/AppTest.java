package com.sauzny.ssq;

import java.util.List;

import org.junit.Test;

import com.sauzny.ssq.entity.YuCeHongTemp;

public class AppTest {
    
    @Test
    public void hongForecast(){
        List<YuCeHongTemp> yuCeHongList = ForecastHong.forecast(8);
        yuCeHongList.forEach(System.out::println);
    }
}
