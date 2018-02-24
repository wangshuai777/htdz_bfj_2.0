package com.ehangtian.core.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wangshuai on 2018/1/4.
 */
public class IdGen {

    public static String getId(){
        int random3 =(int)((Math.random()*9+1)*100);
        String id = getNowTime("yyMMddHHmmss")+random3;
        return id;
    }

    /**
     * 获取当天日期
     * @param dateformat：例如例如：yyyy-MM-dd
     * @return
     */
    public static String getNowTime(String dateformat){
        Date   now   =   new Date();
        SimpleDateFormat   dateFormat   =   new SimpleDateFormat(dateformat);//可以方便地修改日期格式
        String  hehe  = dateFormat.format(now);
        return hehe;
    }



}
