package com.ehangtian.core.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by wangshuai on 2018/1/4.
 */
public class DateUtil {

    //获取系统当前日期的下一天的日期
    public static Date getNextDay(Date date){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH,1);
        date=calendar.getTime();
        return date;
    }

    //获取系统当前日期的前一天日期
    public static Date getPreDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        return date;
    }
}
