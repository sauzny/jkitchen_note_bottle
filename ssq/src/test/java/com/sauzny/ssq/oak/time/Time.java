package com.sauzny.ssq.oak.time;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;

public class Time {
    
    /**
     * @描述: 根据上次开奖日期，计算下次开奖日期
     * @param lastSsqDate
     * @return
     * @返回 LocalDate
     * @创建人  ljx 创建时间 2017年10月10日 上午9:26:32
     */
    public static LocalDate nextSsqDate(LocalDate lastSsqDate){

        LocalDate nextTuesday = lastSsqDate.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
        LocalDate nextThursday = lastSsqDate.with(TemporalAdjusters.next(DayOfWeek.THURSDAY));
        LocalDate nextSunday = lastSsqDate.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        
        LocalDate result = nextTuesday;
        
        if(nextThursday.isBefore(result)){
            result = nextThursday;
        }
        if(nextSunday.isBefore(result)){
            result = nextSunday;
        }
        
        return result;
    }
    
    /**
        SQL -> Java
        --------------------------
        date -> LocalDate
        time -> LocalTime
        timestamp -> LocalDateTime
     */
    
    
    /**
        Monday    星期一
        Tuesday    星期二
        Wednesday   星期三
        Thursday   星期四
        Friday   星期五
        Saturday   星期六
        Sunday   星期日
     */
    
    
    public static void main(String[] args) {
        
        // 取当前日期：
        LocalDate today = LocalDate.now(); // -> 2014-12-24
        System.out.println(today);
        // 根据年月日取日期，12月就是12：
        LocalDate crischristmas = LocalDate.of(2014, 12, 25); // -> 2014-12-25
        // 根据字符串取：
        // 严格按照ISO
        // yyyy-MM-dd验证，02写成2都不行，当然也有一个重载方法允许自己定义格式
        LocalDate endOfFeb = LocalDate.parse("2014-02-28"); 
        //LocalDate.parse("2014-02-29"); // 无效日期无法通过：DateTimeParseException: Invalid date
        
        // 取本月第1天：
        LocalDate firstDayOfThisMonth = today.with(TemporalAdjusters.firstDayOfMonth()); // 2014-12-01
        // 取本月第2天：
        LocalDate secondDayOfThisMonth = today.withDayOfMonth(2); // 2014-12-02
        // 取本月最后一天，再也不用计算是28，29，30还是31：
        LocalDate lastDayOfThisMonth = today.with(TemporalAdjusters.lastDayOfMonth()); // 2014-12-31
        // 取下一天：
        LocalDate firstDayOf2015 = lastDayOfThisMonth.plusDays(1); // 变成了2015-01-01
        // 取2015年1月第一个周一，这个计算用Calendar要死掉很多脑细胞：
        LocalDate firstMondayOf2015 = LocalDate.parse("2015-01-01").with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)); // 2015-01-05
        
        /**
                                    时间也是按照ISO格式识别，但可以识别以下3种格式：
            
            12:00
            12:01:02
            12:01:02.345
         */
        LocalTime now1 = LocalTime.now(); // 11:09:09.240
        LocalTime now2 = LocalTime.now().withNano(0); // 11:09:09
        LocalTime zero = LocalTime.of(0, 0, 0); // 00:00:00
        LocalTime mid = LocalTime.parse("12:00:00"); // 12:00:00
        
        System.out.println(nextSsqDate(LocalDate.parse("2017-10-08")));
        
        LocalDateTime localDateTime =  LocalDateTime.ofInstant(Instant.ofEpochMilli(1471337924226L), ZoneId.of("Asia/Shanghai"));
        System.out.println(localDateTime);
    }
}
