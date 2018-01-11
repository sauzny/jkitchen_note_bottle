package com.sauzny.ssq;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class TimeUtils {
    /**
     * @描述: 根据上次开奖日期，计算下次开奖日期
     * @param lastSsqDate
     * @return
     * @返回 LocalDate
     * @创建人  ljx 创建时间 2017年10月10日 上午9:26:32
     */
    public static LocalDate nextSsqDate(LocalDate currentSsqDate){

        LocalDate nextTuesday = currentSsqDate.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
        LocalDate nextThursday = currentSsqDate.with(TemporalAdjusters.next(DayOfWeek.THURSDAY));
        LocalDate nextSunday = currentSsqDate.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        
        LocalDate result = nextTuesday;
        
        if(nextThursday.isBefore(result)){
            result = nextThursday;
        }
        if(nextSunday.isBefore(result)){
            result = nextSunday;
        }
        
        return result;
    }
}
